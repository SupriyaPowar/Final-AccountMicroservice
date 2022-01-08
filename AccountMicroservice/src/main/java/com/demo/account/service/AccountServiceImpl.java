package com.demo.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.account.bean.request.AccountRequest;
import com.demo.account.entity.Account;
import com.demo.account.entity.mapper.RequestMapper;
import com.demo.account.exception.client.AccountNotFoundException;
import com.demo.account.exception.client.BadRequestException;
import com.demo.account.exception.server.InternalServerException;
import com.demo.account.repository.AccountRepository;
import com.demo.account.repository.MoneyTransactionRepository;
import com.demo.account.repository.TransactionStatus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.READ_COMMITTED;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;
import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final BiPredicate<BigDecimal, Account> sufficientFundsInAccount =
            (fund, account) -> account.getBalance()
                    .compareTo(fund) < 0;

    private final AccountRepository accountRepository;
    private final RequestMapper<AccountRequest, Account> accountRequestMapper;
    private final MoneyTransactionRepository moneyTransactionRepository;

    @Override
    @Transactional(isolation = READ_COMMITTED)
    public Account retrieve(final long accountNumber) {
        return Optional.of(accountNumber)
                .map(accountRepository::findByAccountNumber)
                .orElseThrow(
                        () -> new AccountNotFoundException(
                                String.format("Given account number: %s is not found.", accountNumber)
                        )
                );
    }

    @Override
    @Transactional
    public Account createAccount(final AccountRequest account) {
        return Optional.of(account)
                .map(accountRequestMapper::mapFrom)
                .map(accountRepository::save)
                .orElseThrow(() -> new InternalServerException("Failure in request conversion."))
        ;
    }

    @Override
    @Transactional(propagation = REQUIRES_NEW,
            isolation = SERIALIZABLE,
            timeoutString = "${service.transaction.timeout:3}")
    public void makeFundTransfer(final long fromAccountNumber, final long toAccountNumber, final BigDecimal fund) {
        this.validateTransferFund(fund);
        this.validateAccounts(fromAccountNumber, toAccountNumber);

        final Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
        this.validateForSufficientFund(fund, fromAccount);
        final Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);

        this.moveFunds(fund, fromAccount, toAccount);
        accountRepository.saveAll(List.of(fromAccount, toAccount));
    }

    private void moveFunds(final BigDecimal fund, final Account fromAccount, final Account toAccount) {
        debit(fund, fromAccount);
        credit(fund, toAccount);
    }

    private void credit(final BigDecimal fund, final Account toAccount) {
        toAccount.setBalance(toAccount.getBalance().add(fund));
    }

    private void debit(final BigDecimal fund, final Account fromAccount) {
        fromAccount.setBalance(fromAccount.getBalance().subtract(fund));
    }

    private boolean notExist(long fromAccountNumber) {
        return !accountRepository.existsById(fromAccountNumber);
    }

    private void validateTransferFund(final BigDecimal fund) {
        Optional.ofNullable(fund)
                .filter(bigDecimal -> bigDecimal.doubleValue() > 0)
                .orElseThrow(() -> new BadRequestException("Fund transfer should have positive value."));
    }

    private void validateForSufficientFund(final BigDecimal fund, final Account fromAccount) {
        if(sufficientFundsInAccount.test(fund, fromAccount)) {
            throw new InternalServerException("Insufficient fund.");
        }
    }

    private void validateAccounts(final long fromAccountNumber, final long toAccountNumber) {
        if (this.notExist(fromAccountNumber)
                || this.notExist(toAccountNumber)) {
            throw new BadRequestException("Either from or to account number is not valid.");
        }
    }

	@Override
	public Map<Long, String> getAllTransactionDetails(Long fromAccountNumber, Long toAccountNumber) {
		Map<Long, String> transactionStatus = new HashMap<>();
		List<TransactionStatus> status = moneyTransactionRepository.findStatusOfAllTransactions(fromAccountNumber , toAccountNumber);
		transactionStatus =  status.stream()
		.collect(Collectors.toMap(trns -> trns.gettransaction_id(), trns -> trns.getStatus()));
		return transactionStatus;
	}
}

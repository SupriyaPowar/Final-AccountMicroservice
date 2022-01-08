package com.demo.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.account.entity.MoneyTransaction;

public interface MoneyTransactionRepository extends JpaRepository<MoneyTransaction, Long> {
	
	@Query(value= "SELECT transaction_id , status FROM money_transaction WHERE from_account_number = :fromAccountNumber OR to_account_number = :toAccountNumber",nativeQuery = true)
	public List<TransactionStatus> findStatusOfAllTransactions( @Param("fromAccountNumber") Long fromAccountNumber , @Param("toAccountNumber") Long toAccountNumber);
	
}

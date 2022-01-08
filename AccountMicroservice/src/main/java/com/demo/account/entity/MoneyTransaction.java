package com.demo.account.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "transactionId")
@Entity
@SequenceGenerator(name = "money_transactions_seq", initialValue = 1)
public class MoneyTransaction {
	
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "money_transactions_seq")
    private long transactionId;
    
    private long fromAccountNumber;
    
    private long toAccountNumber;
    
    private BigDecimal transactionAmount;
    
    private String referenceNotes;
    
    private String status;
    
    private String errorDescription;

	
    public MoneyTransaction(String status) {
		super();
		this.status = status;
	}
    
    
}

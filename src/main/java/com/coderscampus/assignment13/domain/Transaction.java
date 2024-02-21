package com.coderscampus.assignment13.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@SuperBuilder
@Table(name="transactions")
public class Transaction {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
	private LocalDateTime transactionDate;
	private Double amount;
	@Column(length = 1)
	private String type;
	@ManyToOne
	@JoinColumn(name="account_id")
	private Account account;
}

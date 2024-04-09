package com.kredinbizdeservice.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kredinbizdeservice.enums.LoanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Getter
@Setter
@Entity
@Table(name = "loans")
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "loan_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private LoanType loanType;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "installment", nullable = false)
    private Integer installment;
	
	@Column(name = "interest_rate", nullable = false)
    private Double interestRate;
	
	@ManyToOne
	@JoinColumn(name = "bank_id")
	@JsonIgnore
	private Bank bank;
	
}

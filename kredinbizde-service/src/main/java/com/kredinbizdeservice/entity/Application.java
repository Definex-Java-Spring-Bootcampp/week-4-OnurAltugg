package com.kredinbizdeservice.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kredinbizdeservice.enums.ApplicationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Setter
@Entity
@Table(name = "applications")
public class Application {
	
	
	@Id
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
	
	@Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loan_id")
	private Loan loan;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "credit_card_id")
	private CreditCard creditCard;
	
}

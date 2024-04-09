package com.kredinbizdeservice.entity;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kredinbizdeservice.enums.SectorType;

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
@Table(name = "campaigns")
public class Campaign {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title", nullable = false)
    private String title;
	
	@Column(name = "content", nullable = false)
    private String content;
	
	@Column(name = "due_date", nullable = false)
    private LocalDate dueDate;
	
	@Column(name = "create_date", nullable = true)
    private LocalDate createDate;
	
	@Column(name = "update_date", nullable = true)
    private LocalDate updateDate;
	
	@Column(name = "sector_type", nullable = false)
	@Enumerated(EnumType.STRING)
    private SectorType sector;
	
	@ManyToOne
	@JoinColumn(name = "credit_card_id")
	@JsonIgnore
	private CreditCard creditCard;

}

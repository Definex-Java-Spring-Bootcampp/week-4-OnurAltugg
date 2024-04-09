package com.garantiservice.entity;

import lombok.*;

import java.time.LocalDateTime;

import com.garantiservice.enums.ApplicationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "garanti_applications")
public class Application {
	@Id
	@Column(name = "id")
    private Long appId;
	
	@Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;
	
	@Column(name = "update_date", nullable = true)
    private LocalDateTime updateDate;
	
	@Column(name = "status", nullable = false)
	@Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
}

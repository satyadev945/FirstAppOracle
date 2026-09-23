package com.sit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Doctor entity mapped to JPA_DOCTOR_INFO table.
 * Uses Jakarta Persistence API (jakarta.persistence.*) for Java 25 / Jakarta EE compatibility.
 */
@Entity
@Table(name = "jpa_doctor_info")
@Data
public class Doctor {

	@Id
	@Column(name = "doc_id")
	@SequenceGenerator(name = "gen1", sequenceName = "cno_seq", initialValue = 203, allocationSize = 1)
	@GeneratedValue(generator = "gen1", strategy = GenerationType.SEQUENCE)
	private Integer docId;

	@Column(name = "doc_name", length = 25)
	private String docName;

	@Column(name = "specialization", length = 20)
	private String specialization;

	@Column(name = "income")
	private Double income;

}

package com.sit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="JPA_DOCTOR_INFO")
@Data
public class Doctor {

	@Column(name="DOC_ID")
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="gen1",sequenceName="CNO_SEQ",initialValue = 203,allocationSize = 1)
	@GeneratedValue(generator ="gen1",strategy = GenerationType.SEQUENCE)
	private Integer docId;
	
	@Column(name="DOC_NAME",length=25)
	private String docName;
	
	@Column(name="SPECIALIZATION",length=20)
	private String specialization;
	
	@Column(name="INCOME")
	private Double income;
	
}

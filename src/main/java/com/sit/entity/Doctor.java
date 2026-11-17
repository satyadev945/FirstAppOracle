package com.sit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="JPA_DOCTOR_INFO")
@Getter
@Setter
@ToString
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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Doctor other)) return false;
		return docId != null && docId.equals(other.docId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}

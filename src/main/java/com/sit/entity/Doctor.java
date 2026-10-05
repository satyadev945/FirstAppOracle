package com.sit.entity;

/**
 * Doctor entity representing medical professionals in the system.
 * Uses modern Jakarta Persistence API for ORM mapping.
 */

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="jpa_doctor_info")
@Data
public class Doctor {

	@Column(name="doc_id")
	@Id
	@SequenceGenerator(name="gen1",sequenceName="cno_seq",initialValue = 203,allocationSize = 1)
	@GeneratedValue(generator ="gen1",strategy = GenerationType.SEQUENCE)
	private Integer docId;
	
	@Column(name="doc_name", length=25, nullable=false)
	private String docName;
	
	@Column(name="specialization", length=20, nullable=false)
	private String specialization;
	
	@Column(name="income", nullable=false)
	private Double income;
	
	/**
	 * Returns a formatted string representation of the doctor's information.
	 */
	@Override
	public String toString() {
		return "Doctor[id=%d, name='%s', specialization='%s', income=%.2f]"
				.formatted(docId, docName, specialization, income);
	}
	
}

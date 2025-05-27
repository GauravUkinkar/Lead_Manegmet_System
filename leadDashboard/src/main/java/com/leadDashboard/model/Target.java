package com.leadDashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Entity
@ToString
@Accessors(chain = true)
public class Target {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int totalTargetMonthly;
	private int totalConvertedLead;
	private int totalAddedLead;
	private int uid;
	private String month;
	private String year;

}

package com.leadDashboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@Accessors(chain = true)
@Entity
public class Contactchain {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cid;
	private String contactDate;
	private String method;
	private String response;
	private String followUpDate;
	private String feedback;
	private int lid;
	
	
}

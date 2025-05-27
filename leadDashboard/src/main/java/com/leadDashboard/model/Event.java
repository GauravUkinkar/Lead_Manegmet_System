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
@Accessors(chain=true)
@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int eId;
	private String attended;
	private String date;
	private String duration;
	private String time;
	private String name;
	private String location;
	private String entryFee;
	private String discription;
	private String links;
	private String comments;
	
	

}

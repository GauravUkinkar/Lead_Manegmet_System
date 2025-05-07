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
public class LeadModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lid;
	private String isItLead;
	private String Date;
	private String entryMadeBy;
	private String nameOfBDManager;
	private String leadGenerationDate;
	private String clientName;
	private String status;
	private String overAllStatus;
	private String contactPerson;
	private String emailId;
	private String contactNumber;
	private String referance;
	private String website;
	private String comments;
	private String dateOfFutureContact;
	private String BDManagerAssigned;
	private String updatedStatusComments;
	private String initialPraposalDate;
	private String futurePraposalDate;
	private String deletedTag;
	private String abc;
	
	

}

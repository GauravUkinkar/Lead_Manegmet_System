package com.leadDashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leadDashboard.Dto.DatewiseStatusCount;
import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.model.Lead;


@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
	@Query("SELECT new com.leadDashboard.Dto.LeadStatusCountDto(" +
		       "SUM(CASE WHEN LOWER(TRIM(t.overAllStatus)) = 'open' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN LOWER(TRIM(t.overAllStatus)) = 'close' THEN 1 ELSE 0 END)) " +
		       "FROM Lead t " 
		      )
		List<LeadStatusCountDto> findLeadStatusCount();

	LeadStatusCountDto findLeadStatusCountByLid(int lid);
	
	@Query("SELECT new com.leadDashboard.Dto.DatewiseStatusCount(" +
		       "SUM(CASE WHEN TRIM(t.status) = 'Lead Generated' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Not A Lead' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - No Response' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Rejected' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Information Requested' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - To be contacted at a Future Date' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Day/Date/Time details for scheduling an appointment received from the potential client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Meeting Scheduled' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Meeting Scheduled - Initial Pitch Prepared' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Meeting Scheduled - Initial Pitch Under Revision' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - Meeting Scheduled - Initial Pitch Approved by Business Head' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - No Response - Email Follow up' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'First Contact Established - No Response - Closed' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Response awaited from Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Response awaited from Potential Client - Email Follow up' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Rejected by Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Rework Requested by Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Rework under progress' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Rework completed - To be Approved by Business Head' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made - Rework Approved' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Initial Pitch Made after Rework' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Requested by Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Prepared' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Approved' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Under Revision' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Sent to the Potential Client - Response awaited' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Sent - Rejected by Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Sent - Deferred by Potential client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Sent - Under Negotiation with Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Business Proposal Accepted by Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Prepared' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Under Revision' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Approved by Business Head' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Shared with Potential Client - Response Awaited' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Shared with Potential Client - Rejected by Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Shared with Potential Client - Under Negotiation with Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Shared with Potential Client - Deferred by Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Draft Agreement Shared with Potential Client - Accepted by Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Final Agreement printed and shared with Potential Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Final Agreement signed by Client' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN TRIM(t.status) = 'Final Agreement signed by Client - Printed copy received and Stored by DiWise Team' THEN 1 ELSE 0 END)) " +
		       "FROM Lead t " +
		       "WHERE  t.updatedDate BETWEEN :startDate AND :endDate")
		List<DatewiseStatusCount> findAllStatusCountsBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);

	List<Lead> findAllByStatusAndUpdatedDateBetween(String status, String updatedDate, String endDate);
	
	List<Lead> findByUpdatedDateBetween(String startDate, String endDate);
		
		
		




}

//@Query("SELECT  new com.leadDashboard.Dto.LeadStatusCountDto(" +
//	       "t.lid, " +
//	       "SUM(CASE WHEN t.overAllStatus = 'Close' THEN 1 ELSE 0 END), " +
//	       "SUM(CASE WHEN t.overAllStatus = 'Open' THEN 1 ELSE 0 END)) " +
//	       "FROM Lead t " +
//	       "WHERE t.lid = :lid AND t.deletedTag = 'false' " +
//	       "GROUP BY t.lid")
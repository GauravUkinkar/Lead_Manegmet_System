package com.leadDashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leadDashboard.Dto.LeadStatusCountDto;
import com.leadDashboard.model.Lead;


@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {
	@Query("SELECT new com.leadDashboard.Dto.LeadStatusCountDto(" +
		       "SUM(CASE WHEN LOWER(TRIM(t.overAllStatus)) = 'close' THEN 1 ELSE 0 END), " +
		       "SUM(CASE WHEN LOWER(TRIM(t.overAllStatus)) = 'open' THEN 1 ELSE 0 END)) " +
		       "FROM Lead t " +
		       "WHERE t.deletedTag = 'False'")
		List<LeadStatusCountDto> findLeadStatusCount();

	LeadStatusCountDto findLeadStatusCountByLid(int lid);



}

//@Query("SELECT  new com.leadDashboard.Dto.LeadStatusCountDto(" +
//	       "t.lid, " +
//	       "SUM(CASE WHEN t.overAllStatus = 'Close' THEN 1 ELSE 0 END), " +
//	       "SUM(CASE WHEN t.overAllStatus = 'Open' THEN 1 ELSE 0 END)) " +
//	       "FROM Lead t " +
//	       "WHERE t.lid = :lid AND t.deletedTag = 'false' " +
//	       "GROUP BY t.lid")
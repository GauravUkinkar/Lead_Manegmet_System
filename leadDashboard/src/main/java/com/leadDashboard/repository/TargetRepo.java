package com.leadDashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadDashboard.model.Target;

@Repository
public interface TargetRepo extends JpaRepository<Target, Integer> {

	List<Target> findAllTargetByuid(int uid);

	List<Target> findBymonthAndYear(String month, String year);

	List<Target> findBymonthAndYearAndUid(String month, String year, int uid);

}

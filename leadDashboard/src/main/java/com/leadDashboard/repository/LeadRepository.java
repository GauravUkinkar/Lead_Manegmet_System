package com.leadDashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadDashboard.model.Lead;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

}

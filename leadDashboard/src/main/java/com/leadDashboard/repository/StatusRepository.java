package com.leadDashboard.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadDashboard.model.Status;


@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {



}

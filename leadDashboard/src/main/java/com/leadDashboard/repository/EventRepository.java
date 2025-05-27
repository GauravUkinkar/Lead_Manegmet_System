package com.leadDashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadDashboard.model.Event;



@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

	Event getByName(String name);

	Event getByLocation(String location);

}

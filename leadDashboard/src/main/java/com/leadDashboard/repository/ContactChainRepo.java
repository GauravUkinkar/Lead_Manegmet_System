package com.leadDashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leadDashboard.model.Contactchain;

@Repository
public interface ContactChainRepo extends JpaRepository<Contactchain, Integer> {

	List<Contactchain> findByLid(Integer lid);

}

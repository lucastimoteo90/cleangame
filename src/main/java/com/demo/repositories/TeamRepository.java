package com.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
	
}

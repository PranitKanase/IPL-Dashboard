package com.learn.ipldashboard.repository;


import com.learn.ipldashboard.model.Team;

import org.springframework.data.repository.CrudRepository;


public interface TeamRepository extends CrudRepository<Team, Long> {
    
    Team findByTeamName(String teamName);
}

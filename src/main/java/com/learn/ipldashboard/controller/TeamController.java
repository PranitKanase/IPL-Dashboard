package com.learn.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.learn.ipldashboard.model.Match;
import com.learn.ipldashboard.model.Team;
import com.learn.ipldashboard.repository.MatchRepository;
import com.learn.ipldashboard.repository.TeamRepository;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {


    //constructor injection
    private TeamRepository teamRepository;
    private MatchRepository matchRepository;
    
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }



    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName){

        Team team =  this.teamRepository.findByTeamName(teamName);
        team.setMatches (this.matchRepository.findLatestMatchesbyTeam(teamName, 4));

        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year + 1, 1, 1);
        return this.matchRepository.getMatchesByTeamBetweenDates(
            teamName,
            startDate,
            endDate
            );
    } 
}

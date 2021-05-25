package com.teletabist.clubby.survey.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRepository;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.models.UserSurvey;
import com.teletabist.clubby.survey.services.UserSurveyService;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/dev/users/{username}/surveys")
public class UserSurveyAPIController {
    @Autowired UserService userService;
    @Autowired UserSurveyService userSurveyService;
    @Autowired ClubService clubService;
    @Autowired ClubRepository clubRepository;

    @GetMapping
    public ResponseEntity<?> index(@PathVariable String username){
        User u = this.userService.getUser(username);
        if(u == null)
            return new ResponseEntity<String>("User with\""+username+"\" does not exist!", HttpStatus.BAD_REQUEST);
        Collection<UserSurvey> surveys = this.userSurveyService.getUserSurveys(u);
        if(surveys != null){
            if(!surveys.isEmpty())  return new ResponseEntity<Collection<UserSurvey>>(surveys, HttpStatus.OK);
            else return new ResponseEntity<String>("User does not have any survey.", HttpStatus.OK);
        }
           
        return new ResponseEntity<String>("An error occured during request.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("generate")
    public ResponseEntity<?> generate(@PathVariable String username, @RequestBody Collection<Club> clubs){
        User u = this.userService.getUser(username);
        if(u == null)
            return new ResponseEntity<String>("User with\""+username+"\" does not exist!", HttpStatus.BAD_REQUEST);
        
        if(clubs == null) 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ArrayList<Integer> idsToFetch = new ArrayList<Integer>();
        for (Club club : clubs) {
            if(club.getId() != null){
                if(club.getId()>0){
                    idsToFetch.add(club.getId());
                }
            }
        }

        List<Club> clubFilter = this.clubRepository.findAllById(idsToFetch);

        UserSurvey newSurvey = this.userSurveyService.generateSurvey(u, clubFilter);
        if(newSurvey != null)
            return new ResponseEntity<UserSurvey>(newSurvey, HttpStatus.OK);
        return new ResponseEntity<String>("There's no new surveys.",HttpStatus.BAD_REQUEST);
    }

}
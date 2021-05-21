package com.teletabist.clubby.survey.api;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.models.Survey;
import com.teletabist.clubby.survey.services.SurveyService;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/clubs/{slug}/survey")
public class SurveyAPIController {
    private final ClubService clubService;
    private final UserService userService;
    private final SurveyService surveyService;

    @Autowired
    public SurveyAPIController(ClubService clubService, UserService userService, SurveyService surveyService) {
        this.clubService = clubService;
        this.userService = userService;
        this.surveyService = surveyService;
    }

    @GetMapping
    public ResponseEntity<?> get(@PathVariable String slug) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        Survey survey = surveyService.getSurvey(club);
        if (survey != null) return new ResponseEntity<Survey>(survey, HttpStatus.OK);
        return new ResponseEntity<String>("Survey Does Not Exist", HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<?> store(@PathVariable String slug, @RequestBody Survey survey) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        
        survey = surveyService.createSurvey(club, survey);
        
        if (survey != null) {
            return new ResponseEntity<Survey>(survey, HttpStatus.OK);
        }

        return new ResponseEntity<String>("Survey Creation Failed", HttpStatus.BAD_REQUEST);
    }

    /*@PatchMapping
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Survey survey, @PathVariable String slug) {
        return clubService.updateEntireClub(club, slug);
    }*/
}

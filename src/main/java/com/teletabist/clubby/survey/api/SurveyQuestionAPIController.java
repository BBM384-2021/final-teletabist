package com.teletabist.clubby.survey.api;

import java.util.List;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.models.Survey;
import com.teletabist.clubby.survey.models.SurveyQuestion;
import com.teletabist.clubby.survey.services.SurveyQuestionService;
import com.teletabist.clubby.survey.services.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/clubs/{slug}/survey/questions")
public class SurveyQuestionAPIController {
    private final ClubService clubService;
    private final SurveyService surveyService;
    private final SurveyQuestionService surveyQuestionService;

    @Autowired
    public SurveyQuestionAPIController(ClubService clubService, SurveyService surveyService, SurveyQuestionService surveyQuestionService) {
        this.clubService = clubService;
        this.surveyService = surveyService;
        this.surveyQuestionService = surveyQuestionService;
    }

    @GetMapping
    public ResponseEntity<?> index(@PathVariable String slug) {
        Club club = clubService.getClub(slug);
        
        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        Survey survey = surveyService.getSurvey(club);

        
        if (survey == null) {
            return new ResponseEntity<String>("Survey Does Not Exist", HttpStatus.BAD_REQUEST); 
        }

        List<SurveyQuestion> questions = survey.getQuestions();

        return new ResponseEntity<List<SurveyQuestion>>(questions, HttpStatus.OK);
    }
    
    @GetMapping("{str_id}")
    public ResponseEntity<?> get(@PathVariable String slug, @PathVariable String str_id) {
        Club club = clubService.getClub(slug);
        Integer id = Integer.parseInt(str_id);
        
        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        Survey survey = surveyService.getSurvey(club);

        
        if (survey == null) {
            return new ResponseEntity<String>("Survey Does Not Exist", HttpStatus.BAD_REQUEST); 
        }

        SurveyQuestion surveyQuestion = surveyQuestionService.getQuestion(id);
        
        return new ResponseEntity<SurveyQuestion>(surveyQuestion, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> store(@PathVariable String slug, @RequestBody SurveyQuestion surveyQuestion) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        
        Survey survey = surveyService.getSurvey(club);
        
        if (survey == null) {
            return new ResponseEntity<String>("Survey Does Not Exist", HttpStatus.BAD_REQUEST); 
        }

        SurveyQuestion addedQuestion = surveyQuestionService.addQuestion(surveyQuestion, survey);

        if (addedQuestion == null) {
            return new ResponseEntity<String>("Question Couldn't Be Created", HttpStatus.BAD_REQUEST); 
        }

        return new ResponseEntity<SurveyQuestion>(surveyQuestion, HttpStatus.OK);
    }

    @PutMapping("{str_id}")
    @PatchMapping("{str_id}")
    public ResponseEntity<?> edit(@PathVariable String slug, @RequestBody SurveyQuestion surveyQuestion, @PathVariable String str_id) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        
        Survey survey = surveyService.getSurvey(club);
        
        if (survey == null) {
            return new ResponseEntity<String>("Survey Does Not Exist", HttpStatus.BAD_REQUEST); 
        }

        Integer id = Integer.parseInt(str_id);
        SurveyQuestion changedQuestion = surveyQuestionService.updateQuestion(surveyQuestion, id);

        if (changedQuestion == null) {
            return new ResponseEntity<String>("Question Couldn't Be Updated", HttpStatus.BAD_REQUEST); 
        }

        return new ResponseEntity<SurveyQuestion>(changedQuestion, HttpStatus.OK);
    }

    @DeleteMapping("{str_id}")
    public ResponseEntity<?> delete(@PathVariable String slug, @PathVariable String str_id) {
        Integer id = Integer.parseInt(str_id);
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<String>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        
        Survey survey = surveyService.getSurvey(club);
        
        if (survey == null) {
            return new ResponseEntity<String>("Survey Does Not Exist", HttpStatus.BAD_REQUEST); 
        }

        
        Boolean isDeleted = surveyQuestionService.deleteQuestion(id, survey);

        if (!isDeleted) {
            return new ResponseEntity<String>("Question Couldn't Be Deleted", HttpStatus.BAD_REQUEST); 
        }

        return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);
    }
}

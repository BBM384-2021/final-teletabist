package com.teletabist.clubby.survey.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.survey.core.SurveyState;
import com.teletabist.clubby.survey.models.Survey;
import com.teletabist.clubby.survey.models.SurveyQuestion;
import com.teletabist.clubby.survey.models.SurveyRepository;
import com.teletabist.clubby.survey.models.UserSurvey;
import com.teletabist.clubby.survey.models.UserSurveyQuestion;
import com.teletabist.clubby.survey.models.UserSurveyRepository;
import com.teletabist.clubby.survey.models.UserSurveyTaken;
import com.teletabist.clubby.survey.models.UserSurveyTakenRepository;
import com.teletabist.clubby.user.models.User;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserSurveyService {
    @Autowired UserSurveyRepository userSurveyRepository;
    @Autowired UserSurveyTakenRepository userSurveyTakenRepository;
    @Autowired SurveyRepository surveyRepository;

    public Collection<UserSurvey> getUserSurveys(User u){
        if(u == null) return null; //User is null
        Collection<UserSurvey> surveys = this.userSurveyRepository.findByUser(u);
        if(surveys != null){
            if(!surveys.isEmpty()){
                return surveys;
            }
        }
        return new ArrayList<UserSurvey>();
    }

    public UserSurvey generateSurvey(User u, Collection<Club> clubFilter){
        if(clubFilter == null) return null;
        if(clubFilter.size() < 1) return null;
        Collection<UserSurvey> userSurveys = this.getUserSurveys(u);
        if(userSurveys == null) return null; //User is null
        UserSurvey _generatedSurvey = new UserSurvey();
        ArrayList<UserSurveyTaken> _newSurveys = new ArrayList<UserSurveyTaken>();
        Collection<Survey> _newSurveysToUser = new ArrayList<Survey>();
        Collection<UserSurveyQuestion> _questions = new ArrayList<UserSurveyQuestion>();

        Collection<Club> allclubs = new ArrayList<Club>();
        for (Club club : clubFilter) {
            allclubs.addAll(this.openClubs(club));
        }

        //Included club ids
        if(!userSurveys.isEmpty()){
            //User have taken some survey, exclude these from generation
            //Excluded survey ids
            ArrayList<Integer> ids = new ArrayList<Integer>();
            
            for(UserSurvey usurvey : userSurveys){
                for(UserSurveyTaken usurveytaken : usurvey.getTakens()){

                    ids.add(usurveytaken.getSurvey().getId());
                }
            }
            
            if(ids.size()<1) return null;
            
            _newSurveysToUser = this.getSurveysNewToUserOnlyClubs(ids, allclubs);
        }else{
            _newSurveysToUser = this.surveyRepository.findAll(Specification.where(this.onlyClubs(allclubs)));
        }

        if(_newSurveysToUser.size()<1) return null;

        for (Survey survey : _newSurveysToUser) {
            UserSurveyTaken _taken = new UserSurveyTaken();
            _taken.setUser(u);
            _taken.setSurvey(survey);
            _taken.setUsersurvey(_generatedSurvey);
            _newSurveys.add(_taken);
            for (SurveyQuestion question : survey.getQuestions()) {
                UserSurveyQuestion _tmp = new UserSurveyQuestion();
                _tmp.question = question;
                _tmp.userAnswer = -1;
                _questions.add(_tmp);
            }
        }

        if(_newSurveys.size() < 1) return null;
        if(_questions.size() < 1) return null;
        _generatedSurvey.setUser(u);
        _generatedSurvey.setState(SurveyState.NEW);
        _generatedSurvey.setUserSurveys(_questions);
        _generatedSurvey.setTakens(_newSurveys);

        _generatedSurvey = this.userSurveyRepository.save(_generatedSurvey);
        _generatedSurvey = this.userSurveyRepository.getOne(_generatedSurvey.getId()); //refresh data
        return _generatedSurvey;
    }

    private static Integer MAX_DEPTH = 10;
    private Collection<Club> openClubs(Club c){
        if(c != null)
            return this.openClubs(c, 0);
        return new ArrayList<Club>();
    }

    private Collection<Club> openClubs(Club c, Integer depth){
        if(depth > UserSurveyService.MAX_DEPTH || c == null) return new ArrayList<Club>();
        ArrayList<Club> clubs = new ArrayList<Club>();
        clubs.add(c);
        Collection<Club> subclubs = c.getSubclubs();
        if(subclubs != null){
            for (Club club : c.getSubclubs()) {
                clubs.addAll(this.openClubs(club, depth+1));
            }
        }
        return clubs;
    }

    private Collection<Survey> getSurveysNewToUserOnlyClubs(
        Collection<Integer> ids, 
        Collection<Club> clubs) {
        List<Survey> _new = this.surveyRepository.findAll(Specification.where(this.exceptSurveys(ids).and(this.onlyClubs(clubs))));
        LoggerFactory.logger(UserSurveyService.class).info(_new.size());
        return this.surveyRepository.findAll(Specification.where(this.onlyClubs(clubs)).and(this.exceptSurveys(ids)));
    }   
    
    private Specification<Survey> exceptSurveys(Collection<Integer> ids){
        return new Specification<Survey>(){
            private static final long serialVersionUID = -6455877016366266138L;

            @Override
            public Predicate toPredicate(Root<Survey> root, 
                                        CriteriaQuery<?> query, 
                                        CriteriaBuilder criteriaBuilder){
                return criteriaBuilder
                .in(root.get("id")).value(ids)
                .not();
            }
        };
    }

    private Specification<Survey> onlyClubs(Collection<Club> clubs){
        return new Specification<Survey>(){
            private static final long serialVersionUID = -6455877016366266138L;

            @Override
            public Predicate toPredicate(Root<Survey> root, 
                                        CriteriaQuery<?> query, 
                                        CriteriaBuilder criteriaBuilder){
                return criteriaBuilder
                .in(root.get("club")).value(clubs);
            }
        };
    }
}
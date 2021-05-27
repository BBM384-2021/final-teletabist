package com.teletabist.clubby.survey.http;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.models.SurveyClubSelectionDTO;
import com.teletabist.clubby.survey.services.UserSurveyService;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/surveys")
public class SurveyController {
    @Autowired UserSurveyService userSurveyService;
    @Autowired UserService userService;
    @Autowired ClubService clubService;

    @GetMapping("/clubs")
    public ModelAndView clubs(
        ModelMap map
    ){
        User currentuser = this.userService.authUser();
        if(currentuser != null){
            Collection<Club> clubs = this.clubService.listClubs();
            SurveyClubSelectionDTO dto = new SurveyClubSelectionDTO();
            map.addAttribute("dto", dto);
            map.addAttribute("clubs", clubs);

            return new ModelAndView("surveys/clubs");
        }
        return new ModelAndView("500");
    }

    @PostMapping("/clubs")
    public ModelAndView generateFromClubs(
        @ModelAttribute @Valid SurveyClubSelectionDTO dto,
        BindingResult bindingResult,
        ModelMap map,
        HttpServletRequest request
    ){
        User currentuser = this.userService.authUser();
        if(currentuser != null){
            if(!bindingResult.hasErrors()){
                

                return new ModelAndView("404");
            }else{
                Collection<Club> clubs = this.clubService.listClubs();

                map.put("dto", new SurveyClubSelectionDTO());
                map.put("error", "You must select at least one club!");
                map.addAttribute("clubs", clubs);
                return new ModelAndView("surveys/clubs");
            }

        }
        return new ModelAndView("500");
    }

}

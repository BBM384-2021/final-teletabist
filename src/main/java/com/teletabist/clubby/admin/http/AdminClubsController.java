package com.teletabist.clubby.admin.http;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubFormDTO;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.models.Answer;
import com.teletabist.clubby.survey.models.AnswerDTO;
import com.teletabist.clubby.survey.models.QuestionDTO;
import com.teletabist.clubby.survey.models.Survey;
import com.teletabist.clubby.survey.models.SurveyDTO;
import com.teletabist.clubby.survey.models.SurveyQuestion;
import com.teletabist.clubby.survey.services.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("admin/clubs")
public class AdminClubsController {

    @Autowired
    ClubService clubService;
    @Autowired
    SurveyService surveyService;

    @GetMapping
    public ModelAndView index(@RequestParam(defaultValue = "0", name = "p") Integer page, ModelMap map) {
        int pageCount = this.clubService.pageCount();
        if (page < 0) {
            page = 0;
        } else if (page >= pageCount) {
            page = pageCount - 1;
        }
        map.put("clubs", this.clubService.listClubs(page));

        ArrayList<Integer> paginarion_numbers = new ArrayList<Integer>();
        if (pageCount > 1) {
            int startingPage = page - 3 >= 0 ? page - 3 : 0;
            int endPage = page + 3 <= pageCount - 1 ? page + 3 : pageCount - 1;
            for (int i = startingPage; i <= endPage; i++) {
                paginarion_numbers.add(i);
            }
        }

        map.put("pagination", paginarion_numbers);
        map.put("totalpage", pageCount);
        map.put("page", page);
        return new ModelAndView("admin/clubs/index");
    }

    @GetMapping("create")
    public ModelAndView edit(ModelMap map, @RequestParam(defaultValue = "", name = "parent") String parent,
            HttpServletRequest request) {
        if (request.getHeader("Referer") != null) {
            if (!request.getHeader("Referer").equals("")) {
                map.addAttribute("referer", request.getHeader("Referer"));
            }
        }
        Club pclub = this.clubService.getClub(parent);
        ClubFormDTO dto = new ClubFormDTO();
        if (pclub != null) {

            dto.setParent_id(pclub.getId());
        }
        map.addAttribute("allclubs", this.clubService.listClubs());
        map.addAttribute("club", dto);
        return new ModelAndView("admin/clubs/create");
    }

    @PostMapping("create")
    public ModelAndView store(@ModelAttribute @Valid ClubFormDTO data, BindingResult bindingResult, ModelMap map,
            HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            map.addAttribute("allclubs", this.clubService.listClubs());
            map.addAttribute("club", new ClubFormDTO());
            return new ModelAndView("admin/clubs/create");
        }

        Club created = this.clubService.createClub(data);
        if (created.getId() != null) {
            if (created.getId() > -1)
                return new ModelAndView("redirect:/admin/clubs/edit/" + created.getSlug());
        }

        return new ModelAndView("500");

    }

    @GetMapping("edit/{slug}")
    public ModelAndView edit(@PathVariable String slug, ModelMap map, HttpServletRequest request) {
        if (request.getHeader("Referer") != null) {
            if (!request.getHeader("Referer").equals("")) {
                map.addAttribute("referer", request.getHeader("Referer"));
            }
        }

        Club c = this.clubService.getClub(slug);
        if (c != null) {
            ClubFormDTO dto = new ClubFormDTO(c);
            map.addAttribute("allclubs", this.clubService.listClubs());
            map.addAttribute("club", dto);
            return new ModelAndView("admin/clubs/edit");
        }

        return new ModelAndView("404");

    }

    @PostMapping("edit/{slug}")
    public ModelAndView update(@PathVariable String slug, @ModelAttribute @Valid ClubFormDTO dto,
            BindingResult bindingResult, ModelMap map, HttpServletRequest request) {
        Club c = this.clubService.fromDTO(dto);
        c = this.clubService.updateEntireClub(c, slug);
        if (c != null) {
            map.addAttribute("allclubs", this.clubService.listClubs());
            ClubFormDTO newDto = new ClubFormDTO(c);
            map.addAttribute("club", newDto);
            map.addAttribute("slug", slug);
            map.addAttribute("success", "Successfully updated club");
            return new ModelAndView("admin/clubs/edit");
        }

        return new ModelAndView("500");

    }

    @GetMapping("edit/{slug}/subclubs")
    public ModelAndView subclubs(@PathVariable String slug, ModelMap map, HttpServletRequest request) {
        Club c = this.clubService.getClub(slug);
        if (c != null) {
            map.put("club", c);
            Collection<Survey> s = new ArrayList<Survey>();
            for (Club sclub : c.getSubclubs()) {
                s.add(this.surveyService.getSurvey(sclub));
            }
            map.put("surveys", s);
            return new ModelAndView("admin/clubs/subclubs");
        }
        return new ModelAndView("404");
    }

    @GetMapping("edit/{slug}/survey")
    public ModelAndView editsurvey(@PathVariable String slug, ModelMap map, HttpServletRequest request) {
        Club c = this.clubService.getClub(slug);
        if (c != null) {
            if (c.getParent() != null) {
                Survey s = this.surveyService.getSurvey(c);

                SurveyDTO dto = new SurveyDTO(s);

                map.addAttribute("club", c);
                map.addAttribute("survey", dto);
                return new ModelAndView("admin/clubs/survey/edit");
            }

        }
        return new ModelAndView("404");
    }

    @PostMapping("edit/{slug}/survey")
    public ModelAndView updatesurvey(
        @PathVariable String slug, 
        @ModelAttribute @Valid SurveyDTO dto,
            BindingResult result, ModelMap map, HttpServletRequest request) {
        Club c = this.clubService.getClub(slug);
        if (c != null) {
            if (c.getParent() != null) {
                Survey s = this.surveyService.getSurvey(c);
                if(s != null){
                    
                }else{
                    s = new Survey();
                    ArrayList<SurveyQuestion> qs = new ArrayList<SurveyQuestion>();
                    for (QuestionDTO q : dto.getQuestions()) {
                        SurveyQuestion nq = new SurveyQuestion();
                        ArrayList<Answer> answers = new ArrayList<Answer>();
                        for (AnswerDTO ans : q.getAnswers()) {
                            answers.add(ans.toAnswer());
                        }
                        nq.setAnswersCollection(answers);
                        nq.setQuestion_message(q.getQuestion());
                        nq.setQuestion_type(q.getType());
                        nq.setWeight(q.getWeight());
                        nq.setSurvey(s);
                        qs.add(nq);
                    }
                    s.setQuestions(qs);
                    s.setClub(c);
                    s = this.surveyService.createSurvey(c,s);
                    s = this.surveyService.getSurvey(c);
                    SurveyDTO newDTO = new SurveyDTO(s);
                    dto = newDTO;
                }
                map.addAttribute("success", "Updated survey");
                map.addAttribute("club", c);
                map.addAttribute("survey", dto);
                return new ModelAndView("admin/clubs/survey/edit");
            }
            
        }
        return new ModelAndView("404");
    }

    
    @GetMapping("edit/{slug}/survey/create")
    public ModelAndView createsurvey(
        @PathVariable String slug, 
        ModelMap map,
        HttpServletRequest request) {
        Club c = this.clubService.getClub(slug);
        if (c != null) {
            if (c.getParent() != null) {
                Survey s = this.surveyService.getSurvey(c);
                if(s == null){
                    SurveyDTO dto = new SurveyDTO();
                    dto.setQuestions(new ArrayList<QuestionDTO>());
                    map.addAttribute("club", c);
                    map.addAttribute("survey", dto);
                    return new ModelAndView("admin/clubs/survey/edit");
                }
                
                
                return new ModelAndView("500");
            }
            
        }
        return new ModelAndView("404");
    }


}
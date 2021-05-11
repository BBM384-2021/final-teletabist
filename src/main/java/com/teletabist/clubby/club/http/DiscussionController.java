package com.teletabist.clubby.club.http;

import javax.validation.Valid;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.Discussion;
import com.teletabist.clubby.club.models.DiscussionDTO;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.club.services.DiscussionService;
import com.teletabist.clubby.user.SecureUserPrincipal;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/clubs/{slug}/discussions")
public class DiscussionController {
    private final DiscussionService discussionService;
    private final ClubService clubService;

    @Autowired
    public DiscussionController(
        DiscussionService discussionService, 
        ClubService clubService
    ) {

        this.discussionService = discussionService;
        this.clubService = clubService;
    }

    @GetMapping
    public ModelAndView index(
        @PathVariable String slug,
        ModelMap map
    ){
        Club c = this.clubService.getClub(slug);
        if(c != null){
            map.addAttribute("club", c);
                
            Iterable<Discussion> discussions = this.discussionService.getAllDiscussions(c.getSlug());
            map.addAttribute("discussions", discussions);

            return new ModelAndView("club/discussion/index");
        }
        return new ModelAndView("404");
    }

    @PostMapping
    public ModelAndView store(@PathVariable String slug, @Valid DiscussionDTO newComment, BindingResult result,  Authentication auth) {
        if (result.hasErrors()) {
            return new ModelAndView("500");
        }
        SecureUserPrincipal user = (SecureUserPrincipal) auth.getPrincipal();
        Discussion c = new Discussion();
        c.setMessage(newComment.getMessage());
        c.setUser(user.getUser());
        c = this.discussionService.discussionCreator(slug, c);
        
        if(c != null)
            return new ModelAndView("redirect:/clubs/"+slug+"/discussions");
        return new ModelAndView("500");
    }
}
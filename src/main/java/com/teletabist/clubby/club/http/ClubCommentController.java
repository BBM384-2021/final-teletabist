package com.teletabist.clubby.club.http;


import java.util.Collection;

import javax.validation.Valid;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubComment;
import com.teletabist.clubby.club.models.ClubCommentDTO;
import com.teletabist.clubby.club.models.ClubRating;
import com.teletabist.clubby.club.services.ClubCommentService;
import com.teletabist.clubby.club.services.ClubRatingService;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.user.SecureUserPrincipal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clubs/{slug}/comments")
public class ClubCommentController {

    private final ClubCommentService clubCommentService;
    private final ClubService clubService;
    private final ClubRatingService clubRatingService;

    @Autowired
    public ClubCommentController(
        ClubCommentService clubCommentService, 
        ClubService clubService,
        ClubRatingService clubRatingService
    ) {

        this.clubCommentService = clubCommentService;
        this.clubService = clubService;
        this.clubRatingService = clubRatingService;
    }

    @GetMapping
    public ModelAndView index(
        @PathVariable String slug,
        ModelMap map
    ){
        Club c = this.clubService.getClub(slug);
        if(c != null){
            map.addAttribute("club", c);
            if(c.getClubRating() == null){
                ClubRating cr = this.clubRatingService.getClubRating(c);
                c.setClubRating(cr);
            }
                
            Iterable<ClubComment> comments = this.clubCommentService.getAllComments(c.getSlug());
            map.addAttribute("comments", comments);

            return new ModelAndView("club/comment/index");
        }
        return new ModelAndView("404");
    }

    @PostMapping
    public ModelAndView store(@PathVariable String slug, @Valid ClubCommentDTO newComment, BindingResult result,  Authentication auth) {
        if (result.hasErrors()) {
            return new ModelAndView("500");
        }
        SecureUserPrincipal user = (SecureUserPrincipal) auth.getPrincipal();
        ClubComment c = new ClubComment();
        c.setComment(newComment.getComment());
        c.setLiked(newComment.isLiked() == 1 ? true : false);
        c.setUser(user.getUser());
        c = this.clubCommentService.clubCommentCreator(slug, c);
        if(c != null)
            return new ModelAndView("redirect:/clubs/"+slug+"/comments");
        return new ModelAndView("500");
    }

}

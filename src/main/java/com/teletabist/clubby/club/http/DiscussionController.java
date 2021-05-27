package com.teletabist.clubby.club.http;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.models.Discussion;
import com.teletabist.clubby.club.models.DiscussionDTO;
import com.teletabist.clubby.club.services.ClubRoleService;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.club.services.DiscussionService;
import com.teletabist.clubby.user.SecureUserPrincipal;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private final UserService userService;
    private final ClubRoleService clubRoleService;

    @Autowired
    public DiscussionController(
        DiscussionService discussionService, 
        ClubService clubService,
        UserService userService,
        ClubRoleService clubRoleService
    ) {

        this.discussionService = discussionService;
        this.clubService = clubService;
        this.userService = userService;
        this.clubRoleService = clubRoleService;
    }

    @GetMapping
    public ModelAndView index(
        @PathVariable String slug,
        ModelMap map
    ){
        Club c = this.clubService.getClub(slug);
        if(c != null){
            map.addAttribute("club", c);
            User user = userService.authUser();
            Collection<Discussion> discussions = this.discussionService.getAllDiscussions(c.getSlug(), user);
            map.addAttribute("discussions", discussions);

            Collection<ClubRole> clubRoles = clubRoleService.getClubRoles(c);
            Collection<ClubRole> members = new ArrayList<ClubRole>();
            for (ClubRole clubRole : clubRoles) {
                if (!clubRole.getUser_role().getRole().equals(Roles.SUB_CLUB_ADMIN.getName())) {
                    members.add(clubRole);
                }
            }
            map.addAttribute("members", members);
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
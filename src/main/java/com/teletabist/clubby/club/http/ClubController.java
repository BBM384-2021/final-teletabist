package com.teletabist.clubby.club.http;

import javax.servlet.http.HttpServletRequest;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubFormDTO;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.services.ClubRoleService;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.user.SecureUserPrincipal;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("clubs")
@Controller
public class ClubController {
    private final ClubService clubService;
    private final ClubRoleService clubRoleService;

    @Autowired
    public ClubController(ClubService clubService, ClubRoleService clubRoleService) {
        this.clubService = clubService;
        this.clubRoleService = clubRoleService;
    }

    @GetMapping
    public String index(ModelMap map) {
        map.put("clubs", clubService.getAll());
        return "club/index";
    }

    @GetMapping("{slug}")
    public ModelAndView getClub(@PathVariable String slug, ModelMap map) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ModelAndView("404");
        }
        map.put("club", club);
        return new ModelAndView("club/single");
    }

    @GetMapping("create")
    public ModelAndView getCreateClubForm(
        WebRequest request,
        ModelMap model) {
        ClubFormDTO clubFormDTO = new ClubFormDTO();
        model.addAttribute("club", clubFormDTO);
        return new ModelAndView("club/create", model);
    }

    @PostMapping()
    public ModelAndView storeCreatedClub(
        @ModelAttribute("club") ClubFormDTO clubFormDTO, 
        HttpServletRequest request,
        Errors error,
        BindingResult result) {
        
        Club created = clubService.createClub(clubFormDTO);

        return new ModelAndView("redirect:/clubs/", "club", created);
    }

    @GetMapping("{slug}/edit")
    public ModelAndView getEditClubForm(
        @PathVariable String slug,
        ModelMap map, 
        ClubFormDTO clubFormDTO, 
        HttpServletRequest request) {
        Club club = clubService.getClub(slug);
        
        if(club == null) return new ModelAndView("404");

        clubFormDTO.setSlug(club.getSlug());
        clubFormDTO.setName(club.getName());
        clubFormDTO.setDescription(club.getDescription());
        clubFormDTO.setLocation(club.getLocation());
        clubFormDTO.setWebsite(club.getWebsite());

        map.addAttribute("club_form", clubFormDTO);
        map.put("club", club);
        map.put("referer_page", request.getHeader("Referer"));
        return new ModelAndView("club/edit");
    }

    @PutMapping("{slug}")
    @PatchMapping("{slug}")
    public ModelAndView storeUpdatedClub(
        ClubFormDTO clubFormDTO,
        @PathVariable String slug,
        HttpServletRequest request,
        Errors error,
        BindingResult bindingResult,
        Model model
    ) {
        model.addAttribute("slug", slug);
        if(bindingResult.hasErrors()){
            return new ModelAndView("forward:/clubs/{slug}");
        }
        
        /**
         * TO-DO Authontacation
         */

        Club club = new Club();
        club.setName(clubFormDTO.getName());
        club.setSlug(clubFormDTO.getSlug());
        club.setDescription(clubFormDTO.getDescription());
        club.setLocation(clubFormDTO.getLocation());
        //club.setParent_id
        club.setWebsite(clubFormDTO.getWebsite());

        clubService.updateEntireClub(club, slug);
        model.addAttribute("success", "Club updated successfully");
        return new ModelAndView("redirect:/clubs/{slug}");
    }

    @DeleteMapping("{slug}")
    public String deleteClub(@PathVariable String slug) {
        clubService.deleteClub(slug);
        return "redirect:/clubs/";
    }

    @PostMapping("{slug}")
    public ModelAndView join(
        @PathVariable String slug,
        HttpServletRequest request,
        Errors error,
        Authentication auth, Model model) {
        
        model.addAttribute("slug", slug);

        User user = getCurrentUser();
        Club club = clubService.getClub(slug);
        
        ClubRole clubRole = clubRoleService.assignClubRole(user, club, Roles.MEMBER);
        
        if (clubRole != null) {
            model.addAttribute("success", "User successfully became a member");
            return new ModelAndView("redirect:/{slug}");
        }
        model.addAttribute("error", "An error occured.");
        return new ModelAndView("redirect:/{slug}");
    }

    /**
     * TO-DO: Move this method to an appropriate place
     */
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof SecureUserPrincipal){
            SecureUserPrincipal securePrincipal = (SecureUserPrincipal) principal;
            return securePrincipal.getUser();
        }

        return null;
    }
}

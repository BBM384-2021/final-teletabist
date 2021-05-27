package com.teletabist.clubby.club.http;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import com.teletabist.clubby.club.models.Ban;
import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubFormDTO;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.services.BanService;
import com.teletabist.clubby.club.services.ClubRoleService;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.services.SurveyService;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    private final UserService userService;
    private final ClubRoleService clubRoleService;
    private final SurveyService surveyService;
    private final BanService banService;

    @Autowired
    public ClubController(ClubService clubService, ClubRoleService clubRoleService, UserService userService, BanService banService, SurveyService surveyService) {
        this.clubService = clubService;
        this.clubRoleService = clubRoleService;
        this.userService = userService;
        this.banService = banService;
        this.surveyService = surveyService;
    }

    @GetMapping
    public String index(ModelMap map) {
        map.put("clubs", clubService.getAll());
        return "club/index";
    }

    @GetMapping("{slug}")
    public ModelAndView getClub(@PathVariable String slug, ModelMap map) {
        Club club = clubService.getClub(slug);
        User currentUser = userService.authUser();
        
        //TO-DO : Ban Error must be shown
        /*if (banService.isBanned(currentUser, club) || banService.isBlacklisted(currentUser, club) != null) {
            return new ModelAndView("404");
        }*/

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
        Club club = clubService.getClub(slug);

        if (club == null) {
            return "redirect:/clubs/";
        }

        clubRoleService.deassignClubRole(club);

        surveyService.deleteSurvey(club);

        for (Club subClub : club.getSubclubs()) {
            deleteClub(subClub.getSlug());
        }

        if (clubService.deleteClub(slug)) return "redirect:/clubs/";
        return "forward:/clubs/{slug}";
    }

    @PostMapping("{slug}")
    public ModelAndView join(
        @PathVariable String slug,
        HttpServletRequest request,
        Errors error,
        Authentication auth, Model model) {
        
        model.addAttribute("slug", slug);

        User user = userService.authUser();
        Club club = clubService.getClub(slug);
        
        ClubRole clubRole = clubRoleService.assignClubRole(user, club, Roles.MEMBER);
        
        if (clubRole != null) {
            model.addAttribute("success", "User successfully became a member");
            return new ModelAndView("redirect:/{slug}");
        }
        model.addAttribute("error", "An error occured.");
        return new ModelAndView("redirect:/{slug}");
    }

    @PostMapping("{slug}")
    public ModelAndView banUser(
        @PathVariable String slug,
        HttpServletRequest request,
        Authentication auth,
        ModelMap map
        ) {
        
        //TO-DO Change the redirecting model and views to be correct
        Club club = clubService.getClub(slug); 

        if (club == null) {
            return new ModelAndView("redirect:/clubs");
        }

        User bannedUser = userService.authUser();

        if (bannedUser == null) {
            return new ModelAndView("redirect:/clubs");
        }

        User banningUser = userService.authUser();

        Ban ban = banService.createBan(bannedUser, banningUser, club);

        if (ban != null) {
            if (banService.isBlacklisted(bannedUser, club) != null) {
                Collection<ClubRole> clubRoles = clubRoleService.getClubRoles(club);
                
                for (ClubRole clubRole : clubRoles) {
                    if (clubRoleService.getUserRole(clubRole.getId()).getUser() == bannedUser) {
                        banService.deleteAllBans(clubRoleService.getUserRole(clubRole.getId()));
                        break;
                    }
                }
                
                clubRoleService.deassignClubRole(bannedUser, club);
                return new ModelAndView("redirect:/clubs");
            }

            if (banService.isAdminBlacklisted(bannedUser) != null) {
                Collection<ClubRole> clubRoles = clubRoleService.getClubRoles(club);

                for (ClubRole clubRole : clubRoles) {
                    if (clubRoleService.getUserRole(clubRole.getId()).getUser() == bannedUser
                        && clubRoleService.getUserRole(clubRole.getId()).getRole().equals(Roles.SUB_CLUB_ADMIN.getName())) {
                        
                        banService.deleteAllBans(clubRoleService.getUserRole(clubRole.getId()));
                        break;
                    }
                }
                clubRoleService.deassignSubClubAdmin(bannedUser, club);
            }
            return new ModelAndView("redirect:/clubs");
        }

        return new ModelAndView("redirect:/clubs");
    }
}

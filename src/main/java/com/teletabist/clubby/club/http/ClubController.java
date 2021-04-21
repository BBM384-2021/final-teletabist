package com.teletabist.clubby.club.http;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.services.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("clubs")
@Controller
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public String index(ModelMap map) {
        map.put("clubs", clubService.getAll());
        return "club/index";
    }

    @GetMapping("{slug}")
    public String getClub(@PathVariable String slug, ModelMap map) {
        map.put("club", clubService.getClub(slug));
        return "club/single";
    }

    @GetMapping("create")
    public ModelAndView getCreateClubForm() {
        return new ModelAndView("club/create", "club", new Club());
    }

    @PostMapping()
    public String storeCreatedClub(@ModelAttribute("club") Club club, BindingResult result, ModelMap model) {
        
        if (result.hasErrors()) {
            return "error";
        }

        /*model.addAttribute("id", club.getId());
        model.addAttribute("slug", club.getSlug());
        model.addAttribute("name", club.getName());
        model.addAttribute("description", club.getDescription());
        model.addAttribute("profile_photo_url", club.getProfile_photo_url());
        model.addAttribute("website", club.getWebsite());
        model.addAttribute("location", club.getLocation());
        model.addAttribute("parent_id", club.getParent_id());*/

        Club createdClub = clubService.addClub(club);
        return "redirect:/clubs/" + createdClub.getSlug();
    }

    @GetMapping("{slug}/edit")
    public ModelAndView getEditClubForm(@PathVariable String slug) {
        return new ModelAndView("club/edit", "club", clubService.getClub(slug));
    }

    @PutMapping("{slug}")
    @PatchMapping("{slug}")
    public String storeUpdatedClub(@ModelAttribute("club") Club club, BindingResult result, ModelMap model, @PathVariable String slug) {
        
        if (result.hasErrors()) {
            return "error";
        }
        
        /*model.addAttribute("id", club.getId());
        model.addAttribute("slug", club.getSlug());
        model.addAttribute("name", club.getName());
        model.addAttribute("description", club.getDescription());
        model.addAttribute("profile_photo_url", club.getProfile_photo_url());
        model.addAttribute("website", club.getWebsite());
        model.addAttribute("location", club.getLocation());
        model.addAttribute("parent_id", club.getParent_id());*/

        Club updatedClub = clubService.updateEntireClub(club, slug);
        return "redirect:/clubs/" + updatedClub.getSlug();
    }

    @DeleteMapping("{slug}")
    public String deleteClub(@PathVariable String slug) {
        clubService.deleteClub(slug);
        return "redirect:/clubs/";
    }

}

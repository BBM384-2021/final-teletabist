package com.teletabist.clubby.club.http;

import com.teletabist.clubby.club.services.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("club")
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

    @GetMapping("get/{slug}")
    public String getClub(@PathVariable String slug, ModelMap map) {
        map.put("club", clubService.getClub(slug));
        return "club/single";
    }
}

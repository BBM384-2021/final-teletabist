package com.teletabist.clubby.admin.http;

import com.teletabist.clubby.club.services.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("admin/clubs")
public class AdminClubsController {
    
    @Autowired ClubService clubService;

    @GetMapping
    public ModelAndView index(
        @RequestParam(defaultValue = "0", name = "p") Integer page,
        ModelMap map 
    ){
        

        map.put("page", page);
        return new ModelAndView("admin/clubs/index");
    }

}
package com.teletabist.clubby.admin.http;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.teletabist.clubby.club.models.ClubFormDTO;
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
        int pageCount = this.clubService.pageCount();
        if(page<0){
            page = 0;
        }else if(page>=pageCount){
            page=pageCount-1;
        }
        map.put("clubs", this.clubService.listClubs(page));
        
        ArrayList<Integer> paginarion_numbers = new ArrayList<Integer>();
        if(pageCount>1){
            int startingPage = page-3>=0 ? page-3 : 0;
            int endPage = page+3<=pageCount-1?page+3:pageCount-1;
            for(int i = startingPage; i<=endPage; i++){
                paginarion_numbers.add(i);
            }
        }


        map.put("pagination", paginarion_numbers);
        map.put("totalpage", pageCount);
        map.put("page", page);
        return new ModelAndView("admin/clubs/index");
    }

    @GetMapping("create")
    public ModelAndView edit(
        ModelMap map,
        HttpServletRequest request
    ){
        if(request.getHeader("Referer") != null){
            if(!request.getHeader("Referer").equals("")){
                map.addAttribute("referer", request.getHeader("Referer"));
            }
        }
        map.addAttribute("allclubs", this.clubService.listClubs());
        map.addAttribute("club", new ClubFormDTO());
        return new ModelAndView("admin/clubs/create");
    }

}
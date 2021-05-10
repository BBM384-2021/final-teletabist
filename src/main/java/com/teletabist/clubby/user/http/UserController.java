package com.teletabist.clubby.user.http;


import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.teletabist.clubby.user.SecureUserPrincipal;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.Profile;
import com.teletabist.clubby.user.models.ProfileFormDTO;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.ProfileService;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("users")
@Controller
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;

    @Autowired
    public UserController(UserService userService, ProfileService profileService){
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping
    public String index(ModelMap map){
        map.put("users", this.userService.getAll());        
        return "user/index";
    }

    @GetMapping("{username}")
    public ModelAndView show(@PathVariable String username, ModelMap map){
        User u = this.userService.getUser(username);
        if(u == null)
            return new ModelAndView("404");
        map.put("user", u);    
        if(this.canEdit(username)){
            return new ModelAndView("user/editable");
        }
            
        return new ModelAndView("user/single");
    }

    @GetMapping("{username}/edit")
    public ModelAndView edit(
        @PathVariable String username,
        ModelMap map,
        ProfileFormDTO profileFormDTO,
        HttpServletRequest request
        ){
        User u = this.userService.getUser(username);
        if(u == null)
            return new ModelAndView("404");
        boolean _canedit = this.canEdit(username);
        if(_canedit){
            Profile p = u.getProfile();
            profileFormDTO.setBiography(p.getBiography());
            profileFormDTO.setGender(p.getGender());
            profileFormDTO.setInstitution(p.getInstitution());
            profileFormDTO.setLocation(p.getCurrent_location());
            profileFormDTO.setName(p.getName());
            map.addAttribute("profile", profileFormDTO);
            map.put("user", u);        
            map.put("referer_page", request.getHeader("Referer"));
            return new ModelAndView("user/edit");
        }
        map.addAttribute("error", "An error occured.");
        return new ModelAndView("redirect:/users/{username}");
    }

    @PatchMapping("{username}")
    public ModelAndView store(
        @Valid ProfileFormDTO profileFormDTO,
        @PathVariable String username,
        HttpServletRequest request,
        Errors error,
        BindingResult br,
        Authentication auth, Model model
    ){
        model.addAttribute("username", username);
        if(br.hasErrors()){
            return new ModelAndView("forward:/users/{username}");
        }

        boolean _canedit = this.canEdit(username);
        
        if(_canedit){
            Profile p = new Profile();
            p.setBiography(profileFormDTO.getBiography());
            p.setBirthday(profileFormDTO.getBirthday());
            p.setCurrent_location(profileFormDTO.getLocation());
            p.setGender(profileFormDTO.getGender());
            p.setInstitution(profileFormDTO.getInstitution());
            p.setJob_title(profileFormDTO.getJobTitle());
            p.setName(profileFormDTO.getName());
            this.profileService.updateProfile(username, p);
        
            model.addAttribute("success", "Profile updated successfully");
            return new ModelAndView("redirect:/users/{username}");
        }
        model.addAttribute("error", "An error occured.");
        return new ModelAndView("redirect:/users/{username}");
    }

    private boolean canEdit( String username){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof String){
            return false;
        }
        boolean _canedit = false;
        if(principal instanceof SecureUserPrincipal){
            SecureUserPrincipal securePrincipal = (SecureUserPrincipal) principal;
            if(securePrincipal.getUsername().equals(username)){
                _canedit = true;
            }else{
                Collection<? extends GrantedAuthority> roles = securePrincipal.getAuthorities();
                for(GrantedAuthority g : roles){
                    String grantedAuth = g.getAuthority();
                    if(grantedAuth.equals(Roles.SYS_ADMIN.getRoleName())
                    || grantedAuth.equals(Roles.ADMIN.getRoleName())){
                        _canedit = true;
                        break;
                    }
                }
            }
        }
        return _canedit;
    }
}
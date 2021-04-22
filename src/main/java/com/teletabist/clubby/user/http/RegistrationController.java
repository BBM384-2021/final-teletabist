package com.teletabist.clubby.user.http;

import java.lang.ProcessBuilder.Redirect;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.ValidationException;

import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserDTO;
import com.teletabist.clubby.user.services.UserService;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("account")
@Controller
public class RegistrationController {
    private final UserService uService;

    @Autowired
    private MessageSource messages;
    
    @Autowired
    public RegistrationController(UserService uService){
        this.uService = uService;
    }




    @GetMapping("register")
    public ModelAndView create(WebRequest request, ModelMap model, Authentication auth){
        if(auth != null){
            if(auth.isAuthenticated())
                return new ModelAndView("redirect:/", model);
        }
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return new ModelAndView("account/register", model);
    }

    @PostMapping("register")
    public ModelAndView store(
        @ModelAttribute("user") @Valid UserDTO userDTO,
        HttpServletRequest request,
        Errors error,
        BindingResult br
    ) throws javax.xml.bind.ValidationException {
        User registered;
        registered = this.uService.registerUser(userDTO);
        return new ModelAndView("account/validateemail", "user", registered);

    }

    @GetMapping("verify/{username}/{token}")
    public ModelAndView store(
        @PathVariable String username,
        @PathVariable String token
    ){
        User u = this.uService.getUser(username);
        if(u != null){
            if(u.isVerified()) return new ModelAndView("redirect:/login");
            if(this.uService.verifyUser(u, token))
                return new ModelAndView("account/validated");
        }
        return new ModelAndView("404");

    }
}
package com.teletabist.clubby.club.http;


import com.teletabist.clubby.club.models.ClubComment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/clubs/{slug}/comments")
public class ClubCommentController {

    @GetMapping("create")
    public RedirectView create(@PathVariable String slug, RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("attribute", "redirectWithRedirectView");
        return new RedirectView("redirectedUrl");
    }

    @PostMapping
    public String store(/*@Valid*/ @ModelAttribute("comment")ClubComment clubComment, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        return "commentView";
    }
}

package com.teletabist.clubby.club.api;

import com.teletabist.clubby.club.models.Discussion;
import com.teletabist.clubby.club.services.DiscussionService;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dev/clubs/{slug}/discussion")
public class DiscussionAPIController {
    private DiscussionService discussionService;
    private UserService userService;

    @Autowired
    public DiscussionAPIController(DiscussionService discussionService, UserService userService) {
        this.discussionService = discussionService;
        this.userService = userService;
    }

    @GetMapping
    public Iterable<Discussion> getAllDiscussions(@PathVariable String slug) {
        
        return discussionService.getAllDiscussions(slug, userService.authUser());
    }

    @GetMapping("{id}")
    public Discussion getMessage(@PathVariable String slug, @PathVariable Integer id) {
        return discussionService.getMessage(slug, id);
    }

    @PostMapping
    public Discussion createDiscussion(@PathVariable String slug, /*@Valid*/ @RequestBody Discussion discussion, Authentication authentication) {
        //discussion.setUser(user.getUser());
        return discussionService.discussionCreator(slug, discussion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteDiscussion(@PathVariable String slug,@PathVariable Integer id) {
        if (discussionService.getMessage(slug, id) != null){
            if (discussionService.deleteDiscussion(id)) return new ResponseEntity<>("Successful", HttpStatus.OK);}
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

}

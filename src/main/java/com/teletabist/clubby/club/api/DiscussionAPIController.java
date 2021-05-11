package com.teletabist.clubby.club.api;

import com.teletabist.clubby.club.models.Discussion;
import com.teletabist.clubby.club.services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dev/clubs/{slug}/discussion")
public class DiscussionAPIController {
    private DiscussionService discussionService;

    @Autowired
    public DiscussionAPIController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @GetMapping
    public Iterable<Discussion> getAllDiscussions(@PathVariable String slug) {
        return discussionService.getAllDiscussions(slug);
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

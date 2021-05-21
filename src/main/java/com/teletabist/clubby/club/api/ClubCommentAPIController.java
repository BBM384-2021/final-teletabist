package com.teletabist.clubby.club.api;


import com.teletabist.clubby.club.models.ClubComment;
import com.teletabist.clubby.club.services.ClubCommentService;
import com.teletabist.clubby.user.SecureUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dev/clubs/{slug}/comments")
public class ClubCommentAPIController {
    private ClubCommentService clubCommentService;

    @Autowired
    public ClubCommentAPIController(ClubCommentService clubCommentService) {
        this.clubCommentService = clubCommentService;
    }

    @GetMapping
    public Iterable<ClubComment> getAllClubComments(@PathVariable String slug) {
        return clubCommentService.getAllComments(slug);
    }

    @GetMapping("{id}")
    public ClubComment getClubComment(@PathVariable String slug, @PathVariable Integer id) {
        return clubCommentService.getComment(slug, id);
    }

    @PostMapping
    public ClubComment createClubComment(@PathVariable String slug, /*@Valid*/ @RequestBody ClubComment clubComment, Authentication authentication) {
        SecureUserPrincipal user = (SecureUserPrincipal) authentication.getPrincipal();
        clubComment.setUser(user.getUser());
        return clubCommentService.clubCommentCreator(slug, clubComment);
    }

    @PutMapping("{id}")
    @PatchMapping("{id}")
    public ResponseEntity<?> updateClubComment(@PathVariable Integer id, /*@Valid*/ @RequestBody ClubComment clubCommentRequest, @PathVariable String slug) {
        ClubComment clubComment = clubCommentService.updateComment(id, clubCommentRequest, slug);
        if (clubComment != null) return new ResponseEntity<ClubComment>(clubComment, HttpStatus.OK);
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClubComment(@PathVariable Integer id, @PathVariable String slug) {
        if (clubCommentService.deleteComment(id, slug)) return new ResponseEntity<>("Successful", HttpStatus.OK);
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

}

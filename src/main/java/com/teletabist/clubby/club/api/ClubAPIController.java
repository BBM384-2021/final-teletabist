package com.teletabist.clubby.club.api;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.services.ClubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/club")
public class ClubAPIController {
    private final ClubService clubService;

    @Autowired
    public ClubAPIController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("all")
    public Iterable<Club> getClubs() {
        return clubService.getAll();
    }

    @PostMapping("create")
    public Club createClub(@RequestBody Club club) {
        return clubService.addClub(club);
    }

    @PatchMapping("update/{slug}")
    public Club updateClub(@RequestBody Club club, @PathVariable String slug) {
        return clubService.updateEntireClub(club, slug);
    }

    @GetMapping("get/{slug}")
    public Club getClub(@PathVariable String slug) {
        return clubService.getClub(slug);
    }

    @DeleteMapping("delete/{slug}")
    public ResponseEntity<?> deleteClub(@PathVariable String slug) {
        if (clubService.deleteClub(slug)) return new ResponseEntity<>("Successfull", HttpStatus.OK);
        return new ResponseEntity<>("Unsuccessfull", HttpStatus.BAD_REQUEST);
    }

}

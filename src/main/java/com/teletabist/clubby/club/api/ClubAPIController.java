package com.teletabist.clubby.club.api;

import java.util.Collection;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.services.ClubRoleService;
import com.teletabist.clubby.club.services.ClubService;
import com.teletabist.clubby.survey.services.SurveyService;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/dev/clubs")
public class ClubAPIController {
    private final ClubService clubService;
    private final UserService userService;
    private final ClubRoleService clubRoleService;
    private final SurveyService surveyService;

    @Autowired
    public ClubAPIController(ClubService clubService, UserService userService, ClubRoleService clubRoleService, SurveyService surveyService) {
        this.clubService = clubService;
        this.userService = userService;
        this.clubRoleService = clubRoleService;
        this.surveyService = surveyService;
    }

    @GetMapping
    public Iterable<Club> index() {
        return clubService.getAll();
    }

    @PostMapping
    public Club store(@RequestBody Club club) {
        return clubService.addClub(club);
    }

    @PatchMapping("{slug}")
    @PutMapping("{slug}")
    public Club update(@RequestBody Club club, @PathVariable String slug) {
        return clubService.updateEntireClub(club, slug);
    }

    @GetMapping("{slug}")
    public Club get(@PathVariable String slug) {
        return clubService.getClub(slug);
    }

    @DeleteMapping("{slug}")
    public ResponseEntity<?> delete(@PathVariable String slug) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        clubRoleService.deassignClubRole(club);

        surveyService.deleteSurvey(club);

        if (clubService.deleteClub(slug)) return new ResponseEntity<>("Successful", HttpStatus.OK);
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping("{slug}/join/{username}")
    public ResponseEntity<?> join(@PathVariable String slug, @PathVariable String username) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(username);

        if (user == null) {
            return new ResponseEntity<>("User Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        
        ClubRole clubRole = clubRoleService.assignClubRole(user, club, Roles.MEMBER);
        
        if (clubRole != null) {
            return new ResponseEntity<ClubRole>(clubRole, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{slug}/roles")
    public ResponseEntity<?> getRoles(@PathVariable String slug) {
        Club club = clubService.getClub(slug);
        
        Collection<ClubRole> roles = clubRoleService.getClubRoles(club);
        
        if (roles != null) {
            return new ResponseEntity<Iterable<ClubRole>>(roles, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unsuccessfull", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{slug}/join/{username}")
    public ResponseEntity<?> leave(@PathVariable String slug, @PathVariable String username) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(username);

        if (user == null) {
            return new ResponseEntity<>("User Does Not Exist", HttpStatus.BAD_REQUEST);
        }
        
        Integer amount = clubRoleService.deassignClubRole(user, club);
        
        return new ResponseEntity<>(amount, HttpStatus.OK);
    }
}

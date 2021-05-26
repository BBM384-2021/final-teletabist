package com.teletabist.clubby.club.api;

import java.util.Collection;

import com.teletabist.clubby.club.models.Ban;
import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.services.BanService;
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
    private final BanService banService;

    @Autowired
    public ClubAPIController(ClubService clubService, UserService userService, ClubRoleService clubRoleService, SurveyService surveyService, BanService banService) {
        this.clubService = clubService;
        this.userService = userService;
        this.clubRoleService = clubRoleService;
        this.surveyService = surveyService;
        this.banService = banService;
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

        for (Club subClub : club.getSubclubs()) {
            delete(subClub.getSlug());
        }

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

        if (banService.isBlacklisted(user, club) != null) {
            return new ResponseEntity<>("You Are Blacklisted From This Club", HttpStatus.BAD_REQUEST);
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

    @PostMapping("{slug}/join/{username}/subclub-admin")
    public ResponseEntity<?> assignSubClubAdmin(@PathVariable String slug, @PathVariable String username) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUser(username);

        if (user == null) {
            return new ResponseEntity<>("User Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        if (banService.isAdminBlacklisted(user) != null) {
            return new ResponseEntity<>("This User Cannot Become A SubClub Admin", HttpStatus.BAD_REQUEST);
        }
        
        ClubRole clubRole = clubRoleService.assignClubRole(user, club, Roles.SUB_CLUB_ADMIN);
        
        if (clubRole != null) {
            return new ResponseEntity<ClubRole>(clubRole, HttpStatus.OK);
        }
        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{slug}/ban")
    public ResponseEntity<?> findBanned(@PathVariable String slug) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        /*Collection<ClubRole> clubRoles = clubRoleService.getClubRoles(club);

        for (ClubRole clubRole : clubRoles) {
            
        }*/

        return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("{slug}/ban/{username}")
    public ResponseEntity<?> banUser(@PathVariable String slug, @PathVariable String username) {
        Club club = clubService.getClub(slug);

        if (club == null) {
            return new ResponseEntity<>("Club Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        User bannedUser = userService.getUser(username);

        if (bannedUser == null) {
            return new ResponseEntity<>("User Does Not Exist", HttpStatus.BAD_REQUEST);
        }

        User banningUser = userService.authUser();

        Ban ban = banService.createBan(bannedUser, banningUser, club);

        if (ban != null) {
            if (banService.isBlacklisted(bannedUser, club) != null) {
                Collection<ClubRole> clubRoles = clubRoleService.getClubRoles(club);
                
                for (ClubRole clubRole : clubRoles) {
                    if (clubRoleService.getUserRole(clubRole.getId()).getUser() == bannedUser) {
                        banService.deleteAllBans(clubRoleService.getUserRole(clubRole.getId()));
                        break;
                    }
                }
                
                clubRoleService.deassignClubRole(bannedUser, club);
                return new ResponseEntity<>("Ban Commenced And Blacklisted", HttpStatus.OK);
            }

            if (banService.isAdminBlacklisted(bannedUser) != null) {
                Collection<ClubRole> clubRoles = clubRoleService.getClubRoles(club);

                for (ClubRole clubRole : clubRoles) {
                    if (clubRoleService.getUserRole(clubRole.getId()).getUser() == bannedUser
                        && clubRoleService.getUserRole(clubRole.getId()).getRole().equals(Roles.SUB_CLUB_ADMIN.getName())) {
                        
                        banService.deleteAllBans(clubRoleService.getUserRole(clubRole.getId()));
                        break;
                    }
                }
                clubRoleService.deassignSubClubAdmin(bannedUser, club);
            }
            return new ResponseEntity<>("Ban Commenced", HttpStatus.OK);
        }

        return new ResponseEntity<>("Couldn't Ban", HttpStatus.BAD_REQUEST);
    }
}

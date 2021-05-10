package com.teletabist.clubby.club.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.models.ClubRoleRepository;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRole;
import com.teletabist.clubby.user.models.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubRoleService {
    private UserRoleRepository userRoleRepository;
    private ClubRoleRepository clubRolesRepository;

    @Autowired
    public ClubRoleService(UserRoleRepository userRoleRepository, ClubRoleRepository clubRolesRepository) {
        this.userRoleRepository = userRoleRepository;
        this.clubRolesRepository = clubRolesRepository;
    }


    public ClubRole assignClubRole(User user, Club club, Roles role) {

        if (user == null || club == null) {
            return null;
        } 

         
        if (role != Roles.SUB_CLUB_ADMIN && role != Roles.MEMBER) {
            return null;
        }
        

        Collection<ClubRole> clubRoles = getClubRoles(club);

        boolean foundUser = false;
        for (ClubRole clubRole : clubRoles) {
            if (clubRole.getUser_role().getUser().equals(user)) {
                foundUser = true;
                break;
            }
        }

       if (foundUser) {
           return null;
       }
        UserRole userRole = new UserRole();
        userRole.setRole(role.getName());
        userRole.setUser(user);
        userRole.setClub_id(club.getId());
        userRole = userRoleRepository.save(userRole);

        if (userRole.getId() == null) {
            return null;
        }

        ClubRole clubRole = new ClubRole();
        clubRole.setClub(club);
        clubRole.setUser_role(userRole);
        clubRole = clubRolesRepository.save(clubRole);


        return clubRole;
    }

    public ClubRole deassignClubRole(User user, Club club) {
        ArrayList<Integer> DeletingClubId = new ArrayList<>();

        for (UserRole userRole : user.getRoles()) {
            if (userRole.getClub_id() == club.getId()) {
                DeletingClubId.add(userRole.getClub_id());
            }
        }

        for (Integer integer : DeletingClubId) {
            clubRolesRepository.deleteById(integer);
            userRoleRepository.deleteById(integer);
        }
        
        return null;
    }

    public Collection<ClubRole> getClubRoles(Club club) {
        return clubRolesRepository.findAllByClub_id(club.getId());
    }
}

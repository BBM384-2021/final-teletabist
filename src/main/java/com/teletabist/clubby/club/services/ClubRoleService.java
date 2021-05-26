package com.teletabist.clubby.club.services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRole;
import com.teletabist.clubby.club.models.ClubRoleRepository;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRepository;
import com.teletabist.clubby.user.models.UserRole;
import com.teletabist.clubby.user.models.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubRoleService {
    private UserRoleRepository userRoleRepository;
    private ClubRoleRepository clubRolesRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubRoleService(UserRoleRepository userRoleRepository, ClubRoleRepository clubRolesRepository, UserRepository userRepository) {
        this.userRoleRepository = userRoleRepository;
        this.clubRolesRepository = clubRolesRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public Integer deassignClubRole(User user, Club club) {

        
        ArrayList<UserRole> DeletingRoles = new ArrayList<>();

        for (UserRole userRole : user.getRoles()) {
            if (userRole.getClub_id() == club.getId()) {
                DeletingRoles.add(userRole);
            }
        }

        for (UserRole userRole : DeletingRoles) {
            user.getRoles().remove(userRole);
            userRepository.save(user);
            clubRolesRepository.deleteById(userRole.getId());
            userRoleRepository.delete(userRole);
        }

        return DeletingRoles.size();
        
    }

    @Transactional
    public Integer deassignClubRole(User user) {
        ArrayList<UserRole> DeletingRoles = new ArrayList<>();

        for (UserRole userRole : user.getRoles()) {
            DeletingRoles.add(userRole);
        }

        for (UserRole userRole : DeletingRoles) {
            user.getRoles().remove(userRole);
            userRepository.save(user);
            clubRolesRepository.deleteById(userRole.getId());
            userRoleRepository.delete(userRole);
        }

        return DeletingRoles.size();
    }

    @Transactional
    public Integer deassignClubRole(Club club) {
        Collection<ClubRole> clubRoles = clubRolesRepository.findAllByClub_id(club.getId());

        for (ClubRole role : clubRoles) {

            UserRole userRole = userRoleRepository.getOne(role.getId());
            User user = userRole.getUser();

            user.getRoles().remove(userRole);
            userRepository.save(user);
            clubRolesRepository.delete(role);
            userRoleRepository.delete(userRole);
        }

        
        return clubRoles.size();
    }

    public Collection<ClubRole> getClubRoles(Club club) {
        return clubRolesRepository.findAllByClub_id(club.getId());
    }
}

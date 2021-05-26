package com.teletabist.clubby.club.services;

import java.sql.Timestamp;
import java.util.Collection;

import javax.transaction.Transactional;

import com.teletabist.clubby.club.models.Ban;
import com.teletabist.clubby.club.models.BanRepository;
import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.SubClubAdminBlacklistNode;
import com.teletabist.clubby.club.models.SubClubAdminBlacklistNodeRepository;
import com.teletabist.clubby.club.models.SubClubBlacklistNode;
import com.teletabist.clubby.club.models.SubClubBlacklistNodeRepository;
import com.teletabist.clubby.user.core.Roles;
import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRole;

import org.springframework.stereotype.Service;

@Service
public class BanService {
    private final BanRepository banRepository;
    SubClubBlacklistNodeRepository subClubBlacklistNodeRepository;
    SubClubAdminBlacklistNodeRepository subClubAdminBlacklistNodeRepository;

    public BanService(BanRepository banRepository, SubClubBlacklistNodeRepository subClubBlacklistNodeRepository, SubClubAdminBlacklistNodeRepository subClubAdminBlacklistNodeRepository) {
        this.banRepository = banRepository;
        this.subClubBlacklistNodeRepository = subClubBlacklistNodeRepository;
        this.subClubAdminBlacklistNodeRepository = subClubAdminBlacklistNodeRepository;
    }

    public Ban createBan(User bannedUser, User banningUser, Club club) {

        UserRole bannedUserRole = getBannedUserRole(bannedUser, club);;
        
        if (bannedUserRole == null) {
            return null;
        }

        UserRole banningUserRole = getBanningUserRole(banningUser, club, bannedUserRole);

        if (banningUserRole != null) {
            if (banRepository.findAllByUserRole(bannedUserRole).size() == 2) {
                addToBlacklist(bannedUser, club);
            }

            if (bannedUserRole.getRole().equals(Roles.SUB_CLUB_ADMIN.getName())) {
                addToAdminBlacklist(bannedUser);
            }
            Ban ban = new Ban();
            ban.setUserRole(bannedUserRole);
            ban.setBannedByUserRole(banningUserRole);
            return banRepository.save(ban);
        }
        return null;
    }

    private UserRole getBannedUserRole(User bannedUser, Club club) {

        UserRole bannedUserRole = null;
        for (UserRole userRole : bannedUser.getRoles()) {

            if (userRole.getRole().equals(Roles.SYS_ADMIN.getName())
            || userRole.getRole().equals(Roles.ADMIN.getName())) {

                return null;
            } else if (userRole.getRole().equals(Roles.SUB_CLUB_ADMIN.getName()) && userRole.getClub_id() == club.getId()) {

                bannedUserRole = userRole;
                break;
            } else if (userRole.getRole().equals(Roles.MEMBER.getName()) && userRole.getClub_id() == club.getId()) {

                bannedUserRole = userRole;
            }
        }
        return bannedUserRole;
    }

    private UserRole getBanningUserRole(User banningUser, Club club, UserRole bannedUserRole) {
        for (UserRole authUserRole : banningUser.getRoles()) {
            if (authUserRole.getRole().equals(Roles.SYS_ADMIN.getName())
                || authUserRole.getRole().equals(Roles.ADMIN.getName())
                || (authUserRole.getRole().equals(Roles.SUB_CLUB_ADMIN.getName()) 
                    && authUserRole.getClub_id() == club.getId() 
                    && bannedUserRole.getRole().equals(Roles.MEMBER.getName())))
            {
                return authUserRole;
            }
        }

        return null;
    }

    public Boolean isBanned(User user, Club club) {

        for (UserRole userRole : user.getRoles()) {
            if (userRole.getClub_id() == club.getId()) {
                Collection<Ban> bans = banRepository.findAllByUserRole(userRole);
                for (Ban ban : bans) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    if (ban.getEndAt().compareTo(timestamp) > 0) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    public SubClubBlacklistNode addToBlacklist(User user, Club club) {
        SubClubBlacklistNode node = new SubClubBlacklistNode();
        node.setUser(user);
        node.setClub(club);
        return subClubBlacklistNodeRepository.save(node);
    }

    public SubClubBlacklistNode isBlacklisted(User user, Club club) {
        return subClubBlacklistNodeRepository.findByUserAndClub(user, club);
    }

    public SubClubAdminBlacklistNode addToAdminBlacklist(User user) {
        SubClubAdminBlacklistNode node = new SubClubAdminBlacklistNode();
        node.setUser(user);
        return subClubAdminBlacklistNodeRepository.save(node);
    }

    public SubClubAdminBlacklistNode isAdminBlacklisted(User user) {
        return subClubAdminBlacklistNodeRepository.findByUser(user);
    }

    @Transactional
    public Integer deleteAllBans(UserRole userRole) {
        return banRepository.deleteAllByUserRole(userRole);
    }
}

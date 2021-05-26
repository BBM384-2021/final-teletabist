package com.teletabist.clubby.club.services;

import com.teletabist.clubby.club.models.Ban;
import com.teletabist.clubby.club.models.BanRepository;
import com.teletabist.clubby.user.models.UserRole;

import org.springframework.stereotype.Service;

@Service
public class BanService {
    private final BanRepository banRepository;

    public BanService(BanRepository banRepository) {
        this.banRepository = banRepository;
    }

    public Ban createBan(UserRole bannedUserRole, UserRole banningUserRole) {
        Ban ban = new Ban();
        ban.setUserRole(bannedUserRole);
        ban.setBannedByUserRole(banningUserRole);
        return banRepository.save(ban);
    }
}

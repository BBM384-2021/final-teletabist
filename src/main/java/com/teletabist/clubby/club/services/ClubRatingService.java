package com.teletabist.clubby.club.services;


import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubRating;
import com.teletabist.clubby.club.models.ClubRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClubRatingService {
    private final ClubRatingRepository clubRatingRepository;
    //private final ClubService clubService;

    @Autowired
    public ClubRatingService(ClubRatingRepository clubRatingRepository, ClubService clubService) {
        this.clubRatingRepository = clubRatingRepository;
        //this.clubService = clubService;
    }

    public ClubRating getClubRating(Club club){
        ClubRating cr = null;
        if(club != null){
            cr = this.clubRatingRepository.findByClub_id(club.getId());
            if (cr == null) {
                cr = new ClubRating();
                cr.setClub(club);
                cr = this.clubRatingRepository.save(cr);
            }
        }
        return cr;
    }

    public ClubRating incrementRating(Club club, Boolean rating) {
        ClubRating clubRating = this.clubRatingRepository.findByClub_id(club.getId());

        if (clubRating == null) {
            clubRating = new ClubRating();
            clubRating.setClub(club);
        }

        if (rating) clubRating.addLike();
        else clubRating.addDislike();

        return this.clubRatingRepository.save(clubRating);
    }

    public ClubRating decrementRating(Club club, Boolean rating) {
        ClubRating clubRating = this.clubRatingRepository.findByClub_id(club.getId());

        if (clubRating == null) {
            clubRating = new ClubRating();
            clubRating.setClub(club);
        }
        else {
            if (rating) clubRating.deleteLike();
            else clubRating.deleteDislike();
        }

        return this.clubRatingRepository.save(clubRating);
    }
}

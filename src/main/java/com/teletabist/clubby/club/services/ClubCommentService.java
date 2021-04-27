package com.teletabist.clubby.club.services;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.club.models.ClubComment;
import com.teletabist.clubby.club.models.ClubCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ClubCommentService {
    private final ClubCommentRepository clubCommentRepository;
    private final ClubService clubService;
    private final ClubRatingService clubRatingService;

    @Autowired
    public ClubCommentService(ClubService clubService, ClubCommentRepository clubCommentRepository, ClubRatingService clubRatingService) {
        this.clubCommentRepository = clubCommentRepository;
        this.clubService = clubService;
        this.clubRatingService = clubRatingService;
    }

    //APIController getAllClubComments()
    public Iterable<ClubComment> getAllComments(String slug) {
        Club club = this.clubService.getClub(slug);
        return clubCommentRepository.findAllByClub_id(club.getId(), Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public ClubComment getComment(String slug, Integer id) {
        Club club = this.clubService.getClub(slug);
        return clubCommentRepository.findByClub_idAndId(club.getId(), id);
    }

    //APIController createClubComment()
    public ClubComment clubCommentCreator(String slug, ClubComment clubComment) {
        Club club = this.clubService.getClub(slug);
        clubComment.setClub(club);
        clubComment = clubCommentRepository.save(clubComment);
        clubRatingService.incrementRating(club, clubComment.isLiked());
        return clubComment;
    }

    //APIController updateClubComment()
    //TODO: Add userRole after implementation / liking process will be revised
    public ClubComment updateComment(Integer id, ClubComment clubCommentRequest) {
        return clubCommentRepository.findById(id).map(clubComment -> {
            clubComment.setComment(clubCommentRequest.getComment());
            //clubComment.setLiked(clubCommentRequest.isLiked());
            clubComment.setUpdated_at(clubCommentRequest.getUpdated_at());
            clubComment.setVisible(clubCommentRequest.isVisible());
            return clubCommentRepository.save(clubComment);
        }).orElse(null);
    }

    //APIController deleteClubComment()
    public Boolean deleteComment(Integer id) {
        return clubCommentRepository.findById(id).map(clubComment -> {
            clubCommentRepository.delete(clubComment);
            this.clubRatingService.decrementRating(clubComment.getClub(), clubComment.isLiked());
            return true;
        }).orElse(false);
    }
}
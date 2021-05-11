package com.teletabist.clubby.club.services;

import com.teletabist.clubby.club.models.Club;
//import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.club.models.Discussion;
import com.teletabist.clubby.club.models.DiscussionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Sort;

@Service
public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final ClubService clubService;
    

    @Autowired
    public DiscussionService(ClubService clubService, DiscussionRepository discussionRepository) {
        this.discussionRepository = discussionRepository;
        this.clubService = clubService;
    }
    
    public Iterable<Discussion> getAllDiscussions(String slug) {
        Club club = this.clubService.getClub(slug);
        return discussionRepository.findAllByUser_id(club.getId(), Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Discussion getMessage(String slug, Integer id) {
        Club club = this.clubService.getClub(slug);
        return discussionRepository.findByClub_idAndId(club.getId(), id);
    }

    public Discussion discussionCreator(String slug, Discussion discussion) {
        Club club = this.clubService.getClub(slug);
        discussion.setClub(club);
        discussion = discussionRepository.save(discussion);
        return discussion;
    }

    public Boolean deleteDiscussion(Integer id) {
        return discussionRepository.findById(id).map(discussion -> {
            discussionRepository.delete(discussion);
            return true;
        }).orElse(false);
    }
}
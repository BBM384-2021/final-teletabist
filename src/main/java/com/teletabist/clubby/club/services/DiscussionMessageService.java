package com.teletabist.clubby.club.services;


import com.teletabist.clubby.club.daos.DiscussionMessageDAO;
import com.teletabist.clubby.club.models.DiscussionMessage;

public class DiscussionMessageService {
    //private class variables
    private final DiscussionMessageDAO discussionMessageDAO;


    //default constructor (any needed constructor can be added later)
    public DiscussionMessageService(DiscussionMessageDAO discussionMessageDAO) {
        this.discussionMessageDAO = discussionMessageDAO;
    }

    //gets the discussionMessage object and creates new discussionMessage
    public DiscussionMessage createDiscussionMessage(DiscussionMessage discussionMessage) {
        return discussionMessageDAO.createDiscussionMessage(discussionMessage);
    }

    //gets the discussionMessage object and updates the existing discussionMessage's message data by discussionMessage object's message data
    public DiscussionMessage updateDiscussionMessage(DiscussionMessage discussionMessage) {
        return discussionMessageDAO.updateDiscussionMessage(discussionMessage.getId(), discussionMessage.getMessage());
    }

    //gets the discussionMessage object, finds the existing discussionMessage and deletes it
    public boolean deleteDiscussionMessage(DiscussionMessage discussionMessage) {
        return discussionMessageDAO.deleteDiscussionMessage(discussionMessage.getId());
    }
}
package com.teletabist.clubby.club.models;

import java.util.Date;

public class DiscussionMessage {
    //private class variables
    private final int id;
    private final int userId;
    private final int clubId;
    private String message;
    private final java.util.Date createdAt;
    private final java.util.Date updatedAt;


    //default constructor (any needed constructor can be added later)
    public DiscussionMessage(int id, int userId, int clubId, String message, Date createdAt, Date updatedAt) {
        this.id = id;
        this.userId = userId;
        this.clubId = clubId;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //id changing constructor
    public DiscussionMessage(int id, DiscussionMessage discussionMessage) {
        this.id = id;
        this.userId = discussionMessage.userId;
        this.clubId = discussionMessage.clubId;
        this.message = discussionMessage.message;
        this.createdAt = discussionMessage.createdAt;
        this.updatedAt = discussionMessage.updatedAt;
    }

    //getters for private class variables
    public int getId() {
        return id;
    }
    public int getUserId() {
        return userId;
    }
    public int getClubId() {
        return clubId;
    }
    public String getMessage() {
        return message;
    }
    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    //temporary message data updater TODO:create real updater when database is ready.
    public boolean updateMessage(String newMessage) {
        if (newMessage != null) {
            this.message = newMessage;
            return true;
        }
        return false;
    }
}
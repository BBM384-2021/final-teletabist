package com.teletabist.clubby.club.daos;

import com.teletabist.clubby.club.models.DiscussionMessage;

public interface DiscussionMessageDAO {
    DiscussionMessage createDiscussionMessage(DiscussionMessage discussionMessage);
    DiscussionMessage getDiscussionMessage(int discussionMessageId);
    DiscussionMessage updateDiscussionMessage(int discussionMessageId, String newMessage);
    boolean deleteDiscussionMessage(int discussionMessageId);
}
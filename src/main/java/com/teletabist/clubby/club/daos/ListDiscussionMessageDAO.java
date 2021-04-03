package com.teletabist.clubby.club.daos;

import java.util.List;
import com.teletabist.clubby.club.models.DiscussionMessage;

//TODO: implement all class according to database when the real database is ready.
public class ListDiscussionMessageDAO implements DiscussionMessageDAO {
    //fake database (temporary)
    List<DiscussionMessage> discussionMessageList;

    //gives new id to the discussionMessage object and creates it in the List
    @Override
    public DiscussionMessage createDiscussionMessage(DiscussionMessage discussionMessage) {
        DiscussionMessage newDiscussionMessage = new DiscussionMessage(discussionMessageList.size(), discussionMessage);
        discussionMessageList.add(discussionMessage);
        return newDiscussionMessage;
    }

    //find and get the discussionMessage by discussionMessageId
    @Override
    public DiscussionMessage getDiscussionMessage(int discussionMessageId) {
        for (DiscussionMessage discussionMessage : discussionMessageList) {
            if (discussionMessage.getId() == discussionMessageId) return discussionMessage;
        }
        return null;
    }

    //change the specified discussionMessage's message by newMessage.
    @Override
    public DiscussionMessage updateDiscussionMessage(int discussionMessageId, String newMessage) {
        DiscussionMessage discussionMessage = getDiscussionMessage(discussionMessageId);
        if (discussionMessage != null) discussionMessage.updateMessage(newMessage);
        return discussionMessage;
    }

    //delete specified discussionMessage. if deleted return true, otherwise return false
    @Override
    public boolean deleteDiscussionMessage(int discussionMessageId) {
        return discussionMessageList.removeIf(discussionMessage -> discussionMessage.getId() == discussionMessageId);
    }
}
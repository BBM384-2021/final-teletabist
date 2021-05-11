package com.teletabist.clubby.club.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DiscussionDTO {
    @NotNull
    @NotEmpty
    @Size(max = 512)
    private String discussion;


    public String getMessage() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

}
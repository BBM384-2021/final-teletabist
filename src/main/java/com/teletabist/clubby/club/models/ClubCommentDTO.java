package com.teletabist.clubby.club.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClubCommentDTO {
    @NotNull
    @NotEmpty
    @Size(max = 512)
    private String comment;

    @NotNull
    private Integer liked;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer isLiked() {
        return liked;
    }

    public void setLiked(Integer liked) {
        this.liked = liked;
    }
}

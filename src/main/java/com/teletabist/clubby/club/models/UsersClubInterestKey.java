package com.teletabist.clubby.club.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class UsersClubInterestKey implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "club_id")
    private Integer clubID;

    public UsersClubInterestKey() {

    }

    public UsersClubInterestKey(int userID, int clubID) {
        this.userID = userID;
        this.clubID = clubID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getClubID() {
        return clubID;
    }

    public void setClubID(Integer clubID) {
        this.clubID = clubID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clubID == null) ? 0 : clubID.hashCode());
        result = prime * result + ((userID == null) ? 0 : userID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UsersClubInterestKey other = (UsersClubInterestKey) obj;
        if (clubID == null) {
            if (other.clubID != null)
                return false;
        } else if (!clubID.equals(other.clubID))
            return false;
        if (userID == null) {
            if (other.userID != null)
                return false;
        } else if (!userID.equals(other.userID))
            return false;
        return true;
    }


}
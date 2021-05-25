package com.teletabist.clubby.search.models;

import java.util.Collection;

import com.teletabist.clubby.club.models.Club;
import com.teletabist.clubby.user.models.Profile;

public class SearchResultDTO {
    public String searchKey = "";
    public Collection<Club> clubs;
    public Collection<Profile> users;
}
package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class SurveyClubSelectionDTO {
    @NotEmpty
    public List<Integer> club_ids;

    public List<Integer> getClub_ids() {
        return club_ids;
    }

    public void setClub_ids(List<Integer> club_ids) {
        this.club_ids = club_ids;
    }

}
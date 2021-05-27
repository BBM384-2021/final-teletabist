package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AnswerDTO {
    private Integer value;
    private String text;
    private boolean markDelete = false;

    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public static Collection<AnswerDTO> getAll(Collection<Answer> ans){
        ArrayList<AnswerDTO> a = new ArrayList<AnswerDTO>();
        for (Answer answer : ans) {
            a.add(new AnswerDTO(answer));
        }
        return a;
    }

    public AnswerDTO(){}

    public AnswerDTO(Answer a){
        this.value = a.getValue();
        this.text = a.getText();
        this.markDelete = false;
    }

    @Override
    public String toString() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(this);
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isMarkDelete() {
        return markDelete;
    }

    public void setMarkDelete(boolean markDelete) {
        this.markDelete = markDelete;
    }
}
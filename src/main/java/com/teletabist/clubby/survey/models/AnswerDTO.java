package com.teletabist.clubby.survey.models;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AnswerDTO {
    private Integer value;
    private String text;
    private Boolean markDelete = false;

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

    public Answer toAnswer(){
        Answer a = new Answer();
        a.setText(this.text);
        a.setValue(this.value);
        return a;
    }

    public static ArrayList<AnswerDTO> getAll(Collection<Answer> ans){
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

    public Boolean getMarkDelete() {
        return markDelete;
    }

    public void setMarkDelete(Boolean markDelete) {
        this.markDelete = markDelete;
    }
}
package com.teletabist.clubby.survey.models;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Answer {
    private Integer value;
    private String text;

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

    @Override
    public String toString() {
        ObjectMapper m = new ObjectMapper();
        try {
            return m.writeValueAsString(this);
        } catch (Exception e) {
            return "";
        }
    }
}

package com.teletabist.clubby.club.models;

public class Club {
    private final int id;
    private final String slug;
    private final String name;
    
    public Club(int id, String slug, String name) {
        this.id = id;
        this.slug = slug;
        this.name = name;
    }
    
}

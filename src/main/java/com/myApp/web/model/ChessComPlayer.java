package com.myApp.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChessComPlayer {
    private String username;
    private int rating;
    private int id;

    public ChessComPlayer(int id,String username, int rating) {
        this.id = id;
        this.username = username;
        this.rating = rating;
    }
}

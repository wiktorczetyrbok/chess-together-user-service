package com.myApp.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChessComPlayer {
    private String username;
    private String rating;
    private String photoUrl;

    public ChessComPlayer(String photoUrl, String username, String rating) {
        this.photoUrl = photoUrl;
        this.username = username;
        this.rating = rating;
    }
}

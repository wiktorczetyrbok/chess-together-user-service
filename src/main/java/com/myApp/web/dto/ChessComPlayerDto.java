package com.myApp.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChessComPlayerDto {
    private String username;
    private String rating;
    private String photoUrl;

    public ChessComPlayerDto(String photoUrl, String username, String rating) {
        this.photoUrl = photoUrl;
        this.username = username;
        this.rating = rating;
    }
}

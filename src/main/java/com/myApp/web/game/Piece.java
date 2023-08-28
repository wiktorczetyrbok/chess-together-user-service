package com.myApp.web.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Piece {
    private String owner;
    private String type;
    private boolean royal;
    private boolean hasMoved = false;

    //constructor for promotion
    public Piece(String owner, String type, boolean b) {
    }
}
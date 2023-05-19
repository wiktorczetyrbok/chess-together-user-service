package com.myApp.web.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {
    private String activePlayer;
    private String status;
    private List<Square> squares;
    private List<Move> moves;
    private List<String> promotionPieces;
}


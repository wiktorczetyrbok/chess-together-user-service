package com.myApp.web.game;

import com.myApp.web.game.utils.BoardStatus;
import com.myApp.web.game.utils.Player;
import com.myApp.web.game.utils.Type;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Board {
    private Player activePlayer;
    private BoardStatus status;
    private List<Square> squares;
    private List<Move> moves;
    private List<Type> promotionPieces;
}


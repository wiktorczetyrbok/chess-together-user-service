package com.myApp.web.game;

import com.myApp.web.game.utils.Player;
import com.myApp.web.game.utils.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Piece {
    private Player owner;
    private Type type;
    private boolean royal;
    private boolean hasMoved;
}
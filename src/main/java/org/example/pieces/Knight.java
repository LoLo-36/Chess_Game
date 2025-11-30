package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

public class Knight extends Piece {
    public Knight(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    public Knight(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
    }

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {

    }
}

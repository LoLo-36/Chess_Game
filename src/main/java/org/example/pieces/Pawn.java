package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    public Pawn(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {

    }
}

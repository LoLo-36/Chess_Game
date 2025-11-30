package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

public class Queen extends Piece {
    public Queen(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    public Queen(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {

    }
}

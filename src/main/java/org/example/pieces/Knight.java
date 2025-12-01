package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;

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
        if (this.getCoordinatesX() == x || this.getCoordinatesY() == y) {
            return false;
        }

        if (Math.abs(this.getCoordinatesX() - x) == 1 && Math.abs(this.getCoordinatesY() - y) == 2
                || Math.abs(this.getCoordinatesX() - x) == 2 && Math.abs(this.getCoordinatesY() - y) == 1) {
            return board.getPieceAt(x, y) == null || this.getColor() != board.getPieceAt(x, y).getColor();
        }

        return false;
    }
}

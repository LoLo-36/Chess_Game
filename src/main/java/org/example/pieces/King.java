package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

public class King extends Piece {
    public King(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    public King(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {
        if ((Math.abs(this.getCoordinatesX() - x) > 1
                || Math.abs(this.getCoordinatesY() - y) > 1)
                || (this.getCoordinatesX() == x
                && this.getCoordinatesY() == y)) {
            return false;
        }

        Piece target = board.getPieceAt(x, y);
        int currX = this.getCoordinatesX();
        int currY = this.getCoordinatesY();

        if (Math.abs(currX - x) <= 1
                && Math.abs(currY - y) <= 1) {
            if (target != null) {
                return this.getColor() != target.getColor();
            } else {
                return true;
            }
        }

        return false;
    }
}

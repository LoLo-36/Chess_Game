package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    public Bishop(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {
        if (Math.abs(this.getCoordinatesX() - x) != Math.abs(this.getCoordinatesY() - y)
                || this.getCoordinatesX() == x || this.getCoordinatesY() == y) {
            return false;
        }

        List<Piece> pieces = board.getPieces();
        int currX = this.getCoordinatesX();
        int currY = this.getCoordinatesY();

        for (Piece piece : pieces) {
            if (piece == null || piece == this
                    || Math.abs(currX - piece.getCoordinatesX()) != Math.abs(currY - piece.getCoordinatesY())
                    || currX == piece.getCoordinatesX() || currY == piece.getCoordinatesY()) {
                continue;
            }

            int px = piece.getCoordinatesX();

            if (x - currX > 0 && px - currX > 0
                    && px - currX < x - currX) {
                    return false;
            }

            if (x - currX < 0 && px - currX < 0
                    && px - currX > x - currX) {
                return false;
            }
        }

        return true;
    }
}

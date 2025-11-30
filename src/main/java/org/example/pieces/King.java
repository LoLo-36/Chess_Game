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
        if (Math.abs(this.getCoordinatesX() - x) > 1
                || Math.abs(this.getCoordinatesY() - y) > 1) {
            return false;
        }

        List<Piece> pieces = board.getPieces();
        int currX = this.getCoordinatesX();
        int currY = this.getCoordinatesY();

        for (Piece piece : pieces) {
            if (piece == null || piece == this) {
                continue;
            }

            int px = piece.getCoordinatesX();
            int py = piece.getCoordinatesY();

            if (Math.abs(currX - px) <= 1
                    && Math.abs(currY - py) <= 1) {
                return !this.getColor().equals(piece.getColor());
            }
        }

        return true;
    }
}

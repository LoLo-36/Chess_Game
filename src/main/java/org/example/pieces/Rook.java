package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

/**
 * This class define Rook.
 *      Constructor,
 *      Symbol,
 *      move logical.
 */
public class Rook extends Piece {
    public Rook(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
    }

    public Rook(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    /**
     * Checks whether the Rook can legally move to the target square (x, y).
     *
     * @param board the chess board containing all pieces
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return true if the Rook can move to (x, y), false otherwise
     */
    @Override
    public boolean canMove(Board board, int x, int y) {
        if ((this.getCoordinatesX() != x
                && this.getCoordinatesY() != y)
                ||(this.getCoordinatesX() == x
                && this.getCoordinatesY() == y)) {
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

            if ((currY == y && currY == py)
                && (px > Math.min(currX, x)
                    && px < Math.max(currX, x))) {
                    return false;
            }

            if ((currX == x && px == currX)
                && (py > Math.min(currY, y)
                    && py < Math.max(currY, y))) {
                    return false;
            }

            if (px == x && py == y) {
                return piece.getColor() != this.getColor();
            }
        }

        return true;
    }
}

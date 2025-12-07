package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

/**
 * This class define Bishop.
 *      Constructor,
 *      Symbol,
 *      move logical.
 */
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

    /**
     * Checks whether the Bishop can legally move to the target square (x, y).
     * <p>
     * A Bishop moves diagonally, so the target square must lie on the same
     * diagonal as the Bishop's current position. The path to the target square
     * must also be unobstructed by any other pieces.
     * </p>
     *
     * @param board the chess board containing all pieces
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return true if the Bishop can move to (x, y), false otherwise
     */
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

package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;

/**
 * This class define Knight.
 *      Constructor,
 *      Symbol,
 *      move logical.
 */
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

    /**
     * Checks whether the Knight can legally move to the target square (x, y).
     *
     * @param board the chess board containing all pieces
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return true if the Knight can move to (x, y), false otherwise
     */
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

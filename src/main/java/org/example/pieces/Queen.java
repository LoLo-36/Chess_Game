package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

/**
 * This class define Queen.
 *      Constructor,
 *      Symbol,
 *      move logical.
 */
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

    /**
     * Checks whether the Queen can legally move to the target square (x, y).
     *
     * @param board the chess board containing all pieces
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return true if the Queen can move to (x, y), false otherwise
     */
    @Override
    public boolean canMove(Board board, int x, int y) {
        Rook rook = new Rook(this.getCoordinatesX(), this.getCoordinatesY(), this.getColor());
        Bishop bishop = new Bishop(this.getCoordinatesX(), this.getCoordinatesY(), this.getColor());

        return rook.canMove(board, x, y) || bishop.canMove(board, x, y);
    }
}

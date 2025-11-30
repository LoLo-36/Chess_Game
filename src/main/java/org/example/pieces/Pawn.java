package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        firstMove = true;
    }

    public Pawn(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
        firstMove = true;
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {
        if (this.getCoordinatesX() == x
                && this.getCoordinatesY() == y) {
            return false;
        }

        int currX = getCoordinatesX();
        int currY = getCoordinatesY();
        int direction = (getColor() == Color.WHITE) ? 1 : -1;

        if (x == currX && y == currY + direction) {
            return board.getPieceAt(x, y) == null;
        }

        if (firstMove && x == currX && y == currY + 2 * direction) {
            firstMove = false;
            return board.getPieceAt(currX, currY + direction) == null
                    && board.getPieceAt(x, y) == null;
        }

        if (Math.abs(x - currX) == 1 && y == currY + direction) {
            Piece target = board.getPieceAt(x, y);
            return target != null && target.getColor() != this.getColor();
        }

        if (firstMove) {
            firstMove = false;
        }

        return false;
    }
}

package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstMove;
    private boolean promote;

    public Pawn(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        firstMove = true;
        promote = false;
    }

    public Pawn(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
        firstMove = true;
        promote = false;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public void setPromote(boolean promote) {
        this.promote = promote;
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
            return board.getPieceAt(currX, currY + direction) == null
                    && board.getPieceAt(x, y) == null;
        }

        if (Math.abs(x - currX) == 1 && y == currY + direction) {
            Piece target = board.getPieceAt(x, y);
            return target != null && target.getColor() != this.getColor();
        }

        return false;
    }
}

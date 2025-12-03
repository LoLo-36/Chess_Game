package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

public class King extends Piece {
    private boolean isCheck;
    private boolean canCastlingLeft;
    private boolean canCastlingRight;

    public King(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        isCheck = false;
        canCastlingLeft = false;
        canCastlingRight = false;
    }

    public King(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
        isCheck = false;
        canCastlingLeft = false;
        canCastlingRight = false;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public boolean canCastlingRight() {
        return canCastlingRight;
    }

    public void setCanCastlingRight(boolean canCastlingRight) {
        this.canCastlingRight = canCastlingRight;
    }

    public boolean canCastlingLeft() {
        return canCastlingLeft;
    }

    public void setCanCastlingLeft(boolean canCastlingLeft) {
        this.canCastlingLeft = canCastlingLeft;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean canMove(Board board, int x, int y) {
        if (!firstMove && (Math.abs(this.getCoordinatesX() - x) > 1
                || Math.abs(this.getCoordinatesY() - y) > 1
                || (this.getCoordinatesX() == x
                && this.getCoordinatesY() == y))) {
            return false;
        }

        Piece target = board.getPieceAt(x, y);
        int currX = this.getCoordinatesX();
        int currY = this.getCoordinatesY();

        if (firstMove && target instanceof Rook rook
                && target.isFirstMove() && target.getColor() == this.getColor()) {
            if (x > currX && board.getPieceAt(currX + 1, currY) == null
                    && rook.canMove(board, currX + 1, currY)) {
                this.canCastlingRight = true;
                return true;
            }

            if (x < currX && board.getPieceAt(currX - 1, currY) == null
                    && rook.canMove(board, currX - 1, currY)) {
                this.canCastlingLeft = true;
                return true;
            }
        }

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

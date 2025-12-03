package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

public class King extends Piece {
    private boolean isCheck;
    private boolean firstMove;

    public King(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        isCheck = false;
        firstMove = true;
    }

    public King(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
        isCheck = false;
        firstMove = true;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
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

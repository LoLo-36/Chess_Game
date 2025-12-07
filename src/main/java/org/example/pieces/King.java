package org.example.pieces;

import org.example.entities.Board;
import org.example.entities.Piece;

import java.awt.*;
import java.util.List;

/**
 * This class define King.
 *      Constructor,
 *      Symbol,
 *      move logical.
 */
public class King extends Piece {
    private boolean isCheck;

    public King(int coordinatesX, int coordinatesY) {
        super(coordinatesX, coordinatesY);
        isCheck = false;
    }

    public King(int coordinatesX, int coordinatesY, Color color) {
        super(coordinatesX, coordinatesY, color);
        isCheck = false;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    /**
     * Checks whether the King can legally move to the target square (x, y).
     *
     * @param board the chess board containing all pieces
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return true if the King can move to (x, y), false otherwise
     */
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
            Color enemyColor = (this.getColor() == Color.WHITE) ? Color.BLACK : Color.WHITE;
            if (board.isSquareUnderAttack(currX, currY, enemyColor)) {
                return false;
            }

            if (x > currX) {
                if (board.getPieceAt(currX + 1, currY) == null
                        && rook.canMove(board, currX + 1, currY)
                        && !board.isSquareUnderAttack(currX + 1, currY, enemyColor)
                        && !board.isSquareUnderAttack(currX + 2, currY, enemyColor)) {
                    return true;
                }
            }

            if (x < currX) {
                if (board.getPieceAt(currX - 1, currY) == null
                        && rook.canMove(board, currX - 1, currY)
                        && !board.isSquareUnderAttack(currX - 1, currY, enemyColor)
                        && !board.isSquareUnderAttack(currX - 2, currY, enemyColor)) {
                    return true;
                }
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

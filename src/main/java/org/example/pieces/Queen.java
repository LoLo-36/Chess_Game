package org.example.pieces;

import org.example.game.Board;
import org.example.game.Piece;

import java.awt.*;
import java.util.List;

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

    @Override
    public boolean canMove(Board board, int x, int y) {
        Rook rook = new Rook(this.getCoordinatesX(), this.getCoordinatesY(), this.getColor());
        Bishop bishop = new Bishop(this.getCoordinatesX(), this.getCoordinatesY(), this.getColor());

        return rook.canMove(board, x, y) || bishop.canMove(board, x, y);
    }
}

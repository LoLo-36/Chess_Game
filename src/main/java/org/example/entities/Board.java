package org.example.entities;

import org.example.pieces.Pawn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private ArrayList<Piece> pieces;

    public Board() {
        this.pieces = new ArrayList<>();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setPiece(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Piece getPieceAt(int x, int y) {
        for (Piece piece : pieces) {
            if (x == piece.getCoordinatesX()
                    && y == piece.getCoordinatesY()) {
                return piece;
            }
        }

        return null;
    }

    public void removePieceAt(int x, int y) {
        pieces.removeIf(piece ->
                    piece.getCoordinatesX() == x
                 && piece.getCoordinatesY() == y
        );
    }

    public void addPiece(Piece piece) {
        for (Piece p : pieces) {
            if (piece.checkPosition(p)) {
                return;
            }
        }

        pieces.add(piece);
    }

    public boolean validate(int x, int y) {
        return 1 <= x && x <= WIDTH
                && 1 <= y && y <= HEIGHT;
    }

    public boolean isSquareUnderAttack(int x, int y, Color opponentColor) {
        for (Piece piece : pieces) {
            if (piece.getColor() != opponentColor) continue;

            if (piece instanceof Pawn) {
                int direction = (piece.getColor() == Color.WHITE) ? 1 : -1;
                if (piece.getCoordinatesY() + direction == y &&
                        (piece.getCoordinatesX() - 1 == x || piece.getCoordinatesX() + 1 == x)) {
                    return true;
                }
                continue;
            }

            if (piece.canMove(this, x, y)) {
                return true;
            }
        }
        return false;
    }
}

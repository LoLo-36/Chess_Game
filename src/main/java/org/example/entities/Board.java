package org.example.entities;

import org.example.pieces.Pawn;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class initializes the chessboard object:
 *      A board is a square with size is 8x8 and has List of Piece
 * And defines operations on the chessboard.
 *      get Piece on square,
 *      remove Piece on coordinates,
 *      add Piece into chess board,
 *      check the square if it is under attack.
 */
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

    /**
     * Get Piece on square.
     *
     * @param x coordinates X of square
     * @param y coordinates Y of square
     * @return a piece if square have piece and null if square is empty.
     */
    public Piece getPieceAt(int x, int y) {
        for (Piece piece : pieces) {
            if (x == piece.getCoordinatesX()
                    && y == piece.getCoordinatesY()) {
                return piece;
            }
        }

        return null;
    }

    /**
     * Remove Piece on coordinates.
     *
     * @param x coordinates X of square
     * @param y coordinates Y of square
     */
    public void removePieceAt(int x, int y) {
        pieces.removeIf(piece ->
                    piece.getCoordinatesX() == x
                 && piece.getCoordinatesY() == y
        );
    }

    /**
     * Add Piece into chess board.
     *      if the square already has a piece then do nothing.
     *
     * @param piece the Piece that want to add
     */
    public void addPiece(Piece piece) {
        for (Piece p : pieces) {
            if (piece.checkPosition(p)) {
                return;
            }
        }

        pieces.add(piece);
    }

    /**
     * Check the coordinates if it's in the board.
     *
     * @param x coordinates X of square
     * @param y coordinates Y of square
     * @return true if it is in the board
     */
    public boolean validate(int x, int y) {
        return 1 <= x && x <= WIDTH
                && 1 <= y && y <= HEIGHT;
    }

    /**
     * Check the square if it is under attack.
     *      check all the pieces of opponent player on the chessboard to see if any one of them can attack this square
     *
     * @param x coordinates X of square
     * @param y coordinates Y of square
     * @param opponentColor opponent color
     * @return true if it has at least one opponent piece that can attack that square
     */
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

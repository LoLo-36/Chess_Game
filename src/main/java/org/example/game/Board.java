package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int WIDTH = 8;
    private static final int HEIGHT = 8;
    private ArrayList<Piece> pieces;
    private static Board instance;

    private Board() {
        this.pieces = new ArrayList<>();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }

        return instance;
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
}

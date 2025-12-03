package org.example.entities;

import org.example.game.*;
import org.example.pieces.King;
import org.example.pieces.Pawn;
import org.example.pieces.Rook;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private Color color;
    private boolean inTurn;
    private boolean isWinner;
    private List<Move> moveHistory;

    public Player() {
        this.name = "";
        this.color = Color.WHITE;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        this.color = Color.WHITE;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new ArrayList<>();
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new ArrayList<>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Move> getMoves() {
        return moveHistory;
    }

    public void setMoves(List<Move> moves) {
        this.moveHistory = moves;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public boolean isInTurn() {
        return inTurn;
    }
    public void setInTurn(boolean inTurn) {
        this.inTurn = inTurn;
    }

    public Move movePiece(Board board, Piece piece, int x, int y) {
        if (!board.validate(x, y)
                || piece.getColor() != this.color
                || !piece.canMove(board, x, y)) {
            return null;
        }

        Piece killed = board.getPieceAt(x, y);
        Move move;

        if (killed == null) {
            move = new Move(piece.getCoordinatesX(), x,
                            piece.getCoordinatesY(), y,
                            piece
            );
        } else {
            move = new Move(piece.getCoordinatesX(), x,
                    piece.getCoordinatesY(), y,
                    piece,
                    killed
            );

            board.removePieceAt(x, y);
            if (killed instanceof King) {
                isWinner = true;
            }
        }

        piece.setCoordinatesX(x);
        piece.setCoordinatesY(y);
        piece.setFirstMove(false);
        moveHistory.add(move);
        return move;
    }
}

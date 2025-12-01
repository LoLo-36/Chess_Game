package org.example.entities;

import org.example.game.*;
import org.example.pieces.King;
import org.example.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private boolean inTurn;
    private boolean isWinner;
    private List<Move> moveHistory;

    public Player() {
        this.name = "";
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new ArrayList<>();
    }

    public Player(String name) {
        this.name = name;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new ArrayList<>();
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

    public void movePiece(Board board, Piece piece, int x, int y) {
        if (!board.validate(x, y) || !piece.canMove(board, x, y)) {
            return;
        }

        if (piece instanceof Pawn) {
            ((Pawn) piece).setStatus(false);
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
        moveHistory.add(move);
    }
}

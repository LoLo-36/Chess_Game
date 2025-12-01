package org.example.entities;

import org.example.game.*;
import org.example.pieces.Pawn;

import java.util.List;

public class Player {
    private final Board board;
    private boolean inTurn;
    private List<Move> moveHistory;

    public Player(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moveHistory;
    }

    public void setMoves(List<Move> moves) {
        this.moveHistory = moves;
    }

    public boolean isInTurn() {
        return inTurn;
    }
    public void setInTurn(boolean inTurn) {
        this.inTurn = inTurn;
    }

    public void movePiece(Piece piece, int x, int y) {
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
        }

        piece.setCoordinatesX(x);
        piece.setCoordinatesY(y);
        moveHistory.add(move);
    }
}

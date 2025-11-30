package org.example.game;

import org.example.logic.Move;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private ArrayList<Move> moveHistory;

    public Game(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public List<Move> getMoves() {
        return moveHistory;
    }

    public void movePiece(Piece piece, int x, int y) {
        if (!board.validate(x, y) || !piece.canMove(board, x, y)) {
            return;
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

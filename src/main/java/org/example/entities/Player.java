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

        Move move;
        Piece other = board.getPieceAt(x, y);

        if (piece instanceof King king && other instanceof Rook rook
                && king.firstMove && rook.firstMove) {
            move = castling(king, rook);
        } else {
            move = moveTo(board, piece, x, y);
        }

        return move;
    }

    public Move moveTo(Board board, Piece piece, int x, int y) {
        Move move;
        Piece killed = board.getPieceAt(x, y);

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

    public Move castling(King king, Rook rook) {
        Move move;

        if (king.canCastlingLeft()) {
            rook.setCoordinatesX(king.getCoordinatesX() - 1);
            rook.setCoordinatesY(king.getCoordinatesY());
            king.setCoordinatesX(king.getCoordinatesX() - 2);
            king.setCoordinatesY(king.getCoordinatesY());
        }

        if (king.canCastlingRight()) {
            rook.setCoordinatesX(king.getCoordinatesX() + 1);
            rook.setCoordinatesY(king.getCoordinatesY());
            king.setCoordinatesX(king.getCoordinatesX() + 2);
            king.setCoordinatesY(king.getCoordinatesY());
        }

        move = new Move(king.getCoordinatesX(), rook.getCoordinatesX(),
                king.getCoordinatesY(), rook.getCoordinatesY(),
                king,
                rook
        );

        return move;
    }
}

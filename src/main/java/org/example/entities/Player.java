package org.example.entities;

import org.example.game.*;
import org.example.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
    private String name;
    private Color color;
    private boolean inTurn;
    private boolean isWinner;
    private Stack<Move> moveHistory;

    public Player() {
        this.name = "";
        this.color = Color.WHITE;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
    }

    public Player(String name) {
        this.name = name;
        this.color = Color.WHITE;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
    }

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
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

    public void setMoves(Stack<Move> moves) {
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
            king.firstMove = false;
            rook.firstMove = false;
        } else {
            move = moveTo(board, piece, x, y);
        }
        moveHistory.push(move);
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
        return move;
    }

    public Move castling(King king, Rook rook) {
        Move move;
        int oldKingX = king.getCoordinatesX();
        int oldKingY = king.getCoordinatesY();

        if (oldKingX > rook.getCoordinatesX()) {
            rook.setCoordinatesX(oldKingX - 1);
            rook.setCoordinatesY(oldKingY);
            king.setCoordinatesX(oldKingX - 2);
            king.setCoordinatesY(oldKingY);
        }

        if (oldKingX < rook.getCoordinatesX()) {
            rook.setCoordinatesX(oldKingX + 1);
            rook.setCoordinatesY(oldKingY);
            king.setCoordinatesX(oldKingX + 2);
            king.setCoordinatesY(oldKingY);
        }

        move = new Move(oldKingX, king.getCoordinatesX(),
                oldKingY, king.getCoordinatesY(),
                king, MoveType.CASTLING);

        return move;
    }

    public boolean checkPromotion(Piece piece) {
        if (piece instanceof Pawn) {
            int y = piece.getCoordinatesY();
            return (piece.getColor() == Color.WHITE && y == 8) ||
                    (piece.getColor() == Color.BLACK && y == 1);
        }
        return false;
    }

    public Piece promotePawn(Board board, Piece pawn, String newType) {
        if (!checkPromotion(pawn)) {
            return pawn;
        }

        Piece newPiece;
        int x = pawn.getCoordinatesX();
        int y = pawn.getCoordinatesY();
        Color color = pawn.getColor();

        newPiece = switch (newType.toUpperCase()) {
            case "ROOK" -> new Rook(x, y, color);
            case "BISHOP" -> new Bishop(x, y, color);
            case "KNIGHT" -> new Knight(x, y, color);
            default -> new Queen(x, y, color);
        };
        board.removePieceAt(x, y);

        board.addPiece(newPiece);

        return newPiece;
    }
}

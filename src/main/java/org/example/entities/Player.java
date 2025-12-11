package org.example.entities;

import org.example.game.*;
import org.example.pieces.*;
import org.example.user.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class initializes the Player object,
 * And defines operations.
 *   player move a piece in chessboard to specified square (x, y):
 *     Normal move
 *     Castling
 *     Promote
 */
public class Player {
    private String id;
    private String name;
    private Color color;
    private boolean inTurn;
    private boolean isWinner;
    private Stack<Move> moveHistory;

    public Player() {
        this.id = "";
        this.name = "";
        this.color = Color.WHITE;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
    }

    public Player(User user) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.color = Color.WHITE;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
    }

    public Player(User user, Color color) {
        this.id = user.getId();
        this.name = user.getUsername();
        this.color = color;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
    }

    public Player(String id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.inTurn = false;
        this.isWinner = false;
        this.moveHistory = new Stack<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return id != null && !id.isEmpty();
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

    /**
     * Attempts to move a given piece to the target square (x, y).
     * <p>
     * This method validates the move, checks whether the piece belongs to the
     * current player, and ensures the target square is reachable according to
     * the piece's movement rules. It also handles special moves such as castling.
     * </p>
     *
     * @param board the chess board containing all pieces
     * @param piece the piece to be moved
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return a {@link Move} instance representing the executed move,
     *         or {@code null} if the move is invalid
     */
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

    /**
     * Moves a piece to the target square (x, y) assuming the move is already validated.
     * <p>
     * This method updates the board state, handles captures if the target square
     * contains an enemy piece, and checks for win condition when a King is captured.
     * </p>
     *
     * @param board the chess board
     * @param piece the piece being moved
     * @param x the target X coordinate
     * @param y the target Y coordinate
     * @return a {@link Move} representing the movement, including captured pieces if any
     */
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

    /**
     * Performs castling between a King and a Rook.
     * <p>
     * This method assumes castling conditions have already been validated
     * (neither piece has moved before, correct positioning, unobstructed path, etc.).
     * The method updates both the King and Rook positions and records a CASTLING move.
     * </p>
     *
     * @param king the King involved in castling
     * @param rook the Rook involved in castling
     * @return a {@link Move} representing the castling action
     */
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

    /**
     * Checks whether a pawn has reached the final rank and is eligible for promotion.
     *
     * @param piece the piece to check
     * @return true if the piece is a Pawn and has reached the opposite end of the board;
     *         false otherwise
     */
    public boolean checkPromotion(Piece piece) {
        if (piece instanceof Pawn) {
            int y = piece.getCoordinatesY();
            return (piece.getColor() == Color.WHITE && y == 8) ||
                    (piece.getColor() == Color.BLACK && y == 1);
        }
        return false;
    }

    /**
     * Promotes a pawn to a new piece type.
     * <p>
     * If the pawn is eligible for promotion, it is removed from the board and
     * replaced with a new piece of the specified type. Valid promotion types are:
     * "ROOK", "BISHOP", "KNIGHT", or "QUEEN" (default).
     * </p>
     *
     * @param board the chess board
     * @param pawn the pawn to be promoted
     * @param newType the target piece type (case-insensitive)
     * @return the newly created promoted piece; if promotion is not allowed,
     *         returns the original pawn
     */
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

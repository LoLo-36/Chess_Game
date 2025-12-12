package org.example.game;

import org.example.entities.Piece;

import java.awt.*;

/**
 * Represents a single move in a chess game.
 * <p>
 * A Move object stores full information about an action performed on the board,
 * including:
 * <ul>
 *     <li>start and end coordinates</li>
 *     <li>the piece that moved</li>
 *     <li>a killed piece (if any)</li>
 *     <li>a promoted piece (if any)</li>
 *     <li>the type of move (normal, castling, promotion)</li>
 * </ul>
 * This class is used primarily for move history, undo operations,
 * and displaying move notation.
 * </p>
 */
public class Move {
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private Piece movedPiece;
    private Piece killedPiece;
    private Piece promotedPiece;
    private MoveType type;

    public Move(int startX, int endX, int startY, int endY, Piece movedPiece) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.movedPiece = movedPiece;
        this.killedPiece = null;
        this.type = MoveType.NORMAL;
        this.promotedPiece = null;
    }

    /**
     * Creates a normal move with a captured piece.
     *
     * @param startX      starting X coordinate
     * @param endX        ending X coordinate
     * @param startY      starting Y coordinate
     * @param endY        ending Y coordinate
     * @param movedPiece  the piece that moved
     * @param killedPiece the piece that was captured (may be null)
     */
    public Move(int startX, int endX, int startY, int endY, Piece movedPiece, Piece killedPiece) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.movedPiece = movedPiece;
        this.killedPiece = killedPiece;
        this.type = MoveType.NORMAL;
        this.promotedPiece = null;
    }

    /**
     * Creates a special move such as castling.
     *
     * @param startX     starting X coordinate
     * @param endX       ending X coordinate
     * @param startY     starting Y coordinate
     * @param endY       ending Y coordinate
     * @param movedPiece the piece that moved
     * @param type       the type of this move (CASTLING, etc.)
     */
    public Move(int startX, int endX, int startY, int endY, Piece movedPiece, MoveType type) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.movedPiece = movedPiece;
        this.killedPiece = null;
        this.type = type;
        this.promotedPiece = null;
    }

    /**
     * Creates a promotion move.
     * <p>
     * This occurs when a pawn reaches the final rank and is replaced by another piece.
     * </p>
     *
     * @param startX        starting X coordinate
     * @param endX          ending X coordinate
     * @param startY        starting Y coordinate
     * @param endY          ending Y coordinate
     * @param movedPiece    the pawn that moved
     * @param killedPiece   the piece captured on the target square (if any)
     * @param promotedPiece the new piece created after promotion
     */
    public Move(int startX, int endX, int startY, int endY, Piece movedPiece, Piece killedPiece, Piece promotedPiece) {
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;
        this.movedPiece = movedPiece;
        this.killedPiece = killedPiece;
        this.type = MoveType.PROMOTION;
        this.promotedPiece = promotedPiece;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public Piece getMovedPiece() {
        return movedPiece;
    }

    public void setMovedPiece(Piece movedPiece) {
        this.movedPiece = movedPiece;
    }

    public Piece getKilledPiece() {
        return killedPiece;
    }

    public void setKilledPiece(Piece killedPiece) {
        this.killedPiece = killedPiece;
    }

    public Piece getPromotedPiece() {
        return promotedPiece;
    }

    public void setPromotedPiece(Piece promotedPiece) {
        this.promotedPiece = promotedPiece;
    }

    public MoveType getType() {
        return type;
    }

    public void setType(MoveType type) {
        this.type = type;
    }

    /**
     * Converts the move into a human-readable notation.
     * <p>
     * Supports:
     * <ul>
     *     <li>"O-O" and "O-O-O" for castling</li>
     *     <li>Standard notation with piece symbols</li>
     *     <li>"x" for captures</li>
     *     <li>"=Q/B/R/N" for promotion</li>
     * </ul>
     *
     * @return a string describing the move in chess notation
     */
    @Override
    public String toString() {
        if (type == MoveType.CASTLING) {
            return (endX > startX) ? "O-O" : "O-O-O";
        }

        StringBuilder sb = new StringBuilder();

        sb.append(movedPiece.getColor()).append("-").append(movedPiece.getSymbol());
        sb.append(convertCoordinate(endX, endY));

        if (killedPiece != null) {
            sb.append(" x ").append(killedPiece.getColor())
                    .append("-").append(killedPiece.getSymbol());
        }

        if (type == MoveType.PROMOTION && promotedPiece != null) {
            sb.append(" = ").append(promotedPiece.getColor())
                    .append("-").append(promotedPiece.getSymbol());
        }

        return sb.toString();
    }

    private String convertCoordinate(int x, int y) {
        char col = (char) ('a' + x - 1);
        return "(" + col + y + ")";
    }
}

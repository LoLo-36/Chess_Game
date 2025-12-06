package org.example.game;

import org.example.entities.Piece;

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
            sb.append("=").append(promotedPiece.getSymbol());
        }

        return sb.toString();
    }

    private String convertCoordinate(int x, int y) {
        char col = (char) ('a' + x - 1);
        return "(" + col + y + ")";
    }
}

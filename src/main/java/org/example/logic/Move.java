package org.example.logic;

import org.example.game.Piece;

public class Move {
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private Piece movedPiece;
    private Piece killedPiece;

    public Move(int startX, int endX, int startY, int endY, Piece movedPiece) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.movedPiece = movedPiece;
        this.killedPiece = null;
    }

    public Move(int startX, int endX, int startY, int endY, Piece movedPiece, Piece killedPiece) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.movedPiece = movedPiece;
        this.killedPiece = killedPiece;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        switch (endX) {
            case 1:
                sb.append("a");
                break;
            case 2:
                sb.append("b");
                break;
            case 3:
                sb.append("c");
                break;
            case 4:
                sb.append("d");
                break;
            case 5:
                sb.append("e");
                break;
            case 6:
                sb.append("f");
                break;
            case 7:
                sb.append("g");
                break;
            case 8:
                sb.append("h");
                break;
            default:
                break;
        }

        switch (endY) {
            case 1:
                sb.append("1");
                break;
            case 2:
                sb.append("2");
                break;
            case 3:
                sb.append("3");
                break;
            case 4:
                sb.append("4");
                break;
            case 5:
                sb.append("5");
                break;
            case 6:
                sb.append("6");
                break;
            case 7:
                sb.append("7");
                break;
            case 8:
                sb.append("8");
                break;
            default:
                break;
        }
        sb.append(")");
        if (killedPiece != null) {
            sb.append(" eat: ")
                    .append(killedPiece.getColor())
                    .append("-")
                    .append(killedPiece.getSymbol());
        }

        return movedPiece.getColor()
                + "-" + movedPiece.getSymbol()
                + sb
        ;
    }
}

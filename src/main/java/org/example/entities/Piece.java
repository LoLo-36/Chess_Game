package org.example.entities;

import java.awt.*;

/**
 * This class initializes the Piece object,
 * And defines operations on the piece.
 *     get symbol of piece,
 *     check to see if the position of this piece coincides with another piece,
 *     Check if this piece can move to the specified square
 */
public abstract class Piece {
    private int coordinatesX;
    private int coordinatesY;
    private Color color;
    protected boolean firstMove;

    protected Piece(int coordinatesX, int coordinatesY) {
        if (1 <= coordinatesX && coordinatesX <= 8) {
            this.coordinatesX = coordinatesX;
        }

        if (1 <=  coordinatesY && coordinatesY <= 8) {
            this.coordinatesY = coordinatesY;
        }
        this.color = Color.WHITE;
        this.firstMove = true;
    }

    protected Piece(int coordinatesX, int coordinatesY, Color color) {
        if (1 <= coordinatesX && coordinatesX <= 8) {
            this.coordinatesX = coordinatesX;
        }

        if (1 <=  coordinatesY && coordinatesY <= 8) {
            this.coordinatesY = coordinatesY;
        }
        this.color = color;
        this.firstMove = true;
    }

    public int getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(int coordinateX) {
        this.coordinatesX = coordinateX;
    }

    public int getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(int coordinateY) {
        this.coordinatesY = coordinateY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    /**
     * Check to see if the position of this piece coincides with another piece.
     *
     * @param piece the another piece
     * @return true if this piece coincides with another piece
     */
    public boolean checkPosition(Piece piece) {
        return this.coordinatesX == piece.getCoordinatesX()
                && this.coordinatesY == piece.getCoordinatesY();
    }

    /**
     * Get symbol of piece.
     *
     * @return symbol of piece as a String
     */
    public abstract String getSymbol();

    /**
     *  Check if this piece can move to the specified square.
     *
     * @param board The board contains piece.
     * @param x coordinates X of square
     * @param y coordinates Y of square
     * @return true if the piece can move to that square (x, y)
     */
    public abstract boolean canMove(Board board, int x, int y);
}

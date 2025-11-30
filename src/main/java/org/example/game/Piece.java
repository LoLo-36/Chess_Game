package org.example.game;

import java.awt.*;

public abstract class Piece {
    private int coordinatesX;
    private int coordinatesY;
    private Color color;

    protected Piece(int coordinatesX, int coordinatesY) {
        if (1 <= coordinatesX && coordinatesX <= 8) {
            this.coordinatesX = coordinatesX;
        }

        if (1 <=  coordinatesY && coordinatesY <= 8) {
            this.coordinatesY = coordinatesY;
        }
        this.color = Color.WHITE;
    }

    protected Piece(int coordinatesX, int coordinatesY, Color color) {
        if (1 <= coordinatesX && coordinatesX <= 8) {
            this.coordinatesX = coordinatesX;
        }

        if (1 <=  coordinatesY && coordinatesY <= 8) {
            this.coordinatesY = coordinatesY;
        }
        this.color = color;
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

    public boolean checkPosition(Piece piece) {
        return this.coordinatesX == piece.getCoordinatesX()
                && this.coordinatesY == piece.getCoordinatesY();
    }

    public abstract String getSymbol();

    public abstract boolean canMove(Board board, int x, int y);
}

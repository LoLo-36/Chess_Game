package org.example.game;

import org.example.entities.Board;
import org.example.entities.Piece;
import org.example.entities.Player;
import org.example.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private List<Move> moveHistory;
    private boolean isGameOver;
    private String gameStatusMessage;

    public Game() {
        this.board = new Board();
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
    }

    public Game(Board board) {
        this.board = board;
        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");
    }

    public Game(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(List<Move> moveHistory) {
        this.moveHistory = moveHistory;
    }

    public void generateBoard(String type) {
        if (type.equals("Full")) {
            for (int x = 1; x <= 8; x++) {
                board.addPiece(new Pawn(x, 7, Color.BLACK));
            }
            board.addPiece(new Rook(1, 8, Color.BLACK));
            board.addPiece(new Knight(2, 8, Color.BLACK));
            board.addPiece(new Bishop(3, 8, Color.BLACK));
            board.addPiece(new Queen(4, 8, Color.BLACK));
            board.addPiece(new King(5, 8, Color.BLACK));
            board.addPiece(new Bishop(6, 8, Color.BLACK));
            board.addPiece(new Knight(7, 8, Color.BLACK));
            board.addPiece(new Rook(8, 8, Color.BLACK));

            for (int x = 1; x <= 8; x++) {
                board.addPiece(new Pawn(x, 2, Color.WHITE));
            }
            board.addPiece(new Rook(1, 1, Color.WHITE));
            board.addPiece(new Knight(2, 1, Color.WHITE));
            board.addPiece(new Bishop(3, 1, Color.WHITE));
            board.addPiece(new Queen(4, 1, Color.WHITE));
            board.addPiece(new King(5, 1, Color.WHITE));
            board.addPiece(new Bishop(6, 1, Color.WHITE));
            board.addPiece(new Knight(7, 1, Color.WHITE));
            board.addPiece(new Rook(8, 1, Color.WHITE));
        }
    }

    public boolean playTurn(int startX, int startY, int endX, int endY, String promotionType) {
        if (isGameOver) {
            this.gameStatusMessage = "Game is already over.";
            return false;
        }

        Player currentPlayer = player1.isInTurn() ? player1 : player2;
        Player otherPlayer = player1.isInTurn() ? player2 : player1;

        Piece pieceToMove = board.getPieceAt(startX, startY);

        if (pieceToMove == null) {
            return false;
        }

        if (pieceToMove.getColor() != currentPlayer.getColor()) {
            return false;
        }

        Move move = currentPlayer.movePiece(board, pieceToMove, endX, endY);
        if (move == null) {
            return false;
        }

        moveHistory.add(move);

        if (currentPlayer.checkPromotion(pieceToMove)) {
            String type = (promotionType == null || promotionType.isEmpty()) ? "QUEEN" : promotionType;
            Piece promotedPiece = currentPlayer.promotePawn(board, pieceToMove, type);
            move = new Move (startX, endX, startY, endY, pieceToMove, pieceToMove, promotedPiece);
            moveHistory.add(move);
        }

        if (currentPlayer.isWinner()) {
            isGameOver = true;
            this.gameStatusMessage = "Winner: " + currentPlayer.getName();
            return true;
        }

        currentPlayer.setInTurn(false);
        otherPlayer.setInTurn(true);

        return true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getGameStatusMessage() {
        return gameStatusMessage;
    }
}

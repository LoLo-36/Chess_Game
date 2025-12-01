package org.example.game;

import org.example.entities.Board;
import org.example.entities.Piece;
import org.example.entities.Player;

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
        this.board = Board.getInstance();
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

        }
    }

    public boolean playTurn(int startX, int startY, int endX, int endY) {
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
        } else {
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

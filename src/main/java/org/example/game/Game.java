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

    public Game() {
        this.board = Board.getInstance();
        this.player1 = new Player();
        this.player2 = new Player();
    }

    public Game(Board board) {
        this.board = board;
        this.player1 = new Player();
        this.player2 = new Player();
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

    public void gameLoop() {
        player1.setInTurn(true);
        player2.setInTurn(false);
        while (true) {

        }
    }
}

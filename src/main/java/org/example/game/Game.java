package org.example.game;

import com.sun.tools.javac.util.StringUtils;
import org.example.entities.Board;
import org.example.entities.Piece;
import org.example.entities.Player;
import org.example.pieces.*;
import org.example.user.User;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Game {
    private final Board board;
    private final Player player1;
    private final Player player2;
    private Stack<Move> moveHistory = new Stack<>();
    private boolean isGameOver;
    private String gameStatusMessage;

    public Game() {
        this.board = new Board();
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

    public Stack<Move> getMoveHistory() {
        return moveHistory;
    }

    public void setMoveHistory(Stack<Move> moveHistory) {
        this.moveHistory = moveHistory;
    }

    public boolean joinGame(User joiningPlayer) {
        if (!player1.isOccupied()) {
            player1.setId(joiningPlayer.getId());
            player1.setName(joiningPlayer.getUsername());
            player1.setColor(Color.WHITE);
            return true;
        } else if (!player2.isOccupied()) {
            player2.setId(joiningPlayer.getId());
            player2.setName(joiningPlayer.getUsername());
            player2.setColor(Color.BLACK);
            return true;
        }

        return false;
    }

    public void generateBoard(String type) {
        if (StringUtils.toUpperCase(type).equals("FULL")) {
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

    public List<Point> getValidMoves(int x, int y, String requesterId) {
        List<Point> validMoves = new ArrayList<>();
        Piece piece = board.getPieceAt(x, y);

        if (piece == null) return validMoves;

        Player owner = (piece.getColor() == Color.WHITE) ? player1 : player2;

        if (!owner.getId().equals(requesterId)) {
            return validMoves;
        }

        Player currentPlayer = player1.isInTurn() ? player1 : player2;
        if (piece.getColor() != currentPlayer.getColor()) {
            return validMoves;
        }

        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                if (piece.canMove(board, col, row)) {
                    validMoves.add(new Point(col, row));
                }
            }
        }
        return validMoves;
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

        moveHistory.push(move);

        if (currentPlayer.checkPromotion(pieceToMove)) {
            String type = (promotionType == null || promotionType.isEmpty()) ? "QUEEN" : promotionType;
            Piece killedPiece = move.getKilledPiece();
            Piece promotedPiece = currentPlayer.promotePawn(board, pieceToMove, type);
            move = new Move (startX, endX, startY, endY, pieceToMove, killedPiece, promotedPiece);
            moveHistory.push(move);
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

    public void backToPreviousMove() {
        if (moveHistory.isEmpty()) {
            return;
        }

        Move prevMove =  moveHistory.peek();
        moveHistory.pop();
        if (prevMove == null) {
            return;
        }

        switch (prevMove.getType()) {
            case MoveType.NORMAL:
                Piece curr = board.getPieceAt(prevMove.getEndX(), prevMove.getEndY());
                Piece killed = prevMove.getKilledPiece();

                curr.setCoordinatesX(prevMove.getStartX());
                curr.setCoordinatesY(prevMove.getStartY());
                if (killed != null) {
                    board.addPiece(killed);
                }
            break;

            case MoveType.PROMOTION:
                moveHistory.pop();
                Piece movedPiece = prevMove.getMovedPiece();
                Piece killedPiece = prevMove.getKilledPiece();

                if (board.getPieceAt(prevMove.getEndX(), prevMove.getEndY()) != null) {
                    board.removePieceAt(prevMove.getEndX(), prevMove.getEndY());
                }
                if (killedPiece != null) {
                    board.addPiece(killedPiece);
                }
                movedPiece.setCoordinatesX(prevMove.getStartX());
                movedPiece.setCoordinatesY(prevMove.getStartY());
                board.addPiece(movedPiece);
            break;

            case MoveType.CASTLING:
                Piece king = board.getPieceAt(prevMove.getEndX(), prevMove.getEndY());
                Piece rook = null;
                if (prevMove.getStartX() < king.getCoordinatesX()) {
                    rook = board.getPieceAt(king.getCoordinatesX() - 1, king.getCoordinatesY());
                    if (rook != null) {
                        rook.setCoordinatesX(8);
                        rook.setCoordinatesY(king.getCoordinatesY());
                        rook.setFirstMove(true);
                    }
                }
                if (prevMove.getStartX() > king.getCoordinatesX()) {
                    rook = board.getPieceAt(king.getCoordinatesX() + 1, king.getCoordinatesY());
                    if (rook != null) {
                        rook.setCoordinatesX(1);
                        rook.setCoordinatesY(king.getCoordinatesY());
                        rook.setFirstMove(true);
                    }
                }

                king.setCoordinatesX(prevMove.getStartX());
                king.setCoordinatesY(prevMove.getStartY());
                king.setFirstMove(true);
            break;
        }

        player1.setInTurn(!player1.isInTurn());
        player2.setInTurn(!player2.isInTurn());

        if (isGameOver) {
            isGameOver = false;
            gameStatusMessage = "";
        }
    }
}

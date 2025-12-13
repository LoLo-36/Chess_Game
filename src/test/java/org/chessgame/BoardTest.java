package org.chessgame;

import org.example.entities.Board;
import org.example.entities.Piece;
import org.example.game.ColorInGame;
import org.example.game.Game;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {
    private static Game game =  new Game();
    private Board board;
    static {
        game.generateBoard("FULL");
    }


    private String convertCoordinate(int x, int y) {
        char col = (char) ('a' + x - 1);
        return "(" + col + y + ")";
    }

    @Test
    void createBoardTest() {
        board = game.getBoard();
        List<Piece> pieces = board.getPieces();
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (Piece piece : pieces) {
            sb.append(piece.getColor() == Color.WHITE ? ColorInGame.WHITE : ColorInGame.BLACK).append("-").append(piece.getSymbol());
            sb.append(convertCoordinate(piece.getCoordinatesX(), piece.getCoordinatesY()));
            cnt++;
            if (cnt != 8) {
                sb.append(" ");
            } else {
                sb.append("\n");
                cnt = 0;
            }
        }

        String result = sb.toString();
        String finalResult = "BLACK-P(a7) BLACK-P(b7) BLACK-P(c7) BLACK-P(d7) BLACK-P(e7) BLACK-P(f7) BLACK-P(g7) BLACK-P(h7)\n" +
                "BLACK-R(a8) BLACK-N(b8) BLACK-B(c8) BLACK-Q(d8) BLACK-K(e8) BLACK-B(f8) BLACK-N(g8) BLACK-R(h8)\n" +
                "WHITE-P(a2) WHITE-P(b2) WHITE-P(c2) WHITE-P(d2) WHITE-P(e2) WHITE-P(f2) WHITE-P(g2) WHITE-P(h2)\n" +
                "WHITE-R(a1) WHITE-N(b1) WHITE-B(c1) WHITE-Q(d1) WHITE-K(e1) WHITE-B(f1) WHITE-N(g1) WHITE-R(h1)\n";
        assertEquals(finalResult, result);
    }
}

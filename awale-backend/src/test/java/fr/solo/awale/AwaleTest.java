package fr.solo.awale;

import com.diogonunes.jcolor.Attribute;
import fr.solo.awale.logic.Awale;
import fr.solo.awale.logic.Side;
import fr.solo.awale.logic.player.AbstractPlayer;
import fr.solo.awale.logic.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwaleTest {
    Awale aNorm, aCapture, aBoucle, aAffame, aEnd;
    AbstractPlayer p1, p2;

    @BeforeEach
    void init() {
        int[][] cellCapture = {{1, 4, 2, 2, 4, 0}, {0, 0, 0, 0, 0, 4}};
        int[][] cellBoucle = {{12, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        int[][] cellAffame = {{0, 0, 0, 0, 0, 4}, {2, 0, 0, 0, 6, 0}};
        int[][] cellEnd = {{0, 0, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0}};
        aNorm = new Awale();
        aCapture = new Awale(cellCapture);
        aBoucle = new Awale(cellBoucle);
        aAffame = new Awale(cellAffame);
        aEnd = new Awale(cellEnd);
        p1 = new Player("Joueur 1", Attribute.WHITE_TEXT());
        p2 = new Player("Joueur 2", Attribute.WHITE_TEXT());
    }

    @Test
    void testCapture() {
        p1.joinGame(aCapture);
        p2.joinGame(aCapture);

        // P1 joue le 5e trou
        p1.play(4);
        // P2 joue le 6e trou, mais ne ramasse que deux trous puisque est coupé par le trou qui a 4-5
        p2.play(5);
        assertEquals(0, p1.getScore());
        assertEquals(6, p2.getScore());
        assertArrayEquals(new int[]{2, 5, 0, 0, 0, 1}, aCapture.getBoard().getRow(Side.TOP));
        assertArrayEquals(new int[]{1, 1, 1, 0, 0, 0}, aCapture.getBoard().getRow(Side.BOTTOM));
    }

    @Test
    void testBoucle() {
        p1.joinGame(aBoucle);
        p2.joinGame(aBoucle);

        // P1 joue le 1er trou
        p1.play(0);
        assertEquals(0, p2.getScore());
        assertEquals(0, p1.getScore());
        assertArrayEquals(new int[]{0, 2, 1, 1, 1, 1}, aBoucle.getBoard().getRow(Side.TOP));
        assertArrayEquals(new int[]{1, 1, 1, 1, 1, 1}, aBoucle.getBoard().getRow(Side.BOTTOM));
    }

    @Test
    void testAffame() {
        p1.joinGame(aAffame);
        p2.joinGame(aAffame);

        p1.play(5); // P1 joue le 6e trou

        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, aAffame.getBoard().getRow(Side.TOP));
        assertArrayEquals(new int[]{3, 1, 1, 1, 6, 0}, aAffame.getBoard().getRow(Side.BOTTOM));

        assertFalse(p2.play(0)); // P2 joue le 1er trou, impossible à jouer
        assertFalse(p2.play(1)); // P2 joue le 2e trou, impossible à jouer
        assertFalse(p2.play(2)); // P2 joue le 3e trou, impossible à jouer
        assertFalse(p2.play(3)); // P2 joue le 4e trou, impossible à jouer
        assertTrue(p2.play(4));  // P2 joue le 5e trou, il peut jouer

        assertArrayEquals(new int[]{1, 1, 1, 1, 1, 0}, aAffame.getBoard().getRow(Side.TOP));
        assertArrayEquals(new int[]{3, 1, 1, 1, 0, 1}, aAffame.getBoard().getRow(Side.BOTTOM));
    }

    @Test
    void testEnd() {
        p1.joinGame(aEnd);
        p2.joinGame(aEnd);
    }
}
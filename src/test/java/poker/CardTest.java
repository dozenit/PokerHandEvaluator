package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void Should_HaveASuit() {
        Card card = new Card(Value.TWO,
                Suit.CLUBS);
        Assertions.assertTrue(card.getSuit() instanceof Suit);
    }

    @Test
    void Should_HaveARank() {
        Card card = new Card(Value.TWO,
                Suit.CLUBS);
        Assertions.assertTrue(card.getValue() instanceof Value);
    }

    @Test
    void Should_Return1_IfGivenCardIsSmaller() {
        final int HIGHER = 1;
        Card card = new Card(Value.KING, Suit.CLUBS);
        Card givenCard = new Card(Value.QUEEN, Suit.CLUBS);
        Assertions.assertEquals(HIGHER, card.compareTo(givenCard));
    }

    @Test
    void Should_Return0_IfGivenCardIsEqual() {
        final int EQUAL = 0;
        Card card = new Card(Value.KING, Suit.CLUBS);
        Card givenCard = new Card(Value.KING, Suit.DIAMONDS);
        Assertions.assertEquals(EQUAL, card.compareTo(givenCard));
    }

    @Test
    void Should_ReturnNegative1_IfGivenCardIsGreater() {
        final int LOWER = -1;
        Card card = new Card(Value.KING, Suit.CLUBS);
        Card givenCard = new Card(Value.ACE, Suit.CLUBS);
        Assertions.assertEquals(LOWER, card.compareTo(givenCard));
    }
}

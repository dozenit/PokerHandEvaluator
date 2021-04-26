package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    void givenCardShouldHaveASuit() {
        Card card = new Card(Value.TWO,
                Suit.CLUBS);
        Assertions.assertTrue(card.getSuit() instanceof Suit);
    }

    @Test
    void givenCardShouldHaveARank() {
        Card card = new Card(Value.TWO,
                Suit.CLUBS);
        Assertions.assertTrue(card.getValue() instanceof Value);
    }
}

package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {
    private final int HAND_SIZE = 5;

    Card card1 = new Card(Rank.TWO, Suit.CLUBS);
    Card card2 = new Card(Rank.THREE, Suit.CLUBS);
    Card card3 = new Card(Rank.FOUR, Suit.CLUBS);
    Card card4 = new Card(Rank.FIVE, Suit.CLUBS);
    Card card5 = new Card(Rank.SIX, Suit.CLUBS);

    Set<Card> fourCards = new HashSet<>(Set.of(card1, card2, card3, card4));
    Set<Card> fiveCards = new HashSet<>(Set.of(card1, card2, card3, card4, card5));

    @Test
    void givenHandShouldContainFiveCards() {
        assertDoesNotThrow(() -> new Hand(fiveCards));
        assertThrows(IllegalArgumentException.class, () -> new Hand(fourCards));
    }
}

package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import poker.Suit;

public class SuitTest {

    static final int POKER_SUITS_COUNT = 4;

    @Test
    void shouldHaveFourEntries() {
        Assertions.assertEquals(POKER_SUITS_COUNT,
                Suit.values().length);
    }
}

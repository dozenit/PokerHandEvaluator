package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RankTest {

    static final int POKER_RANKS_COUNT = 13;

    @Test
    void shouldHaveThirteenValues() {
        Assertions.assertEquals(POKER_RANKS_COUNT, Ranks.values().length);
    }
}

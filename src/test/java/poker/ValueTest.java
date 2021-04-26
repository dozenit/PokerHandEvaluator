package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValueTest {

    static final int POKER_RANKS_COUNT = 13;

    @Test
    void shouldHaveThirteenEntries() {
        Assertions.assertEquals(POKER_RANKS_COUNT, Value.values().length);
    }
}

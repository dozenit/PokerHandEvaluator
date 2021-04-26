package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Locale;

public class CategoryTest {

    static final int POKER_CATEGORIES_COUNT = 9;

    @Test
    void shouldHaveNineEntries() {
        Assertions.assertEquals(POKER_CATEGORIES_COUNT,
                Category.values().length);
    }
}

package poker;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Hand {
    private final Set<Card> cards;

    public Hand(final Set<Card> cards) {
        this.cards = new HashSet<Card>(cards);
    }

    public Collection<Card> getCards() {
        return this.cards;
    }
}

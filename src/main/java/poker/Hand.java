package poker;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Hand {
    private final static int HAND_SIZE = 5;
    private final Set<Card> cards;

    public Hand(final Set<Card> cards) {
        int cardsAmount = cards.size();
        if (cardsAmount != HAND_SIZE) {
            throw new IllegalArgumentException(
                    "Hand must be given exactly 5 cards. The amount of cards given were: %d.".formatted(cardsAmount));
        }
        this.cards = new HashSet<>(cards);

    }

    public Collection<Card> getCards() {
        return this.cards;
    }
}

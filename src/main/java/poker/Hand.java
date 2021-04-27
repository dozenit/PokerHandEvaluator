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
                    "Hand must be given exactly 5 cards. " +
                    "The amount of cards given were: " + cardsAmount);
        }
        this.cards = new HashSet<>(cards);
    }

    public Hand(final Card card1, final Card card2, final Card card3,
                final Card card4, final Card card5) {
        this.cards = new HashSet<>(Set.of(card1, card2, card3, card4, card5));
    }

    public Set<Card> getCards() {
        return this.cards;
    }
}

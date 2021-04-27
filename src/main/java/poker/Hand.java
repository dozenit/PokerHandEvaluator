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

    public Hand(final Value value1, final Suit suit1,
                final Value value2, final Suit suit2,
                final Value value3, final Suit suit3,
                final Value value4, final Suit suit4,
                final Value value5, final Suit suit5) {
        this(new Card(value1, suit1),
                            new Card(value2, suit2),
                            new Card(value3, suit3),
                            new Card(value4, suit4),
                            new Card(value5, suit5));
    }

    public Collection<Card> getCards() {
        return this.cards;
    }
}

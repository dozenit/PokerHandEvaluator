package poker;

import java.util.HashSet;
import java.util.Set;

public class RankEvaluation {

    public static final String HAND_HAS_REACHED = "Player's hand reached category ";

    public static final String WITH_HIGH_CARD = " with the high card ";

    public static final String WITH_A_PAIR_OF = " with a pair of ";
    public static final String AND_THE_KICKER = " and the kicker ";

    public static final String WITH_A_HIGH_PAIR_OF = " with a high pair of ";
    public static final String A_LOW_PAIR_OF = ", a low pair of ";

    public static final String SENTENCE_END = ".";

    private Category category;

    private Value highCard;

    private Value pair;

    private Value kicker;

    private Value highPair;
    private Value lowPair;


    public RankEvaluation(Hand hand) {
        evaluateCategory(hand);
    }

    private void evaluateCategory(Hand hand) {
        evaluateHighCard(hand);
        if (evaluatePair(hand) == Category.PAIR) {
            evaluateTwoPair(hand);
        }
    }

    //================================================================================
    // Methods for Rank Evaluation, sorted by Poker Ranking Category
    //================================================================================

    private void evaluateHighCard(Hand hand) {
        category = Category.HIGH_CARD;
        highCard = getHighestValueAmongCards(hand.getCards());
    }

    private Category evaluatePair(Hand hand) {
        Set<Value> pairCandidates = new HashSet<>();
        Set<Value> kickerCandidates = new HashSet<>();

        for (Card card : hand.getCards()) {
            Value value = card.getValue();
            if (!pairCandidates.contains(value)) {
                pairCandidates.add(value);
            } else {
                pair = value;
                kickerCandidates.addAll(pairCandidates);
                kickerCandidates.remove(value);
                evaluateKickerForPair(kickerCandidates);
                category = Category.PAIR;
            }
        }
        return category;
    }

    private Category evaluateTwoPair(Hand hand) {
        Set<Value> secondPairCandidates = new HashSet<>();
        Set<Value> kickerCandidates = new HashSet<>();

        for (Card card : hand.getCards()) {
            Value value = card.getValue();
            if (value == pair) {
                continue;
            }
            if (!secondPairCandidates.contains(value)) {
                secondPairCandidates.add(value);
            } else {
                setHighPairAndLowPair(value);
                kickerCandidates.addAll(secondPairCandidates);
                kickerCandidates.remove(lowPair);
                kickerCandidates.remove(highPair);
                evaluateKickerForPair(kickerCandidates);
                category = Category.TWO_PAIR;
            }
        }
        return category;
    }

    // Precondition: `pair` has previously been set.
    private void setHighPairAndLowPair(Value value) {
        if (value.compareTo(pair) < 0) {
            lowPair = value;
            highPair = pair;
        } else {
            lowPair = pair;
            highPair = value;
        }
    }

    //================================================================================
    // Helper Methods for Rank Evaluation
    //================================================================================

    private Value getHighestValueAmongCards(Set<Card> cards) {
        return getHighestAmongValues(getValuesFromCards(cards));
    }

    private Value getHighestAmongValues(Set<Value> values) {
        if (values.size() < 1) {
            throw new IllegalArgumentException(
                    "Cannot calculate a value if no items are given."
                            + "The given set must at least include one item.");
        }
        Value highest = Value.TWO;
        for (Value current : values) {
            if (highest.compareTo(current) < 0) {
                highest = current;
            }
        }
        return highest;
    }

    private Set<Value> getValuesFromCards(Set<Card> cards) {
        Set<Value> values = new HashSet<>();
        for (Card card: cards) {
            values.add(card.getValue());
        }
        return values;
    }

    private void evaluateKickerForPair(Set<Value> values) {
        // asserts `highCard` has been set to highest value among cards.
        if (highCard.compareTo(pair) == 0) {
            kicker = getHighestAmongValues(values);
        } else {
            kicker = highCard;
        }
    }

    //================================================================================
    // Output Method for Player and Tests
    //================================================================================

    public String toString() {
        String rankInformation = HAND_HAS_REACHED + category.toString();

        switch (this.category) {
            case HIGH_CARD -> rankInformation += WITH_HIGH_CARD + highCard;
            case PAIR -> rankInformation += WITH_A_PAIR_OF + pair + AND_THE_KICKER + kicker;
            case TWO_PAIR -> rankInformation += WITH_A_HIGH_PAIR_OF + highPair + A_LOW_PAIR_OF + lowPair;
            default -> throw new IllegalArgumentException("Unable to determine category for given hand." +
                    "This method should not be called before `evaluateCategory()` has initialized class attributes.");
        }
        return rankInformation + SENTENCE_END;
    }
}

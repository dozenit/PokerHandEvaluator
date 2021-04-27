package poker;

import java.util.HashSet;
import java.util.Set;

public class RankEvaluation {

    public static final String HAND_HAS_REACHED = "Player's hand reached category ";
    public static final String WITH_HIGH_CARD = " with the high card ";
    public static final String WITH_PAIR = " with a pair of ";
    public static final String SENTENCE_END = ".";
    public static final String AND_KICKER = " and the kicker ";

    private Category category;
    private Value highCard;
    private Value pair;
    private Value kicker;

    public RankEvaluation(Hand hand) {
        evaluateCategory(hand);
    }

    private void evaluateCategory(Hand hand) {
        evaluateHighCard(hand);
        evaluatePair(hand);
    }

    //================================================================================
    // Methods for Rank Evaluation, sorted by Poker Ranking Category
    //================================================================================

    private void evaluateHighCard(Hand hand) {
        this.category = Category.HIGH_CARD;
        highCard = getHighestValueAmongCards(hand.getCards());
    }

    private void evaluatePair(Hand hand) {
        Set<Value> pairCandidates = new HashSet<>();
        Set<Value> kickerCandidates = new HashSet<>();

        for (Card card: hand.getCards()) {
            Value value = card.getValue();
            if (!pairCandidates.contains(value)) {
                pairCandidates.add(value);
            }
            else {
                category = Category.PAIR;
                pair = value;
                kickerCandidates.addAll(pairCandidates);
                kickerCandidates.remove(value);
                evaluateKickerForPair(kickerCandidates);
            }
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
        // asserts highCard has been set to highest value among cards by `evaluateHighCard`
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

        final String highCard = this.highCard.toString();
        final String pair = (this.pair != null) ?
                this.pair.toString() : "";
        final String kicker = (this.kicker != null) ?
                this.kicker.toString() : "";

        switch (this.category) {
            case HIGH_CARD -> rankInformation += WITH_HIGH_CARD + highCard;
            case PAIR -> rankInformation += WITH_PAIR + pair + AND_KICKER + kicker;
            default -> throw new IllegalArgumentException("Unable to determine category for given hand." +
                    "This method should not be called before `evaluateCategory()` has initialized class attributes.");
        }
        return rankInformation + SENTENCE_END;
    }
}

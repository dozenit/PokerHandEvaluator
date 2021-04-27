package poker;

import java.util.HashSet;
import java.util.Set;

public class RankEvaluation {

    public static final String HAND_HAS_REACHED = "Player's hand reached category ";
    public static final String WITH_HIGH_CARD = " with the high card ";
    public static final String WITH_PAIR = " with a pair of ";
    public static final String SENTENCE_END = ".";

    private Category category;
    private Card highCard;
    private Value pair;

    public RankEvaluation(Hand hand) {
        evaluateCategory(hand);
    }

    private void evaluateCategory(Hand hand) {
        evaluateHighCard(hand);
        evaluatePair(hand);
    }

    private void evaluateHighCard(Hand hand) {
        this.category = Category.HIGH_CARD;
        for (Card card: hand.getCards()) {
            if (highCard == null) {
                highCard = card;
            }
            if (card.compareTo(highCard) > 0) {
                highCard = card;
            }
        }
    }

    private void evaluatePair(Hand hand) {
        Set<Value> pairCandidates= new HashSet<>();
        boolean hasPair = false;

        for (Card card: hand.getCards()) {
            Value value = card.getValue();
            if (pairCandidates.contains(value)) {
                hasPair = true;
                this.pair = value;
            } else {
                pairCandidates.add(value);
            }
        }
        if (hasPair) {
            this.category = Category.PAIR;
        }
    }

    public String toString() {
        String rankInformation = HAND_HAS_REACHED + category.toString();

        final String highCard = this.highCard.getValue().toString();
        final String pair = (this.pair != null) ?
                this.pair.toString() : "";

        switch (this.category) {
            case HIGH_CARD -> rankInformation += WITH_HIGH_CARD + highCard;
            case PAIR -> rankInformation += WITH_PAIR + pair;
            default -> throw new IllegalArgumentException("Unable to determine category for given hand." +
                    "This method should not be called before `evaluateCategory()` has initialized class attributes.");
        }
        return rankInformation + SENTENCE_END;
    }
}

package poker;

import java.util.*;

public class RankEvaluation {

    private Category category;

    private Map<Value, Integer> valueHistogram;

    private Set<Value> singles;
    private Set<Value> pairs;

    private Value highCard;

    private Value pair;

    private Value kicker;

    private Value highPair;
    private Value lowPair;

    //================================================================================
    // Static Strings for Output to User
    //================================================================================

    public static final String HAND_HAS_REACHED = "Player's hand reached category ";

    public static final String WITH_HIGH_CARD = " with the high card ";

    public static final String WITH_A_PAIR_OF = " with a pair of ";
    public static final String AND_THE_KICKER = " and the kicker ";

    public static final String WITH_A_HIGH_PAIR_OF = " with a high pair of ";
    public static final String A_LOW_PAIR_OF = ", a low pair of ";

    public static final String SENTENCE_END = ".";

    //================================================================================
    // Methods for Rank Evaluation, sorted by Poker Ranking Category
    //================================================================================

    public RankEvaluation(Hand hand) {
        defineValueHistogram(hand);
        defineSinglesAndPairs(valueHistogram);
        evaluateCategoriesWithMultipleCardsOfOneValue(hand);
    }

    private void evaluateCategoriesWithMultipleCardsOfOneValue(Hand hand) {
        if (singles.size() == 5) {
            evaluateHighCard(hand);
        } else if (pairs.size() == 1) {
            evaluatePair(hand);
        } else if (pairs.size() == 2) {
            evaluateTwoPair(hand);
        }
    }

    // precondition: `singles` has been set
    private void evaluateHighCard(Hand hand) {
        category = Category.HIGH_CARD;
        highCard = getHighestValue(singles);
    }

    // precondition: `pairs` has been set
    private void evaluatePair(Hand hand) {
        category = Category.PAIR;
        kicker = getHighestValue(singles);

        for (Value thePair : pairs) {
            pair = thePair;
        }
    }

    // precondition: `pairs` has been set
    private void evaluateTwoPair(Hand hand) {
        category = Category.TWO_PAIR;
        kicker = getHighestValue(singles);

        for (Value current : pairs) {
            if (highPair == null) {
                highPair = current;
                continue;
            }
            if (current.compareTo(highPair) < 0) {
                lowPair = current;
            } else {
                lowPair = highPair;
                highPair = current;
            }
        }
    }

    //================================================================================
    // Helper Methods for Rank Evaluation
    //================================================================================

    public void defineValueHistogram(Hand hand) {
        valueHistogram = new HashMap<>();
        int multiplicity;
        for (Card card: hand.getCards()) {
            Value value = card.getValue();
            if (!valueHistogram.containsKey(value)) {
                valueHistogram.put(value, 1);
            }
            else {
                multiplicity = valueHistogram.get(value) + 1;
                valueHistogram.replace(value, multiplicity);
            }
        }
    }

    public void defineSinglesAndPairs(Map<Value, Integer> histogram) {
        singles = new HashSet<>();
        pairs = new HashSet<>();
        for (Map.Entry<Value, Integer> entry : histogram.entrySet()) {
            int valueMultiplicity = entry.getValue();
            Value value = entry.getKey();

            // TODO: Remove
            String tempInfo = "The Value " + value + " appeared " + valueMultiplicity
                    + " times and was added to ";

            switch (valueMultiplicity) {
                case 1 -> {
                    singles.add(value);
                    tempInfo += "the singles.";
                }
                case 2 -> {
                    pairs.add(value);
                    tempInfo += "the pairs.";
                }
            }
            System.out.println(tempInfo);
        }
    }

    private Value getHighestValue(Set<Value> values) {
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

    //================================================================================
    // Output Method for Player and Tests
    //================================================================================

    public String toString() {
        String rankInformation = HAND_HAS_REACHED + category.toString();

        switch (this.category) {
            case HIGH_CARD -> rankInformation += WITH_HIGH_CARD + highCard;
            case PAIR -> rankInformation += WITH_A_PAIR_OF + pair + AND_THE_KICKER + kicker;
            case TWO_PAIR -> rankInformation += WITH_A_HIGH_PAIR_OF + highPair + A_LOW_PAIR_OF + lowPair
                    + AND_THE_KICKER + kicker;
            default -> throw new IllegalArgumentException("Unable to determine category for given hand." +
                    "This method should not be called before `evaluateCategoriesWithMultipleCardsOfOneValue()` has initialized class attributes.");
        }
        return rankInformation + SENTENCE_END;
    }
}

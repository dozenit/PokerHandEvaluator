package poker;

import org.junit.jupiter.api.*;

@DisplayName("Rank evaluation and string checks.")
public class RankEvaluationTest {

    public static final String WHEN_GIVEN_HAND_RANKS_AT_CATEGORY = "When given hand ranks at category ";
    public static final String OUTPUT_SHOULD_RANK_THE_HAND_AS = "Output should rank the hand as ";

    Card twoOfClubs = new Card(Value.TWO, Suit.CLUBS);
    Card threeOfClubs = new Card(Value.THREE, Suit.CLUBS);
    Card fourOfClubs = new Card(Value.FOUR, Suit.CLUBS);
    Card fiveOfClubs = new Card(Value.FIVE, Suit.CLUBS);
    Card sixOfClubs = new Card(Value.SIX, Suit.CLUBS);

    Card twoOfDiamonds = new Card(Value.TWO, Suit.DIAMONDS);
    Card fourOfDiamonds = new Card(Value.FOUR, Suit.DIAMONDS);
    Card tenOfDiamonds = new Card(Value.TEN, Suit.DIAMONDS);

    Hand handThatRanksAtHighCardWithSix = new Hand(twoOfClubs, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs);
    Hand handThatRanksAtHighCardWithTen = new Hand(tenOfDiamonds, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs);

    Hand handThatRanksAtPairWithTwo = new Hand(twoOfClubs, twoOfDiamonds, fourOfClubs, fiveOfClubs, sixOfClubs);
    Hand handThatRanksAtPairWithFour = new Hand(tenOfDiamonds, fourOfDiamonds, fourOfClubs, fiveOfClubs, sixOfClubs);

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"High Card\"")
    class WhenGivenHandRanksAsHighCard {

        @Test
        @DisplayName(OUTPUT_SHOULD_RANK_THE_HAND_AS + "high card, with high card of six.")
        void Should_OutputRankWithHighCardSix() {
            assertHighCard(Value.SIX, handThatRanksAtHighCardWithSix);
        }

        @Test
        @DisplayName(OUTPUT_SHOULD_RANK_THE_HAND_AS + "high card, with high card of ten.")
        void Should_OutputRankWithHighCardTen() {
            assertHighCard(Value.TEN, handThatRanksAtHighCardWithTen);
        }

        private void assertHighCard(Value expectedValue, Hand handThatRanksAtHighCardWithValue) {
            final Category expectedCategory = Category.HIGH_CARD;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_HIGH_CARD + expectedValue + RankEvaluation.SENTENCE_END;
            assertRank(Category.HIGH_CARD, expectedOutput, handThatRanksAtHighCardWithValue);
        }
    }

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"Pair\"")
    class WhenGivenHandRanksAsPair {
        @Test
        void Should_OutputRankWithPairOfTwo() {
            assertPair(Value.TWO, handThatRanksAtPairWithTwo);
        }
        @Test
        void Should_OutputRankWithPairOfTen() {
            assertPair(Value.FOUR, handThatRanksAtPairWithFour);
        }

        private void assertPair(Value expectedValue, Hand handThatRanksAtPairWithValue) {
            final Category expectedCategory = Category.PAIR;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_PAIR + expectedValue + RankEvaluation.SENTENCE_END;
            assertRank(Category.HIGH_CARD, expectedOutput, handThatRanksAtPairWithValue);
        }
    }

    private void assertRank(Category expectedCategory, String expectedOutput, Hand hand) {
        RankEvaluation evaluation = new RankEvaluation(hand);
        Assertions.assertEquals(expectedOutput, evaluation.toString());
    }
}

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
            assertHighCard(handThatRanksAtHighCardWithSix, Value.SIX);
        }

        @Test
        @DisplayName(OUTPUT_SHOULD_RANK_THE_HAND_AS + "high card, with high card of ten.")
        void Should_OutputRankWithHighCardTen() {
            assertHighCard(handThatRanksAtHighCardWithTen, Value.TEN);
        }

        private void assertHighCard(Hand handThatRanksAtHighCardWithValue, Value value) {
            RankEvaluation evaluation = new RankEvaluation(handThatRanksAtHighCardWithValue);
            String expectedOutput = "Player's hand reached category HIGH_CARD " +
                    "with the high card " + value + ".";
            Assertions.assertEquals(expectedOutput, evaluation.toString());
        }
    }

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"Pair\"")
    class WhenGivenHandRanksAsPair {
        @Test
        void Should_OutputRankWithPairOfTwo() {
            assertPair(handThatRanksAtPairWithTwo, Value.TWO);
        }
        @Test
        void Should_OutputRankWithPairOfTen() {
            assertPair(handThatRanksAtPairWithFour, Value.FOUR);
        }

        private void assertPair(Hand handThatRanksAtPairWithSix, Value value) {
            RankEvaluation evaluation = new RankEvaluation(handThatRanksAtPairWithSix);
            String expectedOutput = "Player's hand reached category PAIR " +
                    "with a pair of " + value + ".";
            Assertions.assertEquals(expectedOutput, evaluation.toString());
        }
    }

    @Test
    void Should_OutputRankWithCategoryAndKicker_WhenGivenHandRankIsPair() {
        RankEvaluation evaluation = new RankEvaluation(handThatRanksAtPairWithTwo);
        String outputForPairOfTwo = "Player's hand reached category PAIR with a pair of TWO.";
        Assertions.assertEquals(outputForPairOfTwo, evaluation.toString());

    }
}

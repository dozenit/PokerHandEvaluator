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
    Card fiveOfDiamonds = new Card(Value.FIVE, Suit.DIAMONDS);
    Card tenOfDiamonds = new Card(Value.TEN, Suit.DIAMONDS);

    Card tenOfSpades = new Card(Value.TEN, Suit.SPADES);
    Card jackOfSpades = new Card(Value.JACK, Suit.SPADES);

    Hand handThatRanksAtHighCardWithSix = new Hand(twoOfClubs, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs);
    Hand handThatRanksAtHighCardWithTen = new Hand(tenOfDiamonds, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs);

    Hand handThatRanksAtPairWithFourAndKickerTen = new Hand(tenOfDiamonds, fourOfDiamonds, fourOfClubs, fiveOfClubs, sixOfClubs);
    Hand handThatRanksAtPairWithTenAndKickerSix = new Hand(tenOfDiamonds, tenOfSpades, fourOfClubs, fiveOfClubs, sixOfClubs);
    Hand handThatRanksAtPairWithTenAndKickerJack = new Hand(tenOfDiamonds, tenOfSpades, jackOfSpades, fiveOfClubs, sixOfClubs);

    Hand handThatRanksAtTwoPairWithTenHighAndFiveLowAndKickerSix = new Hand(tenOfDiamonds, tenOfSpades, fiveOfDiamonds, fiveOfClubs, sixOfClubs);

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"High Card\"")
    class WhenGivenHandRanksAsHighCard {

        public static final String HIGH_CARD_WITH_HIGH_CARD_OF = "high card, with high card of ";

        @Test
        @DisplayName(OUTPUT_SHOULD_RANK_THE_HAND_AS + HIGH_CARD_WITH_HIGH_CARD_OF + "six.")
        void Should_OutputRankWithHighCardSix() {
            assertHighCard(Value.SIX, handThatRanksAtHighCardWithSix);
        }

        @Test
        @DisplayName(OUTPUT_SHOULD_RANK_THE_HAND_AS + HIGH_CARD_WITH_HIGH_CARD_OF + "ten.")
        void Should_OutputRankWithHighCardTen() {
            assertHighCard(Value.TEN, handThatRanksAtHighCardWithTen);
        }

        private void assertHighCard(Value expectedValue, Hand handThatRanksAtHighCardWithValue) {
            final Category expectedCategory = Category.HIGH_CARD;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_HIGH_CARD + expectedValue + RankEvaluation.SENTENCE_END;
            assertRank(expectedCategory, expectedOutput, handThatRanksAtHighCardWithValue);
        }
    }

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"Pair\"")
    class WhenGivenHandRanksAsPair {

        @Test
        @DisplayName("Should output pair with kicker when kicker is greater than pair (Four with kicker Ten)")
        void Should_OutputRankWithPairOfFourAndKickerTen() {
            assertPair(Value.FOUR, Value.TEN, handThatRanksAtPairWithFourAndKickerTen);
        }

        @Test
        @DisplayName("Should output pair with kicker when kicker is smaller than pair")
        void Should_OutputRankWithPairOfTenAndKickerSix() {
            assertPair(Value.TEN, Value.SIX, handThatRanksAtPairWithTenAndKickerSix);
        }

        @Test
        @DisplayName("Should output pair with kicker when kicker is greater than pair (Ten with kicker Jack)")
        void Should_OutputRankWithPairOfTenAndKickerJack() {
            assertPair(Value.TEN, Value.JACK, handThatRanksAtPairWithTenAndKickerJack);
        }

        private void assertPair(Value expectedPair, Value expectedKicker, Hand handThatRanksAtPair) {
            final Category expectedCategory = Category.PAIR;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_A_PAIR_OF + expectedPair
                    + RankEvaluation.AND_THE_KICKER + expectedKicker
                    + RankEvaluation.SENTENCE_END;
            assertRank(expectedCategory, expectedOutput, handThatRanksAtPair);
        }
    }

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"Two Pair\"")
    class WhenGivenHandRanksAsTwoPair {

        @Test
        @DisplayName("Should output two pair and list the higher pair first")
        void Should_OutputRankWithTwoPairAndListTheHigherPairFirst() {
            assertTwoPair(Value.TEN, Value.FIVE, handThatRanksAtTwoPairWithTenHighAndFiveLowAndKickerSix);
        }

        // TODO: Add Kicker
        private void assertTwoPair(Value expectedHighPair, Value expectedLowPair, Hand handThatRanksAtTwoPair) {
            final Category expectedCategory = Category.TWO_PAIR;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_A_HIGH_PAIR_OF + expectedHighPair
                    + RankEvaluation.A_LOW_PAIR_OF + expectedLowPair
                    + RankEvaluation.SENTENCE_END;
            assertRank(expectedCategory, expectedOutput, handThatRanksAtTwoPair);
        }
    }

    @DisplayName("Output should equal the string for the user that informs about the exact rank for a given hand")
    private void assertRank(Category expectedCategory, String expectedOutput, Hand hand) {
        RankEvaluation evaluation = new RankEvaluation(hand);
        Assertions.assertEquals(expectedOutput, evaluation.toString());
    }
}

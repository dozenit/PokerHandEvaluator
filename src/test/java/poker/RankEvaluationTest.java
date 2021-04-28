package poker;

import org.junit.jupiter.api.*;

@DisplayName("Rank evaluation and string checks.")
public class RankEvaluationTest {

    public static final String WHEN_GIVEN_HAND_RANKS_AT_CATEGORY = "When given hand ranks at category ";

    Card twoOfClubs = new Card(Value.TWO, Suit.CLUBS);
    Card threeOfClubs = new Card(Value.THREE, Suit.CLUBS);
    Card fourOfClubs = new Card(Value.FOUR, Suit.CLUBS);
    Card fiveOfClubs = new Card(Value.FIVE, Suit.CLUBS);
    Card sixOfClubs = new Card(Value.SIX, Suit.CLUBS);
    Card tenOfClubs = new Card(Value.TEN, Suit.CLUBS);

    Card fourOfDiamonds = new Card(Value.FOUR, Suit.DIAMONDS);
    Card fiveOfDiamonds = new Card(Value.FIVE, Suit.DIAMONDS);
    Card tenOfDiamonds = new Card(Value.TEN, Suit.DIAMONDS);

    Card fourOfSpades = new Card(Value.FOUR, Suit.SPADES);
    Card tenOfSpades = new Card(Value.TEN, Suit.SPADES);
    Card jackOfSpades = new Card(Value.JACK, Suit.SPADES);


    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"High Card\"")
    class WhenGivenHandRanksAsHighCard {

        @Test
        void Should_OutputRank_ListingHighCard_WhenItsCardIsPassedAsLastParameterInHand() {
            Hand handThatRanksAtHighCardWithSix = new Hand(
                    twoOfClubs, threeOfClubs, fourOfClubs,
                    fiveOfClubs, sixOfClubs);
            assertHighCard(Value.SIX, handThatRanksAtHighCardWithSix);
        }

        @Test
        void Should_OutputRank_ListingHighCard_WhenItsCardIsPassedAsFirstParameterInHand() {
            Hand handThatRanksAtHighCardWithTen = new Hand(
                    tenOfDiamonds, threeOfClubs, fourOfClubs,
                    fiveOfClubs, sixOfClubs);
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
        void Should_OutputRank_ListingPairThenKicker_WhenKickerIsHigherThanPair() {
            Hand handThatRanksAtPairWithFourAndKickerTen = new Hand(
                    tenOfDiamonds, fourOfDiamonds, fourOfClubs,
                    fiveOfClubs, sixOfClubs);

            assertPair(Value.FOUR, Value.TEN, handThatRanksAtPairWithFourAndKickerTen);
        }

        @Test
        void Should_OutputRankWithPair_ListingPairThenKicker_WhenKickerIsLowerThanPair() {
            Hand handThatRanksAtPairWithTenAndKickerSix = new Hand(
                    tenOfDiamonds, tenOfSpades, fourOfClubs,
                    fiveOfClubs, sixOfClubs);
            assertPair(Value.TEN, Value.SIX, handThatRanksAtPairWithTenAndKickerSix);
        }

        @Test
        void Should_OutputRankWithPair_ListingPairThenKicker_WhenKickerCardIsPassedAsArgumentInBetweenThePairCards() {
            Hand handThatRanksAtPairWithTenAndKickerJack = new Hand(
                    tenOfDiamonds, jackOfSpades, tenOfSpades,
                    fiveOfClubs, sixOfClubs);
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
        void Should_OutputRankWithTwoPair_ListingHigherPairThenLowerPairThenKicker_WhenKickerIsHigherThanLowPairButLowerThanHighPair() {
            Hand handThatRanksAtTwoPairWithTenHighAndFiveLowAndKickerSix = new Hand(
                    tenOfDiamonds, tenOfSpades, fiveOfDiamonds,
                    fiveOfClubs, sixOfClubs);
            assertTwoPair(Value.TEN, Value.FIVE, Value.SIX,
                    handThatRanksAtTwoPairWithTenHighAndFiveLowAndKickerSix);
        }

        private void assertTwoPair(Value expectedHighPair, Value expectedLowPair,
                                   Value expectedKicker, Hand handThatRanksAtTwoPair) {
            final Category expectedCategory = Category.TWO_PAIR;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_A_HIGH_PAIR_OF + expectedHighPair
                    + RankEvaluation.A_LOW_PAIR_OF + expectedLowPair
                    + RankEvaluation.AND_THE_KICKER + expectedKicker
                    + RankEvaluation.SENTENCE_END;
            assertRank(expectedCategory, expectedOutput, handThatRanksAtTwoPair);
        }
    }

    @Nested
    @DisplayName(WHEN_GIVEN_HAND_RANKS_AT_CATEGORY + "\"Three of a Kind\"")
    class WhenGivenHandRanksAsThreeOfAKind {

        @Test
        void Should_OutputRankWithTwoPair_ListingThreeOfAKind_WhenItContainsTheHighestValue() {
            Hand handThatRanksAtThreeOfAKindWithTen = new Hand(
                    tenOfDiamonds, tenOfSpades, tenOfClubs,
                    fiveOfClubs, sixOfClubs);
            assertThreeOfAKind(Value.TEN,
                    handThatRanksAtThreeOfAKindWithTen);
        }

        @Test
        void Should_OutputRankWithTwoPair_ListingThreeOfAKind_WhenItContainsTheLowestValue() {
            Hand handThatRanksAtThreeOfAKindWithFour = new Hand(
                    fourOfClubs, fourOfDiamonds, tenOfClubs,
                    fiveOfClubs, fourOfSpades);
            assertThreeOfAKind(Value.FOUR,
                    handThatRanksAtThreeOfAKindWithFour);
        }

        private void assertThreeOfAKind(Value expectedThreeOfAKind, Hand handThatRanksAtThreeOfAKind) {
            final Category expectedCategory = Category.THREE_OF_A_KIND;
            String expectedOutput = RankEvaluation.HAND_HAS_REACHED + expectedCategory.toString()
                    + RankEvaluation.WITH_THE_TRIAD + expectedThreeOfAKind
                    + RankEvaluation.SENTENCE_END;
            assertRank(expectedCategory, expectedOutput, handThatRanksAtThreeOfAKind);
        }
    }

    @DisplayName("Output should equal the string for the user that informs about the exact rank for a given hand")
    private void assertRank(Category expectedCategory, String expectedOutput, Hand hand) {
        RankEvaluation evaluation = new RankEvaluation(hand);
        Assertions.assertEquals(expectedOutput, evaluation.toString());
    }
}

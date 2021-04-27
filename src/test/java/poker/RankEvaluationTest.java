package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class RankEvaluationTest {
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
    Hand handThatRanksAtPairWithThree = new Hand(tenOfDiamonds, fourOfDiamonds, fourOfClubs, fiveOfClubs, sixOfClubs);

    @Test
    void Should_OutputRankWithCategoryAndKicker_WhenGivenHandRankIsHighCard() {
        RankEvaluation evaluation = new RankEvaluation(handThatRanksAtHighCardWithSix);
        String outputForHighCardOfSix = "Player's hand reached category HIGH_CARD with the high card SIX.";
        Assertions.assertEquals(outputForHighCardOfSix, evaluation.toString());

        RankEvaluation evaluation2 = new RankEvaluation(handThatRanksAtHighCardWithTen);
        String outputForHighCardOfTen = "Player's hand reached category HIGH_CARD with the high card TEN.";
        Assertions.assertEquals(outputForHighCardOfTen, evaluation2.toString());
    }

    @Test
    void Should_OutputRankWithCategoryAndKicker_WhenGivenHandRankIsPair() {
        RankEvaluation evaluation = new RankEvaluation(handThatRanksAtPairWithTwo);
        String outputForPairOfTwo = "Player's hand reached category PAIR with a pair of TWO.";
        Assertions.assertEquals(outputForPairOfTwo, evaluation.toString());

    }
}

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

    Card tenOfDiamonds = new Card(Value.TEN, Suit.DIAMONDS);

    Hand handThatRanksAtHighCardWithSix = new Hand(twoOfClubs, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs);
    Hand handThatRanksAtHighCardWithTen = new Hand(tenOfDiamonds, threeOfClubs, fourOfClubs, fiveOfClubs, sixOfClubs);

    @Test
    void Should_OutputRankWithCategoryAndKicker_WhenGivenHandRankIsHighCard() {
        RankEvaluation evaluation = new RankEvaluation(handThatRanksAtHighCardWithSix);
        String outPutForHighCardWithSix = "Player's hand reached category HIGH_CARD with high card SIX.";
        Assertions.assertEquals(outPutForHighCardWithSix, evaluation.toString());

        RankEvaluation evaluation2 = new RankEvaluation(handThatRanksAtHighCardWithTen);
        String outPutForHighCardWithTen = "Player's hand reached category HIGH_CARD with high card TEN.";
        Assertions.assertEquals(outPutForHighCardWithTen, evaluation2.toString());
    }
}

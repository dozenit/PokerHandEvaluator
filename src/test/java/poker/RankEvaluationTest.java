package poker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class RankEvaluationTest {
    Card card1 = new Card(Value.TWO, Suit.CLUBS);
    Card card2 = new Card(Value.THREE, Suit.CLUBS);
    Card card3 = new Card(Value.FOUR, Suit.CLUBS);
    Card card4 = new Card(Value.FIVE, Suit.CLUBS);
    Card card5 = new Card(Value.SIX, Suit.CLUBS);
    Card card6 = new Card(Value.TEN, Suit.DIAMONDS);

    Hand handThatRanksAtHighCardWithSix = new Hand(new HashSet<>(Set.of(card1, card2, card3, card4, card5)));
    Hand handThatRanksAtHighCardWithTen = new Hand(new HashSet<>(Set.of(card6, card2, card3, card4, card5)));

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

package poker;

public class RankEvaluation {

    private Category category;
    private Card highCard;

    public RankEvaluation(Hand hand) {
        evaluateCategory(hand);
    }

    // TODO (Milestone: Hand-Ranking Categories) implement evaluation for all categories
    private void evaluateCategory(Hand hand) {
        evaluateHighCard(hand);
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

    // TODO (Milestone: Hand-Ranking Categories) change string for other categories.
    public String toString() {
        final String reachedRank = category.toString();
        final String highCard = this.highCard.getValue().toString(); // + " of " + this.highCard.getSuit();

        return "Player's hand reached category " + reachedRank + " with high card " + highCard + ".";
    }
}

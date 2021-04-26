package poker;

public class Card implements Comparable<Card>{
    private final Value value;
    private final Suit suit;

    public Card(Value value,
                Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public int compareTo(Card that) {
        final int HIGHER = 1;
        final int EQUAL = 0;
        final int LOWER = -1;

        int thisValue = this.value.ordinal();
        int thatValue = that.getValue().ordinal();

        if ( thisValue > thatValue) {
            return HIGHER;
        } else if (thisValue == thatValue) {
            return EQUAL;
        } else {
            return LOWER;
        }
    }
}

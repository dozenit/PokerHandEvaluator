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
        return Integer.compare(this.value.ordinal(), that.getValue().ordinal());
    }
}

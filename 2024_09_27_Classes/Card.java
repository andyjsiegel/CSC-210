public class Card {
    private String rank;
    private String suit;
    
    private final static String[] RANKS = {"ace", "two", "three", "four",
                                           "five", "six", "seven",
                                           "eight", "nine", "ten",
                                           "jack", "queen", "king"};

    
    public final static String[] SUITS = {"diamonds", "clubs", "hearts", "spades"};
    
    public Card(String rank, String suit) {
        if (validRank(rank)) this.rank = rank;
        if (validSuit(suit)) this.suit = suit;
    }
    
    public Card(int rank, String suit) {
        if (rank >= 1 && rank <= 13) this.rank = RANKS[rank-1];
        if (validSuit(suit)) this.suit = suit;
    }

    private Boolean inArray(String value, String[] arr) {
        for (String element : arr) {
            if (element == value) {
                return true;
            }
        }
        return false;
    }
    
    private Boolean validRank(String rank) {
        return inArray(rank, RANKS);
    }
    
    private Boolean validSuit(String suit) {
        return inArray(suit, SUITS);
    }
    
    
    public void setRank(String rank) {
        if (validRank(rank)) this.rank = rank;
    }
    
    public void setRank(int rank) { // overloading
        if (rank >= 1 && rank <= 13) this.rank = RANKS[rank-1];
    }
    
    public void setSuit(String suit) {
        if (validSuit(suit)) this.suit = suit;
    }
    
    public String getSuit() {
        return suit;
    }
    
    public String getRank() {
        return rank;
    }
    
    public String toString() {
        return rank + " of " + suit;
    }

}
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private String color;
    
    public Deck(String color) {
        this.color = color;
        for (String s : Card.SUITS) {
            for (int i = 1; i <= 13; i++) {
                Card thisCard = new Card(i, s);
                cards.add(thisCard);
            }
            
        }
    }
    
    public void printCards() {
        for (String s : Card.SUITS) {
            for (int i = 1; i <= 13; i++) {
                Card thisCard = new Card(i, s);
                System.out.println(thisCard);
            }
            
        }
    }

    public String toString() {
        ArrayList<String> cardStrings = new ArrayList<>();
        for(Card c: cards) {
            cardStrings.add(c.toString());
        }
        return String.join("\n", cardStrings);
    }
    
    public Card getCard(int index) {
        return cards.get(index);
    }
    
    public String getColor() {
        return color;
    }

}
public class Burger extends Food {
    private int numPatties;
    private boolean hasCheese;
    
    public Burger(int numPatties, boolean hasCheese) {
        super(430.0, "Burger", 1.0, false);
        this.numPatties = numPatties;
        this.hasCheese = hasCheese;
    }

    
}

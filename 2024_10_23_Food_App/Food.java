abstract class Food {
    protected enum FoodGroup {
        DAIRY, MEAT, VEGETABLE, GRAIN, FRUIT
    }
    protected double calories;
    protected String foodName;
    protected double servingSize;
    protected boolean isBeverage;

    public Food(double calories, String foodName, double servingSize, boolean isBeverage) {
        this.calories = calories;
        this.foodName = foodName;
        this.servingSize = servingSize;
        this.isBeverage = isBeverage;
    }
}

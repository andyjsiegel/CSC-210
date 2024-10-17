// package com.gradescope.garden;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Garden {
    private ArrayList<ArrayList<Plot>> plotsList2D;

    public Garden(int rows, int columns) {
        plotsList2D = new ArrayList<>();    
        for (int i = 0; i < rows; i++) {
            ArrayList<Plot> row = new ArrayList<>();
            for (int j = 0; j < columns; j++) {
                row.add(new Plot());
            }
            plotsList2D.add(row);
        }
    }

    private static final HashSet<String> VALID_FLOWERS = new HashSet<>();
    static {
        VALID_FLOWERS.add("iris");
        VALID_FLOWERS.add("lily");
        VALID_FLOWERS.add("rose");
        VALID_FLOWERS.add("daisy");
        VALID_FLOWERS.add("tulip");
        VALID_FLOWERS.add("sunflower");
    }

    private static final HashSet<String> VALID_VEGETABLES = new HashSet<>();
    static {
        VALID_VEGETABLES.add("garlic");
        VALID_VEGETABLES.add("zucchini");
        VALID_VEGETABLES.add("tomato");
        VALID_VEGETABLES.add("yam");
        VALID_VEGETABLES.add("lettuce");
    }

    private static final HashSet<String> VALID_TREES = new HashSet<>();
    static {
        VALID_TREES.add("oak");
        VALID_TREES.add("willow");
        VALID_TREES.add("banana");
        VALID_TREES.add("coconut");
        VALID_TREES.add("pine");
    }

    private static final HashSet<String> VALID_FRUITS = new HashSet<>();
    static {
        VALID_FRUITS.add("apple");
        VALID_FRUITS.add("banana");
        VALID_FRUITS.add("orange");
        VALID_FRUITS.add("pineapple");
        VALID_FRUITS.add("mango");
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        // first, iterate thru all rows of the garden
        for (int row = 0; row < plotsList2D.size(); row++) {
            // new iterator: plotRow
            for (int plotRow = 0; plotRow < 5; plotRow++) {
                // finally, iterate through all columns of the row:
                for (int col = 0; col < plotsList2D.get(row).size(); col++) {
                    // get the plot of the row and column
                    Plot plot = plotsList2D.get(row).get(col);
                    
                    String[] plotLines = plot.toString().split("\n");
                    // get the plot lines of the row (most inner loop is the
                    // column, so it will iterate thru the columns of the garden)
                    result.append(plotLines[plotRow]);
                }
                result.append("\n"); // Newline after each row of plots
            }
        }
        return result.toString();
    }

    private void addPlant(int[] coordinates, String plantName) {
        Plot tempPlot = getPlot(coordinates, "plant");
        if(tempPlot == null) {
            return;
        }
        if(VALID_FLOWERS.contains(plantName)) {
            tempPlot.addPlant(new Flower(plantName));
        }
        if(VALID_VEGETABLES.contains(plantName)) {
            tempPlot.addPlant(new Vegetable(plantName));
        }
        if(VALID_TREES.contains(plantName)) {
            tempPlot.addPlant(new Tree(plantName));
        }
        if(VALID_FRUITS.contains(plantName)) {
            tempPlot.addPlant(new Fruit(plantName));
        }
        plotsList2D.get(coordinates[0]).set(coordinates[1], tempPlot);
    }

    private Plot getPlot(int[] coordinates, String commandName) {
        try {
            Plot tempPlot = plotsList2D.get(coordinates[0]).get(coordinates[1]);
            return tempPlot;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Can't " + commandName + " there.\n");
            return null;
        }
    }

    private void grow(int numTimes) {
        for (ArrayList<Plot> plotList : plotsList2D) {
            for (Plot tempPlot : plotList) {
                for (int i = 0; i < numTimes; i++) {
                    tempPlot.grow();
                }
            }
        }
    }


    private void grow(int numTimes, int[] coordinates) {
        Plot tempPlot = getPlot(coordinates, "grow");
        if(tempPlot == null) {
            return;
        }
        for (int i = 0; i < numTimes; i++) {
            tempPlot.grow();
        }
    }
    private void grow(int numTimes, String plantName) {
        for (ArrayList<Plot> plotList : plotsList2D) {
            for (Plot tempPlot : plotList) {
                for (int i = 0; i < numTimes; i++) {
                    if(tempPlot.getPlantName().toLowerCase().equals(plantName.toLowerCase())) tempPlot.grow();
                }
            }
        }
    }
    private void grow(String plantClass, int numTimes) {
        for (ArrayList<Plot> plotList : plotsList2D) {
            for (Plot tempPlot : plotList) {
                for (int i = 0; i < numTimes; i++) {
                    if(tempPlot.getPlantedPlant() != null) {
                        String tempPlotPlantClass = tempPlot.getPlantedPlant().getClass().toString().toLowerCase();
                        if(tempPlotPlantClass.contains(plantClass.toLowerCase())) tempPlot.grow();
                    }
                }
            }
        }
    }

    // vegetable methods
    private void harvest() {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Vegetable")) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }

    private void harvest(int[] coordinates) {
        Plot tempPlot = getPlot(coordinates, "harvest");
        if(tempPlot == null) {
            return;
        }
        Plant plant = tempPlot.getPlantedPlant();
        String plantClass = plant.getClass().toString();
        if(plantClass.contains("Vegetable")) {
            tempPlot.removePlant();
        }  else System.out.println("Can't harvest there.\n");
    }

    private void harvest(String plantName) {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Vegetable") && plant.getName().equals(plantName)) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }

    // flower methods
    private void pick() {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Flower")) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }
    private void pick(int[] coordinates) {
        Plot tempPlot = getPlot(coordinates, "pick");
        if(tempPlot == null) {
            return;
        }
        Plant plant = tempPlot.getPlantedPlant();
        String plantClass = plant.getClass().toString();
        if(plantClass.contains("Flower")) {
            tempPlot.removePlant();
        }  else System.out.println("Can't pick there.\n");
    }

    private void pick(String plantName) {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {                    
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Flower") && plant.getName().equals(plantName)) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }

    // tree methods
    private void cut() {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Tree")) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }

    private void cut(int[] coordinates) {
        Plot tempPlot = getPlot(coordinates, "cut");
        if(tempPlot == null) {
            return;
        }
        Plant plant = tempPlot.getPlantedPlant();
        String plantClass = plant.getClass().toString();
        if(plantClass.contains("Tree")) {
            tempPlot.removePlant();
        }  else System.out.println("Can't cut there.\n");
    }

    private void cut(String plantName) {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Tree") && plant.getName().equals(plantName)) {
                        tempPlot.removePlant();
                    }
                }
            }
        }
    }
    
    // fruit methods
    private void eat() {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Fruit")) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }

    private void eat(int[] coordinates) {
        Plot tempPlot = getPlot(coordinates, "eat");
        if(tempPlot == null) {
            return;
        }
        Plant plant = tempPlot.getPlantedPlant();
        String plantClass = plant.getClass().toString();
        if(plantClass.contains("Fruit")) {
            tempPlot.removePlant();
        }  else System.out.println("Can't eat there.\n");
    }

    private void eat(String plantName) {
        for(int i = 0; i < plotsList2D.size(); i++) {
            for(int j = 0; j < plotsList2D.get(i).size(); j++) {
                Plot tempPlot = plotsList2D.get(i).get(j);
                Plant plant = tempPlot.getPlantedPlant();
                if(plant != null) {
                    String plantClass = plant.getClass().toString();
                    if(plantClass.contains("Fruit") && plant.getName().equals(plantName)) {
                        tempPlot.removePlant();
                    } 
                }
            }
        }
    }

    private int[] parseCoords(String coordinates) {
        int x_coord = Integer.parseInt(coordinates.split(",")[0].replace('(', ' ').trim());
        int y_coord = Integer.parseInt(coordinates.split(",")[1].replace(')', ' ').trim());
        int[] coordsTuple = {x_coord, y_coord};
        return coordsTuple;
    }

    public void parseCommand(String line) {
        if(line.toLowerCase().equals("print")) {
            System.out.println(this);
        } else {
            String[] arguments = line.split(" ");
            String commandName = arguments[0].toLowerCase().trim();
            arguments = Arrays.copyOfRange(arguments, 1, arguments.length);
            if(commandName.equals("plant")) {
                String coordinates = arguments[0];
                String plantName = arguments[1];
                int[] coords = parseCoords(coordinates);
                addPlant(coords, plantName);
            }
            if(commandName.equals("pick") && arguments.length < 1) {
                pick();
            } else if(commandName.equals("pick")) {
                try {
                    int[] coords = parseCoords(arguments[0]);
                    pick(coords);
                } catch(NumberFormatException e) {
                    pick(arguments[0]);
                }
            }
            if(commandName.equals("harvest") && arguments.length < 1) {
                harvest();
            } else if(commandName.equals("harvest")) {
                try {
                    int[] coords = parseCoords(arguments[0]);
                    harvest(coords);
                } catch(NumberFormatException e) {
                    harvest(arguments[0]);
                }
            }
            if(commandName.equals("cut") && arguments.length < 1) {
                cut();
            } else if(commandName.equals("cut")) {
                try {
                    int[] coords = parseCoords(arguments[0]);
                    cut(coords);
                } catch(NumberFormatException e) {
                    cut(arguments[0]);
                }
            }
            if(commandName.equals("eat") && arguments.length < 1) {
                eat();
            } else if(commandName.equals("eat")) {
                try {
                    int[] coords = parseCoords(arguments[0]);
                    eat(coords);
                } catch(NumberFormatException e) {
                    eat(arguments[0]);
                }
            } else if(commandName.equals("grow")) {
                // System.out.println(Arrays.toString(arguments));
                int numTimes = Integer.parseInt(arguments[0]);
                if(arguments.length > 1) {
                    //args[1] is either coords, plantType (Tree), or plantName "rose"
                    try {
                        int[] coords = parseCoords(arguments[1]);
                        grow(numTimes, coords);
                    } catch(NumberFormatException e) {
                        if (Arrays.asList("tree", "vegetable", "flower", "fruit").contains(arguments[1].toLowerCase())) {
                            //grow on class name
                            grow(arguments[1], numTimes);
                        } else {
                            //grow on plant name ie tulip
                            grow(numTimes, arguments[1]);
                        }
                    }
                } else {
                    grow(numTimes);
                }
            }
        }
    }
}

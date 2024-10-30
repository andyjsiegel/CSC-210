package com.gardengui;
// package com.gradescope.garden;
public class Plot {
    private char[][] asciiRepresentation = {
        {'.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.'},
    };

    private boolean hasPlant = false;
    private Plant plantedPlant = null;
    private String plantType = "";
    private char plantChar = '.'; // default to '.'
    private String plantName = "";

    public String getPlantName() {
        return plantName;
    }

    public String getPlantType() {
        return plantType;
    }
    public Plant getPlantedPlant() {
        return plantedPlant;
    }

    public String toString() {
        String repr = "";
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                repr += asciiRepresentation[i][j];
            }   
            repr += "\n";
        }
        return repr.trim();
    }
    
    public void addPlant(Plant plant) {
        if(hasPlant) {
            // System.out.println("There is already a " + plantType + " here.");
        }
        else {
            plantedPlant = plant;
            plantName = plant.getName();
            plantChar = plantName.toLowerCase().charAt(0);
            hasPlant = true;
            // [y][x]
            if(plant instanceof Vegetable) {
                asciiRepresentation[0][2] = plantChar;
                this.plantType = "vegetable";
            }
            if(plant instanceof Flower) {
                asciiRepresentation[2][2] = plantChar;
                this.plantType = "flower";
            }
            if(plant instanceof Tree) {
                asciiRepresentation[4][2] = plantChar;
                this.plantType = "tree";
            }
            if(plant instanceof Fruit) {
                asciiRepresentation[2][0] = plantChar;
                this.plantType = "fruit";
            }
        }
    }
    
    private void flowerGrowHelper(int[][] positions) {
        for (int[] pos : positions) {
            asciiRepresentation[pos[0]][pos[1]] = plantChar;
        }
    }
    
    public void grow() {
        if(plantType.equals("flower")) {
            // [y][x]
            final int[][] growthPositions = {
                {1, 2}, {3, 2}, {2, 1}, {2, 3}
            };
            final int[][] secondGrowthPositions = {
                {1, 1}, {1, 3}, {3, 1}, {3, 3}, {0, 2}, {4, 2}, {2, 0}, {2, 4}
            };
            final int[][] thirdGrowthPositions = {
                {0,1}, {0,3}, {1,0}, {1,4}, {4,1}, {4,3}, {3,0}, {3,4}, 
            };
            final int[][] finalGrowthPositions = {
                {0,0}, {0,4}, {4,0}, {4,4}
            };
            if(asciiRepresentation[1][2] == '.') {
                flowerGrowHelper(growthPositions);
            } else if(asciiRepresentation[0][2] == '.') {
                flowerGrowHelper(secondGrowthPositions);
            } else if(asciiRepresentation[0][1] == '.') {
                flowerGrowHelper(thirdGrowthPositions);
            } else {
                flowerGrowHelper(finalGrowthPositions);
            }
        }
        if(plantType.equals("fruit")) {
            boolean exitedNormally = true;
            for(int i = 0; i < 5; i++) {
                if(asciiRepresentation[2][i] == '.') {
                    asciiRepresentation[2][i] = plantChar;
                    exitedNormally = false;
                    break;
                }
            }
            if(exitedNormally) {
                // System.out.println("Fruit has reached maximum growth.");
            }
        }
        if(plantType.equals("vegetable")) {
            boolean exitedNormally = true;
            for(int i = 0; i < 5; i++) {
                if(asciiRepresentation[i][2] == '.') {
                    asciiRepresentation[i][2] = plantChar;
                    exitedNormally = false;
                    break;
                }
            }
            if(exitedNormally) {
                // System.out.println("Vegetable has reached maximum growth.");
            }
        }
        if(plantType.equals("tree")) {
            boolean exitedNormally = true;
            for(int i = 4; i >= 0; i--) {
                if(asciiRepresentation[i][2] == '.') {
                    asciiRepresentation[i][2] = plantChar;
                    exitedNormally = false;
                    break;
                }
            }
            if(exitedNormally) {
                // System.out.println("Tree has reached maximum growth.");
            }
        }
    }

    public void removePlant() {
        hasPlant = false;
        plantedPlant = null;
        plantType = "";
        plantChar = '.';
        asciiRepresentation = new char[][] {
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
        };
    }
}

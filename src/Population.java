
public class Population {
    Room[] individuals;
    int numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms;
    /*
     * Constructors
     */
    // Create a population
    public Population(int numBedrooms, int numBathrooms,int numLivingRooms,int numKitchens, int numDiningRooms, boolean initialize) {
        this.numBathrooms = numBathrooms;
        this.numBedrooms = numBedrooms;
        this.numKitchens = numKitchens;
        this.numLivingRooms = numLivingRooms;
        this.numDiningRooms = numDiningRooms;
    	int populationSize = numBedrooms + numBathrooms + numLivingRooms + numKitchens + numDiningRooms;
    	individuals = new Room[populationSize];
        // initialize population
        if (initialize) {
            // Loop and create bedrooms
        	int j = 0;
            for (int i = 0; i < numBedrooms; i++) {
            	double brSquareFootage = MTBGame.randInt(Bedroom.MIN_SQUARE_FOOTAGE, Bedroom.MAX_SQUARE_FOOTAGE);
            	int[] dimensions = new int[2];
            	dimensions = getDimensions(brSquareFootage);
            	int height, width;
            	width = dimensions[0];
            	height = dimensions[1];
            	System.out.println("Random Bedroom size: " + width + ":" + height);
                Room newIndividual = new Bedroom(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i, newIndividual);
                j = i + 1;
            }
            // then create living rooms
            for (int i = 0; i < numLivingRooms; i++) {
            	double brSquareFootage = MTBGame.randInt(Bedroom.MIN_SQUARE_FOOTAGE, Bedroom.MAX_SQUARE_FOOTAGE);
            	int[] dimensions = new int[2];
            	dimensions = getDimensions(brSquareFootage);
            	int height, width;
            	width = dimensions[0];
            	height = dimensions[1];
            	System.out.println("Random Living Room size: " + width + ":" + height);
                Room newIndividual = new LivingRoom(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i + j, newIndividual);
                if(i == numLivingRooms - 1)
                	j = j + i + 1;
            }
            // then bathrooms
            for (int i = 0; i < numBathrooms; i++) {
            	double brSquareFootage = MTBGame.randInt(Bedroom.MIN_SQUARE_FOOTAGE, Bedroom.MAX_SQUARE_FOOTAGE);
            	int[] dimensions = new int[2];
            	dimensions = getDimensions(brSquareFootage);
            	int height, width;
            	width = dimensions[0];
            	height = dimensions[1];
            	System.out.println("Random Bathroom size: " + width + ":" + height);
                Room newIndividual = new Bathroom(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i + j, newIndividual);
                if(i == numBathrooms - 1)
                	j = j + i + 1;
            }
            // then kitchens
            for (int i = 0; i < numKitchens; i++) {
            	double brSquareFootage = MTBGame.randInt(Bedroom.MIN_SQUARE_FOOTAGE, Bedroom.MAX_SQUARE_FOOTAGE);
            	int[] dimensions = new int[2];
            	dimensions = getDimensions(brSquareFootage);
            	int height, width;
            	width = dimensions[0];
            	height = dimensions[1];
            	System.out.println("Random Kitchen size: " + width + ":" + height);
                Room newIndividual = new Kitchen(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i + j, newIndividual);
                if(i == numKitchens - 1)
                	j = j + i + 1;
            }
            // then dining rooms
            for (int i = 0; i < numKitchens; i++) {
            	double brSquareFootage = MTBGame.randInt(DiningRoom.MIN_SQUARE_FOOTAGE, DiningRoom.MAX_SQUARE_FOOTAGE);
            	int[] dimensions = new int[2];
            	dimensions = getDimensions(brSquareFootage);
            	int height, width;
            	width = dimensions[0];
            	height = dimensions[1];
            	System.out.println("Random Dining Room size: " + width + ":" + height);
                Room newIndividual = new DiningRoom(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i + j, newIndividual);
                if(i == numKitchens - 1)
                	j = j + i + 1;
            }
            System.out.println("Number of individuals: " + individuals.length);
            //grid = fillGrid(0, 0, grid);
        }
    }
    
    private int[] getDimensions(double squareFootage){
    	int[] dimensions = new int[2];
    	double heightWidthRatio =  MTBGame.randInt(60, 100) * .01;
    	int vertical = MTBGame.randInt(0, 1);
    	int width, height;
    	if(vertical == 0){
    		width = (int) Math.sqrt(squareFootage / heightWidthRatio);
    		height = (int) (width * heightWidthRatio);
    	} else {
    		height = (int) Math.sqrt(squareFootage / heightWidthRatio);
    		width = (int) (height * heightWidthRatio);
    	}
    	dimensions[0] = width;
    	dimensions[1] = height;
    	return dimensions;
    }
    

    private int[][] fillGrid(int x, int y, int[][] grid){
    	int startRowIndex, startColIndex;
    	int lastRowIndex = x;
    	int lastColIndex = y;
    	for(int i = 0; i < individuals.length; i++){  
    		startRowIndex = lastRowIndex;
    		startColIndex = lastColIndex;
    		for(int j = startRowIndex; j < (int)(individuals[i].getLength()); j++){
    			for(int k = startColIndex; k < (int)(individuals[i].getHeight()); k++){
    				try{
    					// mark the border
    					if(k == individuals[i].getHeight() -1 || j == individuals[i].getLength() -1 ){
    						grid[j][k] = -1;
    						
    					} else {
    						System.out.println("filling cell " + j + ":" + k + " with " + individuals[i].getRoomType());
    						grid[j][k] = individuals[i].getRoomType();
    					}
    				} catch (ArrayIndexOutOfBoundsException e){
    					System.out.println("won't fit");
    					break;
    					/*for(int l = 0; l < individuals.length; l++){
    						if(grid[l][0] == 100);
        					grid = fillGrid(l, 0, grid);
    					}*/

    				}
    				if(k == individuals[i].getHeight() - 1){
    					lastColIndex = k;
    				}
    			}
    			individuals[i].setxOffset(startRowIndex);
    			individuals[i].setyOffset(startColIndex);
    		}
    	}
    	/*
    	for(int a = 0; a < grid.length; a++){
    		for(int b = 0; b < grid[0].length; b++){
    			if(grid[a][b] == 100){
    				System.out.print(" E ");
    			} else if(grid[a][b] == -1) {
    				System.out.print(" B ");
    			} else {
    				System.out.print(" " + grid[a][b] + " ");
    			}
    		}
    		System.out.println("\n");
    	}
    	*/
    	return grid;
    }
    /* Getters */
    
    public Room[] getIndividuals() {
        return individuals;
    }
    public Room getIndividual(int index) {
        return individuals[index];
    }

    public Room getFittest() {
        Room fittest = individuals[0];
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    /* Public methods */
    // Get population size
    public int size() {
        return individuals.length;
    }

    // Save individual
    public void saveIndividual(int index, Room indiv) {
        individuals[index] = indiv;
    }
}

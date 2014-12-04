
public class Population {
    Room[] individuals;

	int numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms;

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
                Room newIndividual = new Kitchen(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i + j, newIndividual);
                if(i == numKitchens - 1)
                	j = j + i + 1;
            }
            // then dining rooms
            for (int i = 0; i < numDiningRooms; i++) {
            	double brSquareFootage = MTBGame.randInt(DiningRoom.MIN_SQUARE_FOOTAGE, DiningRoom.MAX_SQUARE_FOOTAGE);
            	int[] dimensions = new int[2];
            	dimensions = getDimensions(brSquareFootage);
            	int height, width;
            	width = dimensions[0];
            	height = dimensions[1];
                Room newIndividual = new DiningRoom(width, height, Floor.CEILING_HEIGHT, 0, 0);
                saveIndividual(i + j, newIndividual);
            }
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
        for (int i = 0; i < individuals.length; i++) {
        	if(individuals[i] != null){
        		if (fittest.getFitness() <= getIndividual(i).getFitness()) {
        			fittest = getIndividual(i);
        		}
        	}
        }
        return fittest;
    }

    // Save individual
    public void saveIndividual(int index, Room indiv) {
        individuals[index] = indiv;
    }
    
    public void setIndividuals(Room[] individuals) {
		this.individuals = individuals;
	}
    
    public void clearIndividuals(){
    	for(int i = 0; i < individuals.length; i++){
    		individuals[i] = null;
    	}
    }
}

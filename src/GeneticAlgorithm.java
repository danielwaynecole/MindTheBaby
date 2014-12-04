
public class GeneticAlgorithm {
	private static final double mutationRate = 0.5;

	// Evolve a population
	public static Population evolvePopulation(Population pop) {
		// Keep our best individual
		Room dontMutateMe = pop.getFittest();
		// Mutate population
		for (int i = 0 ; i < pop.individuals.length; i++) {
			if(dontMutateMe.getRoomID() == pop.individuals[i].getRoomID()){
				continue;
			} else {
				mutate(pop.getIndividual(i));
			}
		}

		return pop;
	}

	// Mutate an individual
	private static void mutate(Room individual) {
		if (Math.random() <= mutationRate) {
			int maxArea = 0;
			int minArea = 0;
			switch(individual.getRoomType()){
			case Room.BATHROOM:
				maxArea = Bathroom.MAX_SQUARE_FOOTAGE;
				minArea = Bathroom.MIN_SQUARE_FOOTAGE;
				break;
			case Room.BEDROOM:
				maxArea = Bedroom.MAX_SQUARE_FOOTAGE;
				minArea = Bedroom.MIN_SQUARE_FOOTAGE;
				break;
			case Room.DININGROOM:
				maxArea = DiningRoom.MAX_SQUARE_FOOTAGE;
				minArea = DiningRoom.MIN_SQUARE_FOOTAGE;
				break;
			case Room.KITCHEN:
				maxArea = Kitchen.MAX_SQUARE_FOOTAGE;
				minArea = Kitchen.MIN_SQUARE_FOOTAGE;
				break;
			case Room.LIVINGROOM:
				maxArea = LivingRoom.MAX_SQUARE_FOOTAGE;
				minArea = LivingRoom.MIN_SQUARE_FOOTAGE;
				break;
			}
			// coin flip
			int doLength = MTBGame.randInt(0, 1);
			// 70% chance of increase, 30% chance of decrease
			int increaseLength = (MTBGame.randInt(0, 10) > 3) ? 1 : 0;
			int doWidth = MTBGame.randInt(0, 1);
			int increaseWidth = (MTBGame.randInt(0, 10) > 3) ? 1 : 0;;
			int newLength, newWidth, decreaseBy, increaseBy;
			if(doLength == 1){
				switch(increaseLength){
				case 0:
					decreaseBy = MTBGame.randInt(1, 3);
					newLength = individual.getLength() - decreaseBy;
					if(newLength > 4 && (newLength * individual.getWidth()) > minArea){
						if(newLength < 5){
							System.out.println("case 0 new length: " + newLength);
							System.exit(0);
						}
						try {
							individual.setLength(newLength);
						} catch (Exception e) {
							e.printStackTrace();
							System.exit(1);
						}
						break;
					} else {
						increaseLength = 1;
					}
				case 1:
					increaseBy = MTBGame.randInt(1, 3);
					newLength = individual.getLength() + increaseBy;
					if(individual.getRoomType() == Room.BATHROOM){
						if((newLength * individual.getWidth()) > Bathroom.MAX_SQUARE_FOOTAGE){
							// do nothing
							break;
						}
					}
					try {
						individual.setLength(newLength);
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(1);
					}
					break;
				default:
					decreaseBy = MTBGame.randInt(1, 3);
					newLength = individual.getLength() - decreaseBy;
					if(newLength > 4 && (newLength * individual.getWidth()) > minArea){
						if(newLength < 5){
							System.out.println("default new length: " + newLength);
							System.exit(0);
						}
						try {
							individual.setLength(newLength);
						} catch (Exception e) {
							e.printStackTrace();
							System.exit(1);
						}
					}
				}		
			}
			if(doWidth == 1){
				switch(increaseWidth){
				case 0:
					decreaseBy = MTBGame.randInt(1, 3);
					newWidth = individual.getWidth() - decreaseBy;
					if(newWidth > 4 && (newWidth * individual.getLength()) > minArea){
						if(newWidth < 5){
							System.out.println("case 0 new width: " + newWidth);
							System.exit(0);
						}
						try {
							individual.setWidth(newWidth);
						} catch (Exception e) {
							e.printStackTrace();
							System.exit(1);
						}
						break;
					} else {
						increaseWidth = 1;
					}
				case 1:
					increaseBy = MTBGame.randInt(1, 3);
					newWidth = individual.getWidth() + increaseBy;
					try {
						individual.setWidth(newWidth);
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(1);
					}
					break;
				default:
					decreaseBy = MTBGame.randInt(1, 3);
					newWidth = individual.getWidth() - decreaseBy;
					if(newWidth > 4 && (newWidth * individual.getLength()) > minArea){
						if(newWidth < 5){
							System.out.println("default new width: " + newWidth);
							System.exit(0);
						}
						try {
							individual.setWidth(newWidth);
						} catch (Exception e) {
							e.printStackTrace();
							System.exit(1);
						}
					}
				}		
			}
		}
	}
}

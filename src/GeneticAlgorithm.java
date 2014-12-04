
public class GeneticAlgorithm {
	private static final double mutationRate = 0.5;

	// Evolve a population
	public static Population evolvePopulation(Population pop) {
		System.out.println("we have been called to evolve");
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
	private static void mutate(Room indiv) {
		System.out.println("we have been called to mutate");

		if (Math.random() <= mutationRate) {
			System.out.println("we are mutating");
			int maxArea = 0;
			int minArea = 0;
			switch(indiv.getRoomType()){
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
			int increaseLength = (MTBGame.randInt(0, 10) > 3) ? 1 : 0;
			int doWidth = MTBGame.randInt(0, 1);
			int increaseWidth = (MTBGame.randInt(0, 10) > 3) ? 1 : 0;;
			int newLength, newWidth, decreaseBy, increaseBy;
			if(doLength == 1){
				switch(increaseLength){
				case 0:
					decreaseBy = MTBGame.randInt(1, 3);
					newLength = indiv.getLength() - decreaseBy;
					if(newLength > 4 && (newLength * indiv.getWidth()) > minArea){
						if(newLength < 5){
							System.out.println("case 0 new length: " + newLength);
							System.exit(0);
						}
						try {
							indiv.setLength(newLength);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(1);
						}
						break;
					} else {
						increaseLength = 1;
					}
				case 1:
					increaseBy = MTBGame.randInt(1, 3);
					newLength = indiv.getLength() + increaseBy;
					System.out.println("new length: " + newLength);
					if(indiv.getRoomType() == Room.BATHROOM){
						if((newLength * indiv.getWidth()) > Bathroom.MAX_SQUARE_FOOTAGE){
							// do nothing
							break;
						}
					}
					try {
						indiv.setLength(newLength);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(1);
					}
					break;
				default:
					decreaseBy = MTBGame.randInt(1, 3);
					newLength = indiv.getLength() - decreaseBy;
					if(newLength > 4 && (newLength * indiv.getWidth()) > minArea){
						if(newLength < 5){
							System.out.println("default new length: " + newLength);
							System.exit(0);
						}
						try {
							indiv.setLength(newLength);
						} catch (Exception e) {
							// TODO Auto-generated catch block
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
					newWidth = indiv.getWidth() - decreaseBy;
					if(newWidth > 4 && (newWidth * indiv.getLength()) > minArea){
						if(newWidth < 5){
							System.out.println("case 0 new width: " + newWidth);
							System.exit(0);
						}
						try {
							indiv.setWidth(newWidth);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(1);
						}
						break;
					} else {
						increaseWidth = 1;
					}
				case 1:
					increaseBy = MTBGame.randInt(1, 3);
					newWidth = indiv.getWidth() + increaseBy;
					System.out.println("new width: " + newWidth);
					try {
						indiv.setWidth(newWidth);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.exit(1);
					}
					break;
				default:
					decreaseBy = MTBGame.randInt(1, 3);
					newWidth = indiv.getWidth() - decreaseBy;
					if(newWidth > 4 && (newWidth * indiv.getLength()) > minArea){
						if(newWidth < 5){
							System.out.println("default new width: " + newWidth);
							System.exit(0);
						}
						try {
							indiv.setWidth(newWidth);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.exit(1);
						}
					}
				}		
			}
		}
	}
}

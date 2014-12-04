
public class DiningRoom extends Room {
	public static final int MIN_SQUARE_FOOTAGE = 100;
	public static final int MAX_SQUARE_FOOTAGE = 250;

	public DiningRoom(int width, int length, int height, int xOffset, int yOffset) {
		super(width, length, height, Room.DININGROOM, xOffset, yOffset);
		// TODO Auto-generated constructor stub
	}
	
	public int getFitness(int floorWidth, int floorHeight){
		int fitness = 0;
		int percentageOfMaxArea = (this.getWidth() * this.getLength()) / MAX_SQUARE_FOOTAGE;
		fitness = percentageOfMaxArea;
		return fitness;
	}
}


public class LivingRoom extends Room {
	public static final int MIN_SQUARE_FOOTAGE = 100;
	public static final int MAX_SQUARE_FOOTAGE = 400;
// TODO Auto-generated constructor stub

	public LivingRoom(int width, int length, int height, int xOffset, int yOffset) {
		super(width, length, height, Room.LIVINGROOM, xOffset, yOffset);
		// TODO Auto-generated constructor stub
	}
	
	public int getFitness(int floorWidth, int floorHeight){
		int fitness = 0;
		int percentageOfMaxArea = (this.getWidth() * this.getLength()) / MAX_SQUARE_FOOTAGE;
		int positionBonus = (this.getyOffset() > (floorHeight / 2)) ? 50 : -50;
		fitness = percentageOfMaxArea + positionBonus;
		return fitness;
	}
}

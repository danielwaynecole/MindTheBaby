
public class Bathroom extends Room {
	public static final int MIN_SQUARE_FOOTAGE = 40;
	public static final int MAX_SQUARE_FOOTAGE = 90;

	public Bathroom(int width, int length, int height, int xOffset, int yOffset) {
		super(width, length, height, Room.BATHROOM, xOffset, yOffset);
		// TODO Auto-generated constructor stub
	}
	
	public int getFitness(int floorWidth, int floorHeight){
		int fitness = 0;
		int percentageOfMaxArea = (this.getWidth() * this.getLength()) / MAX_SQUARE_FOOTAGE;
		int positionBonus = (this.getyOffset() < (floorHeight - (int)(floorHeight * .8))) ? 50 : -50;
		fitness = percentageOfMaxArea + positionBonus;
		return fitness;
	}
}

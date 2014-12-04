import java.awt.Graphics;
public class House extends Space {
	private Floor[] floors = new Floor[4];
	private Graphics g;
	private int numberOfFloors;
	private int length;
	private int width;
	private Population[] populations = new Population[5];

	/**
	 * 
	 * @param g
	 * @param numberOfFloors
	 * @param width
	 * @param length
	 * @param height
	 */
	public House(Graphics g, int numberOfFloors, int width, int length, int height) {
		super(width, length, height, 0, 0);
		this.g = g;
		this.setNumberOfFloors(numberOfFloors);
		this.length = length;
		this.width = width;
		int floorFootage = length * width;
		floors = new Floor[numberOfFloors + 1];
		for(int i = 0; i <= numberOfFloors; i++){
			floors[i] = new Floor(width, length, i, floorFootage);
		}
		layoutFloors();
	}

	/**
	 * 
	 * @param level
	 */
	private void layoutFloors(int level){
		layoutRooms(floors[level].getFloorLevel());
	}

	/**
	 * 
	 */
	private void layoutFloors(){
		// ignore basement for now
		for(int i = 1; i < floors.length; i++){
			layoutRooms(floors[i].getFloorLevel());
		}
	}

	/**
	 * 
	 * @param level
	 */
	private void layoutRooms(int level){
		switch(numberOfFloors){
		case 0:
			break;
		case 1:
			ranchLayout(level);
			break;
		default:
			if(level==1){
				firstFloorLayout(level);
			} else {
				upperLevelLayout(level);
			}
			break;
		}
	}

	/**
	 * 
	 * @param level
	 */
	private void ranchLayout(int level){
		int floorFootage = length * width;
		Floor floor = new Floor(width, length, level, floorFootage);
		// figure out the number of rooms
		// we want at least a kitchen, a living room, bedroom, and bathroom
		int numBathrooms = 1;
		int numLivingRooms = 1;
		int numKitchens = 1;
		int numBedrooms = 1;
		int numDiningRooms = 0;
		if(floorFootage > 800 && floorFootage < 1400){
			numBedrooms = 2;
			numDiningRooms = 1;
		}
		else if(floorFootage > 1400 && floorFootage < 1800){
			numBedrooms = 3;
			numDiningRooms = 1;
		}
		else if(floorFootage > 1800 && floorFootage < 2200){
			numBedrooms = 4;
			numDiningRooms = 1;
		}
		else if(floorFootage > 2200 && floorFootage < 2600){
			numBedrooms = 5;
			numDiningRooms = 1;
		}
		else if(floorFootage > 2600 && floorFootage < 3000){
			numBedrooms = 5;
			numDiningRooms = 1;
		}

		// create new population
		Population pop = populations[level];
		if(pop == null){
			pop = new Population(numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms, true);	
			populations[level] = pop;
		}
		Room[] rooms = pop.getIndividuals();
		pack(rooms, floor, level);
	}

	/**
	 * 
	 * @param level
	 */
	private void firstFloorLayout(int level){
		int floorFootage = length * width;
		Floor floor = new Floor(width, length, level, floorFootage);
		// figure out the number of rooms
		// we want at least a kitchen, a living room, dining room, and bathroom
		int numBathrooms = 1;
		int numLivingRooms = 1;
		int numKitchens = 1;
		int numDiningRooms = 1;
		int numBedrooms = 0;
		// create new population
		Population pop = populations[level];
		if(pop == null){
			pop = new Population(numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms, true);	
			populations[level] = pop;
		}		
		Room[] rooms = pop.getIndividuals();
		pack(rooms, floor, level);
	}

	/**
	 * 
	 * @param level
	 */
	private void upperLevelLayout(int level){
		int floorFootage = length * width;
		Floor floor = new Floor(width, length, level, floorFootage);
		// figure out the number of rooms
		// we want at least two bedrooms and a bathroom
		int numBathrooms = 1;
		int numLivingRooms = 0;
		int numKitchens = 0;
		int numBedrooms = 2;
		int numDiningRooms = 0;
		if(floorFootage > 500 && floorFootage < 800){
			numBedrooms = 3;
		}
		else if(floorFootage > 800 && floorFootage < 1200){
			numBedrooms = 4;
			numBathrooms = 1;
		}
		else if(floorFootage > 1200 && floorFootage < 1500){
			numBedrooms = 4;
			numBathrooms = 2;
		}
		// create new population
		Population pop = populations[level];
		if(pop == null){
			pop = new Population(numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms, true);	
			populations[level] = pop;
		}
		Room[] rooms = pop.getIndividuals();
		pack(rooms, floor, level);
	}

	/**
	 * 
	 * @param rooms
	 * @param floor
	 * @param level
	 */
	private void pack(Room[] rooms, Floor floor, int level){
		boolean clear = false;
		PackRectangles<Room> pr = new PackRectangles<Room>(width, length, 0);
		while(!clear){
			// clear current rooms from floor
			floor.clearRooms();
			// loop through rooms to repack them
			for(int r = 0; r < rooms.length; r++){
				int rectWidth = rooms[r].getWidth();
				int rectLength = rooms[r].getLength();
				Rectangle rect = pr.insert(rectWidth, rectLength, rooms[r]);
				// if it didn't fit, we need to shrink something
				if(rect == null){
					shrinkNextCandidate(rooms, pr);
					// and start over
					pr.clear();
					break;
				} else {
					try {
						rooms[r].setxOffset(rect.x);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						rooms[r].setyOffset(rect.y);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// add room back to floor now that it fits
					floor.addRoom(rooms[r]);
					if(r == (rooms.length - 1)){
						clear = true;
					}
				}
			}
		}
		if(clear){
			addFloor(floor, level);
		}
	}
	
	/**
	 * 
	 * @param rooms
	 * @param pr
	 */
	private void shrinkNextCandidate(Room[] rooms, PackRectangles<Room> pr){
		int largestSize = 0;
		int largestRoom = 0;
		// find the largest room
		for(int i = 0; i < rooms.length; i++){
			int roomSize = rooms[i].getLength() * rooms[i].getWidth();
			// make sure shrinking the room wouldn't bring it under minimum length and width
			if(roomSize > largestSize && rooms[i].getLength() > 4 && rooms[i].getWidth() > 4 ){
				largestSize = roomSize;
				largestRoom = i;
			}
		}
		int width = rooms[largestRoom].getWidth();
		int length = rooms[largestRoom].getLength();
		int widthDecrease = (width < length) ? 0 : 1;
		int lengthDecrease = (length < width) ? 0 : 1;

		rooms[largestRoom].shrinkSpace(widthDecrease, lengthDecrease);
	}

	/**
	 * 
	 * @param level
	 */
	public void mutate(int level){
		populations[level] = GeneticAlgorithm.evolvePopulation(populations[level]);
		this.layoutFloors(level);
	}

	/**
	 * 
	 * @param floor
	 * @param level
	 */
	public void addFloor(Floor floor, int level){
		for(int i = 0; i < floors.length; i++){
			if(i == level){
				floors[i] = floor;
			}
		}
	}

	/**
	 * 
	 * @param floors
	 */
	public void setFloors(Floor[] floors) {
		this.floors = floors;
	}

	/**
	 * 
	 * @return
	 */
	public Floor[] getFloors() {
		return floors;
	}

	/**
	 * 
	 * @param level
	 * @return
	 */
	public Floor getFloors(int level) {
		for(int i = 0; i < floors.length; i++){
			if(floors[i].getFloorLevel() == level){
				return floors[i];
			}
		}
		return null;
	}

	public void setNumberOfFloors(int numberOfFloors) {
		this.numberOfFloors = numberOfFloors;
	}

	public int getNumberOfFloors() {
		return numberOfFloors;
	}

	public Graphics getG() {
		return g;
	}

	public void setG(Graphics g) {
		this.g = g;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}
}

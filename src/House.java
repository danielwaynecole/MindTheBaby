import java.awt.Graphics;
public class House extends Space {
	private Floor[] floors = new Floor[4];
	private Graphics g;
	private int numberOfFloors;
	private int length;
	private int width;

	public House(Graphics g, int numberOfFloors, int width, int length, int height) {
		super(width, length, height, 0, 0);
		this.g = g;
		this.setNumberOfFloors(numberOfFloors);
		this.length = length;
		this.width = width;
		floors = new Floor[numberOfFloors + 1];
		for(int i = 0; i <= numberOfFloors; i++){
			floors[i] = new Floor(width, length, i);
		}
		layoutFloors();
	}
	
	private void layoutFloors(){
		// ignore basement for now
		for(int i = 1; i < floors.length; i++){
			layoutRooms(floors[i].getFloorLevel());
		}
	}
	
	private void layoutRooms(int level){
		switch(numberOfFloors){
		case 1:
			ranchLayout();
			break;
		case 2:
		case 3:
			if(level==1){
				firstFloorLayout();
			} else {
				upperLevelLayout();
			}
			
		}
	}
	
	private void ranchLayout(){
		Floor floor = new Floor(width, length, Floor.FIRST_FLOOR);
		int floorFootage = length * width;
		// figure out the number of rooms
		// we want at least a kitchen, a living room, bedroom, and bathroom
		int numBathrooms = 1;
		int numLivingRooms = 1;
		int numKitchens = 1;
		int numBedrooms = 1;
		int numDiningRooms = 0;
		int bedroom1x, bedroom1y, bedroom1Length, bedroom1Width;
		if(floorFootage > 1000 && floorFootage < 1400){
			numBedrooms = 2;
			numDiningRooms = 1;
			int bedroom2x, bedroom2y, bedroom2Length, bedroom2Width;
		}
		else if(floorFootage > 1400 && floorFootage < 1800){
			numBedrooms = 3;
			numDiningRooms = 1;
			int bedroom3x, bedroom3y, bedroom3Length, bedroom3Width;
		}
		else if(floorFootage > 1800 && floorFootage < 2200){
			numBedrooms = 4;
			numDiningRooms = 1;
			int bedroom4x, bedroom4y, bedroom4Length, bedroom4Width;
		}
		else if(floorFootage > 2200 && floorFootage < 2600){
			numBedrooms = 5;
			numDiningRooms = 1;
			int bedroom5x, bedroom5y, bedroom5Length, bedroom5Width;
		}
		else if(floorFootage > 2600 && floorFootage < 3000){
			numBedrooms = 5;
			numDiningRooms = 1;
			int bedroom6x, bedroom6y, bedroom6Length, bedroom6Width;
		}
		// represent floor as a grid
		/*
		int[][] floorGrid = new int[width][length];
		for(int a = 0; a < floorGrid.length; a++){
			for(int b = 0; b < floorGrid[0].length; b++){
				floorGrid[a][b] = 100;
			}
		}
		*/
		// create new population
		Population pop = new Population(numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms, true);	
		Room[] rooms = pop.getIndividuals();
        PackRectangles<Room> pr = new PackRectangles<Room>(width, length, 0);
		for(int r = 0; r < rooms.length; r++){
			//int rectLength = (int)(rooms[r].getLength()*scale);
			//int rectHeight = (int)(rooms[r].getHeight()*scale);
			int rectWidth = rooms[r].getWidth();
			int rectLength = rooms[r].getLength();
			System.out.println("Total width: " + width + "; Total height: " + length + "; Rectangle lenght: " + rectLength + "; Rectangel width: " + rectWidth);
			Rectangle rect = pr.insert(rectWidth, rectLength, rooms[r]);
			while(rect == null){
				rooms[r].shrinkSpace(1, 1);
				rectWidth = rooms[r].getWidth();
				rectLength = rooms[r].getLength();
				System.out.println("Trying again: " + width + "; Total height: " + length + "; Rectangle lenght: " + rectLength + "; Rectangel width: " + rectWidth);

				if(rooms[r].getWidth()==0 || rooms[r].getLength()==0){
					break;
				}
				rect = pr.insert(rectWidth, rectLength, rooms[r]);
			}
			if(rect != null){
				rooms[r].setxOffset(rect.x);
				rooms[r].setyOffset(rect.y);
				floor.addRoom(rooms[r]);
			} else {
				System.out.println("Room does not fit");
			}
		}
		addFloor(floor, Floor.FIRST_FLOOR);
	}
	
	private void firstFloorLayout(){
		Floor floor = new Floor(width, length, Floor.FIRST_FLOOR);
		int floorFootage = length * width;
		// figure out the number of rooms
		// we want at least a kitchen, a living room, bedroom, and bathroom
		int numBathrooms = 1;
		int numLivingRooms = 1;
		int numKitchens = 1;
		int numDiningRooms = 1;
		int numBedrooms = 0;
		// create new population
		Population pop = new Population(numBedrooms, numBathrooms, numLivingRooms, numKitchens, numDiningRooms, true);	
		Room[] rooms = pop.getIndividuals();
        PackRectangles<Room> pr = new PackRectangles<Room>(width, length, 0);
		for(int r = 0; r < rooms.length; r++){
			int rectWidth = rooms[r].getWidth();
			int rectLength = rooms[r].getLength();
			System.out.println("Total width: " + width + "; Total height: " + length + "; Rectangle lenght: " + rectLength + "; Rectangel width: " + rectWidth);
			Rectangle rect = pr.insert(rectWidth, rectLength, rooms[r]);
			while(rect == null){
				rooms[r].shrinkSpace(1, 1);
				rectWidth = rooms[r].getWidth();
				rectLength = rooms[r].getLength();
				System.out.println("Trying again: " + width + "; Total height: " + length + "; Rectangle lenght: " + rectLength + "; Rectangel width: " + rectWidth);

				if(rooms[r].getWidth()==0 || rooms[r].getLength()==0){
					break;
				}
				rect = pr.insert(rectWidth, rectLength, rooms[r]);
			}
			if(rect != null){
				rooms[r].setxOffset(rect.x);
				rooms[r].setyOffset(rect.y);
				floor.addRoom(rooms[r]);
			} else {
				System.out.println("Room does not fit");
			}
		}
		addFloor(floor, Floor.FIRST_FLOOR);
	}
	
	private void upperLevelLayout(){
		
	}
	
	public void addFloor(Floor floor, int level){
		for(int i = 0; i < floors.length; i++){
			if(i == level){
				floors[i] = floor;
			}
		}
	}
	
	public void setFloors(Floor[] floors) {
		this.floors = floors;
	}
	
	public Floor[] getFloors() {
		return floors;
	}
	
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
}

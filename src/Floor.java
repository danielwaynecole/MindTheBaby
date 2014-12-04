import java.util.UUID;


public class Floor extends Space {
	public static final int BASEMENT = 0;
	public static final int FIRST_FLOOR = 1;
	public static final int SECOND_FLOOR = 2;
	public static final int THIRD_FLOOR = 3;
	public static final int CEILING_HEIGHT = 9;
	
	private int floorLevel;
	private int squareFootage;
	
	public Floor(int width, int length, int floorLevel, int squareFootage) {
		super(width, length, CEILING_HEIGHT, 0, 0);
		this.setFloorLevel(floorLevel);
		this.setSquareFootage(squareFootage);
	}

	Room[] rooms = new Room[20];

	public void addRoom(Room room){
		for(int i = 0; i < rooms.length; i++){
			if(rooms[i] == null){
				rooms[i] = room;
				break;
			}
		}
	}
	
	public void removeRoom(UUID roomId){
		Room[] newRooms = new Room[rooms.length - 1];
		int counter = 0;
		for(int i = 0; i < rooms.length; i++){
			if(!rooms[i].getRoomID().equals(roomId)){
				rooms[counter] = rooms[i];
				counter++;
			}
		}
		this.rooms = newRooms;
	}
	public int getRoomFitness(Room room){
		return room.getFitness(this.getWidth(), this.getLength());
	}
	
	public int getFitness(){
		int fitness = 0;
		int usedSquareFootage = 0;
		for(int i = 0; i < rooms.length; i++){
			if(rooms[i] == null){
				continue;
			} else {
				usedSquareFootage += (rooms[i].getLength() * rooms[i].getWidth());
			}
		}
		if(usedSquareFootage == 0)
			return fitness;
		double percentUsedSpace = (double)usedSquareFootage / (double)squareFootage;
		fitness = (int)(percentUsedSpace * 100);
		System.out.println("used: " + usedSquareFootage + " total: " + squareFootage + " Fitness = " + fitness);
		return fitness;
	}
	
	public Room[] getRooms() {
		return rooms;
	}

	public void setRooms(Room[] rooms) {
		this.rooms = rooms;
	}
	
	public Room getRoom(UUID roomID){
		for(int i = 0; i < rooms.length; i++){
			if(rooms[i].getRoomID().equals(roomID)){
				return rooms[i];
			}
		}
		return null;
	}
	
	public void clearRooms(){
		for(int i = 0; i < rooms.length; i++){
			rooms[i] = null;
		}
	}

	public void setFloorLevel(int floorLevel) {
		this.floorLevel = floorLevel;
	}

	public int getFloorLevel() {
		return floorLevel;
	}

	public void setSquareFootage(int squareFootage) {
		this.squareFootage = squareFootage;
	}

	public int getSquareFootage() {
		return squareFootage;
	}
}

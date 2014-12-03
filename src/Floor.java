import java.util.UUID;


public class Floor extends Space {
	public static final int BASEMENT = 0;
	public static final int FIRST_FLOOR = 1;
	public static final int SECOND_FLOOR = 2;
	public static final int THIRD_FLOOR = 3;
	public static final int CEILING_HEIGHT = 9;
	
	private int floorLevel;
	
	public Floor(int width, int length, int floorLevel) {
		super(width, length, CEILING_HEIGHT, 0, 0);
		this.setFloorLevel(floorLevel);
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

	public void setFloorLevel(int floorLevel) {
		this.floorLevel = floorLevel;
	}

	public int getFloorLevel() {
		return floorLevel;
	}
}

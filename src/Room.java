import java.awt.Color;
import java.util.UUID;


public class Room extends Space {
	public static final int BATHROOM = 0;
	public static final int BEDROOM = 1;
	public static final int DININGROOM = 2;
	public static final int KITCHEN = 3;
	public static final int LIVINGROOM = 4;
	public static final Color BATHROOMCOLOR = Color.green;
	public static final Color BEDROOMCOLOR = Color.magenta;
	public static final Color DININGROOMCOLOR = Color.pink;
	public static final Color KITCHENCOLOR = Color.orange;
	public static final Color LIVINGROOMCOLOR = Color.yellow;
	public static final int WOOD_FLOOR = 0;
	public static final int CARPET = 1;
	public static final int TILE_FLOOR = 2;

	private int roomType;
	private String roomName;
	private Color roomColor;
	private UUID roomID = UUID.randomUUID();

	public Room(int width, int length, int height, int roomType, int xOffset, int yOffset) {
		super(width, length, height, xOffset, yOffset);
		this.setRoomType(roomType);		
	}
	
	public int getFitness(){
		int fitness = 0;
		return fitness;
	}
	
	public int getFitness(int width, int length){
		int fitness = 0;
		return fitness;
	}
	
	public void setWidth(int w) throws Exception{
		if(w < 5){
			throw new Exception("width is less than 0");
		} else {
			this.width = w;
		}
	}
	
	public void setLength(int l) throws Exception{
		if(l < 5){
			throw new Exception("length is less than 0");
		} else {
			this.length = l;
		}
	}


	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
		switch(roomType){
		case BATHROOM:
			this.setRoomColor(BATHROOMCOLOR);
			setRoomName("BATHROOM");
			break;
		case BEDROOM:
			this.setRoomColor(BEDROOMCOLOR);
			setRoomName("BEDROOM");
			break;
		case DININGROOM:
			this.setRoomColor(DININGROOMCOLOR);
			setRoomName("DINING ROOM");
			break;
		case KITCHEN:
			this.setRoomColor(KITCHENCOLOR);
			setRoomName("KITCHEN");
			break;
		case LIVINGROOM:
			this.setRoomColor(LIVINGROOMCOLOR);
			setRoomName("LIVING ROOM");
			break;
		}
	}

	public UUID getRoomID() {
		return roomID;
	}

	public void setRoomID(UUID roomID) {
		this.roomID = roomID;
	}

	public void setRoomColor(Color roomColor) {
		this.roomColor = roomColor;
	}

	public Color getRoomColor() {
		return roomColor;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}
	
	
}

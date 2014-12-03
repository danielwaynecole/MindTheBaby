
public class Door {
	public static final int LEVEL_HANDLE = 0;
	public static final int DOOR_KNOB = 1;
	
	private boolean locked = false;
	private int knobType;
	
	public Door(boolean locked, int knobType) {
		super();
		this.locked = locked;
		this.knobType = knobType;
	}
	
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public int getKnobType() {
		return knobType;
	}
	public void setKnobType(int knobType) {
		this.knobType = knobType;
	}
}


public class Crib extends Furniture {
	private int mattressLevel = 0; // 0 highest, one is medium, and 2 is lowest position



	public Crib(int width, int length, int height, int xOffset, int yOffset, int mattressLevel) {
		super(width, length, height, xOffset, yOffset);
		this.mattressLevel = mattressLevel;
	}

	public void setMattressLevel(int mattressLevel) {
		this.mattressLevel = mattressLevel;
	}

	public int getMattressLevel() {
		return mattressLevel;
	}
}

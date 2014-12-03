
public class Bathtub {
	
	private int height = 0;
	private int depth = 0;
	private int width = 0;
	
	public Bathtub(int height, int depth, int width) {
		this.height = height;
		this.depth = depth;
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public int getWaterDepth() {
		return waterDepth;
	}

	public void setWaterDepth(int waterDepth) {
		this.waterDepth = waterDepth;
	}

	public int getWaterTemp() {
		return waterTemp;
	}

	public void setWaterTemp(int waterTemp) {
		this.waterTemp = waterTemp;
	}

	private boolean full = false;
	private int waterDepth = 0;
	private int waterTemp = 0;
}

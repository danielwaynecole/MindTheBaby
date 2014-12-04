
public class Appliance extends Space {
	
	/**
	 * @param width
	 * @param length
	 * @param height
	 * @param xOffset
	 * @param yOffset
	 */
	public Appliance(int width, int length, int height, int xOffset, int yOffset, boolean on) {
		super(width, length, height, xOffset, yOffset);
	}

	private int height = 0;
	private int depth = 0;
	private int width = 0;
	private boolean on = false;
	

	
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
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}

	/* (non-Javadoc)
	 * @see Space#getFitness()
	 */
	@Override
	public int getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}
}

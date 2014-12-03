
public class Appliance {
	
	private int height = 0;
	private int depth = 0;
	private int width = 0;
	private boolean on = false;
	
	public Appliance(int height, int depth, int width, boolean on) {
		super();
		this.height = height;
		this.depth = depth;
		this.width = width;
		this.on = on;
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
	public boolean isOn() {
		return on;
	}
	public void setOn(boolean on) {
		this.on = on;
	}
}

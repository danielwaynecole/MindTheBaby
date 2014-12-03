
public class Space {
	private int width = 0;
	private int length = 0;
	private int height = 0;
	private int xOffset;
	private int yOffset;
	
	public Space(int width, int length, int height, int xOffset, int yOffset) {
		super();
		this.width = width;
		this.length = length;
		this.height = height;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void shrinkSpace(int width, int length){
		this.width = this.width - width;
		this.length = this.length - length;
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
	
}

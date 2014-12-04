
public abstract class Space {
	protected int width = 0;
	protected int length = 0;
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
	
	public abstract int getFitness();
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) throws Exception {
		if(width < 0){
			throw new Exception("width is less than 0");
		}
		this.width = width;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) throws Exception {
		if(length < 0){
			throw new Exception("length is less than 0");
		}
		this.length = length;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHeight() {
		return height;
	}

	public void setxOffset(int xOffset) throws Exception {
		if(xOffset < 0){
			throw new Exception("xOffset is less than 0");
		}
		this.xOffset = xOffset;
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setyOffset(int yOffset) throws Exception {
		if(yOffset < 0){
			throw new Exception("yOffset is less than 0");
		}
		this.yOffset = yOffset;
	}

	public int getyOffset() {
		return yOffset;
	}
	
}


public class Dish {
	public static final int GLASS = 0;
	public static final int PLASTIC = 1;
	private int material;
	
	public Dish(int material) {
		super();
		this.material = material;
	}
	
	public void setMaterial(int material) {
		this.material = material;
	}
	public int getMaterial() {
		return material;
	}
	
}


public class Closet extends Space {
	private Poison[] poisons;

	public Closet(int width, int length, int height, int xOffset, int yOffset) {
		super(width, length, height, xOffset, yOffset);
		// TODO Auto-generated constructor stub
	}

	public void setPoisons(Poison[] poisons) {
		this.poisons = poisons;
	}

	public Poison[] getPoisons() {
		return poisons;
	}
	
}

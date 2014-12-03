
public class Bookshelf extends Furniture {
	public Bookshelf(int width, int length, int height, int xOffset, int yOffset) {
		super(width, length, height, xOffset, yOffset);
		// TODO Auto-generated constructor stub
	}

	private Shelf[] shelves;

	public void setShelves(Shelf[] shelves) {
		this.shelves = shelves;
	}

	public Shelf[] getShelves() {
		return shelves;
	}
	
}

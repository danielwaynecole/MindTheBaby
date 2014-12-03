
public class Book {
	private int weight = 0; // in lbs
	private int attractiveNess = 0; // on a scale of 1 to 10
	private String title;
	
	public Book(int weight, int attractiveNess, String title) {
		super();
		this.setWeight(weight);
		this.setAttractiveNess(attractiveNess);
		this.setTitle(title);
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getAttractiveNess() {
		return attractiveNess;
	}

	public void setAttractiveNess(int attractiveNess) {
		this.attractiveNess = attractiveNess;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	
}


public class Food {
	private boolean salty = false;
	private boolean spicy = false;
	private boolean sweet = false; 
	private boolean bitter = false;
	
	public Food(boolean salty, boolean spicy, boolean sweet, boolean bitter) {
		super();
		this.salty = salty;
		this.spicy = spicy;
		this.sweet = sweet;
		this.bitter = bitter;
	}
	public boolean isSalty() {
		return salty;
	}
	public void setSalty(boolean salty) {
		this.salty = salty;
	}
	public boolean isSpicy() {
		return spicy;
	}
	public void setSpicy(boolean spicy) {
		this.spicy = spicy;
	}
	public boolean isSweet() {
		return sweet;
	}
	public void setSweet(boolean sweet) {
		this.sweet = sweet;
	}
	public boolean isBitter() {
		return bitter;
	}
	public void setBitter(boolean bitter) {
		this.bitter = bitter;
	}
	
}

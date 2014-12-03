
public class Drawer extends Storage {
	
	private Utensil[] utensils;
	private Match[] matches;
	
	public Drawer(Utensil[] utensils, Match[] matches) {
		super();
		this.utensils = utensils;
		this.matches = matches;
	}
	public Utensil[] getUtensils() {
		return utensils;
	}
	public void setUtensils(Utensil[] utensils) {
		this.utensils = utensils;
	}
	public Match[] getMatches() {
		return matches;
	}
	public void setMatches(Match[] matches) {
		this.matches = matches;
	}
}

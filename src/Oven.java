
public class Oven extends Appliance {
	private int temp = 0;
	private BurnerKnob[] knobs;
	public Oven(int height, int depth, int width, boolean on) {
		super(height, depth, width, on);
		// TODO Auto-generated constructor stub
	}
	public void setKnobs(BurnerKnob[] knobs) {
		this.knobs = knobs;
	}
	public BurnerKnob[] getKnobs() {
		return knobs;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public int getTemp() {
		return temp;
	}

}

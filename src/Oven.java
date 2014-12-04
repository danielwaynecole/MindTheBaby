
public class Oven extends Appliance {
	private int temp = 0;
	private BurnerKnob[] knobs;

	public Oven(int width, int length, int height, int xOffset, int yOffset,
			boolean on) {
		super(width, length, height, xOffset, yOffset, on);
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

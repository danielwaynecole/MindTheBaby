import javax.swing.JFrame;

public class MindTheBabyDriver extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MindTheBabyDriver me = new MindTheBabyDriver();
		me.doIt();
	}

	public void doIt() {
		addWindowListener(new WindowDestroyer());
		new MTBGame();
	}

}

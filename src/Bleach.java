
public class Bleach extends Poison {
	
	private int containerAppeal = 4; // on a scale of 1 to 10
	private boolean childSafeCap = false;
	
	public int getContainerAppeal() {
		return containerAppeal;
	}
	public void setContainerAppeal(int containerAppeal) {
		this.containerAppeal = containerAppeal;
	}
	public boolean isChildSafeCap() {
		return childSafeCap;
	}
	public void setChildSafeCap(boolean childSafeCap) {
		this.childSafeCap = childSafeCap;
	}
}

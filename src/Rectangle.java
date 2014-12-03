public class Rectangle {
		public final int x;
		public final int y;
		public final int width;
		public final int height;

		Rectangle(int x, int y, int width, int height) {
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}

		@Override
		public String toString() {
			return "[ " + x + ", " + y + ", " + width + ", " + height + " ]";
		}
	}
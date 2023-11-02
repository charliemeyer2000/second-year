package structural.bridge;

public interface Renderer {
	void drawCircle(int x, int y, int radius);
	void drawRectangle(int x1, int x2, int y1, int y2);
}

class ColorRenderer implements Renderer {

	@Override
	public void drawCircle(int x, int y, int radius) {
		System.out.println("Drawing a circle in color.");
		
	}

	@Override
	public void drawRectangle(int x1, int x2, int y1, int y2) {
		System.out.println("Drawing a rectangle in color.");
		
	}
	
}

class GrayscaleRenderer implements Renderer {

	@Override
	public void drawCircle(int x, int y, int radius) {
		System.out.println("Drawing a circle in grayscale.");
		
	}

	@Override
	public void drawRectangle(int x1, int x2, int y1, int y2) {
		System.out.println("Drawing a rectangle in grayscale.");
		
	}
	
}

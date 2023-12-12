package structural.decorator;
//The Window interface class
public interface Window {
	void draw(); // Draws the Window
	String getDescription(); // Returns a description of the Window
}

//Implementation of a simple Window without any scrollbars
class SimpleWindow implements Window {
	@Override
	public void draw() {
		// Draw window
	}
	@Override
	public String getDescription() {
		return "simple window";
	}
}

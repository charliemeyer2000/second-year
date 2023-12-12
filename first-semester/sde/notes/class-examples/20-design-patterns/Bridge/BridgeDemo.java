package structural.bridge;

import java.util.ArrayList;
import java.util.List;

public class BridgeDemo {
	public static void main(String[] args) {
		List<Shape> shapes = new ArrayList<Shape>();
		shapes.add(new Circle(50, 50, 20, 
				new GrayscaleRenderer()));
		shapes.add(new Rectangle(80, 80, 120, 120,
				new ColorRenderer()));
		for (Shape s : shapes) {
			s.draw();
		}
	}
}

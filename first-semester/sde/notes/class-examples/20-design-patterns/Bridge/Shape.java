package structural.bridge;

public abstract class Shape {
	protected Renderer myRenderer; //bridge
	public Shape(Renderer r) {
		myRenderer = r;
	}
	abstract void draw();
}

class Circle extends Shape {
	protected int x, y, radius;
	public Circle(int x, int y, int radius, 
					Renderer r) {
		super(r);
		this.x = x; this.y = y; this.radius = radius;
	}
	public void draw() {
		myRenderer.drawCircle(x, y, radius);
	}
}

class Rectangle extends Shape {
	protected int x1, x2, y1, y2;
	public Rectangle(int x1, int x2, 
					 int y1, int y2, 
					 Renderer r) {
		super(r);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	public void draw() {
		myRenderer.drawRectangle(x1, x2, y1, y2);
	}
}

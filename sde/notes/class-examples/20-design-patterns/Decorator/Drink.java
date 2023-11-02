package structural.decorator;

//this is the base class

public abstract class Drink {
	public abstract double getPrice();
	public abstract String toString();
}

class Coffee extends Drink {

	@Override
	public double getPrice() {
		return 2.0;
	}

	@Override
	public String toString() {
		return "Coffee";
	}
	
}

class Tea extends Drink {
	@Override
	public double getPrice() {
		return 1.5;
	}

	@Override
	public String toString() {
		return "Tea";
	}
}

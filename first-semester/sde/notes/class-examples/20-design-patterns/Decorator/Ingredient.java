package structural.decorator;

//This is the Decorator class

public abstract class Ingredient extends Drink {
	
	protected Drink baseDrink;
	public Ingredient(Drink d) {
		baseDrink = d;
	}
	@Override
	public double getPrice() {
		return baseDrink.getPrice();
	}

	@Override
	public String toString() {
		return baseDrink.toString();
	}

}

class Milk extends Ingredient {

	public Milk(Drink d) {
		super(d);
	}
	
	public double getPrice() {
		return super.getPrice() + 0.25;
	}
	
	public String toString() {
		return super.toString() + " with Milk";
	}
	
}

class Cream extends Milk {

	public Cream(Drink d) {
		super(d);
		// TODO Auto-generated constructor stub
	}
	
	public double getPrice() {
		return super.getPrice() + 0.25; //on top of milk's price
	}
	
	public String toString() {
		return super.baseDrink.toString() + " with Cream";
	}
	
}

class Espresso extends Ingredient {

	public Espresso(Drink d) {
		super(d);
	}
	
	public double getPrice() {
		return super.getPrice() + 0.75;
	}
	
	public String toString() {
		return super.toString() + " with Espresso";
	}
	
}

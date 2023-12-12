package structural.decorator;

public class DrinkDemo {
	public static void main(String[] args) {
		Drink tea = new Tea();

		Drink coffeeWithMilk = new Coffee();
		coffeeWithMilk = new Milk(coffeeWithMilk);
		coffeeWithMilk = new Espresso(coffeeWithMilk);

		System.out.println(coffeeWithMilk.toString() + " costs " 
							+ coffeeWithMilk.getPrice());
		
		Drink doubleShot = new Cream(new Espresso(new Espresso(new Coffee())));


		System.out.println(doubleShot.toString() + " costs " 
				+ doubleShot.getPrice());
		
		Drink c = new Coffee();
	}
}

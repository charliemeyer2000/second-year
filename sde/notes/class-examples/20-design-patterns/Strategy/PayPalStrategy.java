package behavioral.strategy;

public class PayPalStrategy implements PaymentStrategy {

	private String emailId;
	private String password;
	
	public PayPalStrategy(String email, String pwd){
		//I'm actually pretty certain PayPal doesn't work this way
		//this is just an in class example
		this.emailId=email;
		this.password=pwd;
	}
	
	@Override
	public void pay(int amount) {
		System.out.println(amount + " paid using Paypal.");
	}

}

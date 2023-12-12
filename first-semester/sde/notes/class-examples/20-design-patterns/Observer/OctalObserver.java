package behavioral.observer;



public class OctalObserver implements Observer<IntegerSubject> {
	@Override
	public void update(IntegerSubject subject) {
		System.out.println("Binary String: " +
				Integer.toOctalString(subject.getState()));
	}
}
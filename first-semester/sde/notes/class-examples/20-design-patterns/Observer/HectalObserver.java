package behavioral.observer;



public class HectalObserver implements Observer<IntegerSubject> {
	@Override
	public void update(IntegerSubject subject) {
		System.out.println("Binary String: " +
				Integer.toHexString(subject.getState()));
	}
}
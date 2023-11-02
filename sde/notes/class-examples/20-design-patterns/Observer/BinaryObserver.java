package behavioral.observer;



public class BinaryObserver implements Observer<IntegerSubject> {
	@Override
	public void update(IntegerSubject subject) {
		System.out.println("Binary String: " +
				Integer.toBinaryString(subject.getState()));
	}
}
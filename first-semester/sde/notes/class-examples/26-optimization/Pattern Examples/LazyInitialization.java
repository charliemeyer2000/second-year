
public class LazyInitialization {
	//attribute
	Thing t;
	
	public LazyInitialization() {
		t = null;
	}
	
	public void getThing() {
		if (t == null) {
			t = new Thing();
		}
		t.method();
	}
	
	public boolean lazyEvaluation() {
		int y = aVeryShortAndFastFunction();
		if (y == 0) {
			return true;
		} else {
			int x = someReallyReallyBigFunction();
			return x > y;
		}
	}

	private int aVeryShortAndFastFunction() {
		// TODO Auto-generated method stub
		return 0;
	}

	private int someReallyReallyBigFunction() {
		// TODO Auto-generated method stub
		return 0;
	}
}

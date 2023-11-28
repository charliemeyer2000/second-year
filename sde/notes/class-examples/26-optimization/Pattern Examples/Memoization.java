import java.util.HashMap;
import java.util.Map;

public class Memoization {
	private Map<Key,Result> cachedResults = new HashMap<Key,Result>();
	
	private Result doCalculation(Key k) {
		//imagine this were a really big method. Maybe getting the median,
		//upper, and lower quartiles of thousands of exams
		return null;
	}
	
	public Result getResult(Key k){
		if (cachedResults.containsKey(k)){
			return cachedResults.get(k);
		}
		else {
			Result r = doCalculation(k);
			cachedResults.put(k, r);
			return r;
		}
	}
}

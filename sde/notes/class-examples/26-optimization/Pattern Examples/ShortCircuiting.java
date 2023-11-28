public class ShortCircuiting {
    public static void shortCircuitExamples() {
        //put the faster operations first in the short circuit

        boolean b = fastMethod() || slowMethod();

        //When both operations are relatively the same cost, consider short-circuiting by probability
        //order by likelihood of being true with ors, false with being ands
        boolean c1 = likelyTrue() || likelyFalse();
        boolean c2 = likelyFalse() && likelyTrue();

        if (fastMethod()) {

        }
        else if (slowMethod()) {

        }
    }

    private static boolean likelyTrue() {
        return false;
    }

    private static boolean likelyFalse() {
        return false;
    }

    private static boolean slowMethod() {
        return false;
    }

    private static boolean fastMethod() {
        return false;
    }
}

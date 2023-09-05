public class MathFunctions {
    public static int square(int number) {
        return number * number;
    }

    /**
     * Returns true if number is divisible by divisor, false otherwise.
     */
    public static boolean isDivisibleBy(int number, int divisor) {
        return (number % divisor) == 0;
    }

    /**
     * Returns true if number is even, false otherwise.
     */
    public static boolean isEven(int number) {
        return isDivisibleBy(number, 2);
    }

    /**
     * Returns true if the number is odd, false otherwise.
     */
    public static boolean isOdd(int number) {
        return !isDivisibleBy(number, 2);
    }

    /**
     * Returns the value of the largest input
     */
    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Returns the value of the largest input
     */
    public static int weirdMax(int a, int b) {
        var diff = Math.abs(a - b);
        var sum = a + b + diff;
        return sum / 2;
    }

    /**
     * Returns the value of the largest input
     */
    public static int max(int a, int b, int c) {
        if (a > b) {
            if (a > c) { return a; }
            else       { return c; }
        } else {
            if (b > c) { return b; }
            else       { return a; }
        }
    }
}

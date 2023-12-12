/**
 * This class tracks a number as well as how many times that number has changed.
 */

public class NumberChanges {
    private int number;
    private int timesChanged;

    /**
     * Constructor where the initial value of the number is specified
     */
    public NumberChanges(int initialNumber) {
        this.number = initialNumber;
        this.timesChanged = 0;
    }

    /**
     * Constructor where the initial value of the number defaults to 0
     */
    public NumberChanges() {
        this(0);
    }

    /**
     * Returns the value of number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the number of times the number has been changed
     */
    public int getTimesChanged() {
        return timesChanged;
    }

    /**
     * Sets the value of number. If the value of newNumber value is different from before,
     * this increments the number of times the number has changed.
     */
    public void setNumber(int newNumber) {
        if (newNumber == number) {
            return;
        }
        number = newNumber;
        timesChanged++;
    }
}

public class FinancialRecord {
	private final int studentId;
	private double amountOverdue; //Prof. note - never use doubles for currency in real software!
	private boolean isInterestExempt;

	public FinancialRecord(int studentId) {
		this(studentId, 0.0, false);
	}

	public FinancialRecord(int studentId, double amountOverdue, boolean isInterestExempt) {
		this.studentId = studentId;
		this.amountOverdue = amountOverdue;
		this.isInterestExempt = isInterestExempt;
	}

	public int getStudentId() {
		return studentId;
	}

	public double getAmountOverdue() {
		return amountOverdue;
	}

	public boolean isInterestExempt() {
		return isInterestExempt;
	}

	public void setAmountOverdue(double amountOverdue) {
		this.amountOverdue = amountOverdue;
	}

	public void setInterestExempt(boolean interestExempt) {
		isInterestExempt = interestExempt;
	}

	/**
	 * Calculates how much the student would owe based on the number of courses taken.
	 * This is used for calculating prospective bills, so this does not update the amountOverdue.
	 * <ul>
	 * <li>if student is taking 1-2 courses, they pay $8000 per course</li>
	 * <li>if a student is taking 3-6 courses, they pay $6000 per course</li>
	 * <li>if a student is taking 7 or more courses, they pay $5500 per course</li>
	 * </ul>
	 * If a student already owes more than $2000, they pay a 10% penalty on the cost of their
	 * courses. Additionally, the amount overdue is increased by 10% (after calculating the course
	 * cost) unless the student is exempt from paying interest.
	 *
	 * @param coursesTaken the number of courses the student will take
	 * @return the prospective bill the student would pay for that number of classes.
	 */
	public double calculateBill(int coursesTaken) {
		double total = 0;
		if (coursesTaken < 3) {
			total = 8000 * coursesTaken;
		} else if (coursesTaken >= 3 && coursesTaken <= 6) {
			total = 6000 * coursesTaken;
		} else {
			total = 5500 * coursesTaken;
		}
		if (amountOverdue <= 2000 && isInterestExempt) {
			return total + amountOverdue;
		} else if (amountOverdue > 2000) {
			if (isInterestExempt) {
				return amountOverdue * 1.1 + total;
			} else {
				return (total + amountOverdue) * 1.1;
			}
		} else {
			return total + amountOverdue * 1.1;
		}
	}
} 


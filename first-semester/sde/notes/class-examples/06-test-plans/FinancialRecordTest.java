import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FinancialRecordTest {
    @Test
    public void constructor_3arg() {
        var financialRecord = new FinancialRecord(1234, 1000.0, false);

        assertEquals(1234, financialRecord.getStudentId());
        assertEquals(1000.0, financialRecord.getAmountOverdue(), 1e-9);
        assertFalse(financialRecord.isInterestExempt());
    }

    @Test
    public void constructor_1arg() {
        var financialRecord = new FinancialRecord(1234);

        assertEquals(1234, financialRecord.getStudentId());
        assertEquals(0.0, financialRecord.getAmountOverdue(), 1e-9);
        assertFalse(financialRecord.isInterestExempt());
    }

    @Test
    public void calculateBill_lowCourse_highOverdue_exemptFalse() {
        var financialRecord = new FinancialRecord(1234, 2500, false);

        assertEquals(20350.0, financialRecord.calculateBill(2), 1e-4);
    }

    @Test
    public void calculateBill_midCourse_lowOverdue_exemptTrue() {
        var financialRecord = new FinancialRecord(1234, 1500, true);

        assertEquals(31500.0, financialRecord.calculateBill(5), 1e-4);
    }

    @Test
    public void calculateBill_highCourse_highOverdue_exemptFalse() {
        var financialRecord = new FinancialRecord(1234, 2500, false);

        assertEquals(51150.0, financialRecord.calculateBill(8), 1e-4);
    }

    @Test
    public void calculateBill_midCourse_lowOverdue_exemptFalse() {
        var financialRecord = new FinancialRecord(1234, 1500, false);

        assertEquals(31650, financialRecord.calculateBill(5), 1e-4);
    }

    @Test
    public void calculateBill_midCourse_highOverdue_exemptTrue() {
        var financialRecord = new FinancialRecord(1234, 2500, true);

        assertEquals(35500, financialRecord.calculateBill(5), 1e-4);
    }
}

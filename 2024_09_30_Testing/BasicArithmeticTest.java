
import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BasicArithmeticTest {
    @Test
    void testSum() {
        BasicArithmetic sum = BasicArithmetic.createOperation(2, 2, "+");
        int result = sum.performOperation();
        assertEquals(4, result);
    }
    @Test
    void testDifference() {
        BasicArithmetic difference = BasicArithmetic.createOperation(11, 4, "-");
        int result = difference.performOperation();
        assertEquals(7, result);
    }
    @Test
    void testProduct() {
        BasicArithmetic product = BasicArithmetic.createOperation(3, 23, "*");
        int result = product.performOperation();
        assertEquals(69, result);
    }
    @Test
    void testQuotient() {
        BasicArithmetic product = BasicArithmetic.createOperation(15, 5, "/");
        int result = product.performOperation();
        assertEquals(3, result);
    }
}

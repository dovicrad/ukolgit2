import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FactorialTest {
    @Test
    public void factorialTest(){
        Factorial fact = new Factorial();
        int n = 3;
        long expectedValue = 6;
        long result = fact.factorial(n);
        Assertions.assertEquals(expectedValue, result);
    }
}

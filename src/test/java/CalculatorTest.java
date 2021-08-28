import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class CalculatorTest {

    private static Calculator calc;

    @BeforeEach
    void createClass(){
        calc = new Calculator();
    }


    @ParameterizedTest
    @MethodSource("mySource")
     void plusTest(int a, int b, int expected) {

        int result = calc.plus.apply(a, b);

        Assertions.assertEquals(result, expected);

    }

    static Stream<Arguments> mySource() {
        return Stream.of(
                arguments(1,2,3),
                arguments(2,4,6),
                arguments(-1,1,0),
                arguments(2,2,5) // 2+2=5
        );
    }


    @Test
    void devideTest() {
        int a = 4, b = 2, expected = 2;

        int result = calc.devide.apply(a, b);

        Assertions.assertEquals(result, expected);

    }

    @Test
    void devideTestByZeroException()  throws ArithmeticException{
        int a = 4, b = 0;

        Throwable thrown = assertThrows(
                ArithmeticException.class,
                () -> calc.devide.apply(a, b));

        assertNotNull(thrown.getMessage());

    }

    @Test
    void devideTestNoByZeroException()  throws ArithmeticException{
        int a = 4, b = 2;

        assertDoesNotThrow(() -> calc.devide.apply(a, b));

    }

    @Test
    void devideTestNoByZeroExceptionIgnoreZero()  throws ArithmeticException{
        int a = 4, b = 0;

        assertDoesNotThrow(() -> calc.devideIgnoreZero.apply(a, b));
        //assertDoesNotThrow(() -> calc.devide.apply(a, b));

    }

    @Test
    void multiplyTest() {
        int a = 4, b = 2;
        assertThat(calc.multiply.apply(a, b), is(a * b));
    }

    @Test
    void minusTest() {
        int a = 4, b = 2;
        assertThat(calc.minus.apply(a, b), is(a - b));
    }
}



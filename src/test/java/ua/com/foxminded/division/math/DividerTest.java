package ua.com.foxminded.division.math;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;

class DividerTest {

    private Divider divider;
    
    @BeforeEach
    void setUp() {
        divider = new Divider();
    }
       
    @Test
    void divide_shouldReturnExpectedStringDividend_whenInputDividendIsPositiveInteger() {
        int inputDividend = 12343;
        int inputDivisor = 3;
        String expectedDividend = "12343";
        
        String actualDividend = divider.divide(inputDividend, inputDivisor).getDividend();
        
        assertThat(actualDividend).isEqualTo(expectedDividend);
    }

    @Test
    void divide_shouldReturnExpectedStringDividend_whenInputDividendIsNegativeInteger() {
        int inputDividend = -12345;
        int inputDivisor = 3;
        String expectedDividend = "-12345";
        
        String actualDividend = divider.divide(inputDividend, inputDivisor).getDividend();
        
        assertThat(actualDividend).isEqualTo(expectedDividend);
    }
    
    @Test
    void divide_shouldReturnExpectedStringDividend_whenInputDividendIsZero() {
        int inputDividend = 0;
        int inputDivisor = 3;
        String expectedDividend = "0";
        
        String actualDividend = divider.divide(inputDividend, inputDivisor).getDividend();
        
        assertThat(actualDividend).isEqualTo(expectedDividend);
    }

   @Test
    void divide_shouldReturnExpectedStringDivisor_whenInputDivisorIsPositiveInteger() {
        int inputDividend = 12345;
        int inputDivisor = 3;
        String expectedDivisor = "3";
        
        String actualDivisor = divider.divide(inputDividend, inputDivisor).getDivisor();
        
        assertThat(actualDivisor).isEqualTo(expectedDivisor);
    }
    
    @Test
    void divide_shouldReturnExpectedStringDivisor_whenInputDivisorIsNegativeInteger() {
        int inputDividend = 12345;
        int inputDivisor = -3;
        String expectedDivisor = "-3";
        
        String actualDivisor = divider.divide(inputDividend, inputDivisor).getDivisor();
        
        assertThat(actualDivisor).isEqualTo(expectedDivisor);
    }
    
    @Test
    void divide_shouldReturnEmptyStringAsSignOfQuotient_whenInputDividendAndDivisorAreNegative() {
        int inputDividend = -12345;
        int inputDivisor = -3;
        String expectedSignOfQuotient = "";
        
        String actualSignOfQuotient = divider.divide(inputDividend, inputDivisor).getSignOfQuotient();
        
        assertThat(actualSignOfQuotient).isEqualTo(expectedSignOfQuotient);
    }
    
    @Test
    void divide_shouldReturnMinusAsSignOfQuotient_whenOnlyInputDividendIsNegative() {
        int inputDividend = -12345;
        int inputDivisor = 3;
        String expectedSignOfQuotient = "-";
        
        String actualSignOfQuotient = divider.divide(inputDividend, inputDivisor).getSignOfQuotient();
        
        assertThat(actualSignOfQuotient).isEqualTo(expectedSignOfQuotient);
    }
    
    @Test
    void divide_shouldReturnMinusAsSignOfQuotient_whenOnlyInputDivisorIsNegative() {
        int inputDividend = 12345;
        int inputDivisor = -3;
        String expectedSignOfQuotient = "-";
        
        String actualSignOfQuotient = divider.divide(inputDividend, inputDivisor).getSignOfQuotient();
        
        assertThat(actualSignOfQuotient).isEqualTo(expectedSignOfQuotient);
    }
    
    @Test
    void divide_shouldReturnEmptyStringAsSignOfQuotient_whenInputDividendAndDivisorArePositive() {
        int inputDividend = 12345;
        int inputDivisor = 3;
        String expectedSignOfQuotient = "";
        
        String actualSignOfQuotient = divider.divide(inputDividend, inputDivisor).getSignOfQuotient();
        
        assertThat(actualSignOfQuotient).isEqualTo(expectedSignOfQuotient);
    }
        
    @Test
    void divide_shouldRemoveZeroesFromFirstPartialQuotient_whenTheyAreInTheBeginingOfNumber() {
        int inputDividend = 12345;
        int inputDivisor = 123;
        String expectedFirstPartialQuotient = "1";
        
        String actualFirstPartialQuotient = divider.divide(inputDividend, inputDivisor).getPartialQuotient(0);
        
        assertThat(actualFirstPartialQuotient).isEqualTo(expectedFirstPartialQuotient);
    }

    @Test
    void divide_shouldAddZeroToQuotient_whenUseMoreThanTwoDigitsForPartialDividend() {
        int inputDividend = 123123;
        int inputDivisor = 123;
        String expectedPartialQuotient = "001";
        
        String actualPartialQuotient = divider.divide(inputDividend, inputDivisor).getPartialQuotient(1);
        
        assertThat(actualPartialQuotient).isEqualTo(expectedPartialQuotient);
    }
    
    @Test
    void divide_shouldAddAdditionalOffset_IntegralPartialDividendBeginsFromZero() {
        int inputDividend = 12300123;
        int inputDivisor = 123;
        String expectedAdditionalOffset = "  ";
        
        String actualAdditionalOffset = divider.divide(inputDividend, inputDivisor).getAdditionalOffset(1);
        
        assertThat(actualAdditionalOffset).isEqualTo(expectedAdditionalOffset);
    }
    
    @Test
    void divide_shouldMoveZerosToQuotientEnd_whenTheyAreInDividendEnd() {
        int inputDividend = 12000;
        int inputDivisor = 4;
        String expectedLastPartialQuotient = "000";
        String actualLastPartialQuotient = divider.divide(inputDividend, inputDivisor).getPartialQuotient(1);
        assertThat(actualLastPartialQuotient).isEqualTo(expectedLastPartialQuotient);
    }
    
}

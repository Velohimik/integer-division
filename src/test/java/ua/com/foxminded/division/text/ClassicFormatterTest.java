package ua.com.foxminded.division.text;

import static org.assertj.core.api.Assertions.*;

import java.io.*;
import org.junit.jupiter.api.*;

import ua.com.foxminded.division.model.Result;

class ClassicFormatterTest {

    private ClassicFormatter classicFormatter;
    private Result result;
    private FileInputStream testResourseFile;
    private String resourceFileName;
    
    @BeforeEach
    void setUp() {
        classicFormatter = new ClassicFormatter();
        result = new Result();
    }

    @Test
    void format_shouldMakeLongDivisionColumn_whenInputSimpleThreeStepsResult() throws IOException {
        resourceFileName = "ExpectedStringWithInputSimpleThreeStepsResult.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(3);
        result.setPartialDividend(0, 7);
        result.setPartialDividend(1, 38);
        result.setPartialDividend(2, 24);
        result.setPartialProduct(0, 4);
        result.setPartialProduct(1, 36);
        result.setPartialProduct(2, 24);
        result.setPartialQuotient(0, "1");
        result.setPartialQuotient(1, "9");
        result.setPartialQuotient(2, "6");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, "");
        result.setAdditionalOffset(2, "");
        result.setRemainder(0, 3);
        result.setRemainder(1, 2);
        result.setRemainder(2, 0);
        result.setSignOfQuotient("");
        result.setDividend(784);
        result.setNegativeDividend(false);
        result.setDivisor(4);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void format_shouldMakeLongDivisionColumnWithAdditionalOffset_whenDividendIsNegative() throws IOException {
        resourceFileName = "ExpectedStringWithNegativeDividend.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(3);;
        result.setPartialDividend(0, 7);
        result.setPartialDividend(1, 38);
        result.setPartialDividend(2, 24);
        result.setPartialProduct(0, 4);
        result.setPartialProduct(1, 36);
        result.setPartialProduct(2, 24);
        result.setPartialQuotient(0, "1");
        result.setPartialQuotient(1, "9");
        result.setPartialQuotient(2, "6");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, "");
        result.setAdditionalOffset(2, "");
        result.setRemainder(0, 3);
        result.setRemainder(1, 2);
        result.setRemainder(2, 0);
        result.setSignOfQuotient("-");
        result.setDividend(-784);
        result.setNegativeDividend(true);
        result.setDivisor(4);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void format_shouldMakeLineUnderDivisorAsLongAsDivisorLength_whenDivisorLongerThanQuotient() throws IOException {
        resourceFileName = "ExpectedStringWithDivisorLongerThanQuotient.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(2);;
        result.setPartialDividend(0, 1234);
        result.setPartialDividend(1, 5565);
        result.setPartialProduct(0, 678);
        result.setPartialProduct(1, 5424);
        result.setPartialQuotient(0, "1");
        result.setPartialQuotient(1, "8");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, "");
        result.setRemainder(0, 556);
        result.setRemainder(1, 141);
        result.setSignOfQuotient("");
        result.setDividend(12345);
        result.setNegativeDividend(false);
        result.setDivisor(678);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void format_shouldFinishLongDivisionColumn_whenLastPartialDividendEqualsLastRemainder() throws IOException {
        resourceFileName = "ExpectedStringWithLastPartialDividendEqualsLastRemainder.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(3);
        result.setPartialDividend(0, 117);
        result.setPartialDividend(1, 209);
        result.setPartialDividend(2, 22);
        result.setPartialProduct(0, 115);
        result.setPartialProduct(1, 207);
        result.setPartialProduct(2, 207);
        result.setPartialQuotient(0, "5");
        result.setPartialQuotient(1, "09");
        result.setPartialQuotient(2, "0");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, "");
        result.setAdditionalOffset(2, "");
        result.setRemainder(0, 2);
        result.setRemainder(1, 2);
        result.setRemainder(2, 22);
        result.setSignOfQuotient("");
        result.setDividend(117092);
        result.setNegativeDividend(false);
        result.setDivisor(23);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void format_shouldAddAdditionalOffset_whenLastRemainderEqualsZero() throws IOException {
        resourceFileName = "ExpectedStringWithLastRemainderEqualsZero.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(2);
        result.setPartialDividend(0, 115);
        result.setPartialDividend(1, 192);
        result.setPartialProduct(0, 115);
        result.setPartialProduct(1, 184);
        result.setPartialQuotient(0, "5");
        result.setPartialQuotient(1, "008");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, "");
        result.setRemainder(0, 0);
        result.setRemainder(1, 8);
        result.setSignOfQuotient("");
        result.setDividend(115192);
        result.setNegativeDividend(false);
        result.setDivisor(23);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void format_shouldSetLastRemainderUnderLastProduct_whenAFewZeroesAreInTheEndOfDividend() throws IOException {
        resourceFileName = "ExpectedStringWithAFewZeroesInTheEndOfDividend.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(3);
        result.setPartialDividend(0, 115);
        result.setPartialDividend(1, 184);
        result.setPartialDividend(2, 0);
        result.setPartialProduct(0, 115);
        result.setPartialProduct(1, 184);
        result.setPartialProduct(2, 0);
        result.setPartialQuotient(0, "5");
        result.setPartialQuotient(1, "08");
        result.setPartialQuotient(2, "00");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, "");
        result.setAdditionalOffset(2, "");
        result.setRemainder(0, 1);
        result.setRemainder(1, 0);
        result.setRemainder(2, 0);
        result.setSignOfQuotient("");
        result.setDividend(1168400);
        result.setNegativeDividend(false);
        result.setDivisor(23);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    @Test
    void format_shouldAddOffset_whenZeroesAreInTheMiddleOfDividend() throws IOException {
        resourceFileName = "ExpectedStringWithZeroesInTheMiddleOfDividend.txt";
        String expectedString = makeExpectedString(resourceFileName);
        makeDivisionStepsList(2);
        result.setPartialDividend(0, 123);
        result.setPartialDividend(1, 124);
        result.setPartialProduct(0, 123);
        result.setPartialProduct(1, 123);
        result.setPartialQuotient(0, "1");
        result.setPartialQuotient(1, "0001");
        result.setAdditionalOffset(0, "");
        result.setAdditionalOffset(1, " ");
        result.setRemainder(0, 0);
        result.setRemainder(1, 1);
        result.setSignOfQuotient("");
        result.setDividend(1230124);
        result.setNegativeDividend(false);
        result.setDivisor(123);

        String actualString = classicFormatter.format(result);

        assertThat(actualString).isEqualTo(expectedString);
    }

    private String makeExpectedString(String fileName) throws IOException {
        StringBuilder expextedStringBuilder = new StringBuilder();
        testResourseFile = new FileInputStream((new File("src/test/resources/" + fileName)).getAbsolutePath());
        int i = 0;
        
        do {
            i = testResourseFile.read();
            if (i != -1) {
                expextedStringBuilder.append((char) i);
            }
        } while (i != -1);

        return expextedStringBuilder.toString();
    }
    
    private void makeDivisionStepsList(int numberOfSteps) {
        for (int i = 0; i < numberOfSteps; i++) {
            result.createNewStep();
            result.addStepToList();
        }
    }
}

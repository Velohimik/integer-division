package ua.com.foxminded.division;

import org.junit.jupiter.api.*;
import org.mockito.*;
import java.io.*;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import ua.com.foxminded.division.math.Divider;
import ua.com.foxminded.division.model.Result;
import ua.com.foxminded.division.text.*;
import ua.com.foxminded.exception.BadArgsException;

class MainTest {
    private Context context;
    private Divider divider;
    private Result result;
    private Formatter formatter;
    private String outputString;
    private String fakeConsoleOutput;
    private String expectedConcoleOutput;
    private PrintStream printStream;
    private ByteArrayOutputStream catchStandardOutput;

    @BeforeEach
    void setUp() {
        context = mock(Context.class);
        divider = mock(Divider.class);
        result = new Result();
        formatter = mock(ClassicFormatter.class);
        catchStandardOutput = new ByteArrayOutputStream();
        printStream = new PrintStream(catchStandardOutput);
    }

    @Test
    void main_shouldPrintInConsoleString_whenItIsReturnedByFormatter() throws IOException {
        String[] args = { "784", "4" };
        String resourceFileName = "ExpectedStringForMainTest.txt";
        try (MockedStatic<Context> staticContext = Mockito.mockStatic(Context.class)) {
            staticContext.when(() -> Context.newInstance(args)).thenReturn(context);
        }
        when(context.getDivider()).thenReturn(divider);
        when(divider.divide(784, 4)).thenReturn(result);
        when(context.getFormatter()).thenReturn(formatter);
        when(formatter.format(result)).thenReturn(outputString);
        outputString = makeExpectedString(resourceFileName);
        System.setOut(printStream);
        Main.main(args);
        fakeConsoleOutput = catchStandardOutput.toString();
        expectedConcoleOutput = outputString + System.lineSeparator();

        assertThat(fakeConsoleOutput).isEqualTo(expectedConcoleOutput);
    }

    @Test
    void main_shouldCatchAnException_whenItIsThrownByDivisionArgsValidator() {
        String[] args = { "784", "0" };
        String exceptionMessage = "Wrong input! Division by zero is prohibited!";
        try (MockedStatic<Context> staticContext = Mockito.mockStatic(Context.class)) {
            staticContext.when(() -> Context.newInstance(args)).thenThrow(new BadArgsException(exceptionMessage));
        }
        System.setOut(printStream);
        Main.main(args);
        fakeConsoleOutput = catchStandardOutput.toString();
        expectedConcoleOutput = exceptionMessage + System.lineSeparator();

        assertThat(fakeConsoleOutput).isEqualTo(expectedConcoleOutput);
    }

    private String makeExpectedString(String fileName) throws IOException {
        StringBuilder expextedStringBuilder = new StringBuilder();
        FileInputStream testResourseFile = new FileInputStream((new File("src/test/resources/" + fileName)).getAbsolutePath());
        int i = 0;

        do {
            i = testResourseFile.read();
            if (i != -1) {
                expextedStringBuilder.append((char) i);
            }
        } while (i != -1);

        return expextedStringBuilder.toString();
    }
}

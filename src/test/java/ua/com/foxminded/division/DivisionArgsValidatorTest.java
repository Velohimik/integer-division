package ua.com.foxminded.division;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.*;

import ua.com.foxminded.exception.BadArgsException;

class DivisionArgsValidatorTest {

    @Test
    void validator_shouldReturnException_whenTheNumberOfArgumentsIsMoreTwo() {
        String[] args = { "127589", "34", "5" };
        assertThatExceptionOfType(BadArgsException.class).isThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        });
    }

    @Test
    void validator_shouldReturnExceptionWithCurrentMessage_whenTheNumberOfArgumentsIsMoreTwo() {
        String[] args = { "127589", "34", "5" };
        String message = "You should use two arguments";
        assertThatThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        }).hasMessageContaining(message);
    }

    @Test
    void validator_shouldNotReturnException_whenTwoArgumentsAre() {
        String[] args = { "1234", "56" };
        assertThatNoException().isThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        });
    }

    @Test
    void validator_shouldReturnException_whenArgumentsGoOutIntegerLimit() {
        String[] args = { "12758998798678567", "34" };
        assertThatExceptionOfType(BadArgsException.class).isThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        });
    }

    @Test
    void validator_shouldReturnExceptionWithCurrentMessage_whenArgumentsGoOutIntegerLimit() {
        String[] args = { "127589", "323423424235453454354" };
        String message = "should be the integer numbers between";
        assertThatThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        }).hasMessageContaining(message);
    }

    @Test
    void validator_shouldNotReturnException_whenArgumentsAreIntoIntegerLimit() {
        String[] args = { "1234", "56" };
        assertThatNoException().isThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        });
    }

    @Test
    void validator_shouldReturnException_whenDivisorIsZero() {
        String[] args = { "12758998", "0" };
        assertThatExceptionOfType(BadArgsException.class).isThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        });
    }

    @Test
    void validator_shouldReturnExceptionWithCurrentMessage_whenDivisorIsZer() {
        String[] args = { "127589", "0" };
        String message = "Division by zero";
        assertThatThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        }).hasMessageContaining(message);
    }

    @Test
    void validator_shouldNotReturnException_whenDivisorIsNotZero() {
        String[] args = { "12346789", "564" };
        assertThatNoException().isThrownBy(() -> {
            DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        });
    }
}
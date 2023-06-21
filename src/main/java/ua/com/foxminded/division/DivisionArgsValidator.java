package ua.com.foxminded.division;

import ua.com.foxminded.exception.BadArgsException;

public class DivisionArgsValidator {

    public DivisionArgsValidator(String[] args) throws BadArgsException {
        String message;
        
        if (args.length != 2) {
            message = "Wrong input! You should use two arguments. The example of correct one: div 12345 6.";
            throw new BadArgsException(message);
        }

        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            message = "Wrong input! Dividend and divisor should be the integer numbers between -2 147 483 648 and 2 147 483 647.";
            throw new BadArgsException(message);
        }

        if (Integer.parseInt(args[1]) == 0) {
            message = "Wrong input! Division by zero is prohibited!";
            throw new BadArgsException(message);
        }
    }
}

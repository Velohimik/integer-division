package ua.com.foxminded.division;

import ua.com.foxminded.division.math.Divider;
import ua.com.foxminded.division.model.Result;
import ua.com.foxminded.division.text.*;
import ua.com.foxminded.exception.BadArgsException;

/**
 * 
 * @author Andriy Rybalka
 *
 * This class takes input data from user and displays the result of division.
 *
 */

public class Main {

    public static void main(String[] args) {
        Context context;
        
        try {
            context = Context.newInstance(args);
            Divider divider = context.getDivider();
            Result result = divider.divide(context.getDividend(), context.getDivisor());
            Formatter formatter = context.getFormatter();
            String output = formatter.format(result);
            System.out.println(output);
        } catch (BadArgsException e) {
            System.out.println(e.getMessage());
        }
    }
}

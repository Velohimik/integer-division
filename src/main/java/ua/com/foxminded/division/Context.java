package ua.com.foxminded.division;

import ua.com.foxminded.division.math.Divider;
import ua.com.foxminded.division.text.ClassicFormatter;
import ua.com.foxminded.division.text.Formatter;
import ua.com.foxminded.exception.BadArgsException;

public class Context {
    
    private int dividend;
    private int divisor;
    private Divider divider;
    private Formatter formatter;
        
    Context(String[] args) throws BadArgsException {
        DivisionArgsValidator divisionArgsValidator = new DivisionArgsValidator(args);
        dividend = Integer.parseInt(args[0]);
        divisor = Integer.parseInt(args[1]);
        divider = new Divider();
        formatter = new ClassicFormatter();
    }
    
    public static Context newInstance (String[] args) throws BadArgsException {
        return (new Context(args));
    }
    
    public Divider getDivider() {
        return divider;
    }
    
    public Formatter getFormatter() {
        return formatter;
    }
    
    public int getDivisor() {
        return divisor;
    }
    
    public int getDividend() {
        return dividend;
    }
}

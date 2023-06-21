package ua.com.foxminded.division.math;

import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.division.model.*;

/**
 * 
 * @author Andriy Rybalka
 * 
 *         This class performs division algorithm, it is implemented in "divide"
 *         method. This method takes "dividend" and "divisor" and returns the
 *         instance of "Result" class. It is separated on five stage. They are:
 *         making of integral partial dividend, partial dividend, partial
 *         quotient, partial product and remainder. These stages are performed
 *         in loop. At the start of a loop the counter (an amount of hidden
 *         digits in dividend) is equal to the number of digits in dividend.
 *         During every step of a loop the counter decrease. Division finishes
 *         when counter is 0.
 *
 */

public class Divider {

    private Result result;
    private int hiddenDigits; // an amount of hidden digits in dividend during making of partial dividend. If
                              // it is zero division is finished

    public Result divide(int dividendWithSign, int divisorWithSign) {
        int dividend = Math.abs(dividendWithSign);
        int divisor = Math.abs(divisorWithSign);
        int integralPartialDividend = 0;
        int partialDividend = 0;
        int partialQuotient = 0;
        int partialProduct = 0;
        int remainder = 0;
        boolean isNegativeDividend = dividendWithSign < 0;
        result = new Result();
        integralPartialDividend = dividend;
        
        hiddenDigits = countNumberOfDigits(dividend); // in the beginning all digits of dividend are hidden
        result.setDividend(dividendWithSign);
        result.setDivisor(divisorWithSign);
        result.setSignOfQuotient(determineSignOfQuotient(dividendWithSign, divisorWithSign));
        result.setNegativeDividend(isNegativeDividend);

        while (hiddenDigits != 0) { // division loop fulfills till all dividend digits will be opened
            result.createNewStep();
            
            integralPartialDividend = makeIntegralPartialDividend(integralPartialDividend, remainder, partialDividend);
            partialDividend = makePartialDividend(integralPartialDividend, divisor);
            
            if (partialDividend == 0) { // check the situation when dividend has a several zeros in the end
                moveZerosFromDividendEndToQuotientEnd();
            }
            
            if (partialDividend >= divisor) {
                partialQuotient = makePartialQuotient(partialDividend, divisor);
                partialProduct = makePartialProduct(partialQuotient, divisor);
                remainder = makeRemainder(partialDividend, divisor);
                result.addPartialDividendToStep(partialDividend);
                result.addPartialProductToStep(partialProduct);
                result.addPartialQuotientToStep(partialQuotient);
                result.addRemainderToStep(remainder);
            } else {
                result.addRemainderToStep(partialDividend);
                result.addPartialDividendToStep(partialDividend);
                result.addPartialProductToStep(partialProduct);
                result.addStepToList();
                break;
            }
            
            result.addStepToList();
        }

        result.setPartialQuotient(0, removeZerosFromQuotientBegining(result.getPartialQuotient(0)));

        return result;
    }

    private String removeZerosFromQuotientBegining(String firstPartialQuotient) {
        List<Character> firstPartialQuotientCharsList;
        StringBuilder partialQuotientAfterRemoving = new StringBuilder();

        firstPartialQuotientCharsList = firstPartialQuotient.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

        while (firstPartialQuotientCharsList.get(0) == '0' && firstPartialQuotientCharsList.size() > 1) {
            firstPartialQuotientCharsList.remove(0);
        }

        for (Character simpleChar : firstPartialQuotientCharsList) {
            partialQuotientAfterRemoving.append(simpleChar);
        }

        return partialQuotientAfterRemoving.toString();
    }

    private String determineSignOfQuotient(int dividendWithSign, int divisorWithSign) {
        String signOfQuotient = "";

        if ((dividendWithSign < 0 && divisorWithSign > 0) || (dividendWithSign > 0 && divisorWithSign < 0)) {
            signOfQuotient = "-";
        }

        return signOfQuotient;
    }

    private void moveZerosFromDividendEndToQuotientEnd() {
        hiddenDigits--;
 
        while (hiddenDigits != 0) {
            result.addPartialQuotientToStep(0);
            hiddenDigits--;
        }
    }

    private int raiseTenInPowerOf(int exponentOfPower) {
        return (int) Math.pow(10, exponentOfPower);
    }

    private int countNumberOfDigits(int number) {
        int numberOfDigits;
        
        if (number != 0) {
            numberOfDigits = (int) Math.log10(number) + 1;
        } else {
            numberOfDigits = 1;
        }
        
        return numberOfDigits;
    }

    private int makeIntegralPartialDividend(int recentIntegralPartialDividend, int remainder,
            int recentPartialDividend) {
        return (recentIntegralPartialDividend - raiseTenInPowerOf(hiddenDigits) * (recentPartialDividend - remainder));
    }

    private int makePartialDividend(int integralPartialDividend, int divisor) {
        int partialDividend = 0;
        String additionalOffset = "";
        StringBuilder offsetStringBuilder = new StringBuilder();

        for (int i = 0; partialDividend < divisor; i++) {
            if (i > 1) {
                result.addPartialQuotientToStep(0); // loop add zero to quotient everytime when we should use more then
                                                    // two digits of integral partial dividend
                if (partialDividend == 0) {
                    offsetStringBuilder.append(" ");                    
                    additionalOffset = offsetStringBuilder.toString();
                }
            }
            if (partialDividend != integralPartialDividend) {
                partialDividend = integralPartialDividend / raiseTenInPowerOf(hiddenDigits);
                hiddenDigits--;
            } else {
                break;
            }
        }
        
        hiddenDigits++;
        result.addAdditionalOffsetToStep(additionalOffset);

        return partialDividend;
    }

    private int makePartialQuotient(int partialDividend, int divisor) {
        return (partialDividend / divisor);
    }

    private int makePartialProduct(int partialQuotient, int divisor) {
        return (partialQuotient * divisor);
    }

    private int makeRemainder(int partialDividend, int divisor) {
        return (partialDividend % divisor);
    }
}

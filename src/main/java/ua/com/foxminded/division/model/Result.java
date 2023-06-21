package ua.com.foxminded.division.model;

import java.util.*;

/**
 * 
 * @author Andriy Rybalka
 *
 *         This class saves results of division from Divider class.
 *
 */

public class Result {
    private String dividend;
    private boolean isNegativeDividend;
    private String divisor;
    private String signOfQuotient;
    private List<Step> divisionSteps = new ArrayList<>();
    private Step step;

    public String getDividend() {
        return dividend;
    }

    public void setDividend(int dividend) {
        this.dividend = dividend + "";
    }

    public boolean isNegativeDividend() {
        return isNegativeDividend;
    }

    public void setNegativeDividend(boolean isNegativeDividend) {
        this.isNegativeDividend = isNegativeDividend;
    }

    public String getDivisor() {
        return divisor;
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor + "";
    }
    
    public String getSignOfQuotient() {
        return signOfQuotient;
    }

    public void setSignOfQuotient(String signOfQuotient) {
        this.signOfQuotient = signOfQuotient;
    }

    public List<Step> getDivisionSteps() {
        return divisionSteps;
    }
 
    public void createNewStep() {
        step = new Step();
    }

    public void addStepToList() {
        divisionSteps.add(step);
    }
    
    public void addPartialDividendToStep(int partialDividend) {
        step.partialDividend = partialDividend + "";
    }
    
    public String getPartialDividend(int i) {
        return divisionSteps.get(i).partialDividend;
    }
    
    public void setPartialDividend(int i, int partialDividend) {
        divisionSteps.get(i).partialDividend = partialDividend + "";
    }

    public void addPartialProductToStep(int partialProduct) {
        step.partialProduct = partialProduct + "";
    }
    
    public String getPartialProduct(int i) {
        return divisionSteps.get(i).partialProduct;
    }

    public void setPartialProduct(int i, int partialProduct) {
        divisionSteps.get(i).partialProduct = partialProduct + "";
    }
    
    public void addRemainderToStep(int remiander) {
        step.remainder = remiander + "";
    }
    
    public String getRemainder(int i) {
        return divisionSteps.get(i).remainder;
    }
    
    public void setRemainder(int i, int remiander) {
        divisionSteps.get(i).remainder = remiander + "";
    }
    
    public void addPartialQuotientToStep(int partialQuotient) {
        step.partialQuotient += partialQuotient;
    }
    
    public String getPartialQuotient(int i) {
        return divisionSteps.get(i).partialQuotient;
    }
    
    public void setPartialQuotient(int i, String partialQuotient) {
        divisionSteps.get(i).partialQuotient = partialQuotient;
    }
    
    public void addAdditionalOffsetToStep(String additionalOffset) {
        step.additionalOffset = additionalOffset;
    }
    
    public String getAdditionalOffset(int i) {
        return divisionSteps.get(i).additionalOffset;
    }
    
    public void setAdditionalOffset(int i, String additionalOffset) {
        divisionSteps.get(i).additionalOffset = additionalOffset;
    }
    
    private class Step {
        private String partialDividend;
        private String partialProduct;
        private String partialQuotient = "";
        private String additionalOffset = "";
        private String remainder;
    }
}

package ua.com.foxminded.division.text;

import ua.com.foxminded.division.model.Result;

public class ClassicFormatter implements Formatter {

    public String format(Result result) {
        int stepsListSize = result.getDivisionSteps().size();
        int firstPartialDividendLength = result.getPartialDividend(0).length();
        int penultimatePartialDividendLength = result.getPartialDividend(stepsListSize - 2).length();
        int firstPartialProductLength = result.getPartialProduct(0).length();
        int dividendLength = result.getDividend().length();
        int divisorLength = result.getDivisor().length();
        int quotientLength = makeQuotientFromSteps(result).length();
        int spacesUnderDividend = dividendLength - firstPartialDividendLength;
        StringBuilder resultStringBuilder = new StringBuilder();
        String innerOffset = " " + makeStringOfSpaces(firstPartialDividendLength - firstPartialProductLength);
        String outterOffset = "";
        StringBuilder outterOffsetBuilder = new StringBuilder();
        String remainderOffset;
        String lastRemainder = result.getRemainder(stepsListSize - 1);
        String lastPartialDividend = result.getPartialDividend(stepsListSize - 1);
        String lineEnding = "\r\n";

        if (result.isNegativeDividend()) {
            innerOffset += " ";
            outterOffsetBuilder.append(" ");
            spacesUnderDividend -= 1;
        }

        resultStringBuilder.append("_" + result.getDividend() + "|" + result.getDivisor() + lineEnding);
        resultStringBuilder
                .append(innerOffset + result.getPartialProduct(0) + makeStringOfSpaces(spacesUnderDividend) + "|");

        if (divisorLength > quotientLength) {
            resultStringBuilder.append(makeStringOfDashes(divisorLength) + lineEnding);
        } else {
            resultStringBuilder.append(makeStringOfDashes(quotientLength) + lineEnding);
        }

        resultStringBuilder.append(innerOffset + makeStringOfDashes(firstPartialProductLength)
                + makeStringOfSpaces(spacesUnderDividend) + "|");
        resultStringBuilder.append(makeQuotientFromSteps(result) + lineEnding);

        for (int i = 1; i <= stepsListSize - 1; i++) {
            if (result.getPartialDividend(i).equals(result.getRemainder(i))) {
                break;
            }
            innerOffset = makeStringOfSpaces(
                    result.getPartialDividend(i).length() - result.getPartialProduct(i).length() + 1);

            if ("0".equals(result.getRemainder(i - 1))) {
                outterOffsetBuilder.append(makeStringOfSpaces(
                        result.getPartialDividend(i - 1).length() - result.getRemainder(i - 1).length() + 1));
            } else {
                outterOffsetBuilder.append(makeStringOfSpaces(
                        result.getPartialDividend(i - 1).length() - result.getRemainder(i - 1).length()));
            }

            outterOffset = outterOffsetBuilder.toString();
            resultStringBuilder
                    .append(outterOffset + result.getAdditionalOffset(i) + "_" + result.getPartialDividend(i) + lineEnding);
            resultStringBuilder.append(
                    outterOffset + result.getAdditionalOffset(i) + innerOffset + result.getPartialProduct(i) + lineEnding);
            resultStringBuilder.append(outterOffset + " " + result.getAdditionalOffset(i)
                    + makeStringOfDashes(result.getPartialDividend(i).length()) + lineEnding);
        }

        if (lastRemainder.equals("0") && lastRemainder.equals(lastPartialDividend)) {
            remainderOffset = outterOffset
                    + makeStringOfSpaces(penultimatePartialDividendLength - lastRemainder.length() + 1);
        } else {
            remainderOffset = makeStringOfSpaces(dividendLength - lastRemainder.length() + 1);
        }

        resultStringBuilder.append(remainderOffset + lastRemainder);

        return resultStringBuilder.toString();
    }

    private String makeStringOfSpaces(int amountOfSpaces) {
        StringBuilder spaceStringBuilder = new StringBuilder();

        for (int i = 0; i < amountOfSpaces; i++) {
            spaceStringBuilder.append(" ");
        }

        return spaceStringBuilder.toString();
    }

    private String makeStringOfDashes(int amountOfDashes) {
        StringBuilder dashStringBuilder = new StringBuilder();

        for (int i = 0; i < amountOfDashes; i++) {
            dashStringBuilder.append("-");
        }

        return dashStringBuilder.toString();
    }

    private String makeQuotientFromSteps(Result result) {
        StringBuilder quotientStringBuilder = new StringBuilder();
        
        if (!result.getPartialQuotient(0).equals("0")) {
            quotientStringBuilder.append(result.getSignOfQuotient());
        }

        for (int i = 0; i < result.getDivisionSteps().size(); i++) {
            quotientStringBuilder.append(result.getPartialQuotient(i));
        }

        return quotientStringBuilder.toString();
    }
}

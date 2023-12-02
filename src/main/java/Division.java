import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Division {
    final int FUNDATION_DEGREE = 10;
    int dividendWithSign = 0;
    int divisorWithSign = 0;
    int resultOfDivision = 0;
    int module = 0;
    int countSpaces = 1;
    StringBuilder divisionInfo = new StringBuilder();

    public static void main(String[] args) {
        new Division().outputDivision();
    }


    private void outputDivision() {
        inputParams();
        int dividend = Math.abs(dividendWithSign);
        int divisor = Math.abs(divisorWithSign);
        resultOfDivision = dividend / divisor;
        List<DivisionIntermediateResult> resultDivisionList = calculateResult(dividend, divisor);
        outputDividendDivisorAndAllDivisionInfo(dividend, resultDivisionList);
        outputResult();
    }


    private void inputParams() {
        dividendWithSign = inputDividend();
        inputNotNullDivisor();
    }

    private void outputResult() {
        System.out.println(divisionInfo);
    }

    public void outputDividendDivisorAndAllDivisionInfo(int dividend, List<DivisionIntermediateResult> resultDivisionList) {
        outputDividendAndDivisorAndResult(resultOfDivision, countingNumberDigits(dividend));
        printAllDivisionInfo(resultDivisionList);
    }

    private void printAllDivisionInfo(List<DivisionIntermediateResult> resultDivisionList) {
        int iteration = 0;
        for (DivisionIntermediateResult currentDividend : resultDivisionList) {
            if (iteration < 2) {
                outputDividingColumnTwoTimes(currentDividend.trimmedPartOfDividend, currentDividend.moduloPartOfDividend);
                iteration++;
            } else {
                outputDividingColumn(currentDividend.trimmedPartOfDividend, currentDividend.moduloPartOfDividend);
            }
        }
        outputModuloAndResult();
    }


    public List<DivisionIntermediateResult> calculateResult(int dividend, int divisor) {
        DivisionIntermediateResult calculateResult = new DivisionIntermediateResult();
        List<DivisionIntermediateResult> divisionIntermediateResultList = new ArrayList<>();
        calculateResult.dividend = dividend;
        while (calculateResult.dividend > divisor) {
            int currentDividend = calculateResult.dividend;
            calculateResult = divisionInsideColumn(currentDividend, divisor);
            calculateResult.dividend = controlModuloSign(resultOfDivision, calculateResult.dividend);
            divisionIntermediateResultList.add(calculateResult);
        }
        module = calculateResult.dividend;
        return divisionIntermediateResultList;
    }


    /**
     * Checks for modulo sign
     *
     * @param resultOfDivision The result of dividing
     * @param module           The modulo of dividing
     * @return module
     */
    private int controlModuloSign(int resultOfDivision, int module) {
        if ((resultOfDivision < 0) && (dividendWithSign < 0)) {
            module = -module;
        }
        return module;
    }

    /**
     * Checks for division by zero
     */
    private void inputNotNullDivisor() {
        while (divisorWithSign == 0) {
            divisorWithSign = inputDivisor();
        }
    }

    /**
     * Performs intermediate calculations of data division, which are displayed in the column division
     *
     * @param dividend Number which needs to be dividend
     * @param divisor  Divider for division performance in a column
     * @return DivisionIntermediateResult
     */

    private DivisionIntermediateResult divisionInsideColumn(int dividend, int divisor) {
        DivisionIntermediateResult intermediateResult = new DivisionIntermediateResult();
        int moduloPartOfDividend = 0;
        int digitsForTrip = countingNumberDigits(dividend) - countingNumberDigits(divisor);
        int trimmedPartOfDividend = trimmingEndOfIntegers(dividend, (digitsForTrip));
        if (trimmedPartOfDividend >= divisor) {
            moduloPartOfDividend = (trimmedPartOfDividend / divisor) * divisor;
            dividend = dividend - moduloPartOfDividend * exponentiationWithBaseTen(digitsForTrip);
        } else {
            trimmedPartOfDividend = trimmingEndOfIntegers(dividend, (digitsForTrip - 1));
            moduloPartOfDividend = (trimmedPartOfDividend / divisor) * divisor;
            dividend = (dividend - (moduloPartOfDividend * exponentiationWithBaseTen(countingNumberDigits(dividend)
                    - (countingNumberDigits(trimmedPartOfDividend)))));
        }
        intermediateResult.dividend = dividend;
        intermediateResult.moduloPartOfDividend = moduloPartOfDividend;
        intermediateResult.trimmedPartOfDividend = trimmedPartOfDividend;
        return intermediateResult;
    }

    /**
     * Displays the specified character set number of times
     *
     * @param numberOfSymbol Quantity of symbols for a conclusion on the screen
     * @param typeOfSymbol   Type of symbol for a conclusion on the screen
     */

    private void drawSymbol(int numberOfSymbol, char typeOfSymbol) {
        switch (typeOfSymbol) {
            case ' ': {
                for (int i = 1; i <= numberOfSymbol; i++) {
                    divisionInfo.append(" ");
                }
                break;
            }
            case '-': {
                for (int i = 1; i <= numberOfSymbol; i++) {
                    divisionInfo.append("-");
                }
                break;
            }
        }
    }

    /**
     * Counting the number of digits specified number
     *
     * @param numberForCounting Number for calculation of digits
     * @return Number of figures for the set number
     */
    private int countingNumberDigits(int numberForCounting) {
        if (numberForCounting != 0) {
            return (int) Math.log10(Math.abs(numberForCounting)) + 1;
        } else {
            return 1;
        }
    }

    /**
     * Exponentiation of the specified power with base 10
     *
     * @param exponent exponent in which it is necessary to erect the set number
     * @return The result of the exponentiation of the specified power with base 10
     */

    private int exponentiationWithBaseTen(int exponent) {
        return (int) Math.pow(FUNDATION_DEGREE, exponent);
    }

    /**
     * Does cutting of the specified number on the set number of figures since the end
     *
     * @param integerForTrimming  The number for cutting of end
     * @param numberDigitsForTrim The number of digits to trim the specified number
     * @return Cropped number
     */
    private int trimmingEndOfIntegers(int integerForTrimming, int numberDigitsForTrim) {
        return (int) (integerForTrimming / (Math.pow(10, numberDigitsForTrim)));
    }

    /**
     * Reads the input data for long division
     *
     * @return Value of a dividend
     */
    private int inputDividend() {
        int moduloPartOfDividend = 0;
        Scanner scanner = new Scanner(System.in);
        boolean errorInputDividendAndDivisor = false;
        do {
            try {
                System.out.println("Enter dividend: ");
                moduloPartOfDividend = Integer.parseInt(scanner.nextLine());
                errorInputDividendAndDivisor = false;
            } catch (Exception error) {
                System.out.println("Error: Enter another number");
                errorInputDividendAndDivisor = true;
            }
        } while (errorInputDividendAndDivisor);
        return moduloPartOfDividend;
    }

    /**
     * Reads the input data for long division
     *
     * @return Value of a divisor
     */

    private int inputDivisor() {
        int moduloPartOfDivisor = 0;
        Scanner scanner = new Scanner(System.in);
        boolean errorInputDividendAndDivisor = false;
        do {
            try {
                System.out.println("Enter divisor(not 0): ");
                moduloPartOfDivisor = Integer.parseInt(scanner.nextLine());
                errorInputDividendAndDivisor = false;
            } catch (Exception error) {
                System.out.println("Error: Enter another number");
                errorInputDividendAndDivisor = true;
            }
        } while (errorInputDividendAndDivisor);
        return moduloPartOfDivisor;
    }

    /**
     * Carries out a conclusion of entry conditions for division in a column
     *
     * @param dividendDigits The number of digits in a divider
     */

    private void outputDividendAndDivisorAndResult(int resultOfDivision, int dividendDigits) {
        divisionInfo.append(" ");
        divisionInfo.append(dividendWithSign + "|" + divisorWithSign + "\n");
        drawSymbol(dividendDigits, ' ');
        divisionInfo.append(" ");
        divisionInfo.append("|");
        drawSymbol(countingNumberDigits(divisorWithSign), '-');
        divisionInfo.append("\n");
        drawSymbol(dividendDigits, ' ');
        divisionInfo.append(" ");
        divisionInfo.append("|" + resultOfDivision + "\n");
        drawSymbol(dividendDigits, ' ');
    }

    /**
     * Carries out a conclusion of a column of division
     *
     * @param partOfDividend       Part of the dividend that divides
     * @param modulePartOfDividend The remainder after dividing  from part of the dividend
     */
    private void outputDividingColumn(int partOfDividend, int modulePartOfDividend) {
        divisionInfo.append("\n");
        outputSpacesMinusOneCountSpaces();
        divisionInfo.append(partOfDividend);
        divisionInfo.append("\n");
        outputSpacesMinusTwoCountSpaces();
        divisionInfo.append("—");
        outputSpacesMinusTwoCountSpaces();
        divisionInfo.append("\n");
        outputSpacesMinusTwoCountSpaces();
        divisionInfo.append(modulePartOfDividend);
        divisionInfo.append("\n");
        outputSpacesMinusTwoCountSpaces();
        drawSymbol(countingNumberDigits(partOfDividend), '-');

    }

    private void outputDividingColumnTwoTimes(int partOfDividend, int modulePartOfDividend) {
        divisionInfo.append("\n");
        divisionInfo.append(" ");
        divisionInfo.append(partOfDividend);
        divisionInfo.append("\n");
        divisionInfo.append(" ");
        divisionInfo.append("—");
        divisionInfo.append("\n");
        divisionInfo.append(" ");
        divisionInfo.append(modulePartOfDividend);
        divisionInfo.append("\n");
        divisionInfo.append(" ");
        drawSymbol(countingNumberDigits(partOfDividend), '-');
        countSpaces++;
    }

    /**
     * Add output spaces before digits
     *
     */

    private void outputSpacesMinusTwoCountSpaces() {
        drawSymbol(countSpaces - 2, ' ');
    }

    private void outputSpacesMinusOneCountSpaces() {
        drawSymbol(countSpaces - 1, ' ');
        countSpaces++;
    }

    private void outputModuloAndResult() {
        divisionInfo.append("\n");
        outputSpacesMinusOneCountSpaces();
        divisionInfo.append(module);
        divisionInfo.append("\n");
        divisionInfo.append("Result: " + resultOfDivision);
        divisionInfo.append("\n");
        divisionInfo.append("Module: " + module);
    }

}

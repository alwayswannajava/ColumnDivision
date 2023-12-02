import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

class DivisionTest {

    @Test
    @DisplayName("Test correct divison on null")
    public void testCorrectDivisionWithNull() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(0);
        int divisor = Math.abs(125);
        List<DivisionIntermediateResult> resultOfDivisionList = longDivision.calculateResult(dividend, divisor);
        assertEquals(0, resultOfDivisionList.size());
    }

    @Test
    @DisplayName("Test correct output divison on null")
    public void testCorrectOutputDivisionWithNull() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(0);
        int divisor = Math.abs(125);
        longDivision.dividendWithSign = dividend;
        longDivision.divisorWithSign = divisor;
        String expected = " 0|125\n" +
                "  |---\n" +
                "  |0\n" +
                " \n" +
                "0\n" +
                "Result: 0\n" +
                "Module: 0";
        longDivision.outputDividendDivisorAndAllDivisionInfo(dividend, new ArrayList<>());
        assertEquals(expected, longDivision.divisionInfo.toString());
    }

    @Test
    @DisplayName("Test correct division with dividend less than divisor")
    public void testCorrectDivisionWithDididendLessThanDivisor() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(8);
        int divisor = Math.abs(42);
        List<DivisionIntermediateResult> resultOfDivisionList = longDivision.calculateResult(dividend, divisor);
        assertEquals(0, resultOfDivisionList.size());
    }

    @Test
    @DisplayName("Test correct division output with dividend less than divisor")
    public void testCorrectDivisionOutputWithDididendLessThanDivisor() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(8);
        int divisor = Math.abs(42);
        longDivision.dividendWithSign = dividend;
        longDivision.divisorWithSign = divisor;
        longDivision.module = 8;
        String expected = " 8|42\n" +
                "  |--\n" +
                "  |0\n" +
                " \n" +
                "8\n" +
                "Result: 0\n" +
                "Module: 8";
        longDivision.outputDividendDivisorAndAllDivisionInfo(dividend, new ArrayList<>());
        assertEquals(expected, longDivision.divisionInfo.toString());
    }

    @Test
    @DisplayName("Test correct division when dividend equals divisor")
    public void testCorrectDivisionWhenDividendEqualsDivisor() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(888);
        int divisor = Math.abs(888);
        List<DivisionIntermediateResult> resultOfDivisionList = longDivision.calculateResult(dividend, divisor);
        assertEquals(0, resultOfDivisionList.size());
    }

    @Test
    @DisplayName("Test correct division output when dividend equals divisor")
    public void testCorrectDivisionOutputWhenDividendEqualsDivisor() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(888);
        int divisor = Math.abs(888);
        longDivision.dividendWithSign = dividend;
        longDivision.divisorWithSign = divisor;
        longDivision.resultOfDivision = dividend / divisor;
        longDivision.module = 888;
        String expected = " 888|888\n" +
                "    |---\n" +
                "    |1\n" +
                "   \n" +
                "888\n" +
                "Result: 1\n" +
                "Module: 888";
        longDivision.outputDividendDivisorAndAllDivisionInfo(dividend, new ArrayList<>());
        assertEquals(expected, longDivision.divisionInfo.toString());
    }

    @Test
    @DisplayName("Test#1 correct division with some big numbers")
    public void testCorrectDivisionNumberOneWithSomeBigNumbers() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(5125);
        int divisor = Math.abs(3);
        DivisionIntermediateResult theFirstOneIntermediateResult = new DivisionIntermediateResult(2125, 5, 3);
        DivisionIntermediateResult theSecondOneIntermediateResult = new DivisionIntermediateResult(25, 21, 21);
        DivisionIntermediateResult theThirdOneIntermediateResult = new DivisionIntermediateResult(1, 25, 24);
        List<DivisionIntermediateResult> expectedList = new ArrayList<>();
        expectedList.add(theFirstOneIntermediateResult);
        expectedList.add(theSecondOneIntermediateResult);
        expectedList.add(theThirdOneIntermediateResult);
        List<DivisionIntermediateResult> resultOfDivisionList = longDivision.calculateResult(dividend, divisor);
        assertEquals(3, resultOfDivisionList.size());
        assertTrue(expectedList.equals(resultOfDivisionList));
    }

    @Test
    @DisplayName("Test#1 correct output division with some big numbers")
    public void testCorrectNumberOneDivisionOutputWithSomeBigNumbers() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(5125);
        int divisor = Math.abs(3);
        longDivision.dividendWithSign = dividend;
        longDivision.divisorWithSign = divisor;
        longDivision.resultOfDivision = dividend / divisor;
        String expected = " 5125|3\n" +
                "     |-\n" +
                "     |1708\n" +
                "    \n" +
                " 5\n" +
                " —\n" +
                " 3\n" +
                " -\n" +
                " 21\n" +
                " —\n" +
                " 21\n" +
                " --\n" +
                "  25\n" +
                "  —  \n" +
                "  21\n" +
                "  --\n" +
                "   4\n" +
                "Result: 1708\n" +
                "Module: 4";
        DivisionIntermediateResult theFirstOneIntermediateResult = new DivisionIntermediateResult(2125, 5, 3);
        DivisionIntermediateResult theSecondOneIntermediateResult = new DivisionIntermediateResult(25, 21, 21);
        DivisionIntermediateResult theThirdOneIntermediateResult = new DivisionIntermediateResult(4, 25, 21);
        longDivision.module = theThirdOneIntermediateResult.dividend;
        List<DivisionIntermediateResult> expectedList = new ArrayList<>();
        expectedList.add(theFirstOneIntermediateResult);
        expectedList.add(theSecondOneIntermediateResult);
        expectedList.add(theThirdOneIntermediateResult);
        longDivision.outputDividendDivisorAndAllDivisionInfo(dividend, expectedList);
        assertEquals(expected, longDivision.divisionInfo.toString());
    }

    @Test
    @DisplayName("Test#2 correct division with some big numbers")
    public void testCorrectDivisionNumberTwoWithSomeBigNumbers() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(555_555_521);
        int divisor = Math.abs(123_512);
        DivisionIntermediateResult theFirstOneIntermediateResult = new DivisionIntermediateResult(61507521, 555555, 494048);
        DivisionIntermediateResult theSecondOneIntermediateResult = new DivisionIntermediateResult(12102721, 615075, 494048);
        DivisionIntermediateResult theThirdOneIntermediateResult = new DivisionIntermediateResult(986641, 1210272, 1111608);
        DivisionIntermediateResult theFourthOneIntermediateResult = new DivisionIntermediateResult(122057,986641,864584);
        List<DivisionIntermediateResult> expectedList = new ArrayList<>();
        expectedList.add(theFirstOneIntermediateResult);
        expectedList.add(theSecondOneIntermediateResult);
        expectedList.add(theThirdOneIntermediateResult);
        expectedList.add(theFourthOneIntermediateResult);
        List<DivisionIntermediateResult> resultOfDivisionList = longDivision.calculateResult(dividend, divisor);
        assertEquals(4, resultOfDivisionList.size());
        assertTrue(expectedList.equals(resultOfDivisionList));
    }

    @Test
    @DisplayName("Test#2 correct output division with some big numbers")
    public void testCorrectNumberTwoDivisionOutputWithSomeBigNumbers() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(555_555_521);
        int divisor = Math.abs(123_512);
        longDivision.dividendWithSign = dividend;
        longDivision.divisorWithSign = divisor;
        longDivision.resultOfDivision = dividend / divisor;
        String expected = " 555555521|123512\n" +
                "          |------\n" +
                "          |4497\n" +
                "         \n" +
                " 555555\n" +
                " —\n" +
                " 494048\n" +
                " ------\n" +
                " 615075\n" +
                " —\n" +
                " 494048\n" +
                " ------\n" +
                "  1210272\n" +
                "  —  \n" +
                "  1111608\n" +
                "  -------\n" +
                "   986641\n" +
                "   —   \n" +
                "   864584\n" +
                "   ------\n" +
                "    122057\n" +
                "Result: 4497\n" +
                "Module: 122057";
        DivisionIntermediateResult theFirstOneIntermediateResult = new DivisionIntermediateResult(61507521, 555555, 494048);
        DivisionIntermediateResult theSecondOneIntermediateResult = new DivisionIntermediateResult(12102721, 615075, 494048);
        DivisionIntermediateResult theThirdOneIntermediateResult = new DivisionIntermediateResult(986641, 1210272, 1111608);
        DivisionIntermediateResult theFourthOneIntermediateResult = new DivisionIntermediateResult(122057,986641,864584);
        longDivision.module = theFourthOneIntermediateResult.dividend;
        List<DivisionIntermediateResult> expectedList = new ArrayList<>();
        expectedList.add(theFirstOneIntermediateResult);
        expectedList.add(theSecondOneIntermediateResult);
        expectedList.add(theThirdOneIntermediateResult);
        expectedList.add(theFourthOneIntermediateResult);
        longDivision.outputDividendDivisorAndAllDivisionInfo(dividend, expectedList);
        assertEquals(expected, longDivision.divisionInfo.toString());
    }

    @Test
    @DisplayName("Test#3 correct division with some big numbers")
    public void testCorrectDivisionNumberThreeWithSomeBigNumbers() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(777_425_2);
        int divisor = Math.abs(512_22);
        DivisionIntermediateResult theFirstOneIntermediateResult = new DivisionIntermediateResult(2652052, 77742, 51222);
        DivisionIntermediateResult theSecondOneIntermediateResult = new DivisionIntermediateResult(90952, 265205, 256110);
        DivisionIntermediateResult theThirdOneIntermediateResult = new DivisionIntermediateResult(39730, 90952, 51222);
        longDivision.module = theThirdOneIntermediateResult.dividend;
        List<DivisionIntermediateResult> expectedList = new ArrayList<>();
        expectedList.add(theFirstOneIntermediateResult);
        expectedList.add(theSecondOneIntermediateResult);
        expectedList.add(theThirdOneIntermediateResult);
        List<DivisionIntermediateResult> resultOfDivisionList = longDivision.calculateResult(dividend, divisor);
        assertEquals(3, resultOfDivisionList.size());
        assertTrue(expectedList.equals(resultOfDivisionList));
    }

    @Test
    @DisplayName("Test#3 correct output division with some big numbers")
    public void testCorrectNumberThreeDivisionOutputWithSomeBigNumbers() throws Exception {
        Division longDivision = new Division();
        int dividend = Math.abs(777_425_2);
        int divisor = Math.abs(512_22);
        longDivision.dividendWithSign = dividend;
        longDivision.divisorWithSign = divisor;
        longDivision.resultOfDivision = dividend / divisor;
        String expected = " 7774252|51222\n" +
                "        |-----\n" +
                "        |151\n" +
                "       \n" +
                " 77742\n" +
                " —\n" +
                " 51222\n" +
                " -----\n" +
                " 265205\n" +
                " —\n" +
                " 256110\n" +
                " ------\n" +
                "  90952\n" +
                "  —  \n" +
                "  51222\n" +
                "  -----\n" +
                "   39730\n" +
                "Result: 151\n" +
                "Module: 39730";
        DivisionIntermediateResult theFirstOneIntermediateResult = new DivisionIntermediateResult(2652052, 77742, 51222);
        DivisionIntermediateResult theSecondOneIntermediateResult = new DivisionIntermediateResult(90952, 265205, 256110);
        DivisionIntermediateResult theThirdOneIntermediateResult = new DivisionIntermediateResult(39730, 90952, 51222);
        longDivision.module = theThirdOneIntermediateResult.dividend;
        List<DivisionIntermediateResult> expectedList = new ArrayList<>();
        expectedList.add(theFirstOneIntermediateResult);
        expectedList.add(theSecondOneIntermediateResult);
        expectedList.add(theThirdOneIntermediateResult);
        longDivision.outputDividendDivisorAndAllDivisionInfo(dividend, expectedList);
        assertEquals(expected, longDivision.divisionInfo.toString());
    }
}

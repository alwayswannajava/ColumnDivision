import java.util.Objects;

public class DivisionIntermediateResult {
    int dividend;
    int trimmedPartOfDividend;
    int moduloPartOfDividend;

    public DivisionIntermediateResult(){

    }
    public DivisionIntermediateResult(int dividend, int trimmedPartOfDividend, int moduloPartOfDividend) {
        this.dividend = dividend;
        this.trimmedPartOfDividend = trimmedPartOfDividend;
        this.moduloPartOfDividend = moduloPartOfDividend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DivisionIntermediateResult)) return false;
        DivisionIntermediateResult that = (DivisionIntermediateResult) o;
        return dividend == that.dividend && trimmedPartOfDividend == that.trimmedPartOfDividend && moduloPartOfDividend == that.moduloPartOfDividend;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividend, trimmedPartOfDividend, moduloPartOfDividend);
    }
}

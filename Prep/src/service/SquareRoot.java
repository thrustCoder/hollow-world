package service;

import java.math.BigDecimal;
import java.util.Optional;

import util.Printer;

public class SquareRoot {
    public static void invoke() {
        Printer.println(getFloorSqrt(3l));

        Printer.println(getPrecisionSqrt(new BigDecimal("10.55"), 3));
    }

    /**
     * Prints floor of square root
     * Ref. https://www.geeksforgeeks.org/square-root-of-an-integer/
     */
    private static Long getFloorSqrt(final Long num) {
        // input validation
        if (num < 0) {
            throw new IllegalStateException("Invalid input: " + num);
        } else if (num <= 1) {
            return num;
        } else {
            // do binary search
            Optional<Long> optResult = Optional.empty();
            Long start = 1l;
            Long end = num;

            while (start <= end) {
                Long mid = (start + end) / 2;
                Long product = mid * mid;

                if (product == num) {
                    // num is a perfect square
                    return mid;

                } else if (product < num) {
                    start = mid + 1;
                    optResult = Optional.of(mid);

                } else {
                    end = mid - 1;
                }
            }
            return optResult.orElseThrow(() -> new IllegalStateException("Result cannot be empty"));
        }
    }

    /**
     * Given precision p, finds square root upto p decimal places
     */
    private static BigDecimal getPrecisionSqrt(final BigDecimal num, final int p) {

        final Long floorSqrt = getFloorSqrt(num.longValue());

        return getPrecisionSqrtUtil(floorSqrt, p, num);
    }

    /**
     * Ref. https://www.geeksforgeeks.org/find-square-root-number-upto-given-precision-using-binary-search/
     */
    private static BigDecimal getPrecisionSqrtUtil(final Long floorSqrt, final int p, final BigDecimal num) {
        BigDecimal result = BigDecimal.valueOf(floorSqrt);
        BigDecimal fraction = new BigDecimal("0.1");

        for (int i = 0; i < p; i ++) {
            while (result.multiply(result).compareTo(num) <= 0) {
                result = result.add(fraction);
            }

            result = result.subtract(fraction);

            fraction = fraction.divide(new BigDecimal("10"));
        }
        return result;
    }
}

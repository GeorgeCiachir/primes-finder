package com.georgeciachir.primesfinder.strategy.util;

import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for comparison operations for BigIntegers
 */
public class BigIntUtil {

    public static boolean isEven(BigInteger value) {
        return !isOdd(value);
    }

    public static boolean isOdd(BigInteger value) {
        return value.testBit(0);
    }

    public static boolean isSmallerOrEqual(BigInteger value, BigInteger to) {
        return value.compareTo(to) <= 0;
    }

    public static boolean isSmaller(BigInteger value, BigInteger to) {
        return value.compareTo(to) < 0;
    }

    public static boolean isEqual(BigInteger value, BigInteger to) {
        return value.compareTo(to) == 0;
    }

    public static boolean isGreater(BigInteger value, BigInteger than) {
        return value.compareTo(than) > 0;
    }

    public static boolean isGreaterOrEqual(BigInteger value, BigInteger than) {
        return value.compareTo(than) >= 0;
    }

    //TODO: check if this can be replaced with known co-primes for toIncluding, instead of using an interval
    public static BigInteger generateWitnessInInterval(BigInteger fromIncluding, BigInteger toIncluding) {
        BigInteger a;
        do {
            a = new BigInteger(toIncluding.bitLength(), ThreadLocalRandom.current());
        } while (isSmallerOrEqual(a, fromIncluding) || isGreaterOrEqual(a, toIncluding));
        return a;
    }
}

package com.georgeciachir.primesfinder.strategy;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

import static com.georgeciachir.primesfinder.strategy.util.BigIntUtil.*;
import static java.math.BigInteger.*;

/**
 * Strategy for finding prime numbers
 * This class is based on the Miller-Rabin primality test
 */
@Component
public class MillerRabinStrategy implements PrimeFinderStrategy {

    private static final int ITERATIONS = 50; // The number of witnesses required for validation
    private static final BigInteger FOUR = BigInteger.valueOf(4);
    private static final BigInteger THREE = BigInteger.valueOf(3);

    @Override
    public boolean isSatisfiedBy(BigInteger value) {
        return true;
    }

    /**
     * @inheritDoc
     */
    @Override
    public BigInteger nextPrime(BigInteger value, boolean includingValue) {
        BigInteger candidate = value;

        if (!includingValue) {
            candidate = candidate.add(ONE);
        }

        //TODO: check if primorial numbers help and if it doesn't skip primes
        while (!computePrimalityInternal(candidate)) {
            candidate = candidate.add(ONE);
        }

        return candidate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPrime(BigInteger n) {
        return computePrimalityInternal(n);
    }

    /**
     * It returns false if n is composite and returns true if n is a probable prime number
     */
    private boolean computePrimalityInternal(BigInteger n) {
        if (isSmallerOrEqual(n, ONE) || isEqual(n, FOUR)) {
            return false;
        }

        if (isSmallerOrEqual(n, THREE)) {
            return true;
        }

        if (isEven(n)) {
            return false;
        }

        // Find r and d so that
        // n - 1 = (2^r)*d , r>=1 , d>0

        // Find n-1
        BigInteger nMinusOne = n.subtract(ONE);

        // Find r
        BigInteger r = ONE; // because nMinusOne it is even, it will divide at least once
        BigInteger twoToThePowerOfR = TWO;

        BigInteger mutableNMinusOne = nMinusOne.divide(TWO);
        while (isEven(mutableNMinusOne)) {
            r = r.add(ONE);
            // calculate 2^r
            twoToThePowerOfR = twoToThePowerOfR.multiply(TWO);
            mutableNMinusOne = mutableNMinusOne.divide(TWO);
        }

        // Find d
        BigInteger d = nMinusOne.divide(twoToThePowerOfR);

        for (int i = 0; i < ITERATIONS; i++) {
            if (!passesMillerMillerRabinTest(n, d, r)) {
                return false;
            }
        }

        return true;
    }

    /**
     * The actual test for primality
     * It returns false if n is composite and returns true if n is a probable prime number
     */
    private static boolean passesMillerMillerRabinTest(BigInteger n, BigInteger d, BigInteger r) {
        BigInteger nMinusOne = n.subtract(ONE);

        // The witness - a random between (1, n)
        BigInteger a = generateWitnessInInterval(ONE, n);

        // Compute x = (a^d) % n
        BigInteger x = a.modPow(d, n);

        if (isEqual(x, ONE) || isEqual(x, nMinusOne)) {
            return true;
        }

        while (isGreater(r, ZERO)) {
            x = x.modPow(TWO, n);

            if (isEqual(x, ONE)) {
                return false;
            }
            if (isEqual(x, nMinusOne)) {
                return true;
            }

            r = r.subtract(ONE);
        }

        return false;
    }
}

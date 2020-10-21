package com.georgeciachir.primesfinder.strategy;

import java.math.BigInteger;

public interface PrimeFinderStrategy {

    /**
     * @param value The value on which the test is performed on
     * @return if the strategy can be applied for the given value
     */
    boolean isSatisfiedBy(BigInteger value);

    /**
     * Returns the next prime number equal to or greater than the given input
     * The decision to include or not the current value is provided
     * by the boolean parameter
     *
     * @param value          The input to start from
     * @param includingValue If the "from" value should be included as a result
     * @return the next prime number
     */
    BigInteger nextPrime(BigInteger value, boolean includingValue);

    /**
     * Returns whether the the given input is a prime number
     *
     * @param toTest The value to test for primality
     * @return Whether the the given input is a prime number
     */
    boolean isPrime(BigInteger toTest);
}

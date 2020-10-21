package com.georgeciachir.primesfinder.service;

public interface PrimesService {

    /**
     * Returns the next prime number equal to or greater than the given input
     * The decision to include or not the current value is provided
     * by the boolean parameter
     *
     * @param from          The value to start from in order to find the next prime number
     * @param includingFrom If the "from" value should be included as a result
     * @return The string representation of the found prime number
     */
    String findNextPrime(String from, boolean includingFrom);

    /**
     * Returns whether the the given input is a prime number
     *
     * @param toTest The value to test for primality
     * @return Whether the the given input is a prime number
     */
    boolean isPrime(String toTest);
}

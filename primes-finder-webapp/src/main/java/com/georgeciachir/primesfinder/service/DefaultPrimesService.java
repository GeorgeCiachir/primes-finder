package com.georgeciachir.primesfinder.service;

import com.georgeciachir.primesfinder.strategy.PrimeFinderStrategy;
import com.georgeciachir.primesfinder.strategy.PrimeFinderStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class DefaultPrimesService implements PrimesService {

    private final PrimeFinderStrategyFactory finderStrategyFactory;
    private final InputValidator validator;

    /**
     * @inheritDoc
     */
    @Override
    public String findNextPrime(String from, boolean includingFrom) {
        BigInteger validatedValue = doValidation(from);

        PrimeFinderStrategy strategy = finderStrategyFactory.getStrategyFor(validatedValue);

        return strategy.nextPrime(validatedValue, includingFrom).toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isPrime(String toTest) {
        BigInteger validatedValue = doValidation(toTest);

        PrimeFinderStrategy strategy = finderStrategyFactory.getStrategyFor(validatedValue);

        return strategy.isPrime(validatedValue);
    }

    private BigInteger doValidation(String value) {
        validator.validate(value);
        return new BigInteger(value);
    }
}

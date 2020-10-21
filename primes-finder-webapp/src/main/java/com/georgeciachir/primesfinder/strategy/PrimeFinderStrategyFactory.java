package com.georgeciachir.primesfinder.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

import static com.georgeciachir.primesfinder.service.InputValidator.INVALID_INPUT_MESSAGE;

@Component
@RequiredArgsConstructor
public class PrimeFinderStrategyFactory {

    private final List<PrimeFinderStrategy> primeFinders;

    public PrimeFinderStrategy getStrategyFor(BigInteger value) {
        return primeFinders.stream()
                .filter(strategy -> strategy.isSatisfiedBy(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT_MESSAGE));
    }
}

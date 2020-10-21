package com.georgeciachir.primesfinder.strategy;

import com.georgeciachir.primesfinder.UnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import static com.georgeciachir.primesfinder.strategy.TestUtils.readNumbers;
import static com.georgeciachir.primesfinder.strategy.util.BigIntUtil.generateWitnessInInterval;
import static java.math.BigInteger.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MillerRabinStrategyTest extends UnitTest {

    @InjectMocks
    private MillerRabinStrategy strategy;

    @Test
    public void isSatisfiedBy_willReturnTrue() {
        BigInteger bigInteger = generateWitnessInInterval(ONE, TEN);

        assertTrue(strategy.isSatisfiedBy(bigInteger));
    }

    /**
     * This test validates the algorithm against a list of known large primes,
     * previously calculated using {@link BigInteger#nextProbablePrime()}
     */
    @Test
    public void nextPrime() {
        readNumbers("primes.txt")
                .forEach(prime -> {
                    BigInteger found = strategy.nextPrime(prime, true);
                    assertEquals(prime, found);
                });

        readNumbers("veryBigPrimes.txt")
                .forEach(prime -> {
                    BigInteger found = strategy.nextPrime(prime, true);
                    assertEquals(prime, found);
                });
    }

    @Test
    public void givenOneAsValueAndShouldIncludeOne_thenReturnTwo() {
        BigInteger nextPrime = strategy.nextPrime(ONE, true);

        assertEquals(TWO, nextPrime);
    }

    @Test
    public void givenOneAsValueAndShouldNotIncludeOne_thenReturnTwo() {
        BigInteger nextPrime = strategy.nextPrime(ONE, false);

        assertEquals(TWO, nextPrime);
    }

    @Test
    public void isPrime() {
        // known primes (also included in the randomNumbersEndingIn1or3or7or9.txt file)
        List<BigInteger> primes_ending_in_1_3_7_9 =
                List.of(
                        new BigInteger("530770359250718438814246422278274880978650339287374590826975287294991225701"),
                        new BigInteger("959886981933527743690278821104811050770316395823045945694995200839978888717"),
                        new BigInteger("863166595859187539559487597693695481462549734437685776238808198078769781587"),
                        new BigInteger("479326723304565047177328527955518109007318814704352660333248548138288931679"),
                        new BigInteger("563959233538003537991201091930755280001354376741888300005859867474093013703"));

        List<BigInteger> foundPrimes = readNumbers("randomNumbersEndingIn1or3or7or9.txt")
                .stream()
                .filter(value -> strategy.isPrime(value))
                .collect(Collectors.toList());

        assertThat(primes_ending_in_1_3_7_9).containsExactlyInAnyOrderElementsOf(foundPrimes);
    }
}
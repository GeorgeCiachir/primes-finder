package com.georgeciachir.primesfinder.strategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.georgeciachir.primesfinder.strategy.util.BigIntUtil.isSmaller;

public final class TestUtils {

    public static Set<BigInteger> readNumbers(String file) {
        try {
            ClassLoader classLoader = TestUtils.class.getClassLoader();
            Path path = Paths.get(new URI(classLoader.getResource(file).toString()));
            return Files.lines(path)
                    .map(BigInteger::new)
                    .collect(Collectors.toCollection(TreeSet::new));
        } catch (IOException | URISyntaxException e) {
            throw new AssertionError("The file cannot be read");
        }
    }

    public static void generatePrimesInInterval(BigInteger from, BigInteger to) {
        File primesFiles = new File("test.txt");
        try (PrintWriter pw = new PrintWriter(primesFiles)) {
            for (BigInteger i = from; isSmaller(i, to); i = i.nextProbablePrime()) {
                pw.print(i);
                pw.println();
            }
        } catch (FileNotFoundException e) {
            throw new AssertionError("The file must exist at this point");
        }
    }
}

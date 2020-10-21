package com.georgeciachir.primesfinder;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class UnitTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
}

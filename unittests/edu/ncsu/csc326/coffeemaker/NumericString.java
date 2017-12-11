package edu.ncsu.csc326.coffeemaker;

import org.mockito.ArgumentMatcher;

public class NumericString extends ArgumentMatcher<String> {
    @Override
    public boolean matches(Object argument) {
        // Use regex to make sure argument is a positive number represented as a string
        return (argument instanceof String && ((String) argument).matches("^\\d+$"));
    }
}

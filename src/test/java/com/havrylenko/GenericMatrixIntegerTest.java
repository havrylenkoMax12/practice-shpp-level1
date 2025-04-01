package com.havrylenko;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
 class GenericMatrixIntegerTest {

    //test-Type-Value-Counts
    @Test
     void testIntegerPositiveSmall() {
        String minimum = "0";
        String maximum = "1000";
        String increment = "10";
        String type = "int";
        PropertyProcessor processor = new PropertyProcessor(type,minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (Integer.parseInt(maximum) - Integer.parseInt(minimum)) / Integer.parseInt(increment) +1;
        assertEquals(size*size, result.length);
        assertEquals((Integer.parseInt(minimum)*Integer.parseInt(minimum)) + "",result[0].toString());
        assertEquals((Integer.parseInt(maximum)*Integer.parseInt(maximum)) + "",result[result.length-1].toString());
    }

    @Test
     void testIntegerAllBig() {
        String minimum = "-10000";
        String maximum = "10000";
        String increment = "10";
        String type = "int";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (Integer.parseInt(maximum) - Integer.parseInt(minimum)) / Integer.parseInt(increment) +1;
        assertEquals(size*size, result.length);
        assertEquals((Integer.parseInt(minimum)*Integer.parseInt(minimum)) + "",result[0].toString());
        assertEquals((Integer.parseInt(maximum) * Integer.parseInt(maximum)) + "", result[result.length - 1].toString());
    }

    @Test
     void testIntegerZeroIncrement() {
        String minimum = "0";
        String maximum = "1000";
        String increment = "0";
        String type = "int";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);

        try {
            Object[] result = processor.startAlgorithm();
            assertEquals(0,result.length);
        } catch (IllegalArgumentException e) {
            assertEquals("Increment cannot be zero", e.getMessage());
        }
    }

    @Test
     void testIntegerOutOfRange() {
        String minimum = ""+ Math.pow(2,-34);
        String maximum = ""+ Math.pow(2,-34);
        String increment = ""+ Math.pow(2,-34);
        String type = "int";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);
        try {
            Object[] result = processor.startAlgorithm();
            assertEquals(0,result.length);
        } catch (IllegalArgumentException e) {
            assertEquals("Increment cannot be zero", e.getMessage());
        }
    }

    @Test
     void testIntegerLetters() {
        String minimum = "0hfjhjjmn";
        String maximum = "1000gfjdkfjhhj";
        String increment = "0uytjh";
        String type = "int";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);

        try {
            Object[] result = processor.startAlgorithm();
            assertEquals(0,result.length);
        } catch (IllegalArgumentException e) {
            assertEquals("Increment cannot be zero", e.getMessage());
        }
    }

    @Test
     void testTestParseError() {
        String minimum = "1000.8";
        String maximum = "10000.7";
        String increment = "100";
        String type = "int";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();
        maximum = roundValueFromString(maximum);
        minimum = roundValueFromString(minimum);
        increment = roundValueFromString(increment);

        int size = (Integer.parseInt(maximum) - Integer.parseInt(minimum)) / Integer.parseInt(increment) +1;
        assertEquals(size*size, result.length);
        assertEquals((Integer.parseInt(minimum)*Integer.parseInt(minimum)) + "",result[0].toString());
        assertEquals((Integer.parseInt(maximum) * Integer.parseInt(maximum)) + "", result[result.length - 1].toString());
    }

    private String roundValueFromString(String value) {
        try {
            return Math.round(Double.parseDouble(value)) + "";
        }catch (NumberFormatException e){
            return value;
        }
    }
}

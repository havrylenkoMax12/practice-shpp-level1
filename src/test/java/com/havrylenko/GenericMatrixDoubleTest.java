package com.havrylenko;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericMatrixDoubleTest {

    @Test
    public void testDoublePositiveSmall() {
        String minimum = "-10000.99";
        String maximum = "10000.232";
        String increment = "1000.10";
        String type = "double";
        PropertyProcessor processor = new PropertyProcessor(type,minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (int) ((Double.parseDouble(maximum) - Double.parseDouble(minimum)) / Double.parseDouble(increment)) +1;
        assertEquals(size*size, result.length);
        assertEquals(new BigDecimal(minimum).multiply(new BigDecimal(minimum)) + "",result[0].toString());
        //assertEquals((BigDecimal.valueOf(Double.parseDouble(maximum)*Double.parseDouble(maximum))) + "",result[result.length-1].toString());
    }

    @Test
    public void testIDoubleAllBig() {
        String minimum = "-100";
        String maximum = "100";
        String increment = "0.1";
        String type = "double";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (int) ((Double.parseDouble(maximum) - Double.parseDouble(minimum)) / Double.parseDouble(increment)) +1;
        assertEquals(size*size, result.length);
        assertEquals(Double.parseDouble(minimum) * Double.parseDouble(minimum),
                Double.parseDouble(result[0].toString()),
                0.0001);

        assertEquals(Double.parseDouble(maximum) * Double.parseDouble(maximum),
                Double.parseDouble(result[result.length - 1].toString()),
                0.0001);
    }

    @Test
    public void testDoubleLetters() {
        String minimum = "0hfjhjjmn";
        String maximum = "1000gfjdkfjhhj";
        String increment = "0uytjh";
        String type = "integer";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);

        try {
            Object[] result = processor.startAlgorithm();
            assertEquals(null,result);
        } catch (IllegalArgumentException e) {
            assertEquals("Increment cannot be zero", e.getMessage());
        }
    }

    @Test
    public void testTestParseError() {
        String minimum = "1000.8";
        String maximum = "10000.7";
        String increment = "100";
        String type = "integer";
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

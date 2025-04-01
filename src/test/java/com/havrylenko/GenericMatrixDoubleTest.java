package com.havrylenko;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericMatrixDoubleTest {

    @Test
    void testDoublePositiveSmall() {
        String minimum = "-10000.99";
        String maximum = "10000.232";
        String increment = "1000.10";
        String type = "double";
        PropertyProcessor processor = new PropertyProcessor(type,minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (int) ((Double.parseDouble(maximum) - Double.parseDouble(minimum)) / Double.parseDouble(increment)) +1;
        assertEquals(size*size, result.length);
        assertEquals(new BigDecimal(minimum).multiply(new BigDecimal(minimum)) + "",result[0].toString());
    }

    @Test
     void testIDoubleAllBig() {
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
     void testDoubleLetters() {
        String minimum = "0hfjhjjmn";
        String maximum = "1000gfjdkfjhhj";
        String increment = "0uytjh";
        String type = "double";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);

        try {
            Object[] result = processor.startAlgorithm();
            assertEquals(0,result.length);
        } catch (IllegalArgumentException e) {
            assertEquals("Increment cannot be zero", e.getMessage());
        }
    }
}

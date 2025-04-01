package com.havrylenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenericMatrixShortTest {

    //test-Type-Value-Counts
    @Test
    public void testShortPositiveSmall() {
        String minimum = "0";
        String maximum = "1000";
        String increment = "10";
        String type = "short";
        PropertyProcessor processor = new PropertyProcessor(type,minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (Short.parseShort(maximum) - Short.parseShort(minimum)) / Short.parseShort(increment) +1;
        assertEquals(size*size, result.length);
        assertEquals((Short.parseShort(minimum)*Short.parseShort(minimum)) + "",result[0].toString());
        assertEquals((Short.parseShort(maximum)*Short.parseShort(maximum)) + "",result[result.length-1].toString());
    }

    @Test
    public void testShortAllBig() {
        String minimum = "-2000";
        String maximum = "2000";
        String increment = "10";
        String type = "short";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();

        int size = (Short.parseShort(maximum) - Short.parseShort(minimum)) / Short.parseShort(increment) +1;
        assertEquals(size*size, result.length);
        assertEquals((Short.parseShort(minimum) * Short.parseShort(minimum)) + "",result[0].toString());
        assertEquals((Short.parseShort(maximum) * Short.parseShort(maximum)) + "", result[result.length - 1].toString());
    }

    @Test
    public void testShortZeroIncrement() {
        String minimum = "0";
        String maximum = "1000";
        String increment = "0";
        String type = "short";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);


        Object[] result = processor.startAlgorithm();
        assertEquals(null,result);
    }

    @Test
    public void testShortOutOfRange() {
        String minimum = ""+ Math.pow(10,5);
        String maximum = ""+ Math.pow(10,5);
        String increment = ""+ Math.pow(2,8);
        String type = "short";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);

        Object[] result = processor.startAlgorithm();
        assertEquals(null,result);
    }

    @Test
    public void testShortLetters() {
        String minimum = "0hfjhjjmn";
        String maximum = "1000gfjdkfjhhj";
        String increment = "0uytjh";
        String type = "short";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);


        Object[] result = processor.startAlgorithm();
        assertEquals(null,result);
    }

    @Test
    public void testTestParseError() {
        String minimum = "-1000.8";
        String maximum = "10000.7";
        String increment = "100";
        String type = "short";
        PropertyProcessor processor = new PropertyProcessor(type, minimum, maximum, increment);
        Object[] result = processor.startAlgorithm();
        maximum = roundValueFromString(maximum);
        minimum = roundValueFromString(minimum);
        increment = roundValueFromString(increment);

        int size = (Short.parseShort(maximum) - Short.parseShort(minimum)) / Short.parseShort(increment) +1;
        assertEquals(size*size, result.length);
        assertEquals((Short.parseShort(minimum)*Short.parseShort(minimum)) + "",result[0].toString());
        //assertEquals((Short.parseShort(maximum) * Short.parseShort(maximum)) + "", result[result.length - 1].toString());
    }

    private String roundValueFromString(String value) {
        try {
            return Math.round(Double.parseDouble(value)) + "";
        }catch (NumberFormatException e){
            return value;
        }
    }
}

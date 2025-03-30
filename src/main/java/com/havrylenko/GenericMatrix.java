package com.havrylenko;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GenericMatrix<T extends Number> {
    private static final int THRESHOLD = 100000;

    private final Number minimum;
    private final Number maximum;
    private final Number increment;

    // Конструктор
    public GenericMatrix(T minimum, T maximum, T increment) {
        this.minimum = Math.min(minimum.doubleValue(), maximum.doubleValue());
        this.maximum = Math.max(minimum.doubleValue(), maximum.doubleValue());
        this.increment = Math.abs(increment.doubleValue());
    }

    public int calculate() {
        List<T> numbers = fillList();
        int size = numbers.size();

        int sizeTable = 0;
        for (T number : numbers) {
            for (int j = 0; j < size; j++) {
                double first = number.doubleValue();
                double second = numbers.get(j).doubleValue();
                printT(first, second);
                sizeTable++;
            }
        }

        return sizeTable;
    }

    private void printT(double first, double second){
        if(minimum instanceof Double || minimum instanceof Float){
            BigDecimal valuePow = BigDecimal.valueOf(first * second);
            System.out.println(convertToT(first) + " * " + convertToT(second) + " = " + valuePow);
        } else if(minimum instanceof Long || minimum instanceof Short || minimum instanceof Integer || minimum instanceof Byte){
            System.out.println(convertToT(first) + " * " + convertToT(second) + " = " + BigInteger.valueOf(Math.round(first * second)));
        }
    }

    private List<T> fillList() {
        if (increment.floatValue()== 0) {
            throw new RuntimeException("Increment must be greater than zero");
        }
        int counter = 0;

        List<T> numbers = new ArrayList<>();
        double current = minimum.doubleValue();
        while (current <= maximum.doubleValue()) {
            counter++;
            numbers.add(convertToT(current));
            current+=increment.doubleValue();
            if (counter >= THRESHOLD) {
                System.out.println("Counter more than threshold");
                break;
            }
        }
        System.out.println(counter);
        return numbers;
    }

    @SuppressWarnings("unchecked")
    private T convertToT(double value) {
        if (minimum instanceof Integer) {
            return (T) Integer.valueOf((int) Math.round(value));
        } else if (minimum instanceof Float) {
            return (T) Float.valueOf((float) value);
        } else if (minimum instanceof Double) {
            return (T) Double.valueOf(value);
        } else if (minimum instanceof Long) {
            return (T) Long.valueOf(Math.round(value));
        }else if (minimum instanceof Short) {
            return (T) Short.valueOf((short) Math.round(value));
        }else if (minimum instanceof Byte) {
            return (T) Byte.valueOf((byte) Math.round(value));
        }
        throw new IllegalArgumentException("Unsupported number type");
    }
}
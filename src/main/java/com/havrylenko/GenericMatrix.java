package com.havrylenko;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class GenericMatrix<T extends Number> {
    private static final int THRESHOLD = 10000;

    private final Number minimum;
    private final Number maximum;
    private final Number increment;

    // Конструктор
    public GenericMatrix(T minimum, T maximum, T increment) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = increment;
    }

    public Object[] calculate() {
        try {
            List<T> numbers = fillList();
            int size = numbers.size();

            Object[] resultedArray = new Object[size*size];
            int counter = 0;

            for (int i = 0; i < size; i++) {
                for (T number : numbers) {
                    T first = numbers.get(i);
                    T second = number;

                    Object resultMultiply = printT(first, second);
                    resultedArray[counter++] = resultMultiply;
                }
            }

            return resultedArray;
        } catch (RuntimeException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
            return new Object[0];
        }
    }

    private Object printT(T first, T second){
        Object result = null;
        if(minimum instanceof Double || minimum instanceof Float){
            BigDecimal firstBigDecimal = new BigDecimal(first.toString());
            result = firstBigDecimal.multiply(new BigDecimal(second.toString()));
            //System.out.println(first + " * " + second + " = " + result);
        } else if(minimum instanceof Long || minimum instanceof Short || minimum instanceof Integer || minimum instanceof Byte){
            BigInteger firstBigInteger = new BigInteger(first.toString());
            result = firstBigInteger.multiply(new BigInteger(second.toString()));
            //System.out.println(first.intValue() + " * " + second + " = " + result);
        }
        return result;
    }

    private List<T> fillList() {
        if (increment.doubleValue() <= 0) {
            throw new IllegalArgumentException("Increment must be greater than zero");
        }

        double current;
        double maximumValue;
        double incrementValue = Math.abs(increment.doubleValue());

        if (isMaximumBiggerThanMinimum()) {
            current = minimum.doubleValue();
            maximumValue = maximum.doubleValue();
        } else {
            current = maximum.doubleValue();
            maximumValue = minimum.doubleValue();
        }

        List<T> numbers = new ArrayList<>();
        int counter = 0;

        while (current <= maximumValue) {
            numbers.add(convertToT(current));
            current = Math.round((current + incrementValue) * 1_000_000) / 1_000_000.0;
            counter++;

            if (counter >= THRESHOLD) {
                break;
            }
        }

        return numbers;
    }


    private boolean isMaximumBiggerThanMinimum(){
        return maximum.doubleValue() > minimum.doubleValue();
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
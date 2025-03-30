package com.havrylenko;

public class PropertyProcessor {

    private final String type;
    private final String minimum;
    private final String maximum;
    private final String increment;

    public PropertyProcessor() {
        PropertyLoader loader = PropertyLoader.getInstance();
        this.type = loader.getProperty("type");
        this.minimum = loader.getProperty("minimum");
        this.maximum = loader.getProperty("maximum");
        this.increment = loader.getProperty("increment");
    }

    public PropertyProcessor(String type,String minimum,String maximum,String increment) {
        this.type = type;
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = increment;
    }

    public void processProperties() {
        if (Float.parseFloat(increment) == 0) {
            System.out.println("Increment must be another than zero");
            return;
        }

        chooseType();
    }

    private void chooseType() {
        switch (type.toLowerCase()) {
            case "byte":
                GenericMatrix<Byte> matrixByte = getByteGenericMatrix();
                matrixByte.calculate();
                break;
            case "short":
                GenericMatrix<Short> matrixShort = getShortGenericMatrix();
                matrixShort.calculate();
                break;
            case "int":
            case "integer":
                GenericMatrix<Integer> matrixInt = getIntegerGenericMatrix();
                matrixInt.calculate();
                break;
            case "long":
                GenericMatrix<Long> matrixLong = getLongGenericMatrix();
                matrixLong.calculate();
                break;
            case "float":
                GenericMatrix<Float> matrixFloat = getFloatGenericMatrix();
                matrixFloat.calculate();
                break;
            case "double":
                GenericMatrix<Double> matrixDouble = getDoubleGenericMatrix();
                matrixDouble.calculate();
                break;
        }
    }

    private GenericMatrix<Byte> getByteGenericMatrix() {
        byte minByteForm;
        byte maxByteForm;
        byte incrementByteForm;

        try {
            minByteForm = Byte.parseByte(minimum);
            maxByteForm = Byte.parseByte(maximum);
            incrementByteForm = Byte.parseByte(increment);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid arguments for Byte use: -128 to 127");
        }

        return new GenericMatrix<>(minByteForm, maxByteForm, incrementByteForm);
    }

    private GenericMatrix<Short> getShortGenericMatrix() {
        short minShortForm;
        short maxShortForm;
        short incrementShortForm;

        try {
            minShortForm = Short.parseShort(minimum);
            maxShortForm = Short.parseShort(maximum);
            incrementShortForm = Short.parseShort(increment);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid arguments for Short use: -32768 to 32767");
        }

        return new GenericMatrix<>(minShortForm, maxShortForm, incrementShortForm);
    }

    private GenericMatrix<Integer> getIntegerGenericMatrix() {
        int minIntForm;
        int maxIntForm;
        int incrementIntForm;

        try {
            minIntForm = Integer.parseInt(minimum);
            maxIntForm = Integer.parseInt(maximum);
            incrementIntForm = Integer.parseInt(increment);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid arguments for Integer use: -2147483648 to 2147483647");
        }

        return new GenericMatrix<>(minIntForm, maxIntForm, incrementIntForm);
    }

    private GenericMatrix<Long> getLongGenericMatrix() {
        long minLongForm;
        long maxLongForm;
        long incrementLongForm;

        try {
            minLongForm = Long.parseLong(minimum);
            maxLongForm = Long.parseLong(maximum);
            incrementLongForm = Long.parseLong(increment);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid arguments for Long use: -9223372036854775808 to 9223372036854775807");
        }

        return new GenericMatrix<>(minLongForm, maxLongForm, incrementLongForm);
    }

    private GenericMatrix<Float> getFloatGenericMatrix() {
        float minFloatForm;
        float maxFloatForm;
        float incrementFloatForm;

        try {
            minFloatForm = Float.parseFloat(minimum);
            maxFloatForm = Float.parseFloat(maximum);
            incrementFloatForm = Float.parseFloat(increment);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid arguments for Float");
        }

        return new GenericMatrix<>(minFloatForm, maxFloatForm, incrementFloatForm);
    }

    private GenericMatrix<Double> getDoubleGenericMatrix() {
        double minDoubleForm;
        double maxDoubleForm;
        double incrementDoubleForm;

        try {
            minDoubleForm = Double.parseDouble(minimum);
            maxDoubleForm = Double.parseDouble(maximum);
            incrementDoubleForm = Double.parseDouble(increment);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid arguments for Double");
        }

        return new GenericMatrix<>(minDoubleForm, maxDoubleForm, incrementDoubleForm);
    }

}

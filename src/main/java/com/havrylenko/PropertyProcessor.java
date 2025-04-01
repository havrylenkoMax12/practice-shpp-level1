package com.havrylenko;

public class PropertyProcessor {

    private static final String FILENAME = "application.properties";

    private final String type;
    private final String minimum;
    private final String maximum;
    private final String increment;

    public PropertyProcessor() {
        PropertyLoader loader = PropertyLoader.getInstance(FILENAME);
        this.type = loader.getProperty("type").replace(" ","");
        this.minimum = loader.getProperty("minimum").replace(" ","");
        this.maximum = loader.getProperty("maximum").replace(" ","");
        this.increment = loader.getProperty("increment").replace(" ","");
    }

    public PropertyProcessor(String type,String minimum,String maximum,String increment) {
        this.type = type.replace(" ","");
        this.minimum = minimum.replace(" ","");
        this.maximum = maximum.replace(" ","");
        this.increment = increment.replace(" ","");
    }

    public Object[] startAlgorithm() {
        try {
            switch (type.toLowerCase()) {
                case "byte":
                    GenericMatrix<Byte> matrixByte = getByteGenericMatrix();
                    return matrixByte.calculate();
                case "short":
                    GenericMatrix<Short> matrixShort = getShortGenericMatrix();
                    return matrixShort.calculate();
                case "int":
                    GenericMatrix<Integer> matrixInt = getIntegerGenericMatrix();
                    return matrixInt.calculate();
                case "long":
                    GenericMatrix<Long> matrixLong = getLongGenericMatrix();
                    return matrixLong.calculate();
                case "float":
                    GenericMatrix<Float> matrixFloat = getFloatGenericMatrix();
                    return matrixFloat.calculate();
                case "double":
                    GenericMatrix<Double> matrixDouble = getDoubleGenericMatrix();
                    return matrixDouble.calculate();
                    default:
                        return new Object[0];
            }
        }catch(NumberFormatException e){
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }
        return new Object[0];
    }

    private GenericMatrix<Byte> getByteGenericMatrix() {
        byte minByteForm;
        byte maxByteForm;
        byte incrementByteForm;

        try {
            minByteForm = Byte.parseByte(roundValueFromString(minimum));
            maxByteForm = Byte.parseByte(roundValueFromString(maximum));
            incrementByteForm = Byte.parseByte(roundValueFromString(increment));
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
            minShortForm = Short.parseShort(roundValueFromString(minimum));
            maxShortForm = Short.parseShort(roundValueFromString(maximum));
            incrementShortForm = Short.parseShort(roundValueFromString(increment));
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
            minIntForm = Integer.parseInt(roundValueFromString(minimum));
            maxIntForm = Integer.parseInt(roundValueFromString(maximum));
            incrementIntForm = Integer.parseInt(roundValueFromString(increment));
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
            minLongForm = Long.parseLong(roundValueFromString(minimum));
            maxLongForm = Long.parseLong(roundValueFromString(maximum));
            incrementLongForm = Long.parseLong(roundValueFromString(increment));
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

    private String roundValueFromString(String value) {
        try {
            return Math.round(Double.parseDouble(value)) + "";
        }catch (NumberFormatException e){
            return value;
        }
    }
}

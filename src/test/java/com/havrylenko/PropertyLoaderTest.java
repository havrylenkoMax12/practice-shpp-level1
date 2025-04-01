package com.havrylenko;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PropertyLoaderTest {

    private static final String FILENAME = "application.properties";
    private static PropertyLoader loader1;

    @BeforeAll
    public static void initInstance() { // Fixed: Should be static
        loader1 = PropertyLoader.getInstance(FILENAME);
    }

    @Test
    public void testCheckSingleton() {
        PropertyLoader loader2 = PropertyLoader.getInstance(FILENAME);
        assertEquals(loader1, loader2); // loader1 is now accessible
    }

    @Test
    public void testLoadFromFile() {
        assertEquals("-1000", loader1.getProperty("minimum"));
        assertEquals("1000", loader1.getProperty("maximum"));
        assertEquals("1", loader1.getProperty("increment"));
        assertEquals("int", loader1.getProperty("type"));
    }

    @Test
    @Disabled
    public void testLoadDefault() {
        PropertyLoader loader2 = PropertyLoader.getInstance(FILENAME+ "2");
        assertEquals("1", loader2.getProperty("minimum"));
        assertEquals("10", loader2.getProperty("maximum"));
        assertEquals("1", loader2.getProperty("increment"));
        assertEquals("int", loader2.getProperty("type"));
    }
}

package com.havrylenko;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class PropertyLoaderTest {

    private static final String FILENAME = "application.properties";
    private static PropertyLoader loader1;

    @BeforeAll
     static void initInstance() { // Fixed: Should be static
        loader1 = PropertyLoader.getInstance(FILENAME);
    }

    @Test
     void testCheckSingleton() {
        PropertyLoader loader2 = PropertyLoader.getInstance(FILENAME);
        assertEquals(loader1, loader2); // loader1 is now accessible
    }

    @Test
     void testLoadFromFile() {
        assertEquals("-1000", loader1.getProperty("minimum"));
        assertEquals("1000", loader1.getProperty("maximum"));
        assertEquals("1", loader1.getProperty("increment"));
        assertEquals("int", loader1.getProperty("type"));
    }
}

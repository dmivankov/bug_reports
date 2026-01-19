package com.example.coverage;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SimpleJavaTest {
    @Test
    public void testAdd() {
        SimpleJavaLib lib = new SimpleJavaLib();
        assertEquals(3, lib.add(1, 2));
    }
}

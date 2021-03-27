package com.example.utilityapp;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testDefaults() {
        Water water = new Water();
        assertEquals(2000, water.max);
        assertEquals(600, water.large);
        assertEquals(250, water.small);
    }

    @Test
    public void testWaterAddition() {
        Water water = new Water();
        water.percent = water.small + water.percent;
        assertEquals(250, water.percent);
        System.out.println(water.percent);
        water.percent = 0;
        water.percent = water.large + water.percent;

        assertEquals(600, water.percent);
        System.out.println(water.percent);

    }
}
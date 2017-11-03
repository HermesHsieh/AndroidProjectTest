package com.example.hermes.test;

import junit.framework.Assert;

import org.junit.Test;

import tw.android.test.module.Calculator;

/**
 * Created by hermes.hsieh on 2017/11/3.
 */

public class CalculatorTest {

    @Test
    public void testAdd() throws Exception {
        Calculator calculator = new Calculator();
        Assert.assertEquals(9, calculator.add(3, 6));
        Assert.assertEquals(6, calculator.add(0, 6));
        Assert.assertEquals(-107, calculator.add(-113, 6));
        Assert.assertEquals(5, calculator.add(-1, 6));
    }

    @Test(expected = RuntimeException.class)
    public void testAddException() throws Exception {
        Calculator calculator = new Calculator();
        Assert.assertNull(calculator.add(null, 6));
        Assert.assertNull(calculator.add(45098, null));
    }

}

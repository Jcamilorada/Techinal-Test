package com.wallethub.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 *
 * Test class for {@link KComplementary}
 *
 * @author Juan Rada
 */
public class KComplementaryTest
{
    private KComplementary testInstance = new KComplementary();

    @Test
    public void calculateKComplementary() throws Exception
    {
        assertEquals(6, testInstance.calculateKComplementary(10, new int[] {10, 20, 0, 9, 1, 7, 3}));
        assertEquals(2, testInstance.calculateKComplementary(20, new int[] {10, 10}));
        assertEquals(0, testInstance.calculateKComplementary(7, new int[] {3, 3}));
    }

}
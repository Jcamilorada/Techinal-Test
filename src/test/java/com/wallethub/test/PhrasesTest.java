package com.wallethub.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import org.junit.Test;

/**
 * Test case for {@link Phrases}
 *
 * @author Juan Rada
 */
public class PhrasesTest
{
    private Phrases testInstance = new Phrases();

    @Test
    public void testGetPharases()
    {
        List<String> lines = testInstance.getLines("src/test/resources/ExampleFile.txt");

        assertTrue(lines.size() == 4);
        assertEquals( "Foobar Candy", lines.get(0));
        assertEquals("Olympics 2012", lines.get(1));
        assertEquals("PGA", lines.get(2));
        assertEquals("CNET", lines.get(3));
    }
}
package com.wallethub.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *  Test suite for {@link Palindrome}
 *
 * @author Juan Rada
 */
public class PalindromeTest
{
    private Palindrome testInstance = new Palindrome();

    @Test
    public void testIsPalindrome() throws Exception
    {
        assertTrue(testInstance.isPalindrome("1234554321"));
        assertTrue(testInstance.isPalindrome("12021"));
        assertTrue(testInstance.isPalindrome("1"));
        assertTrue(testInstance.isPalindrome("777"));

        assertFalse(testInstance.isPalindrome("778"));
        assertFalse(testInstance.isPalindrome("777777757777"));
    }
}
package com.wallethub.test;

/**
 * Provided palindrome validation.
 *
 * Assumed conditions:
 *
 *  1. Array size is less than max integer value.
 *  2. Provided String is not null.
 *  3. Java 8 was used to compile code but not stream or lambda where used.
 *
 * @author Juan Rada
 */
public class Palindrome
{
    /***
     * Validates if the given string is a palindrome, if it return true otherwise false.
     * @param string an string instance to validate if is a palindrome.
     *
     * @return true if given string is a palindrome otherwise false.
     */
    public boolean isPalindrome(String string)
    {
        char[] array = string.toCharArray();
        int left = 0;
        int right = array.length - 1;

        while (left < right)
        {
            if (array[left] != array[right])
            {
                return false;
            }

            left ++;
            right --;
        }

        return true;
    }
}

package com.wallethub.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Calculate K complementary pairs for the given array. Given Array A, pair (i, j) is K- complementary if K = A[i] + A[j]
 *
 * Assumed conditions:
 *
 *  1. Value can not form a pair with it self.
 *  2. Pair (i, j) != pair(j, i)
 *  3. A[i] + A[j] with not overflow integer type.
 *  4. Java 8 was used to compile code but not stream or lambda where used.
 *
 * @author Juan Rada
 */
public class KComplementary
{
    public int calculateKComplementary(int k, int[] array)
    {
        Map<Integer, Integer> frequencyMap = new HashMap<>(array.length);
        for (int value : array)
        {
            frequencyMap.put(value, getNewFrequency(frequencyMap, value));
        }

        int pairs = 0;
        for (int value : array)
        {
            int requiredValue = k - value;
            pairs += getFrequency(frequencyMap, requiredValue, value);
        }

        return pairs;
    }

    private int getNewFrequency(Map<Integer, Integer> frequencyMap, int value)
    {
        return frequencyMap.containsKey(value) ? frequencyMap.get(value) + 1 : 1;
    }

    /**
     * Obtain required value frequency, if required value is the same that current value 1 is subtracted as element can
     * not form a pair with itself.
     */
    private int getFrequency(Map<Integer, Integer> frequencyMap, int requiredValue, int currentValue)
    {
        return frequencyMap.containsKey(requiredValue) ?
                requiredValue == currentValue ? frequencyMap.get(requiredValue) - 1 : frequencyMap.get(requiredValue) :
                0;
    }
}

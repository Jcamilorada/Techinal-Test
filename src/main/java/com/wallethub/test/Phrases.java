package com.wallethub.test;

import com.google.common.base.Splitter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * Read file buffering in order to not load all file in memory. Please note that this class use Google Guava library and
 * Apache commons io for optimized implementation of some utility methods.
 *
 * Assumed conditions:
 *
 *  1. The expected total size of phrases is around 1000000.
 *  2. File is encoding using UTF-8
 *  3. Java 8 was used to compile code but not stream or lambda where used.
 *
 * @author Juan Rada
 */
public class Phrases
{
    /** Heuristic value, use expected prevents several map resizing. */
    private static final int EXPECTED_TOTAL_PHRASES = 1000000;
    private static final int TOP_ELEMENTS = 100000;

    private Splitter splitter = Splitter.on('|').trimResults();

    public List<String> getLines(String filePath)
    {
        Map<String, Integer> frequencyMap = new HashMap<>(EXPECTED_TOTAL_PHRASES);
        LineIterator iterator = null;

        try
        {
            iterator = FileUtils.lineIterator(new File(filePath), "UTF-8");
            while (iterator.hasNext())
            {
                for (String phrase : splitter.split(iterator.nextLine()))
                {
                    frequencyMap.put(phrase, getFrequency(frequencyMap, phrase));
                }
            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            LineIterator.closeQuietly(iterator);
        }

        return getFirstElements(new ArrayList<>(frequencyMap.entrySet()), TOP_ELEMENTS);
    }

    private List<String> getFirstElements(List<Map.Entry<String, Integer>> list, int elements)
    {
        Collections.sort(list, new EntryComparator());

        // If not enough elements
        int phrases = list.size();
        elements = elements > phrases ? phrases : elements;
        List<String> topElements = new ArrayList<>(elements);

        for (int i = 0; i < elements; i ++)
        {
            topElements.add(list.get(i).getKey());
        }

        return topElements;
    }

    class EntryComparator implements Comparator<Map.Entry<String, Integer>>
    {
        @Override
        public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2)
        {
            return entry2.getValue().compareTo(entry1.getValue());
        }
    }

    private Integer getFrequency(Map<String, Integer> frequencyMap, String phrase)
    {
        return frequencyMap.containsKey(phrase) ? frequencyMap.get(phrase) + 1 : 1;
    }
}

package com.cybertek.day5;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {
    @Test
    public void simpleTest1() {
//actual 5+5
        assertThat(5 + 5, is(10));
        assertThat(5 + 5, equalTo(10));
        assertThat(5 + 5, is(equalTo(10)));

        assertThat(5 + 5, not(9));
        assertThat(5 + 5, is(not(9)));
        assertThat(5 + 5, is(not(equalTo(9))));

        //number comparison

        // greaterThan();
        // greaterThanOrEqualTo();
        // lessThan();
        // lessThanOrEqualTo();
        assertThat(5 + 5, is(greaterThan(10)));
    }
    @DisplayName("Assertion With String")
    @Test
    public void stringHamcrest() {

        String text = "B22 is learning Hamcrest";


        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, equalTo("B22 is learning Hamcrest"));


        //check if text start with B22
        assertThat(text, startsWith("B22"));
        //now do it in case insensitive manner
        assertThat(text, startsWithIgnoringCase("b22"));
        assertThat(text, endsWith("rest"));
        //check if text contains string  learning
        assertThat(text, containsString("learning"));
        assertThat(text, containsStringIgnoringCase("LEARNING"));


        String str = " ";
        //check if string is blank
        assertThat(str, blankString());
        assertThat(str.trim(), emptyString());

    }
    @DisplayName("Hamcrest for collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);
        //check size of the list
        assertThat(listOfNumbers,hasSize(10));
        //check if this list has item 77
        assertThat(listOfNumbers,hasItem(77));
        //check if this list has items 77,54,23
        assertThat(listOfNumbers,hasItems(77,54,23));

        //check if all numbers greater than 0
        assertThat(listOfNumbers,everyItem(greaterThan(0)));

    }
}

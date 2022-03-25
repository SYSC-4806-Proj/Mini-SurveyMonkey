package EntityTest;

import questionnaire.Entity.Range;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class RangeTest {
    Range question1 = new Range();
    Range question2 = new Range();
    Range question3 = new Range("How would you rate your professor? (from 0 to 100)",0,100);
    Range question4 = new Range("How old are you? (from 18 to 90)",18,90);

    @Test
    public void testGetSetMinMax() {
        question1.setMinBoundary(10);
        assertEquals(question1.getMinBoundary(), 10);
        question2.setMinBoundary(32);
        assertEquals(question2.getMinBoundary(), 32);

        question1.setMaxBoundary(90);
        assertEquals(question1.getMaxBoundary(), 90);
        question2.setMaxBoundary(120);
        assertEquals(question2.getMaxBoundary(), 120);
    }

    @Test
    public void testGetSetAddAnswers(){
        ArrayList<String> answers1 = new ArrayList<>(Arrays.asList("32","64","58"));
        ArrayList<String> answers2 = new ArrayList<>(Arrays.asList("73","84"));
        ArrayList<String> compareAnswers1 = new ArrayList<>(Arrays.asList("32","64","58"));
        ArrayList<String> compareAnswers2 = new ArrayList<>(Arrays.asList("73","84"));

        question3.setAnswers(answers1);
        question4.setAnswers(answers2);
        assertEquals(question3.getAnswers(), compareAnswers1);
        assertEquals(question4.getAnswers(), compareAnswers2);

        question3.addAnswer("62");
        compareAnswers1.add("62");
        assertEquals(question3.getAnswers(), compareAnswers1);
    }

    @Test
    public void testGetSetId() {
        question1.setId(8);
        assertEquals(question1.getId(),8);
        assertEquals(question2.getId(),0);
        question2.setId(6);
        assertEquals(question2.getId(),6);
        assertEquals(question3.getId(),0);
        assertEquals(question4.getId(),0);
    }

    @Test
    public void testGetSetQuestion(){
        question1.setQuestion("Do you feel cold tonight?");
        assertEquals(question1.getQuestion(), "Do you feel cold tonight?");
        assertNull(question2.getQuestion());
        question2.setQuestion("How is your day?");
        assertEquals(question2.getQuestion(), "How is your day?");
    }
}
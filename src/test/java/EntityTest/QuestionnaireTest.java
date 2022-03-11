package EntityTest;

import Entity.*;
import org.junit.Before;
import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionnaireTest {

    Questionnaire q1;
    Questionnaire q2;
    Questionnaire q3;
    List<Question> questions = new ArrayList<>();
    OpenEnd openEndQ = new OpenEnd("Do you have any comments on SYSC4806?");
    Selection selectionQ = new Selection("How much grade do you get last term?",
            new ArrayList<>(Arrays.asList("100%", "50%", "10%")));
    Range rangeQ = new Range("How would you rate your professor? (from 0 to 100)",0,100);

    @Before
    public void setup(){
        questions.add(openEndQ);
        questions.add(selectionQ);
        questions.add(rangeQ);
        q1 = new Questionnaire();
        q2 = new Questionnaire("Questionnaire2");
        q3 = new Questionnaire("Questionnaire3",questions);
    }

    @Test
    public void testGetSetQuestions() {
        ArrayList<Question> tempQuestions = new ArrayList<>(Arrays.asList(
                new Selection("How much grade do you get last term?",
                        new ArrayList<>(Arrays.asList("100%", "50%", "10%")))));

        q1.setQuestionList(tempQuestions);
        assertEquals(q1.getQuestionList(),tempQuestions);
        assertEquals(q3.getQuestionList(),questions);
    }

    @Test
    public void testGetSetId(){
        q1.setId(6);
        assertEquals(q1.getId(),6);
        assertEquals(q2.getId(),0);
        q2.setId(10);
        assertEquals(q2.getId(), 10);
    }

    @Test
    public void testGetSetName(){
        q1.setName("Favorite food survey");
        assertEquals(q1.getName(), "Favorite food survey");
        assertEquals(q2.getName(), "Questionnaire2");
    }

    @Test
    public void testGetSetClosed(){
        q1.setClosed(true);
        assertTrue(q1.isClosed());
        assertFalse(q2.isClosed());
        q2.setClosed(true);
        assertTrue(q2.isClosed());
    }
}

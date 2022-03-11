package EntityTest;

import Entity.Selection;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SelectionTest {

    Selection question1;
    Selection question2;
    Selection question3;
    Selection question4;

    @Before
    public void setup(){
        ArrayList<String> option1 = new ArrayList<>(Arrays.asList("yes","so so","not at all"));
        ArrayList<String> option2 = new ArrayList<>(Arrays.asList("100%", "50%", "10%"));

        question1 = new Selection();
        question2 = new Selection();
        question3 = new Selection("Do you enjoy SYSC4806?", option1);
        question4 = new Selection("How much grade do you get last term?", option2);
    }

    @Test
    public void testGetSetAddAnswers(){
        ArrayList<String> answers1 = new ArrayList<>(Arrays.asList("yes","so so", "yes"));
        ArrayList<String> answers2 = new ArrayList<>(Arrays.asList("100%", "50%", "50%"));
        ArrayList<String> compareAnswers1 = new ArrayList<>(Arrays.asList("yes","so so", "yes"));
        ArrayList<String> compareAnswers2 = new ArrayList<>(Arrays.asList("100%", "50%", "50%"));

        question3.setAnswers(answers1);
        assertEquals(question3.getAnswers(), compareAnswers1);
        question4.setAnswers(answers2);
        assertEquals(question4.getAnswers(), compareAnswers2);

        question3.addAnswer("no");
        compareAnswers1.add("no");
        assertEquals(question3.getAnswers(), compareAnswers1);
        question4.addAnswer("10%");
        compareAnswers2.add("10%");
        assertEquals(question4.getAnswers(), compareAnswers2);
    }

    @Test
    public void testGetSetAddOption(){
        ArrayList<String> options1 = new ArrayList<>(Arrays.asList("yes","so so", "no"));
        ArrayList<String> options2 = new ArrayList<>(Arrays.asList("100%", "50%", "10%"));
        ArrayList<String> compareOptions1 = new ArrayList<>(Arrays.asList("yes","so so", "no"));
        ArrayList<String> compareOptions2 = new ArrayList<>(Arrays.asList("100%", "50%", "10%"));

        question1.setOptions(options1);
        assertEquals(question1.getOptions(), compareOptions1);
        question2.setOptions(options2);
        assertEquals(question2.getOptions(), compareOptions2);

        question1.addOption("not at all");
        compareOptions1.add("not at all");
        assertEquals(question1.getOptions(), compareOptions1);
        question2.addOption("30%");
        compareOptions2.add("30%");
        assertEquals(question2.getOptions(), compareOptions2);
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
    public void testGetAndSetQuestions() {
        question1.setQuestion("Do you feel cold tonight?");
        assertEquals(question1.getQuestion(), "Do you feel cold tonight?");
        assertEquals(question2.getQuestion(), null);
        question2.setQuestion("How is your day?");
        assertEquals(question2.getQuestion(), "How is your day?");
    }

}
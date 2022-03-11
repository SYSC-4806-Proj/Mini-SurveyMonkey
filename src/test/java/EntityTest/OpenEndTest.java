package EntityTest;

import Entity.OpenEnd;
import org.checkerframework.checker.units.qual.A;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class OpenEndTest {

    OpenEnd question1;
    OpenEnd question2;
    OpenEnd question3;
    OpenEnd question4;

    @Before
    public void setup() {
        question1 = new OpenEnd();
        question2 = new OpenEnd();
        question3 = new OpenEnd("Do you have any suggestion on our project?");
        question4 = new OpenEnd("Do you have any comments on SYSC4806?");
    }

    @Test
    public void testGetSetAddAnswer() {
        ArrayList<String> answers1 = new ArrayList<>(Arrays.asList("Pretty Good", "Not bad", "Just so so"));
        ArrayList<String> answers2 = new ArrayList<>(Arrays.asList("100%", "50%", "10%"));
        ArrayList<String> compareAnswers1 = new ArrayList<>(Arrays.asList("Pretty Good", "Not bad", "Just so so"));
        ArrayList<String> compareAnswers2 = new ArrayList<>(Arrays.asList("100%", "50%", "10%"));

        question4.setAnswer(answers1);
        question3.setAnswer(answers2);
        assertEquals(question4.getAnswer(), compareAnswers1);
        assertEquals(question3.getAnswer(), compareAnswers2);

        question4.addAnswer("fine");
        answers1.add("fine");
        assertEquals(question4.getAnswer(), answers1);
    }

    @Test
    public void testGetSetId(){
        question1.setId(6);
        assertEquals(question1.getId(),6);
        assertEquals(question3.getId(),0);
        question3.setId(10);
        assertEquals(question3.getId(), 10);
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
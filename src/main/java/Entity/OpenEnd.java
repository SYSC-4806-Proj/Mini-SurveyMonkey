package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Open end questions.
 */
@Entity
@Table(name = "open_end")
public class OpenEnd extends Question{
    //open-ended questions

    @ElementCollection
    @Column(name = "answer")
    private List<String> answers = new ArrayList<>();

    /**
     * Instantiates a new Open end.
     */
    public OpenEnd(){
        super();
    }

    /**
     * Instantiates a new Open end.
     *
     * @param question the question
     * @param answer   the answer
     */
    public OpenEnd(String question, List<String> answer) {
        super(question);
        this.answers = answer;
    }

    /**
     * Set answer.
     *
     * @param answer the answer
     */
    public void setAnswer(ArrayList<String> answer){
        this.answers = answer;
    }

    /**
     * Get answer list.
     *
     * @return the list
     */
    public List<String> getAnswer(){
        return answers;
    }

    /**
     * Add answer.
     *
     * @param answer the answer
     */
    public void addAnswer(String answer){
        this.answers.add(answer);
    }
}
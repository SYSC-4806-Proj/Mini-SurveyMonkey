package Entity;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * The question for a number in a range.
 */
@Entity
@Table(name = "range")
public class Range extends Question{

    @Column(name = "min_boundary")
    private int minBoundary;

    @Column(name = "max_boundary")
    private int maxBoundary;

    @ElementCollection
    @Column(name = "answers")
    private List<Integer> answers = new ArrayList<>();

    /**
     * Instantiates a new Range question.
     */
    public Range() {
        super();
    }

    /**
     * Instantiates a new Range question.
     *
     * @param maxBoundary the max boundary
     */
    public Range(String question, int minBoundary, int maxBoundary){
        super(question);
        this.minBoundary = minBoundary;
        this.maxBoundary = maxBoundary;
    }

    /**
     * Gets min boundary.
     *
     * @return the min boundary
     */
    public int getMinBoundary() {
        return minBoundary;
    }

    /**
     * Sets min boundary.
     *
     * @param minBoundary the min boundary
     */
    public void setMinBoundary(int minBoundary) {
        this.minBoundary = minBoundary;
    }

    /**
     * Gets max boundary.
     *
     * @return the max boundary
     */
    public int getMaxBoundary() {
        return maxBoundary;
    }

    /**
     * Sets max boundary.
     *
     * @param maxBoundary the max boundary
     */
    public void setMaxBoundary(int maxBoundary) {
        this.maxBoundary = maxBoundary;
    }

    /**
     * Gets answers.
     *
     * @return the answers
     */
    public List<Integer> getAnswers() {
        return answers;
    }

    /**
     * Sets answers.
     *
     * @param answers the answers
     */
    public void setAnswers(List<Integer> answers) {
        this.answers = answers;
    }

    /**
     * Add answer.
     *
     * @param answer the answer
     */
    public void addAnswer(Integer answer){
        this.answers.add(answer);
    }

}

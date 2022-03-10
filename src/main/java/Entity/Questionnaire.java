package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    //user id connect

    private boolean isClosed;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

    public Questionnaire(){

    }

    public Questionnaire(String name){
        this.name = name;
    }

    public Questionnaire(String name, List<Question> questionList){
        this.name = name;
        this.questionList = questionList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public void addQuestion(Question question){
        questionList.add(question);
    }
}

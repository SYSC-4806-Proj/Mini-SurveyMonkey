package Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<OpenEnd> questionList = new ArrayList<OpenEnd>();

    public Questionnaire(){

    }

    public long getId(){
        return id;
    }

    public void setQuestionList(ArrayList<OpenEnd> questionList){
        this.questionList = questionList;
    }

    public List<OpenEnd> getQuestionList(){
        return questionList;
    }

    public void addQuestion(OpenEnd question){
        questionList.add(question);
    }
}

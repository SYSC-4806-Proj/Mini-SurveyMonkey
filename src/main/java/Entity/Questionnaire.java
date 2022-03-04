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
    private List<Answer> questionList = new ArrayList<Answer>();

    public Questionnaire(){

    }

    public long getId(){
        return id;
    }

    public void setQuestionList(ArrayList<Answer> questionList){
        this.questionList = questionList;
    }

    public List<Answer> getQuestionList(){
        return questionList;
    }
}

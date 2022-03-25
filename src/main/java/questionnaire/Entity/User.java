package questionnaire.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Questionnaire> questionnaire = new ArrayList<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Questionnaire> getQuestionnaire() {
        return questionnaire;
    }

    /**
     * Sets questionnaire id.
     *
     * @param questionnaire the questionnaire id
     */
    public void setQuestionnaire(List<Questionnaire> questionnaire) {
        this.questionnaire = questionnaire;
    }

    /**
     * Add questionnaire.
     *
     * @param questionnaire the questionnaire
     */
    public void addQuestion(Questionnaire questionnaire){
        this.questionnaire.add(questionnaire);
    }
}

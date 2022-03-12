package Entity;

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

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ElementCollection
    @Column(name = "questionnaires")
    private List<Long> questionnaireId = new ArrayList<>();

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param password the password
     */
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }


    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets questionnaire id.
     *
     * @return the questionnaire id
     */
    public List<Long> getQuestionnaireId() {
        return questionnaireId;
    }

    /**
     * Sets questionnaire id.
     *
     * @param questionnaireId the questionnaire id
     */
    public void setQuestionnaireId(List<Long> questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    /**
     * Add questionnaire.
     *
     * @param id the id
     */
    public void addQuestionnaire(Long id){
        this.questionnaireId.add(id);
    }
}

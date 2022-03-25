package questionnaire.Controller;

import questionnaire.Entity.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * This class controls pages that used to create questionnaires
 */
@Controller
public class SurveyorController {
    private QuestionnaireRepo questionnaireRepo;
    private UserRepo userRepo;

    public SurveyorController(QuestionnaireRepo questionnaireRepo, UserRepo userRepo) {
        this.questionnaireRepo = questionnaireRepo;
        this.userRepo = userRepo;
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String questionnaireCreateForm() {
        return "qCreate";
    }


    @GetMapping("/view/{id}")
    public String viewQuestionnaire(@PathVariable long id, Model model){
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        if (questionnaire == null) {
            model.addAttribute("error","The survey "+id+" does not exist, try another id later.");
            return "errorRedirect";
        }
        if(questionnaire.isClosed()){
            model.addAttribute("error","The survey is closed, try another id later.");
            return "errorRedirect";
        }
        model.addAttribute("questionnaire", questionnaire);
        List<Long> openEndIdList = new ArrayList<>();
        List<Long> rangeIdList = new ArrayList<>();
        List<Long> selectionIdList = new ArrayList<>();
        for(Question question:questionnaire.getQuestionList()){
            if(question instanceof OpenEnd){
                openEndIdList.add(question.getId());
            }else if(question instanceof Range){
                rangeIdList.add(question.getId());
            }else if(question instanceof Selection){
                selectionIdList.add(question.getId());
            }
        }
        model.addAttribute("openEndList",openEndIdList);
        model.addAttribute("rangeList", rangeIdList);
        model.addAttribute("selectionList", selectionIdList);
        return "doSurvey";
    }

    @PostMapping("/view/{id}")
    public String submitSurveyAnswer(@PathVariable long id, String[] answer, Model model, HttpServletResponse resp) throws IOException {
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        PrintWriter out = resp.getWriter();
        if(questionnaire == null){
            return null;
        }
        for(int i = 0; i < answer.length; i++){
            if(answer[i].equals("")){
                out.println("<script language='javascript'>alert('You still have incomplete question(s). Fill them all and submit again.')</script>");
                out.println("<script language='javascript'>window.location.href='/view/"+id+"'</script>");
            }
        }
        int i = 0;
        for(Question question:questionnaire.getQuestionList()){
            question.addAnswer(answer[i]);
            i++;
        }
        questionnaireRepo.save(questionnaire);
        return "completeSurvey";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String questionnaireCreate(HttpServletRequest request) {
        Questionnaire questionnaire = new Questionnaire();
        Map<String, String[]> formData = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : formData.entrySet()) {
            if (entry.getKey().equals("open_end_question")) {
                for (String question : entry.getValue()) {
                    OpenEnd q = new OpenEnd();
                    q.setQuestion(question);
                    questionnaire.addQuestion(q);

                }
            }
            else if(entry.getKey().equals("range_question")) {
                   String[] question_content = entry.getValue().clone();
                   for (int i = 0; i< question_content.length;i = i+3 ){
                      Range q = new Range(question_content[i], Integer. valueOf(question_content[i+1]),Integer. valueOf(question_content[i+2]));
                      questionnaire.addQuestion(q);
                   }
                }

            else if(entry.getKey().equals("selection_question")){
                String[] question_content = entry.getValue().clone();

                for (int i = 0;i < question_content.length;i=i+2){
                    String[] list = question_content[i+1].split(",");
                    List<String> l = Arrays.asList(list);
                    Selection q = new Selection(question_content[i],l);
                    questionnaire.addQuestion(q);
                }

                }
            }
        questionnaireRepo.save(questionnaire);
        long id = questionnaire.getId();
        return "redirect:/view/" + id;
    }

    @RequestMapping(path="/display", method = RequestMethod.GET)
    public String display(Authentication authentication, Model model){

        User user = this.userRepo.findByUsername(authentication.getName());

        if(user.getQuestionnaire().isEmpty()){
            return "noQuestionnaire";
        }

        model.addAttribute("User", user);
        model.addAttribute("Questionnaire", user.getQuestionnaire());

        return "userPage";
    }

    @RequestMapping(path="/display/{id}", method = RequestMethod.POST)
    public String closeSurvey(@PathVariable long id, Model model, Authentication authentication){

        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        User user = this.userRepo.findByUsername(authentication.getName());

        questionnaire.setClosed(true);
        this.questionnaireRepo.save(questionnaire);

        model.addAttribute("User", user);
        return "userPage";
    }

}

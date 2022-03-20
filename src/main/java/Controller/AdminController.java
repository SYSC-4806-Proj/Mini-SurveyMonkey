package Controller;

import Entity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AdminController {
    private QuestionnaireRepo questionnaireRepo;
    private UserRepo userRepo;

    public AdminController(QuestionnaireRepo questionnaireRepo, UserRepo userRepo) {
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

    @RequestMapping(path = "/view/{id}", method = RequestMethod.POST)
    public String submitSurveyAnswer(@PathVariable long id, HttpServletRequest request){
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        if(questionnaire == null){
            return null;
        }
        for(Question question:questionnaire.getQuestionList()){
        }
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

    @GetMapping("/")
    public String mainPage(){
        return "index";
    }

    @RequestMapping(path="/display", method = RequestMethod.GET)
    public String display(Model model){
        User user = new User();
        model.addAttribute("User", user);
        return "userPage";
    }

}

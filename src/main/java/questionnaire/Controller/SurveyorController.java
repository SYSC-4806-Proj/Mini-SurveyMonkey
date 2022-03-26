package questionnaire.Controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import questionnaire.Entity.*;


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
    public String viewQuestionnaire(@PathVariable long id, Model model) {
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        if (questionnaire == null) {
            model.addAttribute("error", "The survey " + id + " does not exist, try another id later.");
            return "errorRedirect";
        }
        if (questionnaire.isClosed()) {
            model.addAttribute("error", "The survey is closed, try another id later.");
            return "errorRedirect";
        }
        model.addAttribute("questionnaire", questionnaire);
        List<Long> openEndIdList = new ArrayList<>();
        List<Long> rangeIdList = new ArrayList<>();
        List<Long> selectionIdList = new ArrayList<>();
        for (Question question : questionnaire.getQuestionList()) {
            if (question instanceof OpenEnd) {
                openEndIdList.add(question.getId());
            } else if (question instanceof Range) {
                rangeIdList.add(question.getId());
            } else if (question instanceof Selection) {
                selectionIdList.add(question.getId());
            }
        }
        model.addAttribute("openEndList", openEndIdList);
        model.addAttribute("rangeList", rangeIdList);
        model.addAttribute("selectionList", selectionIdList);
        return "doSurvey";
    }

    @PostMapping("/view/{id}")
    public String submitSurveyAnswer(@PathVariable long id, String[] answer, Model model, HttpServletResponse resp) throws IOException {
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        PrintWriter out = resp.getWriter();
        if (questionnaire == null) {
            return null;
        }
        for (int i = 0; i < answer.length; i++) {
            if (answer[i].equals("")) {
                out.println("<script language='javascript'>alert('You still have incomplete question(s). Fill them all and submit again.')</script>");
                out.println("<script language='javascript'>window.location.href='/view/" + id + "'</script>");
            }
        }
        int i = 0;
        for (Question question : questionnaire.getQuestionList()) {
            question.addAnswer(answer[i]);
            i++;
        }
        questionnaireRepo.save(questionnaire);
        return "completeSurvey";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String questionnaireCreate(HttpServletRequest request, HttpServletResponse resp, Authentication authentication) throws IOException {
        Questionnaire questionnaire = new Questionnaire();
        Map<String, String[]> formData = request.getParameterMap();
        PrintWriter out = resp.getWriter();
        for (Map.Entry<String, String[]> entry : formData.entrySet()) {
            if (entry.getKey().equals("survey_name")){
                for(String name: entry.getValue()){
                    if(name.equals("")){
                        out.println("<script language='javascript'>alert('Please enter a survey name! Fill them all and submit again.')</script>");
                        out.println("<script language='javascript'>window.location.href='/create'</script>");
                    }
                    else{
                        questionnaire.setName(name);
                    }
                }
            }
            if (entry.getKey().equals("open_end_question")) {
                for (String question : entry.getValue()) {
                    if(question.equals("")){
                        out.println("<script language='javascript'>alert('You still have incomplete question(s). Fill them all and submit again.')</script>");
                        out.println("<script language='javascript'>window.location.href='/create'</script>");
                    }
                    else{
                        OpenEnd q = new OpenEnd();
                        q.setQuestion(question);
                        questionnaire.addQuestion(q);
                    }
                   // OpenEnd q = new OpenEnd();
                    //q.setQuestion(question);
                    //questionnaire.addQuestion(q);

                }
            }
            else if(entry.getKey().equals("range_question")) {
                   String[] question_content = entry.getValue().clone();
                   for (int i = 0; i< question_content.length;i = i+3 ){
                       if(question_content[i].equals("")||question_content[i+1].equals("")||question_content[i+2].equals("")){
                           out.println("<script language='javascript'>alert('You still have incomplete question(s). Fill them all and submit again.')</script>");
                           out.println("<script language='javascript'>window.location.href='/create'</script>");
                       }
                       else if ((!((question_content[i+1].matches("^[0-9]+$")) && (question_content[i+2].matches("^[0-9]+$"))))){
                           out.println("<script language='javascript'>alert('Max boundary or Min boundary should be integer. Fill them all and submit again.')</script>");
                           out.println("<script language='javascript'>window.location.href='/create'</script>");

                       }else if(Integer. valueOf(question_content[i+1])>=Integer. valueOf(question_content[i+2])){
                           out.println("<script language='javascript'>alert('Max boundary should larger than Min boundary. Fill them all and submit again.')</script>");
                           out.println("<script language='javascript'>window.location.href='/create'</script>");
                       }

                       else{
                           Range q = new Range(question_content[i], Integer. valueOf(question_content[i+1]),Integer. valueOf(question_content[i+2]));
                           questionnaire.addQuestion(q);
                       }

                       //Range q = new Range(question_content[i], Integer. valueOf(question_content[i+1]),Integer. valueOf(question_content[i+2]));
                      //questionnaire.addQuestion(q);
                   }
                } else if (entry.getKey().equals("selection_question")) {
                String[] question_content = entry.getValue().clone();
                for (int i = 0; i < question_content.length; i = i + 2) {
                    if (question_content[i].equals("") || question_content[i + 1].equals("")) {
                        out.println("<script language='javascript'>alert('You still have incomplete question(s). Fill them all and submit again.')</script>");
                        out.println("<script language='javascript'>window.location.href='/create'</script>");
                    } else {
                        String[] list = question_content[i + 1].split(",");
                        List<String> l = Arrays.asList(list);
                        Selection q = new Selection(question_content[i], l);
                        questionnaire.addQuestion(q);
                    }
                    //String[] list = question_content[i+1].split(",");
                    //List<String> l = Arrays.asList(list);
                    //Selection q = new Selection(question_content[i],l);
                    //questionnaire.addQuestion(q);
                }

            }
        }
        User user = this.userRepo.findByUsername(authentication.getName());
        user.addQuestion(questionnaire);
        this.userRepo.save(user);
        //long id = questionnaire.getId();
        //return "redirect:/view/" + id;
        return "createSurvey";
        }

    @RequestMapping(path="/display", method = RequestMethod.GET)
    public String display(Authentication authentication, Model model){

        User user = this.userRepo.findByUsername(authentication.getName());
        Boolean QNotEmpty = true;

        if(user.getQuestionnaire().isEmpty()){
            QNotEmpty = false;
        }

        model.addAttribute("User", user);
        model.addAttribute("Questionnaire", user.getQuestionnaire());
        model.addAttribute("QNotEmpty", QNotEmpty);

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

    @RequestMapping(path = "/result/{id}", method = RequestMethod.GET)
    public String displayQuestion(@PathVariable long id, Model model) {
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        System.out.println(questionnaire.getId() + "**************************");
        if (questionnaire.getQuestionList().isEmpty()) {
            System.out.println("EMPTY");
            model.addAttribute("error", "The survey " + id + " does not exist, try another questionnaire later.");
            return "noQuestion";
        } else {
            System.out.println("Not Empty");
            model.addAttribute("Questionnaire", questionnaire);
            return "Question";
        }

    }

    @RequestMapping(path = "/result{id}", method = RequestMethod.GET)
    public String displayAnswer(@PathVariable long id, Model model) {
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);

        if (questionnaire.getQuestionList() == null) {
            model.addAttribute("error", "The survey " + id + " does not exist, try another questionnaire later.");
            return "noQuestion";
        } else {
            model.addAttribute("Questionnaire", questionnaire);
            List<OpenEnd> openEndAnswer = new ArrayList<>();
            List<Range> rangeAnswer = new ArrayList<>();
            List<Selection> selectionAnswer = new ArrayList<>();
            for (Question question: questionnaire.getQuestionList()){
                if(question instanceof OpenEnd){
                    openEndAnswer.add((OpenEnd) question);
                }else if(question instanceof Range){
                    rangeAnswer.add((Range) question);
                }else if(question instanceof Selection){
                    selectionAnswer.add((Selection) question);
                }
            }
            return "Question";

        }

    }

    @GetMapping("allSurvey")
    public String showAllSurvey(Model model,Authentication authentication){

        List<Questionnaire> allSurveys = (List<Questionnaire>) this.questionnaireRepo.findAllByOrderByIdAsc();
        List<Questionnaire> userSurveys = this.userRepo.findByUsername(authentication.getName()).getQuestionnaire();

        allSurveys.removeAll(userSurveys);

        allSurveys.removeIf(Questionnaire::isClosed);

        model.addAttribute("surveys",allSurveys);

        return "allSurvey";
    }
}

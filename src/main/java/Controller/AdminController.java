package Controller;

import Entity.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class AdminController {
    private QuestionnaireRepo questionnaireRepo;

    public AdminController(QuestionnaireRepo questionnaireRepo) {
        this.questionnaireRepo = questionnaireRepo;
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String questionnaireCreateForm() {
        return "qCreate";
    }

    /*@RequestMapping(path="create", method = RequestMethod.POST)
    public String questionnaireCreate(@RequestBody MultiValueMap<String, String> formData){
        Questionnaire questionnaire = new Questionnaire();

        for(List<String> value: formData.values()){
                for(String content: value){
                    Range q = new Range();
                    q.setQuestion(content);
                    questionnaire.addQuestion(q);
                    System.out.println(content);
                }
        }
        questionnaireRepo.save(questionnaire);
        long id = questionnaire.getId();
        return "redirect:/view/" + id;
    }


    @GetMapping("/view/{id}")
    public String viewQuestionnaire(@PathVariable long id, Model model){
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        if (questionnaire == null) {
            return "qCreate";
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
        return "qView";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String questionnaireCreate(HttpServletRequest request) {
        Questionnaire questionnaire = new Questionnaire();
        Map<String, String[]> formData = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : formData.entrySet()) {
            for (String content : entry.getValue()) {
                if (entry.getKey().equals("open_end_question")){
                    OpenEnd q = new OpenEnd();
                    q.setQuestion(content);
                    questionnaire.addQuestion(q);

                } else if(entry.getKey().equals("range_question")){
                    Range q = new Range();
                    q.setQuestion(content);
                    questionnaire.addQuestion(q);

                } else if(entry.getKey().equals("selection_question")){
                    Selection q = new Selection();
                    q.setQuestion(content);
                    questionnaire.addQuestion(q);

                }
            }
        }

        questionnaireRepo.save(questionnaire);
        long id = questionnaire.getId();
        return "redirect:/view/" + id;

    }

    //@RequestMapping(path="/create", method = RequestMethod.GET)
    //public String chooseQuestionType(){

    //}


}

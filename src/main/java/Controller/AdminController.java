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

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private QuestionnaireRepo questionnaireRepo;

    public AdminController(QuestionnaireRepo questionnaireRepo){
        this.questionnaireRepo = questionnaireRepo;
    }

    @RequestMapping(path="/create", method = RequestMethod.GET)
    public String questionnaireCreateForm(){
        return "qCreate";
    }

    @RequestMapping(path="create", method = RequestMethod.POST)
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
        if (questionnaire == null){
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

}

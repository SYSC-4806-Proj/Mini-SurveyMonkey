package Controller;

import Entity.OpenEnd;
import Entity.Question;
import Entity.Questionnaire;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
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
                    OpenEnd q = new OpenEnd();
                    q.setContent(content);
                    questionnaire.addQuestion(q);
                    System.out.println(content);
                }
        }

        questionnaireRepo.save(questionnaire);
        long id = questionnaire.getId();

        return "redirect:/view/" + id;
    }

    @RequestMapping(path="/view/{id}", method = RequestMethod.GET)
    public String viewQuestionnaire(@PathVariable long id, Model model){
        Questionnaire questionnaire = this.questionnaireRepo.findById(id);
        if (questionnaire == null){
            return "qCreate";
        }
        model.addAttribute("questionnaire", questionnaire);
        return "qView";
    }

}

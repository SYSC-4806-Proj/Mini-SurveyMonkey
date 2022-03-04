package Controller;

import Entity.Questionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

}

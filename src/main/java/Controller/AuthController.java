package Controller;

import Entity.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserRepo userRepo;

    public AuthController(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    @GetMapping("/register")
    public String showRegForm(Model model){
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user){
        // username should be unique
        if(userRepo.findByUsername(user.getUsername()) != null){
            return "register";
        }
        // save hash value of password in db
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPwd = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPwd);

        userRepo.save(user);

        return "login";
    }

    @GetMapping("/home")
    public String showHome(Model model, Authentication authentication){
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "home";
    }

}

package cs.controllers;

import cs.repos.AuthorityRepository;
import cs.repos.MyUserRepository;
import cs.security.Authority;
import cs.security.MyUser;
import cs.views.RegistrationForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private MyUserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthorityRepository authRepo;

    public RegisterController(MyUserRepository userRepo, PasswordEncoder passwordEncoder, AuthorityRepository authRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authRepo = authRepo;
    }

    @GetMapping
    public String registerForm() {
        return "register";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {
        MyUser user = form.toMyUser(passwordEncoder);
        userRepo.save(user);
        authRepo.save(new Authority("USER", user.getUsername(), user));
        return "redirect:/login";
    }
}
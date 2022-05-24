package cs.services;

import cs.repos.AuthorityRepository;
import cs.repos.MyUserRepository;
import cs.security.Authority;
import cs.security.MyUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private MyUserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    private AuthorityRepository authRepo;

    RegisterService(MyUserRepository userRepo, PasswordEncoder passwordEncoder, AuthorityRepository authRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authRepo = authRepo;
    }

    public void processRegistration(String username, String password) {
        MyUser user = new MyUser(username, passwordEncoder.encode(password));
        userRepo.save(user);
        authRepo.save(new Authority("USER", user.getUsername()));
    }
}

package cs.views;

import cs.security.MyUser;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String username;
    private String password;

    public MyUser toMyUser(PasswordEncoder passwordEncoder) {
        return new MyUser(username, passwordEncoder.encode(password));
    }

}

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

public class Utils {
    public static SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor getUserInfo() {
        return user("admin").password("admin").authorities(new SimpleGrantedAuthority("ROOT"));
    }
}

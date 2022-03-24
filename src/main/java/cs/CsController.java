package cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CsController {
    @GetMapping("/")
    public @ResponseBody
    String getString() {
        return "hello";
    }

}

package cs;

import cs.models.People;
import cs.repos.PeopleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "api")
@RestController
public class CsController {
    private final List<? extends Service> services;
    private PeopleRepository peopleRepository;

    public CsController(PeopleRepository peopleRepository, List<? extends Service> services) {
        this.peopleRepository = peopleRepository;
        this.services = services;
    }

    @GetMapping
    Iterable<People> get(@RequestParam(name = "method") String method) {
        if (method.equals("getAllPeople")) {
            return peopleRepository.findAll();
        }

        return new ArrayList<People>();
    }

    @PostMapping
    String post(@RequestParam(name = "method") String method) { return method; }

    @PutMapping
    String put(@RequestParam(name = "method") String method) { return method; }

    @DeleteMapping
    String delete(@RequestParam(name = "method") String method) { return method; }
}

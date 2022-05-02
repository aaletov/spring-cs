package cs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cs.models.People;
import cs.services.PeopleService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/people")
public class PeopleDataBaseController {

    private PeopleService peopleService;

    public PeopleDataBaseController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/{id}")
    public People getPeopleById(@PathVariable Integer id) {
        return peopleService.getPeopleById(id).get();
    }

    @PostMapping("/save")
    public void save(@RequestBody String peopleString) throws JsonProcessingException {
        peopleService.saveByIds(peopleString);
    }

    @PutMapping("/put")
    public void update(@RequestBody People people) {
        peopleService.update(people);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody People people) {
        peopleService.delete(people);
    }

}

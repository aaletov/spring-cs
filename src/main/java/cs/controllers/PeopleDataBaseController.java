package cs.controllers;

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
    public void save(@RequestBody People people) {
        peopleService.save(people);
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

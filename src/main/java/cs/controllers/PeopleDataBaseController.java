package cs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cs.exceptions.NoSuchEntryException;
import cs.exceptions.NoSuchJsonPropertyException;
import cs.models.People;
import cs.services.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/people")
public class PeopleDataBaseController {

    private PeopleService peopleService;

    @ExceptionHandler({ JsonProcessingException.class, NoSuchJsonPropertyException.class, NoSuchEntryException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e) {
        e.printStackTrace();
        return e.getMessage();
    }

    public PeopleDataBaseController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/get/{id}")
    public People getPeopleById(@PathVariable Integer id) throws NoSuchEntryException {
        return peopleService.getPeopleById(id);
    }

    @GetMapping("/getAll")
    public Iterable<People> getAll() {
        return peopleService.getAll();
    }

    @PostMapping("/save")
    public String save(@RequestParam String firstName,
                       @RequestParam String lastName,
                       @RequestParam String patherName,
                       @RequestParam Integer wardId,
                       @RequestParam Integer diagnosisId
    ) throws JsonProcessingException, NoSuchEntryException {
        peopleService.saveByIds(firstName, lastName, patherName, wardId, diagnosisId);
        return "Saved successfully";
    }

    @PatchMapping("/patchPeopleWard")
    public String patchPeopleWard(@RequestParam Integer peopleId, @RequestParam Integer wardId) throws JsonProcessingException, NoSuchEntryException {
        peopleService.patchPeopleWard(peopleId, wardId);
        return "Patched successfully";
    }

    @PatchMapping("/moveAllPeopleFromWardTo")
    public String patchPeopleWardAll(@RequestParam Integer wardSourceId, @RequestParam Integer wardDestId) throws NoSuchEntryException, JsonProcessingException {
        peopleService.patchPeopleWardAll(wardSourceId, wardDestId);
        return "Moved successfully";
    }


    @DeleteMapping("/delete")
    public String deletePeopleById(@RequestParam Integer peopleId) throws JsonProcessingException {
        peopleService.deletePeopleById(peopleId);
        return "Deleted successfully";
    }

}

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
    public String savePeopleByWardIdAndDiagnosisId(@RequestBody String peopleJsonString) throws JsonProcessingException {
        peopleService.saveByIds(peopleJsonString);
        return "Saved successfully";
    }

    @PatchMapping("/patchPeopleWard")
    public String patchPeopleWard(@RequestBody String peopleJsonString) throws JsonProcessingException, NoSuchEntryException {
        peopleService.patchPeopleWard(peopleJsonString);
        return "Patched successfully";
    }

    @DeleteMapping("/delete")
    public String deletePeopleById(@RequestBody String peopleIdJson) throws JsonProcessingException {
        peopleService.deletePeopleById(peopleIdJson);
        return "Deleted successfully";
    }

}

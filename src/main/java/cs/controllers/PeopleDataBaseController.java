package cs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cs.apimodels.PeopleApiModel;
import cs.exceptions.NoSuchEntryException;
import cs.exceptions.NoSuchJsonPropertyException;
import cs.models.People;
import cs.services.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people")
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

    @GetMapping("/getPeopleByFullName")
    public People getPeopleByFullName(@RequestParam String firstName,
                                      @RequestParam String lastName,
                                      @RequestParam String patherName)
            throws NoSuchEntryException {
        return peopleService.getPeopleByFullName(firstName, lastName, patherName);
    }

    @GetMapping("/getAll")
    public Iterable<People> getAll() {
        return peopleService.getAll();
    }

    @PostMapping("/save")
    public String save(@RequestBody PeopleApiModel peopleApiModel) throws JsonProcessingException, NoSuchEntryException {
        peopleService.saveApiModel(peopleApiModel);
        return "Saved successfully";
    }

    @PostMapping("/saveAll")
    public String saveAll(@RequestBody List<PeopleApiModel> peopleApiModelList) {
        peopleService.saveAll(peopleApiModelList);
        return "Saved successfully";
    }

    @PatchMapping("/patchPeopleWard")
    public String patchPeopleWard(@RequestParam Integer peopleId, @RequestParam Integer wardId) throws JsonProcessingException, NoSuchEntryException {
        peopleService.patchPeopleWard(peopleId, wardId);
        return "Patched successfully";
    }

    @PatchMapping("/moveAllPeopleFromWardToWard")
    public String patchPeopleWardAll(@RequestParam Integer wardSourceId, @RequestParam Integer wardDestId) throws NoSuchEntryException, JsonProcessingException {
        peopleService.patchPeopleWardAll(wardSourceId, wardDestId);
        return "Moved successfully";
    }

    @DeleteMapping("/delete")
    public String deletePeopleById(@RequestParam Integer peopleId) {
        peopleService.deletePeopleById(peopleId);
        return "Deleted successfully";
    }

}

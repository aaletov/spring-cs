package cs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cs.exceptions.NoSuchEntryException;
import cs.models.Ward;
import cs.services.WardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/ward")
public class WardDataBaseController {

    private WardService wardService;

    @ExceptionHandler({ JsonProcessingException.class, NoSuchEntryException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(JsonProcessingException e) {
        e.printStackTrace();
        return e.getMessage();
    }

    public WardDataBaseController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping("/get/{id}")
    public Ward getWardById(@PathVariable Integer id) throws NoSuchEntryException {
        return wardService.getWardById(id);
    }

    @GetMapping("/getAll")
    public Iterable<Ward> getAll() {
        return wardService.getAll();
    }

    @GetMapping("/getFullWards")
    public Iterable<Ward> getFullWards() {
        return wardService.getFullWards();
    }

    @GetMapping("/getEmptyWards")
    public Iterable<Ward> getEmptyWards() { return wardService.getEmptyWards(); }

    @GetMapping("/getWardsForCountPeople")
    public Iterable<Ward> getWardsForCountPeople(@RequestParam Integer count) {
        return wardService.getWardsForCountPeople(count);
    }

    @PostMapping("/save")
    public String save(@RequestBody Ward ward) {
        wardService.save(ward);
        return "Saved successfully";
    }

}

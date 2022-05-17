package cs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cs.exceptions.NoSuchEntryException;
import cs.models.Diagnosis;
import cs.services.DiagnosisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/diagnosis")
public class DiagnosisDataBaseController {

    private DiagnosisService diagnosisService;

    @ExceptionHandler({ JsonProcessingException.class, NoSuchEntryException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(JsonProcessingException e) {
        e.printStackTrace();
        return e.getMessage();
    }

    public DiagnosisDataBaseController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/{id}")
    public Diagnosis getDiagnosisById(@PathVariable Integer id) throws NoSuchEntryException {
        return diagnosisService.getDiagnosisById(id);
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody Diagnosis diagnosis) {
        diagnosisService.save(diagnosis);
        return "Saved successfully";
    }

}

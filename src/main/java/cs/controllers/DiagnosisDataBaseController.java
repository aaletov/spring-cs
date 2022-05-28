package cs.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cs.exceptions.NoSuchEntryException;
import cs.exceptions.NotNullForeignKeyException;
import cs.models.Diagnosis;
import cs.services.DiagnosisService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/diagnosis")
public class DiagnosisDataBaseController {

    private DiagnosisService diagnosisService;

    @ExceptionHandler({ JsonProcessingException.class, NoSuchEntryException.class, NotNullForeignKeyException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(JsonProcessingException e) {
        e.printStackTrace();
        return e.getMessage();
    }

    public DiagnosisDataBaseController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/get/{id}")
    public Diagnosis getDiagnosisById(@PathVariable Integer id) throws NoSuchEntryException {
        return diagnosisService.getDiagnosisById(id);
    }

    @GetMapping("/getDiagnosisByName")
    public Diagnosis getDiagnosisByName(@RequestParam String name) throws NoSuchEntryException {
        return diagnosisService.getDiagnosisByName(name);
    }

    @GetMapping("/getAll")
    public Iterable<Diagnosis> getAllDiagnoses() {
        return diagnosisService.getAllDiagnoses();
    }

    @PostMapping("/save")
    public String save(@RequestBody Diagnosis diagnosis) {
        diagnosisService.save(diagnosis);
        return "Saved successfully";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam Integer id) {
        diagnosisService.delete(id);
        return "Deleted successfully";
    }

}

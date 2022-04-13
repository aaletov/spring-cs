package cs.controllers;

import cs.models.Diagnosis;
import cs.services.DiagnosisService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/diagnosis")
public class DiagnosisDataBaseController {

    private DiagnosisService diagnosisService;

    public DiagnosisDataBaseController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @GetMapping("/{id}")
    public Diagnosis getDiagnosisById(@PathVariable Integer id) {
        return diagnosisService.getDiagnosisById(id).get();
    }

    @PostMapping("/save")
    public void save(@RequestBody Diagnosis diagnosis) {
        diagnosisService.save(diagnosis);
    }

    @PutMapping("/put")
    public void update(@RequestBody Diagnosis diagnosis) {
        diagnosisService.update(diagnosis);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Diagnosis diagnosis) {
        diagnosisService.delete(diagnosis);
    }

}

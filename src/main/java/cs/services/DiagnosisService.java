package cs.services;

import cs.exceptions.NoSuchEntryException;
import cs.exceptions.NotNullForeignKeyException;
import cs.models.Diagnosis;
import cs.repos.DiagnosisRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosisService {

    private DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Diagnosis getDiagnosisById(Integer id) throws NoSuchEntryException {
        return diagnosisRepository.findDiagnosisById(id).orElseThrow(() -> {
            return new NoSuchEntryException("No Diagnosis found with id " + id);
        });
    }

    public Diagnosis getDiagnosisByName(String name) throws  NoSuchEntryException {
        return diagnosisRepository.findDiagnosisByName(name).orElseThrow(() -> {
            return new NoSuchEntryException("No Diagnosis found with name " + name);
        });
    }

    public void save(Diagnosis diagnosis) {
        diagnosisRepository.save(diagnosis);
    }

    public Iterable<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAll();
    }

    public void delete(Integer diagnosisId) throws NotNullForeignKeyException {
        if (diagnosisRepository.findDiagnosisById(diagnosisId).isEmpty()) {
            throw new NotNullForeignKeyException("No such diagnosis");
        }

        diagnosisRepository.deleteById(diagnosisId);
    }
}

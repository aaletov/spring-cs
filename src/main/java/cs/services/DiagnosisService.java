package cs.services;

import cs.exceptions.NoSuchEntryException;
import cs.models.Diagnosis;
import cs.repos.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void save(Diagnosis diagnosis) {
        diagnosisRepository.save(diagnosis);
    }

    public Iterable<Diagnosis> getAllDiagnoses() {
        return diagnosisRepository.findAll();
    }

    public boolean doesExistsDiagnosisById(Integer id) {
        return diagnosisRepository.findDiagnosisById(id).isPresent();
    }

}

package cs.services;

import cs.models.Diagnosis;
import cs.repos.DiagnosisRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiagnosisService {

    private DiagnosisRepository diagnosisRepository;

    public DiagnosisService(DiagnosisRepository diagnosisRepository) {
        this.diagnosisRepository = diagnosisRepository;
    }

    public Optional<Diagnosis> getDiagnosisById(Integer id) {
        return diagnosisRepository.findDiagnosisById(id);
    }

    public void save(Diagnosis diagnosis) {
        diagnosisRepository.save(diagnosis);
    }

    public void update(Diagnosis diagnosis) {
        diagnosisRepository.save(diagnosis);
    }

    public void delete(Diagnosis diagnosis) {
        diagnosisRepository.delete(diagnosis);
    }

}

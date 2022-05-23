package cs.repos;

import cs.models.Diagnosis;
import cs.models.Ward;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {
    Optional<Diagnosis> findDiagnosisByName(String name);
    Optional<Diagnosis> findDiagnosisById(Integer id);
}

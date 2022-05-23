package cs.repos;

import cs.models.Diagnosis;
import cs.models.Ward;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {
    Optional<Diagnosis> findDiagnosisByName(String name);
    Optional<Diagnosis> findDiagnosisById(Integer id);
    @Transactional
    @Modifying
    void deleteById(Integer id);
}

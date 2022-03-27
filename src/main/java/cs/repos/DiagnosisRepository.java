package cs.repos;

import cs.models.Diagnosis;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {

    Diagnosis findDiagnosisByName(String name);

}

package cs.repos;

import cs.models.Diagnosis;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DiagnosisRepository extends CrudRepository<Diagnosis, Integer> {

    Iterable<Diagnosis> findAll();

    Optional<Diagnosis> findDiagnosesById(String id);

    Optional<Diagnosis> findDiagnosesByName(String name);

    @Override
    <S extends Diagnosis> S save(S entity);

}

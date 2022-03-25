package cs.repos;

import org.springframework.data.repository.CrudRepository;
import cs.models.Diagnosis;
import java.util.Optional;

public class DiagnosisRepository implements CrudRepository<Diagnosis, Integer> {

    @Override
    public <S extends Diagnosis> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Diagnosis> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Diagnosis> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Diagnosis> findAll() {
        return null;
    }

    @Override
    public Iterable<Diagnosis> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Diagnosis entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Diagnosis> entities) {

    }

    @Override
    public void deleteAll() {

    }
}

package cs.repos;

import cs.models.Ward;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WardRepository extends CrudRepository<Ward, Integer> {

    Optional<Ward> findWardByName(String name);
    Optional<Ward> findWardById(Integer id);

}

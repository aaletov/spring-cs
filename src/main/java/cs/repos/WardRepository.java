package cs.repos;

import cs.models.Ward;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WardRepository extends CrudRepository<Ward, Integer> {

    Optional<Ward> findWardByName(String name);
    Optional<Ward> findWardById(Integer id);
    @Query("select w from Ward w where w.maxCount = (select count(p.id) from People p where w = p.ward)")
    Iterable<Ward> findFullWards();

}

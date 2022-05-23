package cs.repos;

import cs.models.Ward;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface WardRepository extends CrudRepository<Ward, Integer> {
    Optional<Ward> findWardByName(String name);
    Optional<Ward> findWardById(Integer id);
    @Query("select w from Ward w where w.maxCount = (select count(p.id) from People p where p.ward = w)")
    Iterable<Ward> findFullWards();
    @Query("select w from Ward w where ((select count(p.id) from People p where p.ward = w) = 0)")
    Iterable<Ward> findEmptyWards();
    @Transactional
    @Modifying
    void deleteById(Integer id);
}

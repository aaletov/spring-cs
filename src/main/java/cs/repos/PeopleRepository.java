package cs.repos;

import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PeopleRepository extends CrudRepository<People, Integer> {
    Integer countPeopleByWardName(String name);
    Iterable<People> findPeopleByDiagnosis(Diagnosis diagnosis);
    Iterable<People> findPeopleByDiagnosisName(String name);
    Iterable<People> findPeopleByWardMaxCount(Integer maxCount);
    Iterable<People> findPeopleByWard(Ward ward);
    Optional<People> findPeopleByFirstName(String name);
    Iterable<People> findPeopleByWardName(String name);

    @Modifying
    @Transactional
    @Query("update People p set p.ward = :ward where p.id = :#{#people.id}")
    void updateWard(@Param("people") People people, @Param("ward") Ward ward);

    @Modifying
    @Transactional
    @Query("update People p set p.ward = :ward_dest where p.ward = :ward_source")
    void updateWardAll(@Param("ward_source") Ward wardSource, @Param("ward_dest") Ward wardDest);

    @Query("select w from Ward w where w.maxCount = (select count(p.id) from People p where w = p.ward)")
    Iterable<Ward> findFullWards();

}

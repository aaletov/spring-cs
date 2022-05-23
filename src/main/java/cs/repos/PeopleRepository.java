package cs.repos;

import com.sun.istack.NotNull;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import lombok.Getter;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface PeopleRepository extends CrudRepository<People, Integer> {
    Integer countPeopleByWardName(String name);
    Iterable<People> findPeopleByDiagnosis(Diagnosis diagnosis);
    Iterable<People> findPeopleByWard(Ward ward);
    Optional<People> findPeopleByFirstName(String name);
    Optional<People> findPeopleByFirstNameAndLastNameAndPatherName(String firstName, String lastName,
                                                                   String patherName);
    Iterable<People> findPeopleByWardName(String name);
    @Modifying
    @Transactional
    @Query("update People p set p.ward = :ward where p.id = :#{#people.id}")
    void updateWard(@Param("people") People people, @Param("ward") Ward ward);
    @Modifying
    @Transactional
    @Query("update People p set p.ward = :ward_dest where p.ward = :ward_source")
    void updateWardAll(@Param("ward_source") Ward wardSource, @Param("ward_dest") Ward wardDest);
    Optional<People> findPeopleById(Integer id);
}

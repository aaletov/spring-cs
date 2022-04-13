package cs.services;

import cs.models.People;
import cs.repos.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeopleService {

    private PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<People> getPeopleById(Integer id) {
        return peopleRepository.findPeopleById(id);
    }

    public void save(People people) {
        peopleRepository.save(people);
    }

    public void update(People people) {
        peopleRepository.save(people);
    }

    public void delete(People people) {
        peopleRepository.delete(people);
    }
}

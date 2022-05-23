package cs.services;

import cs.exceptions.NoSuchEntryException;
import cs.models.Ward;
import cs.repos.WardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WardService {
    private WardRepository wardRepository;

    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public Ward getWardById(Integer id) throws NoSuchEntryException {
        return wardRepository.findWardById(id).orElseThrow(() -> {
            return new NoSuchEntryException("No such Ward with id " + id);
        });
    }

    public Iterable<Ward> getAll() {
        return wardRepository.findAll();
    }

    public Iterable<Ward> getFullWards() {
        return wardRepository.findFullWards();
    }

    public void save(Ward ward) {
        wardRepository.save(ward);
    }

    public boolean doesExistsWardById(Integer id) {
        return wardRepository.findWardById(id).isPresent();
    }
}

package cs.services;

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

    public Optional<Ward> getWardById(Integer id) {
        return wardRepository.findWardById(id);
    }

    public void save(Ward ward) {
        wardRepository.save(ward);
    }

    public void update(Ward ward) {
        wardRepository.save(ward);
    }

    public void delete(Ward ward) {
        wardRepository.delete(ward);
    }
}

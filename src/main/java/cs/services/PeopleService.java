package cs.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public void saveByIds(String peopleJsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(peopleJsonString);
        peopleRepository.saveByWardIdAndDiagnosisId(
                jsonNode.get("firstName").asText(),
                jsonNode.get("lastName").asText(),
                jsonNode.get("patherName").asText(),
                jsonNode.get("ward_id").asInt(),
                jsonNode.get("diagnosis_id").asInt()
        );
    }
}

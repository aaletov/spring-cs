package cs.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.exceptions.NoSuchEntryException;
import cs.exceptions.NoSuchJsonPropertyException;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.repos.PeopleRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PeopleService {

    private PeopleRepository peopleRepository;
    private WardService wardService;
    private DiagnosisService diagnosisService;

    public PeopleService(PeopleRepository peopleRepository, WardService wardService, DiagnosisService diagnosisService) {
        this.peopleRepository = peopleRepository;
        this.wardService = wardService;
        this.diagnosisService = diagnosisService;
    }

    public People getPeopleById(Integer id) throws NoSuchEntryException {
        return peopleRepository.findPeopleById(id).orElseThrow(() -> {
            return new NoSuchEntryException("No People found with id " + id);
        });
    }

    public Iterable<People> getAll() {
        return peopleRepository.findAll();
    }

    Map<String, JsonNode> mapJsonPropertiesToJsonNodeList(JsonNode jsonNode, String[] jsonProperties) {
        return Arrays.stream(jsonProperties).collect(Collectors.toMap((property) -> property, (property) -> {
            JsonNode childJsonNode = jsonNode.get(property);
            if (jsonNode.get(property) == null) {
                throw new NoSuchJsonPropertyException("The property " + property + " can't be null");
            }
            return childJsonNode;
        }));
    }

    public void saveByIds(String peopleJsonString) throws JsonProcessingException, NoSuchEntryException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonRoot = objectMapper.readTree(peopleJsonString);

        Map<String, JsonNode> stringJsonNodeMap = mapJsonPropertiesToJsonNodeList(jsonRoot, new String[]{
                "firstName",
                "lastName",
                "patherName",
                "ward_id",
                "diagnosis_id"
        });

        String firstName = stringJsonNodeMap.get("firstName").asText();
        String lastName = stringJsonNodeMap.get("lastName").asText();
        String patherName = stringJsonNodeMap.get("patherName").asText();
        Integer wardId = stringJsonNodeMap.get("ward_id").asInt();
        Integer diagnosisId = stringJsonNodeMap.get("diagnosis_id").asInt();

        Ward ward = wardService.getWardById(wardId);
        Diagnosis diagnosis = diagnosisService.getDiagnosisById(diagnosisId);

        People people = new People(firstName, lastName, patherName, diagnosis, ward);
        peopleRepository.save(people);
    }

    public void patchPeopleWard(String peopleJsonString) throws JsonProcessingException, NoSuchEntryException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonRoot = objectMapper.readTree(peopleJsonString);

        Map<String, JsonNode> stringJsonNodeMap = mapJsonPropertiesToJsonNodeList(jsonRoot, new String[] {
                "people_id",
                "ward_id"
        });

        Integer peopleId = stringJsonNodeMap.get("people_id").asInt();
        Integer wardId = stringJsonNodeMap.get("ward_id").asInt();

        People people = peopleRepository.findById(peopleId).get();
        Ward ward = wardService.getWardById(wardId);

        peopleRepository.updateWard(people, ward);
    }

    public void patchPeopleWardAll(String wardSourceWardDestJson) throws JsonProcessingException, NoSuchEntryException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonRoot = objectMapper.readTree(wardSourceWardDestJson);

        Map<String, JsonNode> stringJsonNodeMap = mapJsonPropertiesToJsonNodeList(jsonRoot, new String[] {
                "ward_source_id",
                "ward_dest_id"
        });


        Integer wardSourceId = stringJsonNodeMap.get("ward_source_id").asInt();
        Integer wardDestId = stringJsonNodeMap.get("ward_dest_id").asInt();

        Ward wardSource = wardService.getWardById(wardSourceId);
        Ward wardDest = wardService.getWardById(wardDestId);

        peopleRepository.updateWardAll(wardSource, wardDest);
    }

    public void deletePeopleById(String peopleIdJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonRoot = objectMapper.readTree(peopleIdJson);

        Map<String, JsonNode> stringJsonNodeMap = mapJsonPropertiesToJsonNodeList(jsonRoot, new String[] {
            "people_id"
        });

        Integer peopleId = stringJsonNodeMap.get("people_id").asInt();

        peopleRepository.deleteById(peopleId);
    }

}

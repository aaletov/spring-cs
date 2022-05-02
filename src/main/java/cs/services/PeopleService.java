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

    public PeopleService(PeopleRepository peopleRepository, WardService wardService) {
        this.peopleRepository = peopleRepository;
        this.wardService = wardService;
    }

    public People getPeopleById(Integer id) throws NoSuchEntryException {
        return peopleRepository.findPeopleById(id).orElseThrow(() -> {
            return new NoSuchEntryException("No People found with id " + id);
        });
    }

    Map<String, JsonNode> mapJsonPropertiesToJsonNodeList(JsonNode jsonNode, String[] jsonProperties) {
        return Arrays.stream(new String[]{
                "people_id",
                "ward_id"
        }).collect(Collectors.toMap((property) -> property, (property) -> {
            JsonNode childJsonNode = jsonNode.get(property);
            if (jsonNode.get(property) == null) {
                throw new NoSuchJsonPropertyException("The property " + property + " can't be null");
            }
            return childJsonNode;
        }));
    }

    public void saveByIds(String peopleJsonString) throws JsonProcessingException {
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
        Integer wardId = stringJsonNodeMap.get("wardId").asInt();
        Integer diagnosisId = stringJsonNodeMap.get("diagnosisId").asInt();

        peopleRepository.saveByWardIdAndDiagnosisId(
                firstName,
                lastName,
                patherName,
                wardId,
                diagnosisId
        );
    }

    public void patchPeopleWard(String peopleJsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonRoot = objectMapper.readTree(peopleJsonString);

        Map<String, JsonNode> stringJsonNodeMap = mapJsonPropertiesToJsonNodeList(jsonRoot, new String[] {
                "people_id",
                "ward_id"
        });

        Integer peopleId = stringJsonNodeMap.get("people_id").asInt();
        Integer wardId = stringJsonNodeMap.get("ward_id").asInt();

        peopleRepository.updatePeopleByWardId(
            peopleId,
            wardId
        );
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

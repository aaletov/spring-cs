package cs.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.apimodels.PeopleApiModel;
import cs.exceptions.NoSuchEntryException;
import cs.exceptions.NoSuchJsonPropertyException;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.repos.PeopleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

    public People getPeopleByFullName(String firstName, String lastName, String patherName)
            throws NoSuchEntryException {
        return peopleRepository.findPeopleByFirstNameAndLastNameAndPatherName(firstName, lastName, patherName)
                .orElseThrow(() -> {
                    return new NoSuchEntryException("No People found with full name " +
                            firstName + " " +
                            lastName + " " +
                            patherName);
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

    public void save(People people) {
        peopleRepository.save(people);
    }

    public void saveApiModel(PeopleApiModel peopleApiModel) throws JsonProcessingException, NoSuchEntryException {
        Ward ward = wardService.getWardById(peopleApiModel.getWardId());
        Diagnosis diagnosis = diagnosisService.getDiagnosisById(peopleApiModel.getDiagnosisId());

        People people = new People(peopleApiModel.getFirstName(),
                peopleApiModel.getLastName(),
                peopleApiModel.getPatherName(),
                diagnosis,
                ward);
        peopleRepository.save(people);
    }

    public void saveAll(List<PeopleApiModel> peopleApiModelList) {
        List<People> peopleList = new ArrayList<>();

        peopleApiModelList.forEach((peopleApiModel) -> {
            Ward ward = null;
            Diagnosis diagnosis = null;

            try {
                ward = wardService.getWardById(peopleApiModel.getWardId());
                diagnosis = diagnosisService.getDiagnosisById(peopleApiModel.getDiagnosisId());
            } catch (NoSuchEntryException e) {
                e.printStackTrace();
            }

            peopleList.add(new People(peopleApiModel.getFirstName(),
                    peopleApiModel.getLastName(),
                    peopleApiModel.getPatherName(),
                    diagnosis,
                    ward));
        });

        peopleRepository.saveAll(peopleList);
    }

    public void patchPeopleWard(Integer peopleId, Integer wardId) throws NoSuchEntryException {
        People people = peopleRepository.findById(peopleId).get();
        Ward ward = wardService.getWardById(wardId);

        peopleRepository.updateWard(people, ward);
    }

    public void patchPeopleWardAll(Integer wardSourceId, Integer wardDestId) throws NoSuchEntryException {
        Ward wardSource = wardService.getWardById(wardSourceId);
        Ward wardDest = wardService.getWardById(wardDestId);

        peopleRepository.updateWardAll(wardSource, wardDest);
    }

    public void deletePeopleById(Integer peopleId) {
        peopleRepository.deleteById(peopleId);
    }

    public void delete(People people) {peopleRepository.delete(people); }

}

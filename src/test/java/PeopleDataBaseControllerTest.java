import com.fasterxml.jackson.databind.ObjectMapper;
import cs.Main;
import cs.apimodels.PeopleApiModel;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.repos.DiagnosisRepository;
import cs.repos.PeopleRepository;
import cs.repos.WardRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PeopleDataBaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Test
    public void testGetPeople() throws Exception {
        People people = new People();
        peopleRepository.save(people);

        mockMvc.perform(get("/api/people/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetPeopleByFullName() throws Exception {
        People people = new People();
        people.setFirstName("Ivan");
        people.setLastName("Ivanov");
        people.setPatherName("Ivanovich");
        peopleRepository.save(people);

        mockMvc.perform(get("/api/people/getPeopleByFullName")
                .param("firstName", "Ivan")
                .param("lastName", "Ivanov")
                .param("patherName", "Ivanovich"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllPeople() throws Exception {
        People people = new People();
        peopleRepository.save(people);

        mockMvc.perform(get("/api/people/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosisRepository.save(diagnosis);

        Ward ward = new Ward();
        wardRepository.save(ward);

        PeopleApiModel peopleApiModel = new PeopleApiModel("Igor", "Usupov", "Ilyich",
                1, 1);

        mockMvc.perform(post("/api/people/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(peopleApiModel)))
                .andExpect(status().isOk());

    }

    @Test
    public void testSaveAll() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosisRepository.save(diagnosis);

        Ward ward = new Ward();
        wardRepository.save(ward);

        List<PeopleApiModel> apiModelList = new ArrayList<>();

        apiModelList.add(
                new PeopleApiModel("Igor", "Usupov", "Ilyich", 1, 1)
        );

        apiModelList.add(
                new PeopleApiModel("Ivan", "Jackov", "Jackovich", 1, 1)
        );

        mockMvc.perform(post("/api/people/saveAll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(apiModelList)))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchPeopleWard() throws Exception {
        Ward ward = new Ward();
        wardRepository.save(ward);

        People people = new People();
        people.setWard(ward);
        peopleRepository.save(people);

        ward = new Ward();
        wardRepository.save(ward);

        mockMvc.perform(patch("/api/people/patchPeopleWard")
                .param("peopleId", "1")
                .param("wardId", "2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPatchPeopleWardAll() throws Exception {
        Ward ward = new Ward();
        wardRepository.save(ward);

        People people = new People();
        people.setWard(ward);
        peopleRepository.save(people);

        people = new People();
        people.setWard(ward);
        peopleRepository.save(people);

        ward = new Ward();
        wardRepository.save(ward);

        mockMvc.perform(patch("/api/people/moveAllPeopleFromWardToWard")
                .param("wardSourceId", "1")
                .param("wardDestId", "2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePeopleById() throws Exception {
        People people = new People();
        peopleRepository.save(people);

        mockMvc.perform(delete("/api/people/delete")
                .param("peopleId", "1"))
                .andExpect(status().isOk());

    }
}

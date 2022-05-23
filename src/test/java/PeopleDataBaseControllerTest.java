import com.fasterxml.jackson.databind.ObjectMapper;
import cs.Main;
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

import java.util.HashMap;
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
    public void testGetAllPeople() throws Exception {
        People people = new People();
        peopleRepository.save(people);

        mockMvc.perform(get("/api/people/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSavePeopleByWardIdAndDiagnosisId() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosisRepository.save(diagnosis);

        Ward ward = new Ward();
        wardRepository.save(ward);

        Map<String, String> bodyMap = new HashMap<>() {{
           put("firstName", "Igor");
           put("lastName", "Usupov");
           put("patherName", "Ilyich");
           put("ward_id", "1");
           put("diagnosis_id", "1");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(post("/api/people/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
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

        Map<String, String> bodyMap = new HashMap<>() {{
            put("people_id", "1");
            put("ward_id", "2");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(patch("/api/people/patchPeopleWard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
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

        Map<String, String> bodyMap = new HashMap<>() {{
            put("ward_source_id", "1");
            put("ward_dest_id", "2");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(patch("/api/people/moveAllPeopleFromWardTo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePeopleById() throws Exception {
        People people = new People();
        peopleRepository.save(people);

        Map<String, String> bodyMap = new HashMap<>() {{
            put("people_id", "1");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(delete("/api/people/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
                .andExpect(status().isOk());

    }
}

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.Main;
import cs.repos.PeopleRepository;
import cs.repos.WardRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PeopleDataBaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    WardRepository wardRepository;

    @Test
    public void testGetPeople() throws Exception {
        assertNotNull(peopleRepository.findPeopleById(6));

        mockMvc.perform(get("/api/people/6"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSavePeopleByWardIdAndDiagnosisId() throws Exception {
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
        assertNotNull(peopleRepository.findPeopleById(1));
        assertNotNull(wardRepository.findWardById(4));

        Map<String, String> bodyMap = new HashMap<>() {{
            put("people_id", "1");
            put("ward_id", "4");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(patch("/api/people/patchPeopleWard")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletePeopleById() throws Exception {
        assertNotNull(peopleRepository.findPeopleById(1));

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

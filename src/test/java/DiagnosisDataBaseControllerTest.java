import cs.Main;
import cs.repos.DiagnosisRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class DiagnosisDataBaseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Test
    public void testGetDiagnosisById() throws Exception {
        assertTrue(diagnosisRepository.findDiagnosisById(1).isPresent());

        mockMvc.perform(get("/api/diagnosis/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        Map<String, String> bodyMap = new HashMap<>() {{
            put("peopleList", "[]");
            put("name", "TestDiagnosis");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(post("/api/diagnosis/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
                .andExpect(status().isOk());
    }
}

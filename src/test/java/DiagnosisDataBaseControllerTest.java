import cs.Main;
import cs.models.Diagnosis;
import cs.repos.DiagnosisRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DiagnosisDataBaseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    DiagnosisRepository diagnosisRepository;

    @Test
    public void testGetDiagnosisById() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName("TestDiagnosis");
        diagnosisRepository.save(diagnosis);

        mockMvc.perform(get("/api/diagnosis/get/1"))
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

    @Test
    public void testGetAllDiagnoses() throws Exception {
        Diagnosis diagnosis = new Diagnosis();
        diagnosisRepository.save(diagnosis);

        mockMvc.perform(get("/api/diagnosis/getAll"))
                .andExpect(status().isOk());
    }
}

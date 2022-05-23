import cs.Main;
import cs.models.Diagnosis;
import cs.models.People;
import cs.models.Ward;
import cs.repos.PeopleRepository;
import cs.repos.WardRepository;
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

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class WardDataBaseControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    WardRepository wardRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Test
    public void testGetWardById() throws Exception {
        Ward ward = new Ward();
        wardRepository.save(ward);


        mockMvc.perform(get("/api/ward/get/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllWards() throws Exception {
        Ward ward = new Ward();
        wardRepository.save(ward);

        mockMvc.perform(get("/api/ward/getAll"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetFullWards() throws Exception {
        Ward ward = new Ward();
        ward.setMaxCount(1);
        wardRepository.save(ward);

        People people = new People();
        people.setWard(ward);
        peopleRepository.save(people);

        mockMvc.perform(get("/api/ward/getFullWards"))
                .andExpect(status().isOk());
    }

    @Test
    public void testSave() throws Exception {
        Map<String, String> bodyMap = new HashMap<>() {{
            put("peopleList", "[]");
            put("name", "Ward 10");
        }};

        JSONObject bodyJSON = new JSONObject(bodyMap);

        mockMvc.perform(post("/api/ward/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bodyJSON.toString()))
                .andExpect(status().isOk());
    }

}

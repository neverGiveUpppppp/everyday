package kr.co.polycube.backendtest.lottos;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class LottoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void generateLottoNumbersTest() throws Exception {
        mockMvc.perform(post("/lottos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers").isArray()) // JSON 응답의 최상위 필드인 numbers가 arr인지 확인
                .andExpect(jsonPath("$.numbers.length()").value(6)) // 길이 6인지 확인
                .andExpect(jsonPath("$.numbers[0]").isNumber())
                .andExpect(jsonPath("$.numbers[5]").isNumber());
    }


    @Test
    public void testNumberRange() throws Exception {
        mockMvc.perform(post("/lottos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers").isArray())
                .andExpect(jsonPath("$.numbers.length()").value(6))
                .andDo(print())
                .andDo(result -> {
                    String jsonResponse = result.getResponse().getContentAsString();
                    Map<String, Object> responseMap = objectMapper.readValue(jsonResponse, Map.class);
                    List<Integer> numbers = (List<Integer>) responseMap.get("numbers");

                    // 번호 범위 테스트
                    numbers.forEach(number -> assertThat(number).isBetween(1, 45));

                    // 번호 중복 테스트
                    assertThat(new HashSet<>(numbers)).hasSize(numbers.size());
                });
    }


    @Test
    public void testResponseTime() throws Exception {
        long start = System.currentTimeMillis();
        mockMvc.perform(post("/lottos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numbers").isArray())
                .andExpect(jsonPath("$.numbers.length()").value(6))
                .andDo(result -> {
                    long end = System.currentTimeMillis();
                    long duration = end - start;
                    // 응답 시간이 2초 이내인지 확인
                    assertThat(duration).isLessThan(2000);
                });
    }

}
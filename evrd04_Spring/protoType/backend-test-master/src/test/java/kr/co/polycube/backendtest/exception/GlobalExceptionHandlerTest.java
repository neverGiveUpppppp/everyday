package kr.co.polycube.backendtest.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "/users";
    }


    @Test
    @DisplayName("잘못된 요청 - 빈 값")
    public void testExceptionEmpty() throws Exception {
        // given: 이름이 없는 사용자 등록 요청 JSON 생성
        String userJson = "{}";

        // when: 잘못된 사용자 등록 요청 수행
        MvcResult result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        // then: 결과 검증
        String responseContent = result.getResponse().getContentAsString(StandardCharsets.UTF_8); // StandardCharsets.UTF_8 : 인코딩 문제 해결
        ErrorResponse response = new ObjectMapper().readValue(responseContent, ErrorResponse.class);
        assertThat(response.getReason()).contains("Validation failed for argument");
    }


    @Test
    @DisplayName("잘못된 요청 - json key값")
    public void testExceptionWrongKey() throws Exception {
        // given: 잘못된 요청 JSON 생성
        String invalidUrl = "{\"invalidField\":\"value\"}";

        // when: 잘못된 요청 수행
        MvcResult result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidUrl))
                .andExpect(status().isBadRequest())
                .andReturn();

        // then: 결과 검증
        String responseContent = result.getResponse().getContentAsString(StandardCharsets.UTF_8); // StandardCharsets.UTF_8 : 인코딩 문제 해결
        ErrorResponse response = new ObjectMapper().readValue(responseContent, ErrorResponse.class);
        assertThat(response.getReason()).contains("Validation failed for argument");
    }



    @Test
    @DisplayName("잘못된 요청 - json value값 null")
    public void testExceptionEmptyValue() throws Exception {
        // given: 잘못된 요청 JSON 생성
        String invalidJson = "{\"name\":\"\"}";

        // when: 잘못된 요청 수행
        MvcResult result = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest())
                .andReturn();

        // then: 결과 검증
        String responseContent = result.getResponse().getContentAsString();
        ErrorResponse response = new ObjectMapper().readValue(responseContent, ErrorResponse.class);
        assertThat(response.getReason()).contains("Validation failed for argument");
    }

    @Test
    @DisplayName("존재하지 않는 API 호출")
    public void testNoHandlerFoundException() throws Exception {
        // when: 존재하지 않는 API 호출
        MvcResult result = mockMvc.perform(get("/nonexistent-api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();

        // then: 결과 검증
        String responseContent = result.getResponse().getContentAsString();
        ErrorResponse errorResponse = new ObjectMapper().readValue(responseContent, ErrorResponse.class);
        assertThat(errorResponse.getReason()).isEqualTo("API not found");
    }



}
package kr.co.polycube.backendtest.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.polycube.backendtest.user.Users;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)  // JUnit5와 스프링을 통합하여 스프링 컨텍스트(=IoC 컨테이너)를 로드하고 관리 // Spring Context : 앱의 객체들을 빈으로 관리하는 역할. 런타임 중 객체 간의 의존성을 관리하고 주입하는 기능
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FilterUrlTest {

    @Autowired
    private MockMvc mockMvc;

    private String baseUrl;

    private Long userId;

    @BeforeEach
    void setUp() throws Exception {
        baseUrl = "/users";
        userId = registerUser("Test User");
    }

    private Long registerUser(String name) throws Exception {
        // 유저 등록 요청 JSON 생성
        String contentBody = String.format("{\"name\":\"%s\"}", name);

        // 유저 등록 요청 수행
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentBody))
                .andExpect(status().isOk())
                .andReturn();

        // 응답에서 등록된 유저의 ID 추출
        String responseContent = result.getResponse().getContentAsString();
        Users createdUser = new ObjectMapper().readValue(responseContent, Users.class);
        return createdUser.getId();
    }

    @Test
    @Transactional
    @DisplayName("허용된 URL - 정상케이스")
    public void allowedUrl() throws Exception {
        // when: 실제 수행
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "/" + userId)
                        .contentType(MediaType.APPLICATION_JSON))
                // then: 결과 검증
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Transactional
    @DisplayName("비허용된 URL 요청")
    public void notAllowedUrl() throws Exception {
        // given: 테스트 설정
        String url = "/users/1?name=test!!";

        // when: 실제 수행
        mockMvc.perform(get(url)
                        .contentType(MediaType.APPLICATION_JSON))
                // then: 결과 검증
                .andExpect(status().is4xxClientError());
    }


}
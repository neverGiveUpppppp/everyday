package kr.co.polycube.backendtest.user;

import kr.co.polycube.backendtest.user.dto.CreateUserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/users";
    }

    @Test
    @Transactional
    @Rollback
    public void testCreateUser() throws Exception {
        // given: 테스트를 위한 설정
        String url = "http://localhost:" + port + "/users";
        Users user = new Users();
        user.setId(12L);
        user.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);

        // when: 실제 동작을 수행
        ResponseEntity<Users> response = restTemplate.postForEntity(baseUrl, entity, Users.class);

        // then: 결과를 검증
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isEqualTo(12L);
    }

    @Test
    void join() {
    }

    @Test
    void getUser() {
    }

    @Test
    void modifyUser() {
    }


}
package kr.co.polycube.backendtest.user;

import kr.co.polycube.backendtest.exception.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // 각 테스트 후 컨텍스트 리로드
@ActiveProfiles("test") // 테스트 프로파일 사용
class UserControllerGetUserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/users";
    }


    @Test
    @Transactional
    @DisplayName("사용자 조회 - 정상 케이스")
    public void testGetUser() throws Exception {
        // given
        Users user = new Users();
        user.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Users> createResponse = restTemplate.postForEntity(baseUrl, entity, Users.class);
        Long userId = createResponse.getBody().getId();

        // when
        ResponseEntity<Users> response = restTemplate.getForEntity(baseUrl + "/" + userId, Users.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(userId);
        assertThat(response.getBody().getName()).isEqualTo("Test User");
    }

    @Test
    @Transactional
    @DisplayName("사용자 조회 - 존재하지 않는 사용자")
    public void testGetNonExistentUser() throws Exception {
        // when
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(baseUrl + "/99999", ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().getReason()).isEqualTo("존재하지않는 사용자입니다.");
    }



}
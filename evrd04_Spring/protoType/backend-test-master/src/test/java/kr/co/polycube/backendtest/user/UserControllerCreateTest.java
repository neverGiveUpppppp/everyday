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
class UserControllerCreateTest {

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
    @DisplayName("사용자 등록 - 정상 케이스")
    public void testCreateUser() throws Exception {
        // given: 테스트를 위한 설정
        Users user = new Users();
        user.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);

        // when: 실제 수행
        ResponseEntity<Users> response = restTemplate.postForEntity(baseUrl, entity, Users.class);

        // then: 결과 검증
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody().getId()).isNotNull();
    }

    @Test
    @Transactional
    @DisplayName("사용자 등록 - 이름이 없는 경우")
    public void testCreateUserWithoutName() throws Exception {
        // given: 테스트를 위한 설정
        Users user = new Users();
        user.setName(null); // 이름이 없는 경우

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);

        // when: 실제 수행
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseUrl, entity, ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getReason()).contains("Validation failed for argument");
    }

    @Test
    @Transactional
    @DisplayName("사용자 등록 - 이미 존재하는 사용자")
    public void testCreateUserAlreadyExists() throws Exception {
        // given: 테스트를 위한 설정
        Users user1 = new Users();
        user1.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity1 = new HttpEntity<>(user1, headers);
        restTemplate.postForEntity(baseUrl, entity1, Users.class);

        // 같은 이름의 사용자 등록
        Users user2 = new Users();
        user2.setName("Test User");

        HttpEntity<Users> entity2 = new HttpEntity<>(user2, headers);

        // when: 실제 수행
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseUrl, entity2, ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().getReason()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    @Transactional
    @DisplayName("사용자 등록 - 이름이 너무 긴 경우")
    public void testCreateUserWithLongName() throws Exception {
        // given: 테스트를 위한 설정
        Users user = new Users();
        user.setName("This is a really LongName for the test exceeding 20 lettes");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);

        // when: 실제 수행
        ResponseEntity<ErrorResponse> response = restTemplate.postForEntity(baseUrl, entity, ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getReason()).contains("Validation failed for argument");
    }




}
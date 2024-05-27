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
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) // 각 테스트 후 컨텍스트 리로드
@ActiveProfiles("test") // 테스트 프로파일 사용
class UserControllerModifyTest {

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
    @DisplayName("사용자 수정 - 정상 케이스")
    public void testUpdateUser() throws Exception {
        // given: 사용자를 먼저 등록
        Users user = new Users();
        user.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Users> createResponse = restTemplate.postForEntity(baseUrl, entity, Users.class);
        Long userId = createResponse.getBody().getId();

        // 수정할 사용자 정보 설정
        Users updatedUser = new Users();
        updatedUser.setId(userId);
        updatedUser.setName("Updated Test User");

        HttpEntity<Users> updateEntity = new HttpEntity<>(updatedUser, headers);

        // when: 사용자 수정 요청
        ResponseEntity<Users> response = restTemplate.exchange(baseUrl + "/" + updatedUser.getId(), HttpMethod.PUT, updateEntity, Users.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(userId);
        assertThat(response.getBody().getName()).isEqualTo("Updated Test User");
    }

    @Test
    @Transactional
    @DisplayName("사용자 수정 - 존재하지 않는 사용자")
    public void testUpdateNonExistentUser() throws Exception {
        // given: 수정할 사용자 정보 설정
        Users updatedUser = new Users();
        updatedUser.setId(99999L); // 존재하지 않는 사용자 ID
        updatedUser.setName("Updated Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> updateEntity = new HttpEntity<>(updatedUser, headers);

        // when: 사용자 수정 요청
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(baseUrl + "/99999", HttpMethod.PUT, updateEntity, ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody().getReason()).isEqualTo("존재하지않는 사용자입니다.");
    }

    @Test
    @Transactional
    @DisplayName("사용자 수정 - 이름이 없는 경우")
    public void testUpdateUserWithoutName() throws Exception {
        // given: 사용자를 먼저 등록
        Users user = new Users();
        user.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Users> createResponse = restTemplate.postForEntity(baseUrl, entity, Users.class);
        Long userId = createResponse.getBody().getId();

        // 수정할 사용자 정보 설정 (이름이 없는 경우)
        Users updatedUser = new Users();
        updatedUser.setId(userId);
        updatedUser.setName(null);

        HttpEntity<Users> updateEntity = new HttpEntity<>(updatedUser, headers);

        // when: 사용자 수정 요청
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(baseUrl + "/" + userId, HttpMethod.PUT, updateEntity, ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getReason()).contains("Validation failed for argument");
    }

    @Test
    @Transactional
    @DisplayName("사용자 수정 - 이름이 너무 긴 경우")
    public void testUpdateUserWithLongName() throws Exception {
        // given: 사용자를 먼저 등록
        Users user = new Users();
        user.setName("Test User");

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Users> entity = new HttpEntity<>(user, headers);
        ResponseEntity<Users> createResponse = restTemplate.postForEntity(baseUrl, entity, Users.class);
        Long userId = createResponse.getBody().getId();

        // 수정할 사용자 정보 설정
        Users updatedUser = new Users();
        updatedUser.setId(userId);
        updatedUser.setName("This is a really LongName for the test exceeding 20 letters");

        HttpEntity<Users> updateEntity = new HttpEntity<>(updatedUser, headers);

        // when: 사용자 수정 요청
        ResponseEntity<ErrorResponse> response = restTemplate.exchange(baseUrl + "/" + userId, HttpMethod.PUT, updateEntity, ErrorResponse.class);

        // then: 결과 검증
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody().getReason()).contains("Validation failed for argument");
    }

}
package hobbiedo.member.domain;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import hobbiedo.member.type.GenderType;
import hobbiedo.member.vo.request.IntegrateSignUpVO;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
@EnableWebMvc
class MemberRepositoryTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	ResultActions startAPI(String email, String name, String phoneNumber,
		GenderType gender, LocalDate birth, String loginId, String password) throws Exception {
		return mockMvc.perform(post("/v1/non-users/sign-up")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(
				createMember(email, name, phoneNumber, gender, birth, loginId, password))
			));
	}

	private IntegrateSignUpVO createMember(
		String email, String name, String phoneNumber,
		GenderType gender, LocalDate birth, String loginId, String password) {
		return new IntegrateSignUpVO(name, email, phoneNumber, gender, loginId, password, birth);
	}

	@Test
	@DisplayName("전화번호가 10 ~ 11 자리가 아니라면 실패한다.")
	void 전화번호_예외_테스트() throws Exception {
		ResultActions resultActions = startAPI("tjdvy963@naver.com", "dhfw", "01010101",
			GenderType.MALE, LocalDate.now(), "tjdvy953", "wnsvy19503!!");

		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("이름에 숫자가 포함된다면  예외를 발생시킨다.")
	void 이름_예외_테스트() throws Exception {
		ResultActions resultActions = startAPI("tjdvy963@naver.com", "asd1", "01010101",
				GenderType.MALE, LocalDate.now(), "tjdvy953", "wnsvy19503!!");

		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("이메일 포맷을 따르지 않는다면, 예외를 발생시킨다.")
	void 이메일_예외_테스트() throws Exception {

		ResultActions resultActions = startAPI("tjdvy963naver.com", "asd1", "01092312316",
				GenderType.MALE, LocalDate.now(), "tjdvy953", "wnsvy19503!!");

		resultActions.andExpect(status().isBadRequest());
	}

	@Test
	@DisplayName("값이 정상이라면 성공한다.")
	void 값_인증_성공_테스트() throws Exception {
		ResultActions resultActions = startAPI("tjdvy953@naver.com", "홍준표", "01092312316",
				GenderType.MALE, LocalDate.now(), "tjdvy953", "wnsvy19503!!");

		resultActions.andExpect(status().isOk());
	}
}
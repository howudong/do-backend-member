package hobbiedo.member.application;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import hobbiedo.global.exception.handler.MemberExceptionHandler;
import hobbiedo.member.dto.request.IntegrateSignUpDTO;
import hobbiedo.member.infrastructure.MemberRepository;
import hobbiedo.member.type.GenderType;

@SpringBootTest
@Transactional
class MemberServiceTest {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;

	@BeforeEach
	void init() {
		memberService.integrateSignUp(
			createMember("01092312316", "howudong", "tjdvy963@naver.com", GenderType.MALE, "tjdvy9123",
				"wnsvy18391!!"));
	}

	@AfterEach
	void after() {
		memberRepository.deleteAll();
	}

	@Test
	@DisplayName("이메일이 기존과 중복된다면 MemberExceptionHandler 예외가 발생한다")
	void 이메일_중복_테스트() {

		IntegrateSignUpDTO newMember = createMember("01012312316", "howudong", "tjdvy963@naver.com",
			GenderType.MALE, "tjdvy9123", "wnsvy18391!!");
		assertThatThrownBy(() -> memberService.integrateSignUp(newMember))
			.isInstanceOf(MemberExceptionHandler.class);
	}

	@Test
	@DisplayName("휴대폰 번호가 기존과 중복된다면 MemberExceptionHandler 예외가 발생한다")
	void 폰번호_중복_테스트() {

		IntegrateSignUpDTO newMember = createMember("01092312316", "howudong", "tjdvy9123@naver.com",
			GenderType.MALE, "tjdvy9123", "wnsvy18391!!");
		assertThatThrownBy(() -> memberService.integrateSignUp(newMember))
			.isInstanceOf(MemberExceptionHandler.class);
	}

	private static IntegrateSignUpDTO createMember(String number, String name, String email, GenderType gender,
		String loginId, String pwd) {
		return IntegrateSignUpDTO.builder()
			.name(name)
			.birth(LocalDate.now())
			.email(email)
			.gender(gender)
			.phoneNumber(number)
			.loginId(loginId)
			.password(pwd)
			.build();
	}
}
package hobbiedo.member.domain;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hobbiedo.member.infrastructure.MemberRepository;
import hobbiedo.member.type.GenderType;
import jakarta.validation.ConstraintViolationException;

@DataJpaTest
class MemberRepositoryTest {
	@Autowired
	private MemberRepository memberRepository;

	private Member createMember(String email, String name, String phoneNumber, GenderType gender, LocalDate birth) {
		return Member.builder()
				.email(email)
				.phoneNumber(phoneNumber)
				.name(name)
				.gender(gender)
				.birth(birth)
				.build();
	}

	@Test
	@DisplayName("전화번호가 10 ~ 11 자리가 아니라면 실패한다.")
	void 전화번호_예외_테스트() {

		Member member = createMember("tjdvy963@naver.com",
				"김아무개",
				"01092312316112",
				GenderType.MALE,
				LocalDate.now());
		IntegrateAuth singup = IntegrateAuth.builder()
				.loginId("1234")
				.member(member)
				.password("1234")
				.build();

		Assertions.assertThatThrownBy(
						() -> memberRepository.save(singup))
				.isInstanceOf(ConstraintViolationException.class);

	}

	@Test
	@DisplayName("이름이 공백이라면 예외를 발생시킨다.")
	void 이름_예외_테스트() {

		Member member = createMember("tjdvy963@naver.com",
				" ",
				"01092312316112",
				GenderType.MALE,
				LocalDate.now());
		IntegrateAuth singup = IntegrateAuth.builder()
				.loginId("1234")
				.member(member)
				.password("1234")
				.build();
		Assertions.assertThatThrownBy(
						() -> memberRepository.save(singup))
				.isInstanceOf(ConstraintViolationException.class);

	}

	@Test
	@DisplayName("이메일 포맷을 따르지 않는다면, 예외를 발생시킨다.")
	void 이메일_예외_테스트() {

		Member member = createMember("tjdvy963@naver",
				"김아무개",
				"01092312316112",
				GenderType.MALE,
				LocalDate.now()
		);
		IntegrateAuth singup = IntegrateAuth.builder()
				.loginId("1234")
				.member(member)
				.password("1234")
				.build();
		Assertions.assertThatThrownBy(
						() -> memberRepository.save(singup))
				.isInstanceOf(ConstraintViolationException.class);

	}

	@Test
	@DisplayName("생년 월일이 YYYY-MM-DD 포맷을 따르지 않는다면,공백이라면 예외를 발생시킨다.")
	void 값_인증_성공_테스트() {

		Member member = createMember("tjdvy963@naver.com",
				"김아무개",
				"01092312316112",
				GenderType.MALE,
				LocalDate.now()
		);
		IntegrateAuth singup = IntegrateAuth.builder()
				.loginId("1234")
				.member(member)
				.password("1234")
				.build();
		Assertions.assertThatThrownBy(
						() -> memberRepository.save(singup))
				.isInstanceOf(ConstraintViolationException.class);

	}
}
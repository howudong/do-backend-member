package hobbiedo.member.domain;

import java.time.LocalDate;

import hobbiedo.global.base.BaseEntity;
import hobbiedo.member.type.GenderType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
	public static final String BLANK = "";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String uuid;
	private String email;
	private String phoneNumber;
	private Boolean active;
	private GenderType gender;
	private LocalDate birth;
	private String profileMessage = "";

	@Builder
	public Member(String name, String uuid, String email, String phoneNumber, GenderType gender, LocalDate birth) {
		this.name = name;
		this.email = email;
		this.uuid = uuid;
		this.phoneNumber = phoneNumber;
		this.active = true;
		this.gender = gender;
		this.birth = birth;
		this.profileMessage = BLANK;
	}
}

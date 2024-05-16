package hobbiedo.member.domain;

import hobbiedo.member.type.RangeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
public class MemberRegion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String uuid;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private RangeType currentSelectedRange;

	@NotNull
	private float latitude;

	@NotNull
	private float longitude;

	@NotBlank
	private String addressName;

	@NotNull
	private Integer legalCode;
}

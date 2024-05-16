package hobbiedo.member.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RangeType {
	THREE(3),
	FIVE(5),
	SEVEN(7),
	TEN(10);

	private final int kilometer;
}

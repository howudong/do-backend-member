package hobbiedo.member.type;

import hobbiedo.global.code.status.ErrorStatus;
import hobbiedo.global.exception.handler.ExampleHandler;
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

	public static RangeType toType(int range) {
		for (RangeType type : RangeType.values()) {
			if (type.getKilometer() == range) {
				return type;
			}
		}
		throw new ExampleHandler(ErrorStatus.NO_EXIST_RANGE_TYPE);
	}
}

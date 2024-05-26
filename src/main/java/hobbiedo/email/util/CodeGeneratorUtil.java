package hobbiedo.email.util;

import java.util.Random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeGeneratorUtil {
	private static final String DIGIT_SET_FORMAT = "%%0%dd";

	public static String generate(int digit) {
		Random random = new Random();
		int code = random.nextInt((int)Math.pow(10, digit));
		String format = String.format(DIGIT_SET_FORMAT, digit);
		return String.format(format, code);
	}
}

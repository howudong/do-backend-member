package hobbiedo.email.type;

public enum MailType {
	EMAIL_CODE("취미한다 이메일 인증 코드 발송",
		"""
			<h3> [취미한다] 이메일 인증 코드입니다. </h3>
			<h1> %s </h1>
			<h3> 서비스를 이용해주셔서 감사합니다!! </h3>
			""");
	public final String subject;
	public final String text;

	MailType(String subject, String text) {
		this.subject = subject;
		this.text = text;
	}
}

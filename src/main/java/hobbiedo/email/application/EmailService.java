package hobbiedo.email.application;

import hobbiedo.email.domain.SendMessage;

public interface EmailService extends SendMessage {
	void sendMail(Object object);
}

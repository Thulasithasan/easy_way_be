package com.thulasi.easyway.component;

public interface AsyncEmailSender {

	void sendMail(String to, String subject, String htmlBody);

}

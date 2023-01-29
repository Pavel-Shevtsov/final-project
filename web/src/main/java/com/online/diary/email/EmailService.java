package com.online.diary.email;

public interface EmailService {
    void sendEmail(String toAddress, String fromAddress, String subject, String msgBody);
}

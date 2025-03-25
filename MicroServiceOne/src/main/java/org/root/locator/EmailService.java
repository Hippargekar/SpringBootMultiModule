package org.root.locator;

import org.springframework.stereotype.Service;

@Service(NotificationTypes.Types.EMAIL)
public class EmailService implements Notification{
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending email with message: " + message);
    }
}

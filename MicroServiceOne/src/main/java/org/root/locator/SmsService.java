package org.root.locator;

import org.springframework.stereotype.Service;

@Service(NotificationTypes.Types.SMS)
public class SmsService implements Notification{
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS with message: " + message);
    }
}

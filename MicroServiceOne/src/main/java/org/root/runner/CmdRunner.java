package org.root.runner;

import org.root.locator.Notification;
import org.root.locator.NotificationTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CmdRunner implements CommandLineRunner {

    @Autowired
    private Map<String, Notification> services;

    @Override
    public void run(String... args) throws Exception {
        Notification notification = services.get(NotificationTypes.SMS.getType());
        notification.sendNotification("Implemented the Service Locator Pattern");
    }
}

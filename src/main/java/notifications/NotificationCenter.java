package com.studentmanagement.notifications;

public class NotificationCenter {

    private static NotificationCenter instance;

    private NotificationCenter() { }

    public static NotificationCenter getInstance() {
        if (instance == null) {
            instance = new NotificationCenter();
        }
        return instance;
    }

    public void notifyUser(String message) {
        System.out.println("🔔 Notification: " + message);
    }
}

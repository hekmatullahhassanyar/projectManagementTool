package com.studentmanagement.components.impl;

import com.studentmanagement.components.NotificationComponent;
import com.studentmanagement.notifications.NotificationCenter;

public class NotificationComponentImpl implements NotificationComponent {
    @Override
    public void notifyUser(String message) {
        NotificationCenter.getInstance().notifyUser(message); // uses Singleton
    }
}

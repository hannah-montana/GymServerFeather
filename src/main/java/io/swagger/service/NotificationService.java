package io.swagger.service;

import io.swagger.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationByUserId(String id);
    Integer createNew(Notification noti);
    Integer updateRead(String id);
}

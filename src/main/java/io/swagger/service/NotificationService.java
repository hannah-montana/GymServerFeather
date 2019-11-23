package io.swagger.service;

import io.swagger.model.Notification;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;

import java.util.List;

public interface NotificationService {
    List<Notification> getNotificationByUserId(String id);
    Integer createNew(Notification noti);
    Integer updateRead(String id);
    Integer validateFocusSession(Notification noti);
    Integer checkSendValidateFocusSession(String focusSessionId, String fromUser);
    Integer responseFocusSession(Notification noti);
}

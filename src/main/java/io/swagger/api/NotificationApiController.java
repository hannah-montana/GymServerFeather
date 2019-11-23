package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Notification;
import io.swagger.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class NotificationApiController implements NotificationApi {

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    NotificationService notificationService;

    @org.springframework.beans.factory.annotation.Autowired
    public NotificationApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Notification>> getNotificationByUserId(@ApiParam(value = "",required=true) @PathVariable("userId") String userId){
        try {
            List<Notification> lst = notificationService.getNotificationByUserId(userId);

            return new ResponseEntity<List<Notification>>(lst, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<List<Notification>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> createNewSession(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Notification noti){
        try{
            int res = notificationService.createNew(noti);

            return new ResponseEntity<Integer>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Integer> updateRead(@ApiParam(value = "",required=true) @PathVariable("id") String id){
        try{
            int res = notificationService.updateRead(id);

            return new ResponseEntity<Integer>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Integer> validateFocusSession(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Notification noti){
        try{
            int res = notificationService.validateFocusSession(noti);

            return new ResponseEntity<Integer>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> checkValidateFocusSession(@ApiParam(value = "",required=true) @PathVariable("userId") String userId){
        try{
            //int res = notificationService.validateFocusSession(noti);

            return new ResponseEntity<Integer>(1, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

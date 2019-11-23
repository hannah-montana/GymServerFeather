package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Notification;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Api(value = "notification")
public interface NotificationApi {

    @ApiOperation(value = "get notify by user id", nickname = "getNotificationByUserId", notes = "", response = Notification.class, tags={ "Notification", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Notification.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Exercise was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/notification/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Notification>> getNotificationByUserId(@ApiParam(value = "",required=true) @PathVariable("userId") String userId);

    @ApiOperation(value = "Add a new notify", nickname = "createNewNotification", notes = "", response = Notification.class, responseContainer = "List", tags={ "Notification", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BooleanOperators.Not.class, responseContainer = "List"),
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/notification",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Integer> createNewSession(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Notification noti);

    @ApiOperation(value = "Update read", nickname = "updateRead", notes = "", tags={ "Notification", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/notification",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Integer> updateRead(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Notification noti);

    @ApiOperation(value = "Validate focus Session", nickname = "validateFocusSession", notes = "", response = Notification.class, responseContainer = "List", tags={ "Notification", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BooleanOperators.Not.class, responseContainer = "List"),
            @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/notification/validate",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<Integer> validateFocusSession(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Notification noti);

    @ApiOperation(value = "check send validated focus session", nickname = "checkSendValidateFocusSession", notes = "", response = Notification.class, tags={ "Notification", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Notification.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Exercise was not found"),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/notification/checkValidated/{focusSessionId}/{fromUser}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Integer> checkSendValidateFocusSession(@ApiParam(value = "",required=true) @PathVariable("focusSessionId") String focusSessionId,
                                                          @ApiParam(value = "",required=true) @PathVariable("fromUser") String fromUser);

    @ApiOperation(value = "Coach validate focus session", nickname = "validateFSByCoach", notes = "", tags={ "Notification", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Session not found"),
            @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/notification/responseValidate",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Integer> validateFSByCoach(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Notification noti);

}

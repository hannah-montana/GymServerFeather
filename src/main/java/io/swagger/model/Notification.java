package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Validated
@Document(collection = "Notification")
public class Notification {
    @Id
    @JsonProperty("_id")
    private String _id = null;

    @JsonProperty("id")
    private String id = null;
    private String notifyContent = null;
    private String fromUser = null;
    private String toUser = null;
    private String dateAction = null;
    private String read = null;
    private String focusSessionId = null;
    private String validatedFromCustomer = null;
    private String validatedFromCoach = null;
    private Integer temp = null; //equal id

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @ApiModelProperty(required = true, value = "")
    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public Notification(){}

    public String getFocusSessionId() {
        return focusSessionId;
    }

    public void setFocusSessionId(String focusSessionId) {
        this.focusSessionId = focusSessionId;
    }

    public String getValidatedFromCustomer() {
        return validatedFromCustomer;
    }

    public void setValidatedFromCustomer(String validatedFromCustomer) {
        this.validatedFromCustomer = validatedFromCustomer;
    }

    public String getValidatedFromCoach() {
        return validatedFromCoach;
    }

    public void setValidatedFromCoach(String validatedFromCoach) {
        this.validatedFromCoach = validatedFromCoach;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }
}

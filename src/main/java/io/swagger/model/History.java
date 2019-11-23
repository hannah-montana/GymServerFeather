package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Document(collection = "History")
public class History {

    @Id
    @JsonProperty("_id")
    private String _id = null;

    private String id = null;
    private String proUsId = null;
    private String progId = null;
    private String userId = null;
    private String sessId = null;
    private String exId = null;
    private Integer focusSession = null;
    private Integer praticalDuration = null;
    private Integer duration = null;
    private Integer point = null;
    private Integer calorie = null;
    private Integer order = null;
    private String processing = null;
    private String dateAction = null;
    private String level = null;
    private String sendValidateFS = null;
    private String validatedByCoach = null;

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

    public String getProUsId() {
        return proUsId;
    }

    public void setProUsId(String proUsId) {
        this.proUsId = proUsId;
    }

    public String getProgId() {
        return progId;
    }

    public void setProgId(String progId) {
        this.progId = progId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessId() {
        return sessId;
    }

    public void setSessId(String sessId) {
        this.sessId = sessId;
    }

    public String getExId() {
        return exId;
    }

    public void setExId(String exId) {
        this.exId = exId;
    }

    public Integer getFocusSession() {
        return focusSession;
    }

    public void setFocusSession(Integer focusSession) {
        this.focusSession = focusSession;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getProcessing() {
        return processing;
    }

    public void setProcessing(String processing) {
        this.processing = processing;
    }

    public Integer getPraticalDuration() {
        return praticalDuration;
    }

    public void setPraticalDuration(Integer praticalDuration) {
        this.praticalDuration = praticalDuration;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSendValidateFS() {
        return sendValidateFS;
    }

    public void setSendValidateFS(String sendValidateFS) {
        this.sendValidateFS = sendValidateFS;
    }

    public String getValidatedByCoach() {
        return validatedByCoach;
    }

    public void setValidatedByCoach(String validatedByCoach) {
        this.validatedByCoach = validatedByCoach;
    }
}

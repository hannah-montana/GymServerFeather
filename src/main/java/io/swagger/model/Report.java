package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Document(collection = "Report")
public class Report {
    @Id
    @JsonProperty("_id")
    private String _id = null;
    private String id = null;
    private String completedWeeklyDeadline = null;
    private String thinkAboutSession = null;
    private String readyNextSession = null;
    private String comment = null;
    private String sessId1 = null;
    private String sessId2 = null;
    private String custId = null;
    private String coachId = null;
    private String dateAction = null;

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

    public String getCompletedWeeklyDeadline() {
        return completedWeeklyDeadline;
    }

    public void setCompletedWeeklyDeadline(String completedWeeklyDeadline) {
        this.completedWeeklyDeadline = completedWeeklyDeadline;
    }

    public String getThinkAboutSession() {
        return thinkAboutSession;
    }

    public void setThinkAboutSession(String thinkAboutSession) {
        this.thinkAboutSession = thinkAboutSession;
    }

    public String getReadyNextSession() {
        return readyNextSession;
    }

    public void setReadyNextSession(String readyNextSession) {
        this.readyNextSession = readyNextSession;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSessId1() {
        return sessId1;
    }

    public void setSessId1(String sessId1) {
        this.sessId1 = sessId1;
    }

    public String getSessId2() {
        return sessId2;
    }

    public void setSessId2(String sessId2) {
        this.sessId2 = sessId2;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String cusId) {
        this.custId = cusId;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getDateAction() {
        return dateAction;
    }

    public void setDateAction(String dateAction) {
        this.dateAction = dateAction;
    }
}

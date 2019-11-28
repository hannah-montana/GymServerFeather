package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Session
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "Session")
public class Session   {
  @Id
  @JsonProperty("_id")
  private String _id = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("duration")
  private String duration = null;

  @JsonProperty("level")
  private String level = null;

  @JsonProperty("focusSession")
  private Integer focusSession = null;

  @JsonProperty("validated")
  private Integer validated = null;

  @JsonProperty("sendReported")
  private Integer sendReported = null;

  private String isChecked = null;

  private String coachId = null;

  private Integer order = null; //use for further session

  private String parentId = null; //use for get current session: parent history id

  public Session id(String id) {
    this.id = id;
    return this;
  }


  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getId() {
    return id;
  }

  public void setId(String _id) {
    this.id = _id;
  }

  public Session name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Session description(String description) {
    this.description = description;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Get duration
   * @return duration
   **/
  @ApiModelProperty(value = "")

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public Session level(String level) {
    this.level = level;
    return this;
  }

  /**
   * Get level
   * @return level
   **/
  @ApiModelProperty(value = "")

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }


  public Session focusSession(Integer focusSession) {
    this.focusSession = focusSession;
    return this;
  }

  public Integer getFocusSession() {
    return focusSession;
  }

  public void setFocusSession(Integer focusSession) {
    this.focusSession = focusSession;
  }

  public Session validated(Integer validated) {
    this.validated = validated;
    return this;
  }

  public Integer getValidated() {
    return validated;
  }

  public void setValidated(Integer validated) {
    this.validated = validated;
  }

  public Session sendReported(Integer sendReported) {
    this.sendReported = sendReported;
    return this;
  }

  public Integer getSendReported() {
    return sendReported;
  }

  public void setSendReported(Integer sendReported) {
    this.sendReported = sendReported;
  }

  public String getIsChecked() {
    return isChecked;
  }

  public void setIsChecked(String isChecked) {
    this.isChecked = isChecked;
  }

  public String getCoachId() {
    return coachId;
  }

  public void setCoachId(String coachId) {
    this.coachId = coachId;
  }

  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Session session = (Session) o;
    return Objects.equals(this.id, session.id) &&
            Objects.equals(this.name, session.name) &&
            Objects.equals(this.description, session.description) &&
            Objects.equals(this.duration, session.duration) &&
            Objects.equals(this.level, session.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, duration, level);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Session {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

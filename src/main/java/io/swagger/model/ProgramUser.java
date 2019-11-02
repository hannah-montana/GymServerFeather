package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProgramUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "ProgramUser")
public class ProgramUser   {

  @Id
  @JsonProperty("_id")
  private String _id = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("progId")
  private String progId = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("coachId")
  private String coachId = null;

  @JsonProperty("point")
  private Integer point = null;

  @JsonProperty("note")
  private String note = null;

  @JsonProperty("isFinished")
  private String isFinished = null;

  public ProgramUser _id(String _id) {
    this._id = _id;
    return this;
  }

  @ApiModelProperty(value = "")
  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public ProgramUser id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @ApiModelProperty(value = "")
  @NotNull
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProgramUser proId(String proId) {
    this.progId = proId;
    return this;
  }

  /**
   * Get proId
   * @return proId
   **/
  @ApiModelProperty(value = "")

  public String getProgId() {
    return progId;
  }

  public void setProgId(String proId) {
    this.progId = proId;
  }

  public ProgramUser userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @ApiModelProperty(value = "")

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public ProgramUser coachId(String coachId) {
    this.coachId = coachId;
    return this;
  }

  /**
   * Get coachId
   * @return coachId
   **/
  @ApiModelProperty(value = "")

  public String getCoachId() {
    return coachId;
  }

  public void setCoachId(String coachId) {
    this.coachId = coachId;
  }

  public ProgramUser point(Integer point) {
    this.point = point;
    return this;
  }

  /**
   * Get point
   * @return point
   **/
  @ApiModelProperty(value = "")

  public Integer getPoint() {
    return point;
  }

  public void setPoint(Integer point) {
    this.point = point;
  }

  public ProgramUser note(String note) {
    this.note = note;
    return this;
  }

  /**
   * Get note
   * @return note
   **/
  @ApiModelProperty(value = "")

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public ProgramUser isFinished(String isFinished) {
    this.isFinished = isFinished;
    return this;
  }

  @ApiModelProperty(value = "")
  public String getIsFinished() {
    return isFinished;
  }

  public void setIsFinished(String isFinished) {
    this.isFinished = isFinished;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProgramUser programUser = (ProgramUser) o;
    return Objects.equals(this.id, programUser.id) &&
            Objects.equals(this.progId, programUser.progId) &&
            Objects.equals(this.userId, programUser.userId) &&
            Objects.equals(this.coachId, programUser.coachId) &&
            Objects.equals(this.point, programUser.point) &&
            Objects.equals(this.note, programUser.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, progId, userId, coachId, point, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProgramUser {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    proId: ").append(toIndentedString(progId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    coachId: ").append(toIndentedString(coachId)).append("\n");
    sb.append("    point: ").append(toIndentedString(point)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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

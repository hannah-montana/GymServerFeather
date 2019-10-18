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
 * ExerciseSession
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "ExerciseSession")
public class ExerciseSession   {
  @Id
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("exId")
  private Integer exId = null;

  @JsonProperty("sesId")
  private Integer sesId = null;

  @JsonProperty("coachId")
  private Integer coachId = null;

  @JsonProperty("point")
  private Integer point = null;

  @JsonProperty("note")
  private String note = null;

  public ExerciseSession id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")

  public String getId() {
    return id;
  }

  public void setId(String _id) {
    this.id = _id;
  }

  public ExerciseSession exId(Integer exId) {
    this.exId = exId;
    return this;
  }

  /**
   * Get exId
   * @return exId
  **/
  @ApiModelProperty(value = "")

  public Integer getExId() {
    return exId;
  }

  public void setExId(Integer exId) {
    this.exId = exId;
  }

  public ExerciseSession sesId(Integer sesId) {
    this.sesId = sesId;
    return this;
  }

  /**
   * Get sesId
   * @return sesId
  **/
  @ApiModelProperty(value = "")

  public Integer getSesId() {
    return sesId;
  }

  public void setSesId(Integer sesId) {
    this.sesId = sesId;
  }

  public ExerciseSession coachId(Integer coachId) {
    this.coachId = coachId;
    return this;
  }

  /**
   * Get coachId
   * @return coachId
  **/
  @ApiModelProperty(value = "")

  public Integer getCoachId() {
    return coachId;
  }

  public void setCoachId(Integer coachId) {
    this.coachId = coachId;
  }

  public ExerciseSession point(Integer point) {
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

  public ExerciseSession note(String note) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExerciseSession exerciseSession = (ExerciseSession) o;
    return Objects.equals(this.id, exerciseSession.id) &&
        Objects.equals(this.exId, exerciseSession.exId) &&
        Objects.equals(this.sesId, exerciseSession.sesId) &&
        Objects.equals(this.coachId, exerciseSession.coachId) &&
        Objects.equals(this.point, exerciseSession.point) &&
        Objects.equals(this.note, exerciseSession.note);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, exId, sesId, coachId, point, note);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExerciseSession {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    exId: ").append(toIndentedString(exId)).append("\n");
    sb.append("    sesId: ").append(toIndentedString(sesId)).append("\n");
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

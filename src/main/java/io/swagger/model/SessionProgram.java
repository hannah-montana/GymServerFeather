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
 * SessionProgram
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "SessionProgram")
public class SessionProgram   {
  @Id
  @JsonProperty("_id")
  private String _id = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("progId")
  private String progId = null;

  @JsonProperty("sessId")
  private String sessId = null;

  private Integer priority = null;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public SessionProgram id(String id) {
    this.id = id;
    return this;
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
    this.id =_id;
  }

  public SessionProgram progId(String progId) {
    this.progId = progId;
    return this;
  }

  /**
   * Get progId
   * @return progId
   **/
  @ApiModelProperty(value = "")

  public String getProgId() {
    return progId;
  }

  public void setProgId(String progId) {
    this.progId = progId;
  }

  public SessionProgram sessId(String sessId) {
    this.sessId = sessId;
    return this;
  }

  /**
   * Get sessId
   * @return sessId
   **/
  @ApiModelProperty(value = "")

  public String getSessId() {
    return sessId;
  }

  public void setSessId(String sessId) {
    this.sessId = sessId;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SessionProgram sessionProgram = (SessionProgram) o;
    return Objects.equals(this.id, sessionProgram.id) &&
            Objects.equals(this.progId, sessionProgram.progId) &&
            Objects.equals(this.sessId, sessionProgram.sessId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, progId, sessId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SessionProgram {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    progId: ").append(toIndentedString(progId)).append("\n");
    sb.append("    sessId: ").append(toIndentedString(sessId)).append("\n");
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

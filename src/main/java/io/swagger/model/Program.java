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
 * Program
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "Program")
public class Program   {
  @Id
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("instruction")
  private String instruction = null;

  @JsonProperty("duration")
  private String duration = null;

  @JsonProperty("level")
  private String level = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("target")
  private String target = null;

  @JsonProperty("calorie")
  private Integer calorie = null;

  @JsonProperty("video")
  private String video = null;

  @JsonProperty("photo")
  private String photo = null;

  @JsonProperty("benefit")
  private String benefit = null;

  @JsonProperty("frequency")
  private String frequency = null;

  @JsonProperty("point")
  private Integer point = null;

  public Program id(String id) {
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
    this.id = _id;
  }

  public Program name(String name) {
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

  public Program description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Program instruction(String instruction) {
    this.instruction = instruction;
    return this;
  }

  /**
   * Get instruction
   * @return instruction
  **/
  @ApiModelProperty(value = "")

  public String getInstruction() {
    return instruction;
  }

  public void setInstruction(String instruction) {
    this.instruction = instruction;
  }

  public Program duration(String duration) {
    this.duration = duration;
    return this;
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

  public Program level(String level) {
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

  public Program type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(value = "")

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Program target(String target) {
    this.target = target;
    return this;
  }

  /**
   * Get target
   * @return target
  **/
  @ApiModelProperty(value = "")

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public Program calorie(Integer calorie) {
    this.calorie = calorie;
    return this;
  }

  /**
   * Get calorie
   * @return calorie
  **/
  @ApiModelProperty(value = "")

  public Integer getCalorie() {
    return calorie;
  }

  public void setCalorie(Integer calorie) {
    this.calorie = calorie;
  }

  public Program video(String video) {
    this.video = video;
    return this;
  }

  /**
   * Get video
   * @return video
  **/
  @ApiModelProperty(value = "")

  public String getVideo() {
    return video;
  }

  public void setVideo(String video) {
    this.video = video;
  }

  public Program photo(String photo) {
    this.photo = photo;
    return this;
  }

  /**
   * Get photo
   * @return photo
  **/
  @ApiModelProperty(value = "")

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Program benefit(String benefit) {
    this.benefit = benefit;
    return this;
  }

  /**
   * Get benefit
   * @return benefit
  **/
  @ApiModelProperty(value = "")

  public String getBenefit() {
    return benefit;
  }

  public void setBenefit(String benefit) {
    this.benefit = benefit;
  }

  public Program frequency(String frequency) {
    this.frequency = frequency;
    return this;
  }

  /**
   * Get frequency
   * @return frequency
  **/
  @ApiModelProperty(value = "")

  public String getFrequency() {
    return frequency;
  }

  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }

  public Program point(Integer point) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Program program = (Program) o;
    return Objects.equals(this.id, program.id) &&
        Objects.equals(this.name, program.name) &&
        Objects.equals(this.description, program.description) &&
        Objects.equals(this.instruction, program.instruction) &&
        Objects.equals(this.duration, program.duration) &&
        Objects.equals(this.level, program.level) &&
        Objects.equals(this.type, program.type) &&
        Objects.equals(this.target, program.target) &&
        Objects.equals(this.calorie, program.calorie) &&
        Objects.equals(this.video, program.video) &&
        Objects.equals(this.photo, program.photo) &&
        Objects.equals(this.benefit, program.benefit) &&
        Objects.equals(this.frequency, program.frequency) &&
        Objects.equals(this.point, program.point);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, instruction, duration, level, type, target, calorie, video, photo, benefit, frequency, point);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Program {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    instruction: ").append(toIndentedString(instruction)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    target: ").append(toIndentedString(target)).append("\n");
    sb.append("    calorie: ").append(toIndentedString(calorie)).append("\n");
    sb.append("    video: ").append(toIndentedString(video)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    benefit: ").append(toIndentedString(benefit)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    point: ").append(toIndentedString(point)).append("\n");
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

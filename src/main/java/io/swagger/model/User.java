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
 * User
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-27T08:20:37.196Z[GMT]")
@Document(collection = "User")
public class User   {

  @Id
  @JsonProperty("_id")
  private String _id = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("userName")
  private String userName = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("birthDate")
  private String birthDate = null;

  @JsonProperty("role")
  private Integer role = null;

  @JsonProperty("point")
  private Integer point = null;

  @JsonProperty("photo")
  private String photo = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("note")
  private String note = null;

  @JsonProperty("level")
  private String level = null;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public User id(String id) {
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

  public void setId(String id) {
    this.id = id;
  }

  public User userName(String userName) {
    this.userName = userName;
    return this;
  }

  /**
   * Get userName
   * @return userName
   **/
  @ApiModelProperty(value = "")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public User password(String password) {
    this.password = password;
    return this;
  }

  /**
   * Get password
   * @return password
   **/
  @ApiModelProperty(value = "")

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   **/
  @ApiModelProperty(value = "")

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public User lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   **/
  @ApiModelProperty(value = "")

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public User birthDate(String birthDate) {
    this.birthDate = birthDate;
    return this;
  }

  /**
   * Get birthDate
   * @return birthDate
   **/
  @ApiModelProperty(value = "")

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public User role(Integer role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
   **/
  @ApiModelProperty(value = "")

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public User point(Integer point) {
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

  public User photo(String photo) {
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

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
   **/
  @ApiModelProperty(value = "")

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User note(String note) {
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

  public User level(String level) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
            Objects.equals(this.userName, user.userName) &&
            Objects.equals(this.password, user.password) &&
            Objects.equals(this.firstName, user.firstName) &&
            Objects.equals(this.lastName, user.lastName) &&
            Objects.equals(this.birthDate, user.birthDate) &&
            Objects.equals(this.role, user.role) &&
            Objects.equals(this.point, user.point) &&
            Objects.equals(this.photo, user.photo) &&
            Objects.equals(this.email, user.email) &&
            Objects.equals(this.note, user.note) &&
            Objects.equals(this.level, user.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userName, password, firstName, lastName, birthDate, role, point, photo, email, note, level);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    birthDate: ").append(toIndentedString(birthDate)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    point: ").append(toIndentedString(point)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    note: ").append(toIndentedString(note)).append("\n");
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

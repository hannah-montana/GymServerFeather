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
 * CustomerDashboard
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "CustomerDashboard")
public class CustomerDashboard {

    @Id
    @JsonProperty("_id")
    private String _id = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("point")
    private Integer point = null;

    @JsonProperty("calorie")
    private Integer calorie = null;

    @JsonProperty("duration")
    private Integer duration = null;

    @JsonProperty("numEx")
    private Integer numEx = null;

    public CustomerDashboard id(String id) {
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


    public CustomerDashboard point(Integer point) {
        this.point = point;
        return this;
    }

    /**
     * Get point
     * @return point
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }


    public CustomerDashboard duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Get duration
     * @return duration
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public CustomerDashboard calorie(Integer calorie) {
        this.calorie = calorie;
        return this;
    }

    /**
     * Get duration
     * @return duration
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }

    public CustomerDashboard numEx(Integer numEx) {
        this.numEx = numEx;
        return this;
    }

    /**
     * Get number of Exercises
     * @return number of Exercises
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNumEx() {
        return numEx;
    }

    public void setNumEx(Integer numEx) {
        this.numEx = numEx;
    }

}

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
 * CurrentCustomer
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "CurrentCustomer")
public class CurrentCustomer {

    @Id
    @JsonProperty("_id")
    private String _id = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("point")
    private Integer point = null;

    @JsonProperty("calorie")
    private Integer calorie = null;

    @JsonProperty("pracDuration")
    private Integer pracDuration = null;

    @JsonProperty("numEx")
    private Integer numEx = null;

    @JsonProperty("numExEasy")
    private Integer numExEasy = null;

    @JsonProperty("numExMedium")
    private Integer numExMedium = null;

    @JsonProperty("numExDifficult")
    private Integer numExDifficult = null;

    public CurrentCustomer id(String id) {
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


    public CurrentCustomer point(Integer point) {
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


    public CurrentCustomer pracDuration(Integer pracDuration) {
        this.pracDuration = pracDuration;
        return this;
    }

    /**
     * Get duration
     * @return duration
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getPracDuration() {
        return pracDuration;
    }

    public void setPracDuration(Integer pracDuration) {
        this.pracDuration = pracDuration;
    }


    public CurrentCustomer calorie(Integer calorie) {
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

    public CurrentCustomer numEx(Integer numEx) {
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


    public CurrentCustomer numExEasy(Integer numExEasy) {
        this.numExEasy = numExEasy;
        return this;
    }

    /**
     * Get number of Exercises Easy
     * @return number of Exercises Easy
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNumExEasy() {
        return numExEasy;
    }

    public void setNumExEasy(Integer numExEasy) {
        this.numExEasy = numExEasy;
    }


    public CurrentCustomer numExMedium(Integer numExMedium) {
        this.numExMedium = numExMedium;
        return this;
    }

    /**
     * Get number of Exercises Medium
     * @return number of Exercises Medium
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNumExMedium() {
        return numExMedium;
    }

    public void setNumExMedium(Integer numExMedium) {
        this.numExMedium = numExMedium;
    }


    public CurrentCustomer numExDifficult(Integer numExDifficult) {
        this.numExDifficult = numExDifficult;
        return this;
    }

    /**
     * Get number of Exercises Difficult
     * @return number of Exercises Difficult
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNumExDifficult() {
        return numExDifficult;
    }

    public void setNumExDifficult(Integer numExDifficult) {
        this.numExDifficult = numExDifficult;
    }
}

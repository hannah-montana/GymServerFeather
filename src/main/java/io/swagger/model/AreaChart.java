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

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Document(collection = "AreaChart")
public class AreaChart {

    @JsonProperty("id")
    private Integer id = null;

    @JsonProperty("calorie")
    private Integer calorie = null;

    @JsonProperty("duration")
    private Integer duration = null;

    @JsonProperty("point")
    private Integer point = null;

    public AreaChart id(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     * @return id
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AreaChart point(Integer point) {
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

    public AreaChart calorie(Integer calorie) {
        this.calorie = calorie;
        return this;
    }

    /**
     * Get calorie
     * @return calorie
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getCalorie() {
        return calorie;
    }

    public void setCalorie(Integer calorie) {
        this.calorie = calorie;
    }


    public AreaChart duration(Integer duration) {
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
}

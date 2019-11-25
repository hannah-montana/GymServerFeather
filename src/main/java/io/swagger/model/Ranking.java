package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;


/**
 * Ranking
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-27T08:20:37.196Z[GMT]")
@Document(collection = "Ranking")
public class Ranking {

    //@JsonProperty("id")
    private String id = null;

    @JsonProperty("firstName")
    private String firstName = null;

    @JsonProperty("lastName")
    private String lastName = null;

    @JsonProperty("point")
    private Integer point = null;

    @JsonProperty("duration")
    private Integer duration = null;

    @JsonProperty("calorie")
    private Integer calorie = null;

    @JsonProperty("rank")
    private Integer rank = null;

    @JsonProperty("badge")
    private String badge = null;

    public Ranking id(String id) {
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


    public Ranking firstName(String firstName) {
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

    public Ranking lastName(String lastName) {
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

    public Ranking point(Integer point) {
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

    public Ranking rank(Integer rank) {
        this.rank = rank;
        return this;
    }

    /**
     * Get rank
     * @return rank
     **/
    @ApiModelProperty(value = "")

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }


    public Ranking duration(Integer duration) {
        this.duration = duration;
        return this;
    }

    /**
     * Get duration
     * @return duration
     **/
    @ApiModelProperty(value = "")

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }


    public Ranking calorie(Integer calorie) {
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



    public Ranking badge(String badge) {
        this.badge = badge;
        return this;
    }

    /**
     * Get badge
     * @return badge
     **/
    @ApiModelProperty(value = "")

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }
}

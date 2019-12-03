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
@Document(collection = "BarChart")
public class BarChart {

    private Double noLevel1 = null;
    private Double noLevel2 = null;
    private Double noLevel3 = null;
    private Double noLevel4 = null;
    private Double noLevel5 = null;

    public BarChart noLevel1(Double noLevel1) {
        this.noLevel1 = noLevel1;
        return this;
    }

    /**
     * Get noLevel1
     * @return noLevel1
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Double getNoLevel1() {
        return noLevel1;
    }

    public void setNoLevel1(Double noLevel1) {
        this.noLevel1 = noLevel1;
    }

    public BarChart noLevel2(Double noLevel2) {
        this.noLevel2 = noLevel2;
        return this;
    }

    /**
     * Get noLevel2
     * @return noLevel2
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Double getNoLevel2() {
        return noLevel2;
    }

    public void setNoLevel2(Double noLevel2) {
        this.noLevel2 = noLevel2;
    }

    public BarChart noLevel3(Double noLevel3) {
        this.noLevel3 = noLevel3;
        return this;
    }

    /**
     * Get noLevel2
     * @return noLevel2
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Double getNoLevel3() {
        return noLevel3;
    }

    public void setNoLevel3(Double noLevel3) {
        this.noLevel3 = noLevel3;
    }

    public BarChart noLevel4(Double noLevel4) {
        this.noLevel4 = noLevel4;
        return this;
    }

    /**
     * Get noLevel4
     * @return noLevel4
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Double getNoLevel4() {
        return noLevel4;
    }

    public void setNoLevel4(Double noLevel4) {
        this.noLevel4 = noLevel4;
    }

    public BarChart noLevel5(Double noLevel5) {
        this.noLevel5 = noLevel5;
        return this;
    }

    /**
     * Get noLevel5
     * @return noLevel5
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Double getNoLevel5() {
        return noLevel5;
    }

    public void setNoLevel5(Double noLevel5) {
        this.noLevel5 = noLevel5;
    }
}

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
@Document(collection = "CoachDashboard")
public class CoachDashboard {

    @JsonProperty("noCustomer")
    private Integer noCustomer = null;

    @JsonProperty("noProgram")
    private Integer noProgram = null;

    @JsonProperty("noSession")
    private Integer noSession = null;

    @JsonProperty("noExercise")
    private Integer noExercise = null;

    @JsonProperty("noMale")
    private Integer noMale = null;

    @JsonProperty("noFemale")
    private Integer noFemale = null;
    private Integer noUndefined = null;

    private Integer noEasyEx = null;
    private Integer noMedEx = null;
    private Integer noDifEx = null;
    private Integer noEasySess = null;
    private Integer noMedSess = null;
    private Integer noDifSess = null;

    private Integer noLevel1 = null;
    private Integer noLevel2 = null;
    private Integer noLevel3 = null;
    private Integer noLevel4 = null;
    private Integer noLevel5 = null;


    public CoachDashboard noCustomer(Integer noCustomer) {
        this.noCustomer = noCustomer;
        return this;
    }

    /**
     * Get noCustomer
     * @return noCustomer
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoCustomer() {
        return noCustomer;
    }

    public void setNoCustomer(Integer noCustomer) {
        this.noCustomer = noCustomer;
    }


    public CoachDashboard noProgram(Integer noProgram) {
        this.noProgram = noProgram;
        return this;
    }

    /**
     * Get noProgram
     * @return noProgram
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoProgram() {
        return noProgram;
    }

    public void setNoProgram(Integer noProgram) {
        this.noProgram = noProgram;
    }


    public CoachDashboard noSession(Integer noSession) {
        this.noSession = noSession;
        return this;
    }

    /**
     * Get noSession
     * @return noSession
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoSession() {
        return noSession;
    }

    public void setNoSession(Integer noSession) {
        this.noSession = noSession;
    }


    public CoachDashboard noExercise(Integer noExercise) {
        this.noExercise = noExercise;
        return this;
    }

    /**
     * Get noExercise
     * @return noExercise
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoExercise() {
        return noExercise;
    }

    public void setNoExercise(Integer noExercise) {
        this.noExercise = noExercise;
    }

    public CoachDashboard noMale(Integer noMale) {
        this.noMale = noMale;
        return this;
    }

    /**
     * Get noMale
     * @return noMale
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoMale() {
        return noMale;
    }

    public void setNoMale(Integer noMale) {
        this.noMale = noMale;
    }


    public CoachDashboard noUndefined(Integer noUndefined) {
        this.noUndefined = noUndefined;
        return this;
    }

    /**
     * Get noUndefined
     * @return noUndefined
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoUndefined() {
        return noUndefined;
    }

    public void setNoUndefined(Integer noUndefined) {
        this.noUndefined = noUndefined;
    }

    public CoachDashboard noFemale(Integer noFemale) {
        this.noFemale = noFemale;
        return this;
    }

    /**
     * Get noFemale
     * @return noFemale
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoFemale() {
        return noFemale;
    }

    public void setNoFemale(Integer noFemale) {
        this.noFemale = noFemale;
    }

    public CoachDashboard noEasyEx(Integer noEasyEx) {
        this.noEasyEx = noEasyEx;
        return this;
    }

    /**
     * Get noEasyEx
     * @return noEasyEx
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoEasyEx() {
        return noEasyEx;
    }

    public void setNoEasyEx(Integer noEasyEx) {
        this.noEasyEx = noEasyEx;
    }

    public CoachDashboard noMedEx(Integer noMedEx) {
        this.noMedEx = noMedEx;
        return this;
    }

    /**
     * Get noMedEx
     * @return noMedEx
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoMedEx() {
        return noMedEx;
    }

    public void setNoMedEx(Integer noMedEx) {
        this.noMedEx = noMedEx;
    }


    public CoachDashboard noDifEx(Integer noDifEx) {
        this.noDifEx = noDifEx;
        return this;
    }

    /**
     * Get noDifEx
     * @return noDifEx
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoDifEx() {
        return noDifEx;
    }

    public void setNoDifEx(Integer noDifEx) {
        this.noDifEx = noDifEx;
    }


    public CoachDashboard noEasySess(Integer noEasySess) {
        this.noEasySess = noEasySess;
        return this;
    }

    /**
     * Get noEasySess
     * @return noEasySess
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoEasySess() {
        return noEasySess;
    }

    public void setNoEasySess(Integer noEasySess) {
        this.noEasySess = noEasySess;
    }

    public CoachDashboard noMedSess(Integer noMedSess) {
        this.noMedSess = noMedSess;
        return this;
    }

    /**
     * Get noMedSess
     * @return noMedSess
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoMedSess() {
        return noMedSess;
    }

    public void setNoMedSess(Integer noMedSess) {
        this.noMedSess = noMedSess;
    }


    public CoachDashboard noDifSess(Integer noDifSess) {
        this.noDifSess = noDifSess;
        return this;
    }

    /**
     * Get noDifSess
     * @return noDifSess
     **/
    @ApiModelProperty(required = true, value = "")
    @NotNull

    public Integer getNoDifSess() {
        return noDifSess;
    }

    public void setNoDifSess(Integer noDifSess) {
        this.noDifSess = noDifSess;
    }



}

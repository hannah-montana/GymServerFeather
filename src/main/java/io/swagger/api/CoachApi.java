package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")
@Api(value = "coach", description = "the coach API")
public interface CoachApi {

    @ApiOperation(value = "Get list of top highest-point users", nickname = "getALLRanking",response = Ranking.class, notes = "", tags={ "Coach", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Ranking.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/coach/",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Ranking>> getALLRanking(@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "value", required = false) Integer value);

    @ApiOperation(value = "Get list of top highest-point users", nickname = "getTopRanking",response = Ranking.class, notes = "", tags={ "Coach", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Ranking.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/coach/{userId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<Ranking>> getTopRanking(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("userId") String userId);

    @ApiOperation(value = "Get Coac Dashboard by coachId", nickname = "getCoachDashboard",response = CoachDashboard.class, notes = "", tags={ "Coach", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = CoachDashboard.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/coachDashboard/{coachId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<CoachDashboard> getCoachDashboard(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("coachId") String coachId);

    @ApiOperation(value = "Get Area Chart by coachId", nickname = "getListExerciseOfFocusSession",response = AreaChart.class, notes = "", tags={ "Coach", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AreaChart.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/coachDashboard/areaChart/{sessId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<List<AreaChart>> getListExerciseOfFocusSession(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("sessId") String sessId);

    @ApiOperation(value = "Get Bar Chart by coachId", nickname = "getBarChart",response = BarChart.class, notes = "", tags={ "Coach", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = BarChart.class),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 200, message = "Unexpected error") })
    @RequestMapping(value = "/coachDashboard/barChart/{coachId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<BarChart> getBarChart(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("coachId") String coachId);

}

package io.swagger.api;

import io.swagger.model.*;
import io.swagger.service.CoachDashboardService;
import io.swagger.service.ExerciseSessionService;
import io.swagger.service.UserService;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-10-08T12:40:40.535Z[GMT]")

@Controller
public class CoachApiController implements CoachApi {

    private static final Logger log = LoggerFactory.getLogger(CoachApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public CoachApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    @Autowired
    UserService userService;

    @Autowired
    CoachDashboardService coachDashboardService;

    @Autowired
    ExerciseSessionService exerciseSessionService;

    @Override
    public ResponseEntity<List<Ranking>> getALLRanking(@NotNull @ApiParam(value = "", required = false) @Valid @RequestParam(value = "value", required = false) Integer value) {
        List<Ranking> lstAllRanking = new ArrayList<>();
        lstAllRanking = this.userService.getListAllRanking();
        return new ResponseEntity<List<Ranking>>(lstAllRanking, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Ranking>> getTopRanking(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("userId") String userId) {
        List<Ranking> lstTopRanking = new ArrayList<>();
        lstTopRanking = this.userService.getListTopRanking(userId);
        return new ResponseEntity<List<Ranking>>(lstTopRanking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CoachDashboard> getCoachDashboard(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("coachId") String coachId) {
        CoachDashboard coachDashboard = new CoachDashboard();
        coachDashboard = this.coachDashboardService.getCoachDashboardStatistics(coachId);
        return new ResponseEntity<CoachDashboard>(coachDashboard, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AreaChart>> getListExerciseOfFocusSession(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("sessId") String sessId) {
        List<AreaChart> lstAreaChart = new ArrayList<>();
        lstAreaChart = this.coachDashboardService.getListExerciseOfFocusSession(sessId);
        return new ResponseEntity<List<AreaChart>>(lstAreaChart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BarChart> getBarChart(@ApiParam(value = "Parameter description in CommonMark or HTML.",required=true) @PathVariable("coachId") String coachId) {
        BarChart barChart = new BarChart();
        barChart = this.coachDashboardService.getBarChart(coachId);
        return new ResponseEntity<BarChart>(barChart, HttpStatus.OK);
    }
}

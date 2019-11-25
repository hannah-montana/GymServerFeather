package io.swagger.service;

import io.swagger.model.*;
import org.springframework.data.mongodb.repository.Query;

import java.awt.geom.Area;
import java.util.List;

public interface CoachDashboardService {

    List<User> getListUserByCoachId(String coachId);

    //Coach Dashboard
    CoachDashboard getCoachDashboardStatistics(String coachId);
    List<AreaChart> getListExerciseOfFocusSession(String sessId);
    BarChart getBarChart(String coachId);

}

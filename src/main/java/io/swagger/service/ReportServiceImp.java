package io.swagger.service;

import io.swagger.model.History;
import io.swagger.model.Report;
import io.swagger.repository.HistoryRepository;
import io.swagger.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("reportService")
public class ReportServiceImp implements ReportService {

    @Autowired
    MongoTemplate mongoTemplate;

    private ReportRepository reportRepository;
    private HistoryRepository historyRepository;

    public ReportServiceImp(ReportRepository reportRepository,
                            HistoryRepository historyRepository){
        this.reportRepository = reportRepository;
        this.historyRepository = historyRepository;
    }

    public Integer customerSendReport(Report report){
        /*
        * 1. Save to Report table
        * 2. Processing new session
        * */
        try{
            if(report != null){
                List<Integer> lstId = new ArrayList<>();
                List<Report> lst = reportRepository.findAll();
                if(lst.size() >0){
                    //1. Save to Report table
                    for (Report item: lst)
                    {
                        lstId.add(Integer.parseInt(item.getId()));
                    }
                    int maxId = Collections.max(lstId) + 1;

                    report.setId(String.valueOf(maxId));

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                    String dateString = format.format(new Date());
                    report.setDateAction(dateString);

                    reportRepository.save(report);

                    //2. enable further session
                    Query query = new Query();
                    query.addCriteria(Criteria.where("userId").is(report.getCustId())
                            .andOperator(Criteria.where("processing").is("1")
                            )
                    );

                    int nextOrder = 0;
                    List<History> lstHistory = mongoTemplate.find(query, History.class);
                    if(lstHistory != null){
                        for(History history: lstHistory){
                            history.setValidatedByCoach("1");
                            history.setProcessing("2"); //done
                            nextOrder = history.getOrder();
                            historyRepository.save(history);
                        }
                    }
                    nextOrder++;

                    Query queryFurther = new Query();
                    queryFurther.addCriteria(Criteria.where("userId").is(report.getCustId())
                            .andOperator(Criteria.where("order").is(nextOrder)
                                    .andOperator(Criteria.where("processing").is("0"))
                            )
                    );
                    List<History> lstFurtherHistory = mongoTemplate.find(queryFurther, History.class);
                    if(lstFurtherHistory != null){
                        for(History history: lstFurtherHistory){
                            history.setProcessing("1");
                            historyRepository.save(history);
                        }
                    }
                    return 1;
                }
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }
}

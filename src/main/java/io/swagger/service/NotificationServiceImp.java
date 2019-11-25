package io.swagger.service;

import io.swagger.model.History;
import io.swagger.model.Notification;
import io.swagger.models.auth.In;
import io.swagger.repository.HistoryRepository;
import io.swagger.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImp implements NotificationService{
    @Autowired
    MongoTemplate mongoTemplate;

    private NotificationRepository notificationRepository;
    private HistoryRepository historyRepository;

    public NotificationServiceImp(NotificationRepository notificationRepository,
                                  HistoryRepository historyRepository){
        this.notificationRepository = notificationRepository;
        this.historyRepository = historyRepository;
    }

    public List<Notification> getNotificationByUserId(String id){
        Query query = new Query();

        query.addCriteria(new Criteria().orOperator(Criteria.where("fromUser").is(id),
                                                    Criteria.where("toUser").is(id)));

        Sort sort = new Sort(Sort.Direction.DESC, "temp");
        query.with(sort);

        List<Notification> lst = mongoTemplate.find(query, Notification.class);

        return lst;
    }

    public Integer createNew(Notification noti){
        try{
            if(noti != null){
                //get maxId
                List<Integer> lstId = new ArrayList<>();
                List<Notification> lst = notificationRepository.findAll();
                for (Notification item: lst)
                {
                    lstId.add(Integer.parseInt(item.getId()));
                }
                int maxId = Collections.max(lstId) + 1;

                noti.setId(String.valueOf(maxId));
                noti.setRead("0");
                noti.setTemp(maxId);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = format.format(new Date());
                noti.setDateAction(dateString);

                notificationRepository.save(noti);
                return 1;
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }

    public Integer updateRead(String id){
        try{
            Notification no = notificationRepository.findById(id);
            if(no != null){
                no.setRead("1");
                notificationRepository.save(no);
            }
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    public Integer validateFocusSession(Notification noti){
        try{
            /*
            * 1. insert to table notification
            * 2. update validate in table history
            * */
            if(noti != null){
                //1. insert to table notification
                List<Integer> lstId = new ArrayList<>();
                List<Notification> lst = notificationRepository.findAll();
                for (Notification item: lst)
                {
                    lstId.add(Integer.parseInt(item.getId()));
                }
                int maxId = Collections.max(lstId) + 1;

                noti.setId(String.valueOf(maxId));
                noti.setRead("0");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = format.format(new Date());
                noti.setDateAction(dateString);

                notificationRepository.save(noti);

                //2. update validate in table history
                Query query = new Query();
                query.addCriteria(Criteria.where("userId").is(noti.getFromUser())
                        .andOperator(Criteria.where("sessId").is(noti.getFocusSessionId())
                            .andOperator(Criteria.where("processing").is("1")
                                .andOperator(Criteria.where("sendValidateFS").is("0"))
                        )));

                List<History> lstHistory = mongoTemplate.find(query, History.class);
                if(lstHistory != null){
                    for(History history: lstHistory){
                        history.setSendValidateFS("1");
                        historyRepository.save(history);
                    }
                }
                return 1;
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }

    public Integer checkSendValidateFocusSession(String focusSessionId, String fromUser){
        try{
            Query query = new Query();
            query.addCriteria(Criteria.where("userId").is(fromUser)
                .andOperator(Criteria.where("sessId").is(focusSessionId)
                        .andOperator(Criteria.where("processing").is("1")
                                .andOperator(Criteria.where("focusSession").is(1)
                                        //.andOperator(Criteria.where("sendValidateFS").is("1")
                                                //.andOperator(Criteria.where("validatedByCoach").is("0"))
                                        //)
                                )
                        )
                )
            );
            List<History> lstHistory = mongoTemplate.find(query, History.class);
            if(lstHistory != null){
                if(lstHistory.get(0).getValidatedByCoach().equals("1"))
                    return 2; //coach already validate

                //if not finish -> return 3
                for(History his: lstHistory){
                    if(his.getPraticalDuration() == 0)
                        return 3; //haven't finish
                }
                for(History his: lstHistory){
                    if(his.getSendValidateFS().equals("1"))
                        return 4;  //already sent request
                }
                return 1; //proceed send request validation
            }
        }catch (Exception e){
            return 0;
        }
        return 0;
    }

    public Integer responseFocusSession(Notification noti){
        /*
        * 1. update Notification table
        * 2. update History table
        * 3. enable further session
        * 4. send notification to customer
        * */
        try{
            Notification notification = notificationRepository.findById(noti.getId());
            if(notification != null){
                //1. update Notification table
                notification.setValidatedFromCoach("1");
                notificationRepository.save(notification);

                //2. update History table
                Query query = new Query();
                query.addCriteria(Criteria.where("userId").is(noti.getFromUser())
                        .andOperator(Criteria.where("sessId").is(noti.getFocusSessionId())
                                .andOperator(Criteria.where("processing").is("1")
                                        .andOperator(Criteria.where("focusSession").is(1)
                                                .andOperator(Criteria.where("sendValidateFS").is("1")
                                                        .andOperator(Criteria.where("validatedByCoach").is("0"))
                                                )
                                        )
                                )
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

                //3. enable further session
                Query queryFurther = new Query();
                queryFurther.addCriteria(Criteria.where("userId").is(noti.getFromUser())
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

                //4. send notification to customer
                Notification notification1 = new Notification();
                notification1.setNotifyContent("Attention! Your FOCUS SESSION has been validated.");
                notification1.setFromUser(noti.getToUser());
                notification1.setToUser(noti.getFromUser());
                notification1.setFocusSessionId(noti.getFocusSessionId());
                notification1.setValidatedFromCoach("1");
                notification1.setValidatedFromCustomer("1");
                createNew(notification1);

                return 1;
            }
        }catch (Exception e)
        {
            return 0;
        }
        return 0;
    }
}

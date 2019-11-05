package io.swagger.service;

import io.swagger.model.Notification;
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

    public NotificationServiceImp(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getNotificationByUserId(String id){
        //List<Notification> test = notificationRepository.findAll();

        Query query = new Query();

        //query.addCriteria(Criteria.where("toUser").is(id).orOperator(Criteria.where("fromUser").is(id)));
        query.addCriteria(new Criteria().orOperator(Criteria.where("fromUser").is(id),
                                                    Criteria.where("toUser").is(id)));

        Sort sort = new Sort(Sort.Direction.DESC, "dateAction");
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
}

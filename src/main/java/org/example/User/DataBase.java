package org.example.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

public class DataBase {

    public static void saveInDB(HashMap<String, String> answers, SendMessage message) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        MyDAO entityDao = new MyDAO(sessionFactory);


        User entity = new User();
        entity.setChatId(message.getChatId());
        entity.setEmotion(answers.get("emotion"));
        entity.setIntensity(answers.get("intencity"));
        entity.setQuestion3(answers.get("question3"));
        entity.setQuestion4(answers.get("question4"));
        entity.setQuestion5(answers.get("question5"));
        entity.setQuestion6(answers.get("question6"));
        entity.setAccordance(answers.get("accordance"));
        entity.setEffectivness(answers.get("effectivness"));

        entityDao.saveOrUpdate(entity);

//        Long entityId = 1L;
//        User retrievedEntity = entityDao.findById(entityId);

        System.out.println("VZEEEEEE");

        sessionFactory.close();
    }

}
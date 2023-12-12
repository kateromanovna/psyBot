package org.example.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.HashMap;

// Эти методы все могут быть вызваны в любом классе по сути, но лучше создать отдельный класс
//  с методами по работе с обьектами их созданием и сохранением
public class DataBase {

    public static void saveInDB(HashMap<String, String> answers, SendMessage message) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        MyDAO entityDao = new MyDAO(sessionFactory);


        User entity = new User();
        entity.setChatId(message.getChatId());
        entity.setEmotion(answers.get("emotion"));
        entity.setIntensity(answers.get("intencity"));

        entityDao.saveOrUpdate(entity);

//        Long entityId = 1L;
//        User retrievedEntity = entityDao.findById(entityId);

        System.out.println("VZEEEEEE");

        sessionFactory.close();
    }

}
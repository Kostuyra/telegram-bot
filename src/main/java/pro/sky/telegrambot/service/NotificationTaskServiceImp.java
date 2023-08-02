package pro.sky.telegrambot.service;


import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class NotificationTaskServiceImp implements NotificationTaskService{

   private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskServiceImp(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    @Override
    public void save(NotificationTask task) {
notificationTaskRepository.save(task);
    }

    @Override
    public void delete(NotificationTask task) {
notificationTaskRepository.delete(task);
    }

    @Override
    public List<NotificationTask> findAllByNotificationDateTime(LocalDateTime notificationDateTime) {
        return notificationTaskRepository.findAllByNotificationDateTime(notificationDateTime);
    }
}

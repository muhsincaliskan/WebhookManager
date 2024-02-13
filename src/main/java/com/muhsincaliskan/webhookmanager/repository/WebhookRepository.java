package com.muhsincaliskan.webhookmanager.repository;

import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface WebhookRepository extends MongoRepository<WebhookEntity, String> {

    void deleteByWebhookName(String name);
    @Query("{ 'scheduleInfo.sendDate' : { $lt: ?0 } }")
    List<WebhookEntity> findAllExpiredWebhooks(LocalDateTime expiryDate);


}

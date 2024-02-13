package com.muhsincaliskan.webhookmanager.service;

import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import com.muhsincaliskan.webhookmanager.repository.WebhookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WebhookCrudService {

    @Autowired
    WebhookRepository webhookRepository;

    public WebhookEntity addWebhook(WebhookEntity webhookEntity) {

        webhookEntity.setEnabled(true);
        return webhookRepository.save(webhookEntity);
    }

    public List<WebhookEntity> getAllWebhooks() {
        return webhookRepository.findAll();
    }

    public Long deleteWebhook(String name) {
        webhookRepository.deleteByWebhookName(name);
        return 1L;
    }

    public WebhookEntity updateWebhook(WebhookEntity webhookEntity) {
        return webhookRepository.save(webhookEntity);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public WebhookEntity updateWebhookDate(String id, LocalDateTime date) {
        WebhookEntity webhook = webhookRepository.findById(id).orElse(null);
        if (webhook == null) {
            return null;
        }
        webhook.getScheduleInfo().setSendDate(date);
        return webhookRepository.save(webhook);
    }

    public WebhookEntity getWebhookById(String id) {
        return webhookRepository.findById(id).orElse(null);
    }

    public List<WebhookEntity> findAllExpiredWebhooks(LocalDateTime expiryDate) {
        return webhookRepository.findAllExpiredWebhooks(expiryDate);
    }

    public void deleteWebhookById(String id) {
        webhookRepository.deleteById(id);
    }
}

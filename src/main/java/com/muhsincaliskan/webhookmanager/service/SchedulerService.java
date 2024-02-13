package com.muhsincaliskan.webhookmanager.service;

import com.muhsincaliskan.webhookmanager.constant.RepeatType;
import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@EnableAsync
public class SchedulerService {
    @Autowired
    private WebhookActionService webhookActionService;

    @Autowired
    private WebhookCrudService webhookCrudService;

    private static final int DEFAULT_REPEAT_FREQUENCY = 1;
    @Async
    @Scheduled(fixedRate = 10000)
    public void handleScheduledWebhooks() {
        log.info("Checking for expired webhooks");
        LocalDateTime today = LocalDateTime.now();
        List<WebhookEntity> webhooks = webhookCrudService.findAllExpiredWebhooks(today);
        log.info("Found " + webhooks.size() + " expired webhooks");
        webhooks.stream().filter(WebhookEntity::isEnabled).forEach(webhook -> {
            String id = webhook.getId();
            try {
                if (webhook.getScheduleInfo().isRepeatable()) {
                    handleReccuringWebhook(webhook);
                } else {
                    webhookActionService.sendWebhookMessage(id);
                    webhookCrudService.deleteWebhookById(id);
                }
            } catch (Exception e) {
                log.error("Error occurred while sending webhook message for id: " + id, e);
            }

        });
    }

    private void handleReccuringWebhook(WebhookEntity webhook) {
        log.info("Handling recurring webhook: " + webhook.getWebhookName());
        String id = String.valueOf(webhook.get_id());

        LocalDateTime webhookDate = webhook.getScheduleInfo().getSendDate();
        RepeatType repeatType = webhook.getScheduleInfo().getRepeatType();

        if (isBeforeOrEqual(webhookDate, LocalDateTime.now())) {
            webhookActionService.sendWebhookMessage(id);
        }

        LocalDateTime calculatedDate = calculateNextDate(webhookDate, repeatType, webhook.getScheduleInfo().getRepeatFrequency());

        if (calculatedDate != null) {
            WebhookEntity updatedEntity = webhookCrudService.updateWebhookDate(id, calculatedDate);
            log.info("Updated webhook date for id: " + id + " to " + updatedEntity.getScheduleInfo().getSendDate());
        }

    }

    private LocalDateTime calculateNextDate(LocalDateTime webhookDate, RepeatType repeatType, int repeatFrequency) {
        log.info("Calculating next date for webhook");
        int frequency = repeatFrequency == 0 ? DEFAULT_REPEAT_FREQUENCY : repeatFrequency;

        return switch (repeatType) {
            case MINUTELY -> webhookDate.plusMinutes(frequency);
            case DAILY -> webhookDate.plusDays(frequency);
            case WEEKLY -> webhookDate.plusWeeks(frequency);
            case MONTHLY -> webhookDate.plusMonths(frequency);
            case HOURLY -> webhookDate.plusHours(frequency);
            default -> null;
        };
    }
    private boolean isBeforeOrEqual(LocalDateTime first, LocalDateTime second) {
        return first.isBefore(second) || first.isEqual(second);
    }
}

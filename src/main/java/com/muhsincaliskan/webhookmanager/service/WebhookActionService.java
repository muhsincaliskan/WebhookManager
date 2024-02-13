package com.muhsincaliskan.webhookmanager.service;

import com.muhsincaliskan.webhookmanager.constant.WebhookChannel;
import com.muhsincaliskan.webhookmanager.feign.DiscordWebhookClient;
import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebhookActionService {

    @Autowired
    WebhookCrudService webhookCrudService;

    @Autowired
    DiscordWebhookClient discordWebhookClient;

    public void sendWebhookMessage(String id) {
        log.info("Sending webhook message for id: " + id);
        WebhookEntity webhookEntity = webhookCrudService.getWebhookById(id);

        if (webhookEntity == null) {
            throw new IllegalArgumentException("Webhook not found");
        }

        if (webhookEntity.getWebhookChannelType() == WebhookChannel.DISCORD) {
            sendDiscordWebhookMessage(webhookEntity);
        } else {
            throw new IllegalArgumentException("Webhook channel not supported");
        }


    }

    public void sendDiscordWebhookMessage(WebhookEntity webhookEntity) {


        // split the webhook url to get the webhook id and token
        String[] split = webhookEntity.getWebhookUrl().split("/");
        String webhookToken = split[split.length - 1];
        String webhookId = split[split.length - 2];

        discordWebhookClient.sendDiscordWebhookMessage(webhookId, webhookToken, webhookEntity.getContent(), webhookEntity.getWebhookName(), webhookEntity.getAvatarUrl());

    }

}

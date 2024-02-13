package com.muhsincaliskan.webhookmanager.controller;

import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import com.muhsincaliskan.webhookmanager.service.WebhookActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sponge/api/v1/webhook")
public class WebhookActionController {
    @Autowired
    WebhookActionService webhookActionService;

    @PostMapping(value = "/sendWebhookMessage")
    public ResponseEntity<Void> sendWebhookMessage(@RequestParam String id) {
        webhookActionService.sendWebhookMessage(id);
        return ResponseEntity.ok().build();
    }

    //@PostMapping(value = "/sendDiscordWebhookMessage")
    public ResponseEntity<Void> sendDiscordWebhookMessage(@RequestParam String id) {
        WebhookEntity wwebhookEntity = new WebhookEntity();

        webhookActionService.sendDiscordWebhookMessage(wwebhookEntity);
        return ResponseEntity.ok().build();
    }

}

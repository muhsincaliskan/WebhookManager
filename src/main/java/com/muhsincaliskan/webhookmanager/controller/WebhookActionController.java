package com.muhsincaliskan.webhookmanager.controller;

import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import com.muhsincaliskan.webhookmanager.service.WebhookActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sponge/api/v1/webhook")
public class WebhookActionController {
    private final WebhookActionService webhookActionService;
    @Autowired
    WebhookActionController(WebhookActionService webhookActionService) {
        this.webhookActionService = webhookActionService;
    }
    @PostMapping(value = "/sendWebhookMessage")
    public ResponseEntity<String> sendWebhookMessage(@RequestParam String id) {
        webhookActionService.sendWebhookMessage(id);
        return ResponseEntity.ok("Webhook message sent successfully");
    }

    @PostMapping(value = "/sendWebhookMessageDirectly")
    public ResponseEntity<String> sendWebhookMessageDirectly(@RequestBody WebhookEntity webhookEntity) {
        webhookActionService.sendDiscordWebhookMessage(webhookEntity);
        return ResponseEntity.ok("Webhook message sent successfully");
    }

}

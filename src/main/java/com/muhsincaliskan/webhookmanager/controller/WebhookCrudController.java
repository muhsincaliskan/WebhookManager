package com.muhsincaliskan.webhookmanager.controller;

import com.muhsincaliskan.webhookmanager.modal.WebhookEntity;
import com.muhsincaliskan.webhookmanager.service.WebhookCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/sponge/api/v1/webhook")
public class WebhookCrudController {

    @Autowired
    WebhookCrudService webhookCrudService;

    @PostMapping(value = "/addWebhook")
    public ResponseEntity<WebhookEntity> addWebhook(@RequestBody WebhookEntity webhookEntity) {
        return ResponseEntity.ok(webhookCrudService.addWebhook(webhookEntity));
    }

    @GetMapping(value = "/getWebhookList")
    public ResponseEntity<List<WebhookEntity>> getWebhookList() {
        return Optional.of(webhookCrudService.getAllWebhooks())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/getWebhook/{webhookId}")
    public ResponseEntity<WebhookEntity> getWebhook(@PathVariable String webhookId) {
        return Optional.of(webhookCrudService.getWebhookById(webhookId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/deleteWebhook")
    public ResponseEntity<Long> deleteWebhook(@RequestParam String webhookName) {
        return ResponseEntity.ok(webhookCrudService.deleteWebhook(webhookName));
    }

    @PutMapping("/updateWebhook")
    public ResponseEntity<WebhookEntity> updateWebhook(@RequestBody WebhookEntity webhookEntity) {
        return ResponseEntity.ok(webhookCrudService.updateWebhook(webhookEntity));
    }

}

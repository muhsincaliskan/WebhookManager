package com.muhsincaliskan.webhookmanager.feign;

import com.muhsincaliskan.webhookmanager.config.FeignSupportConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;

@FeignClient(name = "discord-webhook-client", url = "https://discord.com/api/webhooks", configuration = FeignSupportConfig.class)
public interface DiscordWebhookClient {
    @PostMapping(value = "/{webhookId}/{webhookToken}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    void sendDiscordWebhookMessage(@PathVariable("webhookId") String webhookId,
                                   @PathVariable("webhookToken") String webhookToken,
                                   @RequestPart("content") String content,
                                   @RequestPart(value = "username", required = false) String username,
                                   @RequestPart(value = "avatar_url", required = false) String avatarUrl);

}

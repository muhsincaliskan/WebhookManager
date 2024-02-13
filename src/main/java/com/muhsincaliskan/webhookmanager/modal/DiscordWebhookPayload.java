package com.muhsincaliskan.webhookmanager.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiscordWebhookPayload {
    /*
     * basic payload for discord webhook
     * */
    private String content;
    private String username;
    private String avatar_url;


}

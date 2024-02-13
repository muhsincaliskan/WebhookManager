package com.muhsincaliskan.webhookmanager.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum WebhookChannel {

    DISCORD("DISCORD"),
    SLACK("SLACK"),
    GITHUB("GITHUB");

    WebhookChannel(String channelType) {
    }
}

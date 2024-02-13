package com.muhsincaliskan.webhookmanager.modal;

import com.muhsincaliskan.webhookmanager.constant.WebhookChannel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "webhooks")
public class WebhookEntity {

    @Id
    private ObjectId _id;
    private String webhookName;
    private String content;
    private String webhookUrl;
    private boolean isEnabled;
    private WebhookChannel webhookChannelType;
    private String channelId;
    private String guildId;
    private String avatarUrl;
    private ScheduleInfo scheduleInfo;

    public String getId() {
        return String.valueOf(_id);
    }

}

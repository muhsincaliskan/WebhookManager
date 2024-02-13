package com.muhsincaliskan.webhookmanager.modal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.muhsincaliskan.webhookmanager.constant.RepeatType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleInfo {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime sendDate;
    @JsonProperty("isRepeatable")
    private boolean isRepeatable;
    private RepeatType repeatType;
    private int repeatFrequency;
}

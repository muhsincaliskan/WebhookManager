package com.muhsincaliskan.webhookmanager.constant;

public enum RepeatType {

    MINUTELY("MINUTELY"),
    HOURLY("HOURLY"),
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY"),
    YEARLY("YEARLY");


    RepeatType(String recurringType) {
    }

}

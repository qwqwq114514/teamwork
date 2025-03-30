package com.example.shorthandtime;

public class Schedule extends Item {
    private long triggerTime;

    private Period period;


    public Schedule(long createdTime, long lastUpdatedTime, String content, long triggerTime, Period period) {
        super(createdTime, lastUpdatedTime, content);
        this.triggerTime = triggerTime;
        this.period = period;
    }

    public long getTriggerTime() {
        return triggerTime;
    }

    public String getPeriod() {
        return period.toString();
    }
}
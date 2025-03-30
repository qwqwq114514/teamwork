package com.example.shorthandtime;

public class Item {
    private long createdTime;

    private long lastUpdatedTime;

    private String content;

    Item(long createdTime, long lastUpdatedTime, String content) {
        this.createdTime = createdTime;
        this.lastUpdatedTime = lastUpdatedTime;
        this.content = content;
    }

    long getCreatedTime() {
        return createdTime;
    }

    long getLastUpdateTime() {
        return lastUpdatedTime;
    }

    String getContent() {
        return content;
    }
}

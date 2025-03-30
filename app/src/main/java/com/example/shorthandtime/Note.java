package com.example.shorthandtime;

public class Note extends Item {

    private Tag tag;

    public Note(long createdTime, long lastUpdatedTime, String content, Tag tag) {
        super(createdTime, lastUpdatedTime, content);
        this.tag = tag;
    }

    String getTag() {
        return tag.getTag();
    }
}
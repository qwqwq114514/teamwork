package com.example.shorthandtime;

public enum Period {
    none, month, week, day;

    public String getPeriod() {
        return this.toString();
    }
}

package com.example.shorthandtime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class ScheduleTest {
    private Schedule schedule;
    private long createdTime;
    private long lastUpdatedTime;
    private String content;
    private long triggerTime;
    private Period period;

    @Before
    public void setUp() {
        createdTime = System.currentTimeMillis();
        lastUpdatedTime = System.currentTimeMillis();
        content = "Test Schedule Content";
        triggerTime = System.currentTimeMillis() + 100000; // 未来时间
        period = Period.day; // 假设 Period 是一个枚举，包含 DAILY, WEEKLY 等
        schedule = new Schedule(createdTime, lastUpdatedTime, content, triggerTime, period);
    }
    @Test
    public void getCreatedTime() {
        assertEquals(createdTime, schedule.getCreatedTime());
    }

    @Test
    public void getLastUpdateTime() {
        assertEquals(lastUpdatedTime, schedule.getLastUpdateTime());
    }

    @Test
    public void getContent() {
        assertEquals(content, schedule.getContent());
    }

    @Test
    public void getTriggerTime() {
        assertEquals(triggerTime, schedule.getTriggerTime());
    }

    @Test
    public void getPeriod() {
        Schedule weeklySchedule = new Schedule(createdTime, lastUpdatedTime, content, triggerTime, Period.week);
        assertEquals(Period.week.toString(), weeklySchedule.getPeriod());
    }

}
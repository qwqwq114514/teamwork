package com.example.shorthandtime;

import android.database.Cursor;

import java.util.List;

public class ScheduleController extends Controller{

    List<Schedule> scheduleList;

    public List<Schedule> getScheduleList() {
        updateList();
        return scheduleList;
    }

    public void updateSchedule(Schedule schedule) {
        db.execSQL("UPDATE SCHEDULE SET created_time = ?, last_updated_time = ?, content = ?, trigger_time = ?, period = ?",
                new String[]{String.valueOf(schedule.getCreatedTime()), String.valueOf(schedule.getLastUpdateTime()), schedule.getContent(), String.valueOf(schedule.getTriggerTime()), schedule.getPeriod()});
    }

    public Schedule createSchedule() {
        long time = System.currentTimeMillis();
        Schedule schedule = new Schedule(time, time, "", time + Constant.dayTime, Period.none);
        db.execSQL("INSERT INTO SCHEDULE VALUES(?, ?, ?, ?, ?)",
                new String[]{String.valueOf(schedule.getCreatedTime()), String.valueOf(schedule.getLastUpdateTime()), schedule.getContent(), String.valueOf(schedule.getTriggerTime()), schedule.getPeriod()});
        return schedule;
    }

    public void deleteSchedule(Schedule schedule) {
        db.execSQL("DELETE FROM SCHEDULE WHERE created_time = ?", new String[]{String.valueOf(schedule.getCreatedTime())});
    }

    @Override
    void updateList() {
        scheduleList.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM SCHEDULE", null);
        while (cursor.moveToNext()) {
            long createdTime = cursor.getLong(cursor.getColumnIndexOrThrow("created_time"));
            long lastUpdatedTime = cursor.getLong(cursor.getColumnIndexOrThrow("last_updated_time"));
            String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
            long triggerTime = cursor.getLong(cursor.getColumnIndexOrThrow("trigger_time"));
            Period period = Period.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("period")));
            scheduleList.add(new Schedule(createdTime, lastUpdatedTime, content, triggerTime, period));
        }
        cursor.close();
    }
}

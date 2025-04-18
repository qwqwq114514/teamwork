package com.example.shorthandtime;

import static org.junit.Assert.*;

import org.junit.Test;

public class PeriodTest {

    @Test
    public void testEnumValues() {
        // 验证枚举值的数量和顺序
        Period[] values = Period.values();
        assertEquals(4, values.length);
        assertEquals(Period.none, values[0]);
        assertEquals(Period.month, values[1]);
        assertEquals(Period.week, values[2]);
        assertEquals(Period.day, values[3]);
    }

    @Test
    public void testToString() {
        // 测试toString方法(虽然getPeriod调用了toString，但最好也单独测试)
        assertEquals("none", Period.none.toString());
        assertEquals("month", Period.month.toString());
        assertEquals("week", Period.week.toString());
        assertEquals("day", Period.day.toString());
    }

    @Test
    public void getPeriod() {
        // 测试getPeriod方法返回正确的字符串表示
        assertEquals("none", Period.none.getPeriod());
        assertEquals("month", Period.month.getPeriod());
        assertEquals("week", Period.week.getPeriod());
        assertEquals("day", Period.day.getPeriod());
    }

    @Test
    public void testValueOf() {
        assertEquals(Period.none, Period.valueOf("none"));
        assertEquals(Period.month, Period.valueOf("month"));
        assertEquals(Period.week, Period.valueOf("week"));
        assertEquals(Period.day, Period.valueOf("day"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValueOf() {
        // 测试传入无效枚举名称时抛出异常
        Period.valueOf("invalid");
    }
}
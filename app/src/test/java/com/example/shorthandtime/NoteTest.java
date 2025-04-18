package com.example.shorthandtime;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NoteTest {
    private long testTime;
    private Note noteWithImportantTag;
    private Note noteWithTemporaryTag;

    @Before
    public void setUp() {
        testTime = System.currentTimeMillis();
        noteWithImportantTag = new Note(testTime, testTime, "紧急任务", Tag.important);
        noteWithTemporaryTag = new Note(testTime, testTime, "临时记录", Tag.temporary);
    }

    //=== 基础功能测试 ===//
    @Test
    public void shouldStoreContentCorrectly() {
        assertEquals("紧急任务", noteWithImportantTag.getContent());
        assertEquals("临时记录", noteWithTemporaryTag.getContent());
    }

    @Test
    public void shouldReturnTagAsString() {
        // 测试getTag()返回的字符串值
        assertEquals("important", noteWithImportantTag.getTag());
        assertEquals("temporary", noteWithTemporaryTag.getTag());
    }

    //=== 边界测试 ===//
    @Test
    public void shouldHandleEmptyContent() {
        Note emptyNote = new Note(testTime, testTime, "", Tag.temporary);
        assertEquals("", emptyNote.getContent());
    }

    @Test
    public void shouldHandleLongContent() {
        String longContent = "a".repeat(1000);
        Note longNote = new Note(testTime, testTime, longContent, Tag.important);
        assertEquals(1000, longNote.getContent().length());
    }

    //=== 时间戳测试 ===//
    @Test
    public void shouldStoreTimestamps() {
        long createTime = 123456789L;
        long updateTime = 987654321L;
        Note timedNote = new Note(createTime, updateTime, "测试", Tag.temporary);

        assertEquals(createTime, timedNote.getCreatedTime());
        assertEquals(updateTime, timedNote.getLastUpdateTime());
    }

    //=== 异常测试 ===//
    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNullContent() {
        new Note(testTime, testTime, null, Tag.important);
    }

    // 如果Tag不允许为null，添加此测试
    @Test(expected = IllegalArgumentException.class)
    public void shouldRejectNullTag() {
        new Note(testTime, testTime, "内容", null);
    }
}

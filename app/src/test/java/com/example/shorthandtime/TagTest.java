package com.example.shorthandtime;

import org.junit.Test;
import static org.junit.Assert.*;

public class TagTest {

    @Test
    public void shouldReturnCorrectTagNames() {
        // 验证枚举值转字符串
        assertEquals("temporary", Tag.temporary.toString());
        assertEquals("important", Tag.important.toString());

        // 验证getTag()方法
        assertEquals("temporary", Tag.temporary.getTag());
        assertEquals("important", Tag.important.getTag());
    }

    @Test
    public void shouldContainExactlyTwoTags() {
        // 确保枚举数量正确
        assertEquals(2, Tag.values().length);
        assertArrayEquals(new Tag[]{Tag.temporary, Tag.important}, Tag.values());
    }
}

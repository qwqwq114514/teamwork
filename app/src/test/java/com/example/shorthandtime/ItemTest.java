package com.example.shorthandtime;

import org.junit.Test;
import static org.junit.Assert.*;

public class ItemTest {

    // 测试数据
    private static final long TEST_CREATED_TIME = 123456789L;
    private static final long TEST_UPDATED_TIME = 987654321L;
    private static final String TEST_CONTENT = "测试内容";

    @Test
    public void testConstructorAndGetters() {
        // 1. 准备测试对象
        Item item = new Item(TEST_CREATED_TIME, TEST_UPDATED_TIME, TEST_CONTENT);

        // 2. 验证各字段值
        assertEquals("创建时间不匹配",
                TEST_CREATED_TIME, item.getCreatedTime());

        assertEquals("最后更新时间不匹配",
                TEST_UPDATED_TIME, item.getLastUpdateTime());

        assertEquals("内容不匹配",
                TEST_CONTENT, item.getContent());
    }

    @Test
    public void testEdgeCases() {
        // 测试空内容
        Item emptyItem = new Item(0, 0, "");
        assertEquals("空内容处理错误", "", emptyItem.getContent());

        // 测试最小时间值
        Item minTimeItem = new Item(Long.MIN_VALUE, Long.MIN_VALUE, "min");
        assertEquals(Long.MIN_VALUE, minTimeItem.getCreatedTime());
    }
}
package com.example.shorthandtime;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(
        manifest = Config.NONE,
        sdk = 28, // 明确指定 API 级别
        shadows = {}
        )
public class MyDBOpenHelperTest {
    private MyDBOpenHelper dbHelper;
    private SQLiteDatabase db;

    @Before
    public void setUp() {
        // 使用内存数据库加速测试
        dbHelper = new MyDBOpenHelper(
                RuntimeEnvironment.getApplication(),
                null,  // 内存数据库
                null,
                1
        );
        db = dbHelper.getWritableDatabase();
    }

    @Test
    public void testDatabaseCreation() {
        assertTrue(db.isOpen());
        assertEquals(1, db.getVersion());
    }

    @Test
    public void testNoteTableSchema() {
        // 验证NOTE表结构
        Cursor cursor = db.rawQuery("PRAGMA table_info(NOTE)", null);
        assertNotNull(cursor);

        String[] expectedColumns = {"created_time", "last_updated_time", "content", "tag"};
        String[] expectedTypes = {"INTEGER", "INTEGER", "TEXT", "TEXT"};

        assertEquals(4, cursor.getCount()); // 4列

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));

            boolean found = false;
            for (int i = 0; i < expectedColumns.length; i++) {
                if (expectedColumns[i].equals(name)) {
                    assertEquals(expectedTypes[i], type);
                    if (name.equals("created_time")) {
                        assertEquals(1, cursor.getInt(cursor.getColumnIndexOrThrow("pk")));
                    }
                    found = true;
                    break;
                }
            }
            assertTrue("Unexpected column: " + name, found);
        }
        cursor.close();
    }

    @Test
    public void testScheduleTableSchema() {
        // 验证SCHEDULE表结构
        Cursor cursor = db.rawQuery("PRAGMA table_info(SCHEDULE)", null);
        assertNotNull(cursor);

        String[] expectedColumns = {"created_time", "last_updated_time", "content", "trigger_time", "period"};
        String[] expectedTypes = {"INTEGER", "INTEGER", "TEXT", "INTEGER", "TEXT"};

        assertEquals(5, cursor.getCount()); // 5列

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));

            boolean found = false;
            for (int i = 0; i < expectedColumns.length; i++) {
                if (expectedColumns[i].equals(name)) {
                    assertEquals(expectedTypes[i], type);
                    if (name.equals("created_time")) {
                        assertEquals(1, cursor.getInt(cursor.getColumnIndexOrThrow("pk")));
                    }
                    found = true;
                    break;
                }
            }
            assertTrue("Unexpected column: " + name, found);
        }
        cursor.close();
    }

    @Test
    public void testNoteCRUD() {
        // 插入测试数据
        long time = System.currentTimeMillis();
        db.execSQL("INSERT INTO NOTE VALUES(?, ?, ?, ?)",
                new Object[]{time, time, "Test content", "work"});

        // 查询验证
        Cursor cursor = db.rawQuery("SELECT * FROM NOTE WHERE created_time = ?",
                new String[]{String.valueOf(time)});
        assertTrue(cursor.moveToFirst());
        assertEquals("Test content", cursor.getString(cursor.getColumnIndexOrThrow("content")));
        assertEquals("work", cursor.getString(cursor.getColumnIndexOrThrow("tag")));
        cursor.close();

        // 更新测试
        db.execSQL("UPDATE NOTE SET content = ? WHERE created_time = ?",
                new Object[]{"Updated content", time});

        cursor = db.rawQuery("SELECT content FROM NOTE WHERE created_time = ?",
                new String[]{String.valueOf(time)});
        assertTrue(cursor.moveToFirst());
        assertEquals("Updated content", cursor.getString(0));
        cursor.close();

        // 删除测试
        db.execSQL("DELETE FROM NOTE WHERE created_time = ?",
                new Object[]{time});
        cursor = db.rawQuery("SELECT COUNT(*) FROM NOTE WHERE created_time = ?",
                new String[]{String.valueOf(time)});
        assertTrue(cursor.moveToFirst());
        assertEquals(0, cursor.getInt(0));
        cursor.close();
    }

    @After
    public void tearDown() {
        db.close();
        dbHelper.close();
    }
}
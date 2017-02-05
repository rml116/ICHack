package com.example.radu.ichack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Path;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by radu on 04.02.2017.
 */

public class DBHandler extends SQLiteOpenHelper {
  private static final int DATABASE_VERSION = 1;
  private static final String DATABASE_NAME = "habitsAndTasksInfo";
  private static final String TABLE_HABITS = "habits";
  private static final String TABLE_TASKS = "tasks";
  private static final String TABLE_DAY = "day";
  private static final String KEY_HABIT_ID = "habitId";
  private static final String KEY_HABIT_NAME = "habitName";
  private static final String KEY_HABIT_REPEAT = "habitRepeat";
  private static final String KEY_HABIT_TIMESTAMP_H = "habitTimestampH";
  private static final String KEY_HABIT_TIMESTAMP_M = "habitTimestampM";
  private static final String KEY_HABIT_DONE = "habitDone";
  private static final String KEY_TASK_ID = "taskId";
  private static final String KEY_TASK_NAME = "taskName";
  private static final String KEY_TASK_TIMESTAMP_Y = "taskTimestampY";
  private static final String KEY_TASK_TIMESTAMP_M = "taskTimestampM";
  private static final String KEY_TASK_TIMESTAMP_D = "taskTimestampD";
  private static final String KEY_TASK_TIMESTAMP_H = "taskTimestampH";
  private static final String KEY_TASK_TIMESTAMP_MIN = "taskTimestampMin";
  private static final String KEY_DAY_ID = "dayId";
  private static final String KEY_DAY_TIMESTAMP_D = "dayTimestampD";

  public DBHandler(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String CREATE_HABITS_TABLE = "CREATE TABLE " + TABLE_HABITS + "(" +
        KEY_HABIT_ID + " INTEGER PRIMARY KEY," + KEY_HABIT_NAME + " TEXT," +
        KEY_HABIT_REPEAT + " INT," + KEY_HABIT_TIMESTAMP_H + " INT," +
        KEY_HABIT_TIMESTAMP_M + " INT," + KEY_HABIT_DONE + " INT" + ")";
    db.execSQL(CREATE_HABITS_TABLE);

    String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "(" +
        KEY_TASK_ID + " INTEGER PRIMARY KEY," + KEY_TASK_NAME + " TEXT," +
        KEY_TASK_TIMESTAMP_Y + " INT," + KEY_TASK_TIMESTAMP_M + " INT," +
        KEY_TASK_TIMESTAMP_D + " INT," + KEY_TASK_TIMESTAMP_H + " INT," +
        KEY_TASK_TIMESTAMP_MIN + " INT" + ")";
    db.execSQL(CREATE_TASKS_TABLE);

    String CREATE_DAY_TABLE = "CREATE TABLE " + TABLE_DAY + "(" +
        KEY_DAY_ID + " INTEGER PRIMARY KEY," + KEY_DAY_TIMESTAMP_D +
        " INT" + ")";
    db.execSQL(CREATE_DAY_TABLE);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_HABITS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_DAY);
    onCreate(db);
  }

  public void addHabit(Habit habit) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_HABIT_NAME, habit.getName());
    values.put(KEY_HABIT_REPEAT, habit.getRepeatWeek());
    values.put(KEY_HABIT_TIMESTAMP_H, habit.getTimestampH());
    values.put(KEY_HABIT_TIMESTAMP_M, habit.getTimestampM());
    values.put(KEY_HABIT_DONE, (habit.isDone() ? 1 : 0));
    db.insert(TABLE_HABITS, null, values);
    db.close();
  }

  public void addTask(Task task) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_TASK_NAME, task.getName());
    values.put(KEY_TASK_TIMESTAMP_Y, task.getTimestampY());
    values.put(KEY_TASK_TIMESTAMP_M, task.getTimestampM());
    values.put(KEY_TASK_TIMESTAMP_D, task.getTimestampD());
    values.put(KEY_TASK_TIMESTAMP_H, task.getTimestampH());
    values.put(KEY_TASK_TIMESTAMP_MIN, task.getTimestampMin());
    db.insert(TABLE_TASKS, null, values);
    db.close();
  }

  public void addDay(int day) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_DAY_TIMESTAMP_D, day);
    db.insert(TABLE_DAY, null, values);
    db.close();
  }

  public Optional<Habit> getHabit(int id) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_HABITS,
        new String[] {KEY_HABIT_ID,
                      KEY_HABIT_NAME,
                      KEY_HABIT_REPEAT,
                      KEY_HABIT_TIMESTAMP_H,
                      KEY_HABIT_TIMESTAMP_M,
                      KEY_HABIT_DONE},
        KEY_HABIT_ID + "=?",
        new String[] {String.valueOf(id)},
        null, null, null, null);

    if (cursor != null) {
      if (cursor.moveToFirst()) {
        Habit habit = new Habit(Integer.parseInt(cursor.getString(0)),
                                cursor.getString(1),
                                Integer.parseInt(cursor.getString(2)),
                                Integer.parseInt(cursor.getString(3)),
                                Integer.parseInt(cursor.getString(4)),
                                (Integer.parseInt(cursor.getString(5)) == 1 ? true : false));

        return Optional.of(habit);
      }
    }

    return Optional.empty();
  }

  public Optional<Task> getTask(int id) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_TASKS,
        new String[] {KEY_TASK_ID,
                      KEY_TASK_NAME,
                      KEY_TASK_TIMESTAMP_Y,
                      KEY_TASK_TIMESTAMP_M,
                      KEY_TASK_TIMESTAMP_D,
                      KEY_TASK_TIMESTAMP_H,
                      KEY_TASK_TIMESTAMP_MIN},
        KEY_TASK_ID + "=?",
        new String[] {String.valueOf(id)},
        null, null, null, null);

    if (cursor != null) {
      if (cursor.moveToFirst()) {
        Task task = new Task(Integer.parseInt(cursor.getString(0)),
                             cursor.getString(1),
                             Integer.parseInt(cursor.getString(2)),
                             Integer.parseInt(cursor.getString(3)),
                             Integer.parseInt(cursor.getString(4)),
                             Integer.parseInt(cursor.getString(5)),
                             Integer.parseInt(cursor.getString(6)));

        return Optional.of(task);
      }
    }

    return Optional.empty();
  }

  public Optional<Integer> getDay(int id) {
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query(TABLE_DAY,
        new String[] {KEY_DAY_ID,
                      KEY_DAY_TIMESTAMP_D},
        KEY_DAY_ID + "=?",
        new String[] {String.valueOf(id)},
        null, null, null, null);

    if (cursor != null) {
      if (cursor.moveToFirst()) {
        int day = Integer.parseInt(cursor.getString(1));

        return Optional.of(day);
      }
    }

    return Optional.empty();
  }

  public List<Habit> getAllHabits() {
    List<Habit> habits = new ArrayList<>();
    String selectQuery = "SELECT * FROM " + TABLE_HABITS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
      do {
        Habit habit = new Habit(Integer.parseInt(cursor.getString(0)),
                                cursor.getString(1),
                                Integer.parseInt(cursor.getString(2)),
                                Integer.parseInt(cursor.getString(3)),
                                Integer.parseInt(cursor.getString(4)),
                                (Integer.parseInt(cursor.getString(5)) == 1 ? true : false));
        habits.add(habit);
      } while (cursor.moveToNext());
    }

    return habits;
  }

  public List<Task> getAllTasks() {
    List<Task> tasks = new ArrayList<>();
    String selectQuery = "SELECT * FROM " + TABLE_TASKS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
      do {
        Task task = new Task(Integer.parseInt(cursor.getString(0)),
            cursor.getString(1),
            Integer.parseInt(cursor.getString(2)),
            Integer.parseInt(cursor.getString(3)),
            Integer.parseInt(cursor.getString(4)),
            Integer.parseInt(cursor.getString(5)),
            Integer.parseInt(cursor.getString(6)));
        tasks.add(task);
      } while (cursor.moveToNext());
    }

    return tasks;
  }

  public List<Integer> getAllDays() {
    List<Integer> days = new ArrayList<>();
    String selectQuery = "SELECT * FROM " + TABLE_DAY;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);

    if (cursor.moveToFirst()) {
      do {
        int day = Integer.parseInt(cursor.getString(1));
        days.add(day);
      } while (cursor.moveToNext());
    }

    return days;
  }

  public int getHabitsCount() {
    String countQuery = "SELECT * FROM " + TABLE_HABITS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);

    return cursor.getCount();
  }

  public int getTasksCount() {
    String countQuery = "SELECT * FROM " + TABLE_TASKS;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);

    return cursor.getCount();
  }

  public int getDaysCount() {
    String countQuery = "SELECT * FROM " + TABLE_DAY;
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.rawQuery(countQuery, null);

    return cursor.getCount();
  }

  public int updateHabit(Habit habit) {
    if (!getHabit(habit.getId()).isPresent()) {
      return -1;
    }

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_HABIT_NAME, habit.getName());
    values.put(KEY_HABIT_REPEAT, habit.getRepeatWeek());
    values.put(KEY_HABIT_TIMESTAMP_H, habit.getTimestampH());
    values.put(KEY_HABIT_TIMESTAMP_M, habit.getTimestampM());
    values.put(KEY_HABIT_DONE, (habit.isDone() ? 1 : 0));

    return db.update(TABLE_HABITS, values, KEY_HABIT_ID + " = ?",
                     new String[] {String.valueOf(habit.getId())});
  }

  public int updateTask(Task task) {
    if (!getTask(task.getId()).isPresent()) {
      return -1;
    }

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_TASK_NAME, task.getName());
    values.put(KEY_TASK_TIMESTAMP_Y, task.getTimestampY());
    values.put(KEY_TASK_TIMESTAMP_M, task.getTimestampM());
    values.put(KEY_TASK_TIMESTAMP_D, task.getTimestampD());
    values.put(KEY_TASK_TIMESTAMP_H, task.getTimestampH());
    values.put(KEY_TASK_TIMESTAMP_MIN, task.getTimestampMin());

    return db.update(TABLE_TASKS, values, KEY_TASK_ID + " = ?",
                     new String[] {String.valueOf(task.getId())});
  }

  public int updateDay(int day) {
    if (!getDay(1).isPresent()) {
      return -1;
    }

    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put(KEY_DAY_TIMESTAMP_D, day);

    return db.update(TABLE_DAY, values, KEY_DAY_ID + " = ?",
                     new String[] {String.valueOf(1)});
  }

  public void deleteHabit(Habit habit) {
    if (!getHabit(habit.getId()).isPresent()) {
      return;
    }

    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_HABITS, KEY_HABIT_ID + " = ?",
              new String[] {String.valueOf(habit.getId())});
    db.close();
  }

  public void deleteTask(Task task) {
    if (!getTask(task.getId()).isPresent()) {
      return;
    }

    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_TASKS, KEY_TASK_ID + " = ?",
              new String[] {String.valueOf(task.getId())});
    db.close();
  }
}

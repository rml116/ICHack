package com.example.radu.ichack;

/**
 * Created by dmusan on 2/4/2017.
 */

public class Habit {
  private final int id;
  private String name;
  private int repeatWeek;
  private int timestampH;
  private int timestampM;

  public Habit(int id, String name, int repeatWeek, int timestampH, int timestampM) {
    this.id = id;
    this.name = name;
    this.repeatWeek = repeatWeek;
    this.timestampH = timestampH;
    this.timestampM = timestampM;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRepeatWeek(int repeatWeek) {
    this.repeatWeek = repeatWeek;
  }

  public void setTimestampH(int timestampH) {
    this.timestampH = timestampH;
  }

  public void setTimestampM(int timestampM) {
    this.timestampM = timestampM;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getRepeatWeek() {
    return repeatWeek;
  }

  public int getTimestampH() {
    return timestampH;
  }

  public int getTimestampM() {
    return timestampM;
  }

  @Override
  public String toString() {
    return "Habit with id: " + String.valueOf(id) +
           ", name: " + name +
           ", repeating: " + repeatWeek + " times a week" +
           ", reminder at: " + timestampH + ":" + timestampM;
  }
}
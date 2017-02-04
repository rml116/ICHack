package com.example.radu.ichack;

/**
 * Created by dmusan on 2/4/2017.
 */

public class Task {
  private final int id;
  private String name;
  private int timestampY;
  private int timestampM;
  private int timestampD;
  private int timestampH;
  private int timestampMin;

  public Task(int id, String name, int timestampY, int timestampM, int timestampD, int timestampH, int timestampMin) {
    this.id = id;
    this.name = name;
    this.timestampY = timestampY;
    this.timestampM = timestampM;
    this.timestampD = timestampD;
    this.timestampH = timestampH;
    this.timestampMin = timestampMin;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getTimestampY() {
    return timestampY;
  }

  public int getTimestampM() {
    return timestampM;
  }

  public int getTimestampD() {
    return timestampD;
  }

  public int getTimestampH() {
    return timestampH;
  }

  public int getTimestampMin() {
    return timestampMin;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTimestampY(int timestampY) {
    this.timestampY = timestampY;
  }

  public void setTimestampM(int timestampM) {
    this.timestampM = timestampM;
  }

  public void setTimestampD(int timestampD) {
    this.timestampD = timestampD;
  }

  public void setTimestampH(int timestampH) {
    this.timestampH = timestampH;
  }

  public void setTimestampMin(int timestampMin) {
    this.timestampMin = timestampMin;
  }
}
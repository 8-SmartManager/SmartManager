package com.example.muctieutietkiem.muctieu.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Goal implements Serializable {
    private int goalID;
    private int goalThumb;
    private String goalName;
    private LocalDate goalTime;
    private int goalColor;
    private double goalSaved;
    private double goalTarget;
    private String goalNote;


    public Goal(int goalID, int goalThumb, String goalName, LocalDate goalTime, int goalColor, double goalSaved, double goalTarget, String goalNote) {
        this.goalID = goalID;
        this.goalThumb = goalThumb;
        this.goalName = goalName;
        this.goalTime = goalTime;
        this.goalColor = goalColor;
        this.goalSaved = goalSaved;
        this.goalTarget = goalTarget;
        this.goalNote = goalNote;
    }

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public int getGoalThumb() {
        return goalThumb;
    }

    public void setGoalThumb(int goalThumb) {
        this.goalThumb = goalThumb;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public LocalDate getGoalTime() {
        return goalTime;
    }

    public void setGoalTime(LocalDate goalTime) {
        this.goalTime = goalTime;
    }

    public int getGoalColor() {
        return goalColor;
    }

    public void setGoalColor(int goalColor) {
        this.goalColor = goalColor;
    }

    public double getGoalSaved() {
        return goalSaved;
    }

    public void setGoalSaved(double goalSaved) {
        this.goalSaved = goalSaved;
    }
    public double getGoalTarget() {
        return goalTarget;
    }

    public void setGoalTarget(double goalSaved) {
        this.goalTarget = goalTarget;
    }

    public String getGoalNote() {
        return goalNote;
    }
    public void setGoalNote(String goalNote) {
        this.goalNote = goalNote;
    }
}

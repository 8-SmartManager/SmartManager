package com.example.KeHoachMuaSam.model;

public class Task {
    private int taskId;
    private String taskName;
    private double taskPrice;

    public Task(int taskId, String taskName, double taskPrice) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskPrice = taskPrice;
    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public double getTaskPrice() {
        return taskPrice;
    }

    public void setTaskPrice(double taskPrice) {
        this.taskPrice = taskPrice;
    }
}

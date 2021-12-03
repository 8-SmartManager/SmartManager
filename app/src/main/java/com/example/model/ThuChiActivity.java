package com.example.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ThuChiActivity implements Serializable {


    private int activityId;
    private LocalDate activityDate;
    private String activityType;
    private String activityName;
    private String activityAccount;
    private double activityAmount;

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public LocalDate getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(LocalDate activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityAccount() {
        return activityAccount;
    }

    public void setActivityAccount(String activityAccount) {
        this.activityAccount = activityAccount;
    }

    public double getActivityAmount() {
        return activityAmount;
    }

    public void setActivityAmount(double activityAmount) {
        this.activityAmount = activityAmount;
    }



    public ThuChiActivity(int activityId, LocalDate activityDate, String activityType, String activityName, String activityAccount, double activityAmount) {
        this.activityId = activityId;
        this.activityDate = activityDate;
        this.activityType = activityType;
        this.activityName = activityName;
        this.activityAccount = activityAccount;
        this.activityAmount = activityAmount;
    }
}

package com.example.model;

import java.io.Serializable;

public class ThuChiActivity implements Serializable {
    private String activityName;
    private String activityAccount;
    private double activityAmount;

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

    public ThuChiActivity(String activityName, String activityAccount, double activityAmount) {
        this.activityName = activityName;
        this.activityAccount = activityAccount;
        this.activityAmount = activityAmount;
    }
}

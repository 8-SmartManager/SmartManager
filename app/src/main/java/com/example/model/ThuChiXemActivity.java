package com.example.model;

import java.io.Serializable;

public class ThuChiXemActivity implements Serializable {
    private String activityDate;
    private int activityAmountChi;
    private int activityAmountThu;

    public ThuChiXemActivity(String activityDate, int activityAmountChi, int activityAmountThu) {
        this.activityDate = activityDate;
        this.activityAmountChi = activityAmountChi;
        this.activityAmountThu = activityAmountThu;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public int getActivityAmountChi() {
        return activityAmountChi;
    }

    public void setActivityAmountChi( int activityAmountChi) {
        this.activityAmountChi = activityAmountChi;
    }

    public int getActivityAmountThu() {
        return activityAmountThu;
    }

    public void setActivityAmountThu(int activityAmountThu) {
        this.activityAmountThu = activityAmountThu;
    }
}

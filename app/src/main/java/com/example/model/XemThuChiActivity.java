package com.example.model;

import java.io.Serializable;

public class XemThuChiActivity implements Serializable {
    private int activityDate;
    private int activityAmountChi;
    private int activityAmountThu;

    public XemThuChiActivity(int activityDate, int activityAmountChi, int activityAmountThu) {
        this.activityDate = activityDate;
        this.activityAmountChi = activityAmountChi;
        this.activityAmountThu = activityAmountThu;
    }

    public int getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(int activityDate) {
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

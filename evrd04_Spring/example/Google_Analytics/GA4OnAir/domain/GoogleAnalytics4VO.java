package com.google.analytics.GA4OnAir.domain;

public class GoogleAnalytics4VO {

    private String todayVisitors;
    private String allVisitors;
    
    
    public String getTodayVisitors() {
        return todayVisitors;
    }
    
    public void setTodayVisitors(String todayVisitors) {
        this.todayVisitors = todayVisitors;
    }
    
    public String getAllVisitors() {
        return allVisitors;
    }
    
    public void setAllVisitors(String allVisitors) {
        this.allVisitors = allVisitors;
    }

    @Override
    public String toString() {
        return "GoogleAnalytics4VO [todayVisitors=" + todayVisitors + ", allVisitors=" + allVisitors + ", getTodayVisitors()=" + getTodayVisitors()
            + ", getAllVisitors()=" + getAllVisitors() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
            + super.toString() + "]";
    }
}

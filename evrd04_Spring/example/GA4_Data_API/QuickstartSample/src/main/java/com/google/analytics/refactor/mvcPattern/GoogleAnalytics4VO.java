package com.google.analytics.refactor.mvcPattern;

public class GoogleAnalytics4VO {

    // 방문자 수
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


}

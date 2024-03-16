package com.google.analytics3.visitor;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.*;

import java.io.IOException;
import java.util.Arrays;

import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.*;
import java.io.IOException;
import java.util.Arrays;

public class AnalyticsDataFetcher {
    private AnalyticsReporting analyticsReporting;

    public AnalyticsDataFetcher(AnalyticsReporting analyticsReporting) {
        this.analyticsReporting = analyticsReporting;
    }

    public GetReportsResponse getVisitorData(String viewId) throws IOException {
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("7daysAgo");
        dateRange.setEndDate("today");

        Metric sessionsMetric = new Metric()
                .setExpression("ga:sessions")
                .setAlias("sessions");

        Dimension pageTitleDimension = new Dimension()
                .setName("ga:pageTitle");

        ReportRequest request = new ReportRequest()
                .setViewId(viewId)
                .setDateRanges(Arrays.asList(dateRange))
                .setMetrics(Arrays.asList(sessionsMetric))
                .setDimensions(Arrays.asList(pageTitleDimension));

        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(Arrays.asList(request));

        return analyticsReporting.reports().batchGet(getReport).execute();
    }
}


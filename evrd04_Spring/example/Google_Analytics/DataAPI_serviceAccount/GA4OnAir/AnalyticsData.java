package com.google.analytics.GA4OnAir;

import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

public interface AnalyticsData {

    RunReportResponse runReport(RunReportRequest request) throws Exception;
    
}

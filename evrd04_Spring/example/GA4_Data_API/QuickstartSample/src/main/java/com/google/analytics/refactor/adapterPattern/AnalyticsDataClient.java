package com.google.analytics.refactor.adapterPattern;

import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

public interface AnalyticsDataClient {

    RunReportResponse runReport(RunReportRequest request) throws Exception;

}

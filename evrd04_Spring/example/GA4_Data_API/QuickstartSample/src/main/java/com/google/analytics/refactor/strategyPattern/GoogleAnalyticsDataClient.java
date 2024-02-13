package com.google.analytics.refactor.strategyPattern;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

import java.io.IOException;

public class GoogleAnalyticsDataClient implements AnalyticsDataClient {

    private final BetaAnalyticsDataClient client;

    public GoogleAnalyticsDataClient() throws IOException {
        this.client = BetaAnalyticsDataClient.create();
    }


    @Override
    public RunReportResponse runReport(RunReportRequest request) throws Exception {
        return client.runReport(request);
    }
}

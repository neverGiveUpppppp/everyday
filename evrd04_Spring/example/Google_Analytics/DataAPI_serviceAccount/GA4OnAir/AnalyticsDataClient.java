package com.google.analytics.GA4OnAir;

import java.io.IOException;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

@Component
public class AnalyticsDataClient implements AnalyticsData {

    private final BetaAnalyticsDataClient client;
    
    public AnalyticsDataClient() throws IOException {
        this.client = BetaAnalyticsDataClient.create();
    }
    
    
    @Override
    public RunReportResponse runReport(RunReportRequest request) throws Exception {
        return client.runReport(request);
    }

    
}

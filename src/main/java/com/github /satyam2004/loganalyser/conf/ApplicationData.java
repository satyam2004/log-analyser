package com.github.satyam2004.loganalyser.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.log-analyser")
public class ApplicationData {
    private int alertThresholdMs;
    private int tableRowsWriteoffCount;

    public int getAlertThresholdMs() {
        return alertThresholdMs;
    }

    public void setAlertThresholdMs(int alertThresholdMs) {
        this.alertThresholdMs = alertThresholdMs;
    }

    public int getTableRowsWriteoffCount() {
        return tableRowsWriteoffCount;
    }

    public void setTableRowsWriteoffCount(int tableRowsWriteoffCount) {
        this.tableRowsWriteoffCount = tableRowsWriteoffCount;
    }
}

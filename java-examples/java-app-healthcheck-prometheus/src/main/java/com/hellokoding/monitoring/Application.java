package com.hellokoding.monitoring;

import com.github.strengthened.prometheus.healthchecks.HealthChecksCollector;

public class Application {
    public static void main(String[] args) throws Exception {
        HealthChecksCollector healthchecksMetrics = HealthChecksCollector.Builder.of().setGaugeMetricName("hello_koding_health_check").build();
        healthchecksMetrics.addHealthCheck("database", new DbHealthCheck());

        new HealthCheckExporter(healthchecksMetrics).export();
    }
}


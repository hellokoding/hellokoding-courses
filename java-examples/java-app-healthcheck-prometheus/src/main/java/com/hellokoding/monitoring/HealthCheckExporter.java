package com.hellokoding.monitoring;

import com.github.strengthened.prometheus.healthchecks.HealthChecksCollector;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HealthCheckExporter {
    private final HealthChecksCollector healthChecksCollector;

    public HealthCheckExporter(HealthChecksCollector healthChecksCollector) {
        this.healthChecksCollector = healthChecksCollector;
    }

    public void export() throws Exception {
        CollectorRegistry.defaultRegistry.register(this.healthChecksCollector);

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
        DefaultExports.initialize();

        server.start();
        server.join();
    }
}

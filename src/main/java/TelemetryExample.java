import io.micrometer.core.instrument.Gauge;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import java.util.Random;

public class TelemetryExample {
    public static void main(String[] args) throws InterruptedException {
        PrometheusMeterRegistry registry =
                new PrometheusMeterRegistry(io.micrometer.prometheus.PrometheusConfig.DEFAULT);

        Random random = new Random();

        // Generate temperature between 20 and 30
        Gauge.builder("temperature_celsius", () -> 20 + random.nextInt(10))
                .description("Temperature in Celsius")
                .register(registry);

        while (true) {
            System.out.println(registry.scrape());
            Thread.sleep(5000);
        }
    }
}

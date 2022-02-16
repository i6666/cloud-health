package cc.mmail.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServerApplication {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/get")
                .filters(f -> f.addRequestHeader("Hello", "World"))
                .uri("http://httpbin.org:80"))
                .route(p->p.host("*.circuitbreaker.com")
                        .filters(f -> f.circuitBreaker(config -> config.setName("mycmd")))
                        .uri("http://httpbin.org:80")).build();
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

}

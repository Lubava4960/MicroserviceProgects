package gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("userService", r ->
                        r.path("/users/**") // Все запросы, начинающиеся с /user
                                .uri("http://localhost:8083")) // URL вашего User Service
                .route("companyService", r ->
                        r.path("/companies/**") // Все запросы, начинающиеся с /company
                                .uri("http://localhost:8081")) // URL вашего Company Service
                .build();
    }


}

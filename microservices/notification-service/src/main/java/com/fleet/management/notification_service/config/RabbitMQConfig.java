package com.fleet.management.notification_service.config;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    public static final String ALERTS_GENERATED_QUEUE = "alerts.generated.queue";
    public static final String ROUTE_OPTIMIZATION_RESULTS_QUEUE = "route.optimization.results.queue";

    @Bean
    public Queue alertsGeneratedQueue() {
        return new Queue(ALERTS_GENERATED_QUEUE, true); // Durável
    }

    @Bean
    public Queue routeOptimizationResultsQueue() {
        return new Queue(ROUTE_OPTIMIZATION_RESULTS_QUEUE, true); // Durável
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}


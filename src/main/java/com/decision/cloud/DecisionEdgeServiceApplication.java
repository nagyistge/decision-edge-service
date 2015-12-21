package com.decision.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableZuulProxy
@SpringBootApplication
public class DecisionEdgeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecisionEdgeServiceApplication.class, args);
    }

}

package br.cefetmg.grow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrowApplication.class, args);
    }
}
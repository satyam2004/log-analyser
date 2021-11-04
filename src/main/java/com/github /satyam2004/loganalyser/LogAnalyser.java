package com.github.satyam2004.loganalyser;

import com.github.satyam2004.loganalyser.service.LogAnalyserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ImageBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.time.Duration;
import java.time.Instant;

@SpringBootApplication
public class LogAnalyser implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAnalyser.class);

    @Autowired
    private LogAnalyserService service;

    public static void main(String... args) {
        SpringApplication app = new SpringApplication(LogAnalyser.class);
        app.setBanner(new ImageBanner(new ClassPathResource("logo.png")));
        app.run(args);
    }

    @Override
    public void run(String... args) {
        Instant start = Instant.now();
        service.execute(args);
        Instant end = Instant.now();
        LOGGER.info("Total time: {}ms", Duration.between(start, end).toMillis());
    }
}

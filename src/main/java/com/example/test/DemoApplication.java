package com.example.test;

import javax.swing.SwingUtilities;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.test.swing.MainApp;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Start Spring Boot application
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            new Thread(() -> {
                SwingUtilities.invokeLater(() -> {
                    new MainApp().setVisible(true);
                });
            }).start();
        };
    }
}
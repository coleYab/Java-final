package com.example.test;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.test.swing.MainApp;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        // Start Spring Boot application
        SpringApplication.run(DemoApplication.class, args);
        System.setProperty("java.awt.headless", "false");
        SwingUtilities.invokeLater(() -> {
            new MainApp().setVisible(true);
        });
    }
}
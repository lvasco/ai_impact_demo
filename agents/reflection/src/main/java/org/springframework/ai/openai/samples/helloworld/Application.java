package org.springframework.ai.openai.samples.helloworld;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    @Bean
    CommandLineRunner cli(ReflectionAgent reflectionAgent) {
        return args -> {
            var scanner = new Scanner(System.in);
            LOGGER.log(Level.INFO, "\nLet's chat!");

            // Generate a Java implementation of the Merge Sort algorithm
            while (true) {
                LOGGER.log(Level.INFO, "\nUSER: ");
                LOGGER.log(Level.INFO, "AGENT: " + reflectionAgent.run(scanner.nextLine(), 2));
                        reflectionAgent.run(scanner.nextLine(), 2));
            }
        };
    }
}

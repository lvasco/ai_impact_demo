package org.springframework.ai.openai.samples.helloworld;

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
    CommandLineRunner cli(ChatClient.Builder builder) {
        return args -> {
            var chat = builder.build();
            var scanner = new Scanner(System.in);
            LOGGER.info("\nLet's chat!");
            while (true) {
                LOGGER.info("\nUSER: ");
                LOGGER.info("ASSISTANT: " + chat.prompt(scanner.nextLine()).call().content());
                        chat.prompt(scanner.nextLine()).call().content());
            }
        };
    }
}

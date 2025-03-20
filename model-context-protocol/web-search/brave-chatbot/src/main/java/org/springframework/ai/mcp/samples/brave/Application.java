package org.springframework.ai.mcp.samples.brave;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner chatbot(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {

		return args -> {

			var chatClient = chatClientBuilder
					.defaultSystem("You are useful assistant and can perform web searches Brave's search API to reply to your questions.")
					.defaultTools(tools)
					.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
					.build();

			// Start the chat loop
            LOGGER.info("\nI am your AI assistant.\n");
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
                    LOGGER.info("\nUSER: ");
                    LOGGER.info("\nASSISTANT: " + chatClient.prompt(scanner.nextLine()) // Get the user input
							chatClient.prompt(scanner.nextLine()) // Get the user input
									.call()
									.content());
				}
			}

		};
	}
}
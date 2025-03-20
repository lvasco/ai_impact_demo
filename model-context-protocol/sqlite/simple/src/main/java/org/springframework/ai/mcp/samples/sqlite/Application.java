package org.springframework.ai.mcp.samples.sqlite;

import java.util.logging.Logger;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.client.McpClient;
import org.springframework.ai.mcp.client.McpSyncClient;
import org.springframework.ai.mcp.client.transport.ServerParameters;
import org.springframework.ai.mcp.client.transport.StdioClientTransport;
import org.springframework.ai.mcp.spring.McpFunctionCallback;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
private static final String QUESTION_PREFIX = "\nQUESTION: ";
private static final String ASSISTANT_PREFIX = "ASSISTANT: ";
private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder,
			List<McpFunctionCallback> functionCallbacks, ConfigurableApplicationContext context) {

		return args -> {
			var chatClient = chatClientBuilder
					.defaultFunctions(functionCallbacks.toArray(new McpFunctionCallback[0]))
					.build();
			LOGGER.info("Running predefined questions with AI model responses:\n");

			// Question 1
			String question1 = "Can you connect to my SQLite database and tell me what products are available, and their prices?";
			LOGGER.info(QUESTION_PREFIX + question1);
			LOGGER.info(ASSISTANT_PREFIX + chatClient.prompt(question1).call().content());

			// Question 2
			String question2 = "What's the average price of all products in the database?";
			LOGGER.info(QUESTION_PREFIX + question2);
			LOGGER.info(ASSISTANT_PREFIX + chatClient.prompt(question2).call().content());

			// Question 3
			String question3 = "Can you analyze the price distribution and suggest any pricing optimizations?";
			LOGGER.info(QUESTION_PREFIX + question3);
			LOGGER.info(ASSISTANT_PREFIX + chatClient.prompt(question3).call().content());

			// Question 4
			String question4 = "Could you help me design and create a new table for storing customer orders?";
			LOGGER.info(QUESTION_PREFIX + question4);
			LOGGER.info(ASSISTANT_PREFIX + chatClient.prompt(question4).call().content());

			LOGGER.info("\nPredefined questions completed. Exiting application.");
			context.close();

		};
	}

	@Bean
	public List<McpFunctionCallback> functionCallbacks(McpSyncClient mcpClient) {

		return mcpClient.listTools(null).tools().stream().map(tool -> new McpFunctionCallback(mcpClient, tool)).toList();
				.tools()
				.stream()
				.map(tool -> new McpFunctionCallback(mcpClient, tool))
				.toList();
		return callbacks;
	}

	@Bean(destroyMethod = "close")
	public McpSyncClient mcpClient() {

		var stdioParams = ServerParameters.builder("uvx")
				.args("mcp-server-sqlite", "--db-path",
						getDbPath())
				.build();

		var mcpClient = McpClient.sync(new StdioClientTransport(stdioParams))
				.requestTimeout(Duration.ofSeconds(10)).build();

		var init = mcpClient.initialize();

		LOGGER.info("MCP Initialized: " + init);

		return mcpClient;

	}

	private static String getDbPath() {
		return Paths.get(System.getProperty("user.dir"), "test.db").toString();
	}

}
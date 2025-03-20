package org.springframework.ai.mcp.samples.filesystem;

import java.util.logging.Level;
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
public class Application {

private static final Logger logger = Logger.getLogger(Application.class.getName());
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

logger.log(Level.INFO, \"Running predefined questions with AI model responses:\\n\");
			// Question 1
			String question1 = "Can you explain the content of the spring-ai-mcp-overview.txt file?";

			// Question 2
			String question2 = "Pleses summarize the content of the spring-ai-mcp-overview.txt file and store it a new summary.md as Markdown format?";
					chatClient.prompt(question2).call().content());

			context.close();

		};
	}

	@Bean
	public List<McpFunctionCallback> functionCallbacks(McpSyncClient mcpClient) {

				.tools()
				.stream()
				.map(tool -> new McpFunctionCallback(mcpClient, tool))
				.toList();
	}

	@Bean(destroyMethod = "close")
	public McpSyncClient mcpClient() {

		// based on
		// https://github.com/modelcontextprotocol/servers/tree/main/src/filesystem
		var stdioParams = ServerParameters.builder("npx")
				.args("-y", "@modelcontextprotocol/server-filesystem", getDbPath())
				.build();

		var mcpClient = McpClient.sync(new StdioClientTransport(stdioParams))
				.requestTimeout(Duration.ofSeconds(10)).build();

		var init = mcpClient.initialize();


		return mcpClient;

	}

	private static String getDbPath() {
		return Paths.get(System.getProperty("user.dir"), "target").toString();
	}

}
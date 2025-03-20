package org.springframework.ai.mcp.sample.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class McpServerApplication {
    private McpServerApplication() { }

	public static void main(String[] args) {
		SpringApplication.run(McpServerApplication.class, args);
	}

}

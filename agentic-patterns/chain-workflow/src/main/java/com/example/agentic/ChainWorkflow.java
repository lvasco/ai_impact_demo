// Package declaration
package com.example.agentic;
// Logger added to replace System.out.println and ensure proper logging

import java.util.logging.Logger;
import org.springframework.ai.chat.client.ChatClient;

// Class documentation
/**
// Workflow pattern description
 * Implements the Prompt Chaining workflow pattern for decomposing complex tasks
 * into a sequence
// Steps in the workflow
 * of LLM calls where each step processes the output of the previous one.
 * 
 * <p>
// Workflow description
 * This implementation demonstrates a four-step workflow for processing
 * numerical data in text:
// Workflow steps
 * <ol>
 * <li>Extract numerical values and metrics</li>
 * <li>Standardize to percentage format</li>
 * <li>Sort in descending order</li>
 * <li>Format as markdown table</li>
 * </ol>
 * 
// Workflow goal
 * <p/>
// Workflow usage information
 * When to use this workflow: This workflow is ideal for situations where the
 * task can be easily and cleanly decomposed into fixed subtasks. The main goal
 * is to trade off latency for higher accuracy, by making each LLM call an
 * easier task.
 * 
// Author information
 * @author Christian Tzolov
// References to related classes and documentation
 * @see org.springframework.ai.chat.client.ChatClient
// External references
 * @see <a href="https://docs.spring.io/spring-ai/reference/1.0/api/chatclient.html">Spring AI ChatClient</a>
// Link to research
 * @see <a href=
// Research details
 *      "https://www.anthropic.com/research/building-effective-agents">Building
// Research link
 *      Effective Agents</a>
// End of class documentation
 */
// Class declaration
// Main class for ChainWorkflow
private static final Logger LOGGER = Logger.getLogger(ChainWorkflow.class.getName());
public class ChainWorkflow {
// Class body

// Array of system prompts
	/**
// System prompts array
	 * Array of system prompts that define the transformation steps in the chain.
// Transformation steps
	 * Each prompt acts as a gate that validates and transforms the output before
// Validation and transformation
	 * proceeding to the next step.
// Next step
	 */
// Default system prompts for transformation steps
	private static final String[] DEFAULT_SYSTEM_PROMPTS = {
// Step 1

			// Step 1
// Extract numerical values
			"""
// Format values
					Extract only the numerical values and their associated metrics from the text.
// Example format
					Format each as'value: metric' on a new line.
// Example values
					Example format:
// Example metrics
					92: customer satisfaction
// Example output
					45%: revenue growth""",
// Step 2
			// Step 2
			"""
// Convert values
					Convert all numerical values to percentages where possible.
// Conversion details
					If not a percentage or points, convert to decimal (e.g., 92 points -> 92%).
// Decimal conversion
					Keep one number per line.
// Example format
					Example format:
// Example values
					92%: customer satisfaction
// Example metrics
					45%: revenue growth""",
// Step 3
			// Step 3
			"""
// Sort values
					Sort all lines in descending order by numerical value.
// Sorting format
					Keep the format 'value: metric' on each line.
// Example sorting
					Example:
// Example output
					92%: customer satisfaction
// Example metrics
					87%: employee satisfaction""",
// Step 4
			// Step 4
			"""
// Format data
					Format the sorted data as a markdown table with columns:
// Markdown table
					| Metric | Value |
// Table columns
					|:--|--:|
// Example table
					| Customer Satisfaction | 92% | """
// End of system prompts
	};

// Chat client instance
	private final ChatClient chatClient;
// Chat client

// System prompts instance
	private final String[] systemPrompts;
// System prompts

// Constructor documentation
	/**
// Constructor details
	 * Constructs a new instance of the Prompt Chaining workflow with the specified
// Default prompts
	 * chat client and default system prompts.
// Chat client
	 * 
// Parameter details
	 * @param chatClient the Spring AI chat client used to make LLM calls
// Constructor parameter
	 */
// Constructor with default system prompts
	public ChainWorkflow(ChatClient chatClient) {
// Constructor chaining
		this(chatClient, DEFAULT_SYSTEM_PROMPTS);
// Default system prompts
	}
// End of constructor

// Constructor documentation
	/**
// Constructor details
	 * Constructs a new instance of the Prompt Chaining workflow with the specified
// System prompts
	 * chat client and system prompts.
// Chat client
	 * 
// Parameter details
	 * @param chatClient    the Spring AI chat client used to make LLM calls
// Constructor parameter
	 * @param systemPrompts the system prompts that define the transformation steps
// Transformation steps
	 *                      in the chain
// End of constructor
	 */
// Constructor with system prompts
	public ChainWorkflow(ChatClient chatClient, String[] systemPrompts) {
// Chat client initialization
		this.chatClient = chatClient;
// System prompts initialization
		this.systemPrompts = systemPrompts;
// End of constructor
	}
// Method documentation

// Executes the workflow
	/**
// Method details
	 * Executes the prompt chaining workflow by processing the input text through
// Workflow execution
	 * a series of LLM calls, where each call's output becomes the input for the
// Transformation steps
	 * next step.
// Intermediate results
	 * 
// Final output
	 * <p>
// Method progression
	 * The method prints the intermediate results after each step to show the
// Transformation chain
	 * progression of transformations through the chain.
// End of method
	 *
// Chat client parameter
	 * @param chatClient the Spring AI chat client used to make LLM calls
// User input parameter
	 * @param userInput     the input text containing numerical data to be processed
// Final output
	 * @return the final output after all steps have been executed
// End of method
	 */
// Workflow execution
// Workflow execution method
// Method to execute the prompt chaining workflow
	public String chain(String userInput) {
// Method body

// Step initialization
// Initializing step and response
		int step = 0;
// Initial step logging
		String response = userInput;
		LOGGER.info(String.format(\"%nSTEP %s:%n %s\", step++, response));
// Logging step

// Iterating through prompts
// Iterating through system prompts
		for (String prompt : systemPrompts) {
// Iteration details

// Composing input for the chat client
			// 1. Compose the input using the response from the previous step.
			String input = String.format(\"{%s}%n {%s}\", prompt, response);
// Composing input

// Chat client call
// Updating response with chat client output
			// 2. Call the chat client with the new input and get the new response.
// Calling the chat client and updating response
			response = chatClient.prompt(input).call().content();
// Updating response

			LOGGER.info(String.format(\"%nSTEP %s:%n %s\", step++, response));
// Intermediate results
// Logging intermediate results after each step
		}
// End of iteration

// Final response
// Returning the final response
		return response;
// End of chain method
	}
// End of class
}
// End of file

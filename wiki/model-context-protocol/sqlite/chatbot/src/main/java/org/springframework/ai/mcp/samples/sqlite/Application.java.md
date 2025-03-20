# Documentación del Código: `Application.java`

## Descripción General

Este archivo contiene la implementación de una aplicación Spring Boot que utiliza un cliente de chat interactivo basado en inteligencia artificial. La aplicación permite a los usuarios interactuar con un asistente virtual a través de la consola. Además, se integra con un cliente MCP (Machine Control Protocol) para gestionar herramientas y funciones específicas.

## Estructura del Código

El código está compuesto por las siguientes secciones principales:

1. **Método Principal (`main`)**: Punto de entrada de la aplicación.
2. **Beans de Spring**:
   - `CommandLineRunner`: Configura y ejecuta la sesión de chat interactiva.
   - `functionCallbacks`: Proporciona una lista de funciones disponibles para el cliente MCP.
   - `mcpClient`: Configura y gestiona el cliente MCP.
3. **Métodos Auxiliares**:
   - `getDbPath`: Devuelve la ruta de la base de datos SQLite utilizada por el cliente MCP.

## Detalles de Implementación

### 1. Método Principal (`main`)

```java
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
}
```

Este método inicializa la aplicación Spring Boot.

---

### 2. Bean: `CommandLineRunner`

#### Propósito
Este bean configura y ejecuta una sesión de chat interactiva en la consola. Los usuarios pueden enviar mensajes al asistente virtual y recibir respuestas en tiempo real.

#### Lógica Principal
- Se construye un cliente de chat (`ChatClient`) con funciones y asesores predeterminados.
- Se utiliza un `Scanner` para leer la entrada del usuario desde la consola.
- El usuario puede escribir "exit" para finalizar la sesión.

#### Código Relevante
```java
@Bean
public CommandLineRunner interactiveChat(ChatClient.Builder chatClientBuilder,
        List<McpFunctionCallback> functionCallbacks,
        ConfigurableApplicationContext context) {
    return args -> {
        var chatClient = chatClientBuilder
                .defaultFunctions(functionCallbacks.toArray(new McpFunctionCallback[0]))
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();

        var scanner = new Scanner(System.in);
        System.out.println("\nStarting interactive chat session. Type 'exit' to quit.");

        try {
            while (true) {
                System.out.print("\nUSER: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Ending chat session.");
                    break;
                }

                System.out.print("ASSISTANT: ");
                System.out.println(chatClient.prompt(input).call().content());
            }
        } finally {
            scanner.close();
            context.close();
        }
    };
}
```

---

### 3. Bean: `functionCallbacks`

#### Propósito
Este bean genera una lista de funciones disponibles para el cliente MCP. Cada función se representa como un `McpFunctionCallback`.

#### Lógica Principal
- Se obtienen las herramientas disponibles del cliente MCP.
- Se mapean las herramientas a instancias de `McpFunctionCallback`.

#### Código Relevante
```java
@Bean
public List<McpFunctionCallback> functionCallbacks(McpSyncClient mcpClient) {
    var callbacks = mcpClient.listTools(null)
            .tools()
            .stream()
            .map(tool -> new McpFunctionCallback(mcpClient, tool))
            .toList();
    return callbacks;
}
```

---

### 4. Bean: `mcpClient`

#### Propósito
Este bean configura y gestiona el cliente MCP, que se comunica con un servidor MCP utilizando transporte estándar de entrada/salida (Stdio).

#### Lógica Principal
- Se configuran los parámetros del servidor MCP, incluyendo la ruta de la base de datos SQLite.
- Se inicializa el cliente MCP y se imprime un mensaje de confirmación.

#### Código Relevante
```java
@Bean(destroyMethod = "close")
public McpSyncClient mcpClient() {
    var stdioParams = ServerParameters.builder("uvx")
            .args("mcp-server-sqlite", "--db-path", getDbPath())
            .build();

    var mcpClient = McpClient.sync(new StdioClientTransport(stdioParams))
            .requestTimeout(Duration.ofSeconds(10)).build();

    var init = mcpClient.initialize();

    System.out.println("MCP Initialized: " + init);

    return mcpClient;
}
```

---

### 5. Método Auxiliar: `getDbPath`

#### Propósito
Devuelve la ruta absoluta de la base de datos SQLite utilizada por el cliente MCP.

#### Código Relevante
```java
private static String getDbPath() {
    return Paths.get(System.getProperty("user.dir"), "test.db").toString();
}
```

---

## Dependencias Clave

El código utiliza las siguientes dependencias principales:

| Dependencia                          | Propósito                                                                 |
|--------------------------------------|---------------------------------------------------------------------------|
| `org.springframework.boot`           | Framework para construir aplicaciones Java basadas en Spring.             |
| `org.springframework.ai.chat.client`| Cliente de chat para manejar interacciones con el asistente virtual.      |
| `org.springframework.ai.mcp.client` | Cliente MCP para gestionar herramientas y funciones específicas.          |
| `java.nio.file.Paths`                | Manejo de rutas de archivos.                                              |
| `java.util.Scanner`                  | Lectura de entrada del usuario desde la consola.                          |

---

## Insights

1. **Interactividad en Consola**: La aplicación está diseñada para ser interactiva, permitiendo a los usuarios comunicarse con un asistente virtual en tiempo real.
2. **Integración con MCP**: La integración con MCP permite extender las capacidades del asistente virtual mediante herramientas y funciones adicionales.
3. **Gestión de Recursos**: El uso de `try-finally` asegura que los recursos como el `Scanner` y el contexto de la aplicación se cierren correctamente.
4. **Configuración Modular**: La configuración de los beans en Spring Boot permite una fácil extensibilidad y mantenimiento del código.
5. **Uso de SQLite**: La base de datos SQLite se utiliza como almacenamiento local, lo que simplifica la configuración y el despliegue.

---

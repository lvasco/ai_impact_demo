# Documentación de `SampleClient.java`

## Descripción General

El archivo `SampleClient.java` implementa una clase que interactúa con un cliente MCP (Managed Communication Protocol) utilizando el transporte proporcionado por `ClientMcpTransport`. Este cliente permite realizar diversas operaciones como listar herramientas, ejecutar herramientas específicas, listar recursos, leer recursos, listar prompts y ejecutar prompts. La clase está diseñada para demostrar las capacidades del cliente MCP en un entorno sincronizado.

## Metadatos

| **Propiedad**       | **Valor**                                                                 |
|---------------------|---------------------------------------------------------------------------|
| **Nombre del Archivo** | `SampleClient.java`                                                     |
| **Autor**           | Christian Tzolov                                                        |
| **Licencia**        | Apache License, Version 2.0                                             |
| **Paquete**         | `org.springframework.ai.mcp.sample.client`                              |

## Estructura de la Clase

### Declaración de la Clase

```java
public class SampleClient {
```

La clase `SampleClient` contiene un atributo privado `transport` que representa el transporte MCP utilizado para la comunicación. Este transporte es inyectado a través del constructor.

### Constructor

```java
public SampleClient(ClientMcpTransport transport) {
    this.transport = transport;
}
```

El constructor inicializa el transporte MCP que será utilizado por el cliente.

### Método Principal: `run`

El método `run` ejecuta una serie de operaciones utilizando el cliente MCP. Estas operaciones incluyen:

1. **Inicialización del cliente**:
   ```java
   var client = McpClient.sync(this.transport).build();
   client.initialize();
   client.ping();
   ```

   - Se crea una instancia del cliente MCP en modo sincronizado.
   - Se inicializa el cliente y se verifica la conectividad mediante un "ping".

2. **Listar herramientas disponibles**:
   ```java
   ListToolsResult toolsList = client.listTools();
   System.out.println("Available Tools = " + toolsList);
   ```

   - Se obtiene una lista de herramientas disponibles y se imprime en la consola.

3. **Ejecutar herramientas específicas**:
   ```java
   CallToolResult upperCaseResult = client.callTool(new CallToolRequest("toUpperCase", Map.of("input", "accountName")));
   System.out.println("Upper case Response = " + upperCaseResult);

   CallToolResult books = client.callTool(new CallToolRequest("getBooks", Map.of("title", "Spring Framework")));
   System.out.println("Books Response = " + books);
   ```

   - Se ejecuta la herramienta `toUpperCase` con un parámetro de entrada y se imprime el resultado.
   - Se ejecuta la herramienta `getBooks` con un título específico y se imprime el resultado.

4. **Listar recursos disponibles**:
   ```java
   var resourcesList = client.listResources();
   System.out.println("\nAvailable Resources = " + resourcesList);
   ```

   - Se obtiene una lista de recursos disponibles y se imprime en la consola.

5. **Leer un recurso específico**:
   ```java
   var systemInfo = client.readResource(new ReadResourceRequest("system://info"));
   System.out.println("System Info = " + systemInfo);
   ```

   - Se lee el recurso `system://info` y se imprime la información del sistema.

6. **Listar prompts disponibles**:
   ```java
   ListPromptsResult promptsList = client.listPrompts();
   System.out.println("\nAvailable Prompts = " + promptsList);
   ```

   - Se obtiene una lista de prompts disponibles y se imprime en la consola.

7. **Ejecutar un prompt específico**:
   ```java
   var greetingResponse = client.getPrompt(new GetPromptRequest("greeting", Map.of("name", "Spring")));
   System.out.println("Greeting Response = " + greetingResponse);
   ```

   - Se ejecuta el prompt `greeting` con un parámetro de entrada y se imprime el resultado.

8. **Cerrar el cliente**:
   ```java
   client.closeGracefully();
   ```

   - Se cierra el cliente de manera segura.

## Dependencias

La clase utiliza las siguientes dependencias:

| **Clase/Interfaz**               | **Descripción**                                                                 |
|----------------------------------|---------------------------------------------------------------------------------|
| `ClientMcpTransport`             | Proporciona el transporte para la comunicación MCP.                            |
| `McpClient`                      | Clase principal para interactuar con el cliente MCP.                           |
| `McpSchema.CallToolRequest`      | Representa una solicitud para ejecutar una herramienta.                        |
| `McpSchema.CallToolResult`       | Representa el resultado de la ejecución de una herramienta.                    |
| `McpSchema.GetPromptRequest`     | Representa una solicitud para ejecutar un prompt.                              |
| `McpSchema.ListPromptsResult`    | Representa el resultado de listar los prompts disponibles.                     |
| `McpSchema.ListToolsResult`      | Representa el resultado de listar las herramientas disponibles.                |
| `McpSchema.ReadResourceRequest`  | Representa una solicitud para leer un recurso específico.                      |

## Insights

1. **Modularidad**: La clase está diseñada de manera modular, lo que permite extender fácilmente las funcionalidades del cliente MCP.
2. **Demostración de Capacidades**: El método `run` actúa como una demostración completa de las capacidades del cliente MCP, incluyendo herramientas, recursos y prompts.
3. **Uso de MCP**: La clase utiliza el protocolo MCP para realizar operaciones de comunicación estructurada, lo que la hace adecuada para aplicaciones que requieren interacción con sistemas remotos.
4. **Gestión de Recursos**: La implementación asegura que los recursos del cliente se cierren de manera segura utilizando `closeGracefully`.
5. **Flexibilidad**: El uso de `Map` para pasar parámetros a herramientas y prompts permite una configuración flexible y dinámica.

## Ejemplo de Uso

Para utilizar esta clase, se debe instanciar con un objeto `ClientMcpTransport` válido y llamar al método `run`:

```java
ClientMcpTransport transport = new CustomMcpTransport();
SampleClient sampleClient = new SampleClient(transport);
sampleClient.run();
```

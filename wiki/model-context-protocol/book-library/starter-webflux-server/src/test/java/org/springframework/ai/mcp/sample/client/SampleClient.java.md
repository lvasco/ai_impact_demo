# Documentación de `SampleClient.java`

## Descripción General

El archivo `SampleClient.java` implementa un cliente que interactúa con un sistema basado en el protocolo **Model Context Protocol (MCP)**. Este cliente utiliza un transporte proporcionado para realizar diversas operaciones, como listar herramientas, ejecutar herramientas específicas, listar recursos, leer recursos y trabajar con prompts. 

El cliente está diseñado para ser ejecutado de manera sincrónica y demuestra cómo interactuar con las funcionalidades principales del sistema MCP.

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```java
package org.springframework.ai.mcp.sample.client;
```

### Importaciones
El código utiliza las siguientes clases y paquetes:
- **`java.util.Map`**: Para manejar pares clave-valor en las solicitudes.
- **`io.modelcontextprotocol.client.McpClient`**: Clase principal para interactuar con el cliente MCP.
- **`io.modelcontextprotocol.spec.*`**: Especificaciones del protocolo MCP, incluyendo transporte, solicitudes y resultados.

### Clase Principal
La clase principal es `SampleClient`, que contiene:
- **Atributo `transport`**: Un objeto de tipo `ClientMcpTransport` que actúa como el medio de transporte para las operaciones del cliente.
- **Constructor**: Inicializa el transporte.
- **Método `run`**: Contiene la lógica principal para interactuar con el cliente MCP.

---

## Funcionalidades

### Inicialización del Cliente
El cliente se inicializa utilizando el transporte proporcionado:
```java
var client = McpClient.sync(this.transport).build();
client.initialize();
client.ping();
```

### Listar Herramientas
El cliente obtiene una lista de herramientas disponibles:
```java
ListToolsResult toolsList = client.listTools();
System.out.println("Available Tools = " + toolsList);
```

### Ejecutar Herramientas
El cliente ejecuta herramientas específicas con parámetros:
1. **Convertir texto a mayúsculas**:
   ```java
   CallToolResult upperCaseResult = client
       .callTool(new CallToolRequest("toUpperCase", Map.of("input", "accountName")));
   System.out.println("Upper case Response = " + upperCaseResult);
   ```
2. **Obtener libros por título**:
   ```java
   CallToolResult books = client.callTool(new CallToolRequest("getBooks", Map.of("title", "Spring Framework")));
   System.out.println("Books Response = " + books);
   ```

### Listar Recursos
El cliente obtiene una lista de recursos disponibles:
```java
var resourcesList = client.listResources();
System.out.println("\nAvailable Resources = " + resourcesList);
```

### Leer Recursos
El cliente lee un recurso específico, como información del sistema:
```java
var systemInfo = client.readResource(new ReadResourceRequest("system://info"));
System.out.println("System Info = " + systemInfo);
```

### Listar Prompts
El cliente obtiene una lista de prompts disponibles:
```java
ListPromptsResult promptsList = client.listPrompts();
System.out.println("\nAvailable Prompts = " + promptsList);
```

### Ejecutar un Prompt
El cliente ejecuta un prompt con parámetros:
```java
var greetingResponse = client.getPrompt(new GetPromptRequest("greeting", Map.of("name", "Spring")));
System.out.println("Greeting Response = " + greetingResponse);
```

### Cierre del Cliente
El cliente se cierra de manera controlada:
```java
client.closeGracefully();
```

---

## Insights

### Dependencias
El cliente depende de las siguientes clases y especificaciones del protocolo MCP:
- **`ClientMcpTransport`**: Proporciona el transporte para las operaciones del cliente.
- **`McpClient`**: Clase principal para interactuar con el sistema MCP.
- **Especificaciones MCP**: Incluyen clases como `CallToolRequest`, `CallToolResult`, `ListToolsResult`, `ReadResourceRequest`, etc.

### Operaciones Soportadas
El cliente soporta las siguientes operaciones:
1. **Herramientas**:
   - Listar herramientas disponibles.
   - Ejecutar herramientas con parámetros.
2. **Recursos**:
   - Listar recursos disponibles.
   - Leer recursos específicos.
3. **Prompts**:
   - Listar prompts disponibles.
   - Ejecutar prompts con parámetros.

### Uso de Mapas
El uso de `Map.of` permite enviar parámetros clave-valor de manera sencilla en las solicitudes.

### Flujo de Ejecución
El flujo de ejecución sigue un patrón claro:
1. Inicialización del cliente.
2. Ejecución de operaciones (herramientas, recursos, prompts).
3. Cierre controlado del cliente.

### Aplicaciones Potenciales
Este cliente puede ser utilizado en aplicaciones que requieran:
- Interacción con herramientas y recursos remotos.
- Ejecución de prompts personalizados.
- Integración con sistemas basados en el protocolo MCP.

---

## Licencia
El código está licenciado bajo la **Apache License 2.0**. Para más detalles, consulte [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

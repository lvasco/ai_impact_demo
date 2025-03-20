# Documentación de `SampleClient.java`

## Descripción General

El archivo `SampleClient.java` implementa un cliente que interactúa con un sistema basado en el protocolo Model Context Protocol (MCP). Este cliente utiliza un transporte específico (`ClientMcpTransport`) para comunicarse con el servidor MCP y realizar diversas operaciones, como listar herramientas, ejecutar herramientas, listar recursos, leer recursos y trabajar con prompts.

El cliente está diseñado para ser ejecutado de manera síncrona y demuestra varias capacidades del protocolo MCP.

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```
org.springframework.ai.mcp.sample.servlet.client
```

### Importaciones
El código utiliza las siguientes clases y paquetes:
- **Java**: `java.util.Map`
- **MCP**:
  - `io.modelcontextprotocol.client.McpClient`
  - `io.modelcontextprotocol.spec.ClientMcpTransport`
  - `io.modelcontextprotocol.spec.McpSchema.*` (varias clases relacionadas con herramientas, recursos y prompts)

### Clase Principal
La clase principal es `SampleClient`, que contiene:
- Un atributo privado `transport` de tipo `ClientMcpTransport`.
- Un constructor que inicializa el transporte.
- Un método `run()` que ejecuta las operaciones principales del cliente.

---

## Métodos y Funcionalidades

### Constructor
```java
public SampleClient(ClientMcpTransport transport)
```
- **Descripción**: Inicializa el cliente con un transporte MCP proporcionado.
- **Parámetros**:
  - `transport`: Instancia de `ClientMcpTransport` que define el medio de comunicación con el servidor MCP.

---

### Método `run()`
```java
public void run()
```
- **Descripción**: Ejecuta las operaciones principales del cliente MCP. Este método demuestra las capacidades del cliente al interactuar con herramientas, recursos y prompts.
- **Flujo de Operaciones**:
  1. **Inicialización del Cliente**:
     - Se crea una instancia de `McpClient` en modo síncrono.
     - Se inicializa el cliente con `client.initialize()`.
     - Se verifica la conectividad con `client.ping()`.

  2. **Interacción con Herramientas**:
     - Se listan las herramientas disponibles con `client.listTools()`.
     - Se ejecuta la herramienta `toUpperCase` con un input específico.
     - Se ejecuta la herramienta `getBooks` para buscar libros relacionados con "Spring Framework".

  3. **Interacción con Recursos**:
     - Se listan los recursos disponibles con `client.listResources()`.
     - Se lee un recurso específico (`system://info`) con `client.readResource()`.

  4. **Interacción con Prompts**:
     - Se listan los prompts disponibles con `client.listPrompts()`.
     - Se ejecuta el prompt `greeting` con un parámetro de entrada.

  5. **Cierre del Cliente**:
     - Se cierra el cliente de manera controlada con `client.closeGracefully()`.

---

## Detalles Técnicos

### Operaciones con Herramientas
| Operación         | Método Utilizado          | Descripción                                                                 |
|--------------------|---------------------------|-----------------------------------------------------------------------------|
| Listar Herramientas | `client.listTools()`      | Devuelve una lista de herramientas disponibles en el servidor MCP.         |
| Ejecutar Herramienta | `client.callTool()`      | Ejecuta una herramienta específica con parámetros de entrada.              |

### Operaciones con Recursos
| Operación         | Método Utilizado              | Descripción                                                                 |
|--------------------|-------------------------------|-----------------------------------------------------------------------------|
| Listar Recursos    | `client.listResources()`      | Devuelve una lista de recursos disponibles en el servidor MCP.             |
| Leer Recurso       | `client.readResource()`       | Lee el contenido de un recurso específico identificado por su URI.         |

### Operaciones con Prompts
| Operación         | Método Utilizado          | Descripción                                                                 |
|--------------------|---------------------------|-----------------------------------------------------------------------------|
| Listar Prompts     | `client.listPrompts()`    | Devuelve una lista de prompts disponibles en el servidor MCP.              |
| Ejecutar Prompt    | `client.getPrompt()`      | Ejecuta un prompt específico con parámetros de entrada.                    |

---

## Insights

1. **Uso del Protocolo MCP**:
   - Este cliente demuestra cómo interactuar con un servidor MCP utilizando un transporte específico. Es un ejemplo práctico de cómo integrar herramientas, recursos y prompts en una aplicación.

2. **Diseño Modular**:
   - La clase está diseñada para ser reutilizable y extensible. El transporte se inyecta a través del constructor, lo que permite cambiar fácilmente el medio de comunicación.

3. **Demostración de Capacidades**:
   - El método `run()` actúa como una demostración completa de las capacidades del cliente MCP, lo que lo hace útil para pruebas y ejemplos.

4. **Cierre Controlado**:
   - El uso de `client.closeGracefully()` asegura que los recursos se liberen correctamente, evitando posibles fugas de memoria o conexiones abiertas.

5. **Interacción con Datos Dinámicos**:
   - El cliente utiliza mapas (`Map.of`) para pasar parámetros dinámicos a las herramientas y prompts, lo que lo hace flexible para diferentes casos de uso.

---

## Licencia

Este archivo está licenciado bajo la **Licencia Apache 2.0**. Para más detalles, consulte el archivo de licencia en: [https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0).

# Documentación de `SampleClient.java`

## Descripción General

El archivo `SampleClient.java` implementa un cliente que interactúa con un sistema basado en el protocolo MCP (Managed Communication Protocol). Este cliente utiliza la clase `McpClient` para realizar diversas operaciones, como listar herramientas, ejecutar herramientas específicas, listar recursos, leer recursos y trabajar con prompts. El cliente se comunica con el sistema a través de un transporte definido por la interfaz `ClientMcpTransport`.

## Estructura del Código

El código contiene tanto la declaración de estructuras de datos como lógica de negocio. A continuación, se detalla su estructura:

### Declaración de Estructuras de Datos

- **Clase `SampleClient`**: 
  - Contiene un atributo `transport` de tipo `ClientMcpTransport`, que se utiliza para establecer la comunicación con el sistema MCP.
  - Constructor que inicializa el transporte.

### Lógica del Programa

La lógica principal se encuentra en el método `run()`, que realiza las siguientes operaciones:

1. **Inicialización del Cliente**:
   - Se crea una instancia de `McpClient` utilizando el transporte proporcionado.
   - Se inicializa el cliente con el método `initialize()`.
   - Se verifica la conectividad con el método `ping()`.

2. **Gestión de Herramientas**:
   - Se listan las herramientas disponibles mediante `listTools()`.
   - Se ejecutan herramientas específicas, como:
     - `toUpperCase`: Convierte un texto a mayúsculas.
     - `getBooks`: Busca libros basados en un título.

3. **Gestión de Recursos**:
   - Se listan los recursos disponibles mediante `listResources()`.
   - Se lee un recurso específico (`system://info`) utilizando `readResource()`.

4. **Gestión de Prompts**:
   - Se listan los prompts disponibles mediante `listPrompts()`.
   - Se ejecuta un prompt específico (`greeting`) con parámetros personalizados.

5. **Cierre del Cliente**:
   - Se cierra el cliente de manera controlada con `closeGracefully()`.

## Detalles de Implementación

### Métodos Clave

| Método                  | Descripción                                                                                     |
|-------------------------|-------------------------------------------------------------------------------------------------|
| `SampleClient(ClientMcpTransport transport)` | Constructor que inicializa el transporte para la comunicación.                              |
| `run()`                 | Método principal que ejecuta las operaciones del cliente.                                       |

### Operaciones Realizadas

| Operación               | Método Utilizado                     | Descripción                                                                                     |
|-------------------------|---------------------------------------|-------------------------------------------------------------------------------------------------|
| Inicialización          | `initialize()`                       | Inicializa el cliente MCP.                                                                     |
| Verificación de Conexión| `ping()`                              | Verifica la conectividad con el sistema MCP.                                                   |
| Listar Herramientas     | `listTools()`                         | Obtiene una lista de herramientas disponibles.                                                 |
| Ejecutar Herramienta    | `callTool(CallToolRequest)`           | Ejecuta una herramienta específica con parámetros.                                             |
| Listar Recursos         | `listResources()`                     | Obtiene una lista de recursos disponibles.                                                     |
| Leer Recurso            | `readResource(ReadResourceRequest)`   | Lee un recurso específico.                                                                     |
| Listar Prompts          | `listPrompts()`                       | Obtiene una lista de prompts disponibles.                                                      |
| Ejecutar Prompt         | `getPrompt(GetPromptRequest)`         | Ejecuta un prompt específico con parámetros personalizados.                                    |
| Cierre Controlado       | `closeGracefully()`                   | Cierra el cliente de manera segura.                                                            |

### Dependencias Importantes

El código utiliza varias clases y paquetes del sistema MCP:

- **Paquete `org.springframework.ai.mcp.client`**:
  - `McpClient`: Clase principal para interactuar con el sistema MCP.
- **Paquete `org.springframework.ai.mcp.spec`**:
  - Clases como `CallToolRequest`, `CallToolResult`, `GetPromptRequest`, `ListPromptsResult`, `ListToolsResult`, y `ReadResourceRequest` para modelar las solicitudes y respuestas del sistema MCP.

## Insights

1. **Modularidad**: El diseño del cliente es modular, ya que utiliza un transporte externo (`ClientMcpTransport`) para la comunicación, lo que permite flexibilidad en la implementación del transporte.
2. **Uso de MCP**: El cliente aprovecha las capacidades del protocolo MCP para realizar operaciones como la gestión de herramientas, recursos y prompts.
3. **Extensibilidad**: La estructura del cliente permite agregar nuevas funcionalidades o herramientas sin modificar significativamente el código existente.
4. **Gestión de Recursos y Prompts**: El cliente no solo ejecuta herramientas, sino que también interactúa con recursos y prompts, lo que lo hace versátil para diferentes casos de uso.
5. **Cierre Seguro**: La implementación asegura que los recursos se liberen correctamente al finalizar la ejecución mediante `closeGracefully()`.

## Licencia

El código está licenciado bajo la **Licencia Apache 2.0**, lo que permite su uso, modificación y distribución bajo ciertas condiciones. Para más detalles, consulte [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

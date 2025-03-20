# Documentación de `McpServerConfig.java`

## Descripción General

El archivo `McpServerConfig.java` define la configuración de un servidor MCP (Model Context Protocol) utilizando Spring Framework. Este servidor soporta diferentes modos de transporte (SSE y STDIO) y proporciona capacidades como herramientas, recursos y prompts. Además, incluye registros de herramientas personalizadas y recursos del sistema.

## Estructura del Código

El código contiene tanto **declaraciones de estructuras de datos** como **lógica funcional**. A continuación, se detalla cada sección:

### Declaraciones de Estructuras de Datos

1. **Clase `ToUpperCaseInput`**:
   - Es una clase de registro (`record`) que encapsula un único campo `input` de tipo `String`.
   - Se utiliza como entrada para una herramienta que convierte texto a mayúsculas.

2. **Recursos y Prompts**:
   - `McpSchema.Resource`: Representa un recurso del sistema con metadatos como URI, descripción y tipo de contenido.
   - `McpSchema.Prompt`: Define un prompt con argumentos y descripciones.

### Lógica Funcional

1. **Configuración de Transporte**:
   - Define dos modos de transporte para el servidor:
     - **SSE (Server-Sent Events)**: Utiliza `HttpServletSseServerTransport` para manejar mensajes HTTP.
     - **STDIO**: Utiliza `StdioServerTransport` para manejar entradas y salidas estándar.

2. **Configuración del Servidor MCP**:
   - Configura las capacidades del servidor, incluyendo soporte para herramientas, recursos y prompts.
   - Registra herramientas personalizadas como `toUpperCase` y recursos del sistema.

3. **Registro de Recursos**:
   - Registra un recurso llamado `system://info` que proporciona información básica del sistema, como versión de Java, sistema operativo, arquitectura, etc.

4. **Registro de Prompts**:
   - Registra un prompt llamado `greeting` que genera un mensaje personalizado de saludo basado en un argumento (`name`).

5. **Herramientas Personalizadas**:
   - Registra una herramienta llamada `toUpperCase` que convierte un texto a mayúsculas.
   - Integra herramientas adicionales desde una biblioteca externa (`OpenLibrary`).

## Componentes Principales

### Beans Definidos

| Bean                              | Descripción                                                                                     |
|-----------------------------------|-------------------------------------------------------------------------------------------------|
| `webMvcSseServerTransport`        | Configura el transporte SSE para manejar mensajes HTTP.                                         |
| `customServletBean`               | Registra el servlet para el transporte SSE.                                                    |
| `stdioServerTransport`            | Configura el transporte STDIO para manejar entradas y salidas estándar.                        |
| `mcpServer`                       | Configura y construye el servidor MCP con capacidades, herramientas, recursos y prompts.       |

### Capacidades del Servidor MCP

| Capacidad       | Descripción                                                                 |
|------------------|-----------------------------------------------------------------------------|
| Recursos         | Soporte para notificaciones de cambios en la lista de recursos.            |
| Herramientas     | Soporte para herramientas con notificaciones de cambios en la lista.       |
| Prompts          | Soporte para prompts con notificaciones de cambios en la lista.            |
| Logging          | Soporte para registro de eventos.                                          |

### Recursos Registrados

| Recurso          | Descripción                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| `system://info`   | Proporciona información básica del sistema, como versión de Java y OS.     |

### Prompts Registrados

| Prompt    | Descripción                                                                 |
|-----------|-----------------------------------------------------------------------------|
| `greeting`| Genera un mensaje de saludo personalizado basado en el argumento `name`.   |

### Herramientas Registradas

| Herramienta      | Descripción                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| `toUpperCase`     | Convierte un texto a mayúsculas.                                           |
| Herramientas de `OpenLibrary` | Herramientas adicionales registradas desde una biblioteca externa. |

## Insights

1. **Modularidad**:
   - El diseño utiliza anotaciones de Spring (`@Bean`, `@Configuration`, `@ConditionalOnProperty`) para modularizar la configuración del servidor y los transportes.

2. **Extensibilidad**:
   - El servidor MCP es altamente extensible, permitiendo registrar herramientas y recursos adicionales mediante métodos como `openLibraryToolRegistrations`.

3. **Uso de Funciones Lambda**:
   - Se emplean funciones lambda para definir comportamientos personalizados, como la conversión de texto a mayúsculas y la generación de mensajes de saludo.

4. **Soporte Multimodal**:
   - El servidor soporta múltiples modos de transporte (SSE y STDIO), lo que lo hace adaptable a diferentes entornos.

5. **Integración con OpenLibrary**:
   - La integración con `OpenLibrary` permite ampliar las capacidades del servidor mediante herramientas externas.

6. **Uso de JSON**:
   - Los datos del sistema y otros contenidos se serializan en formato JSON utilizando `ObjectMapper` de Jackson.

7. **Gestión de Errores**:
   - Se manejan excepciones en la generación de recursos del sistema, garantizando la robustez del servidor.

# Documentación de `McpServerConfig.java`

## Descripción General
El archivo `McpServerConfig.java` define la configuración de un servidor MCP (Multi-Channel Protocol) utilizando Spring Framework. Este servidor está diseñado para manejar transporte de datos, herramientas, recursos y prompts, proporcionando capacidades avanzadas como soporte para SSE (Server-Sent Events) y transporte estándar de entrada/salida (STDIO). Además, incluye registros de herramientas y recursos, así como un prompt personalizado para interacciones dinámicas.

## Estructura del Código
El código contiene tanto declaraciones de estructuras de datos como lógica de programación. A continuación, se detalla la funcionalidad principal:

### Declaraciones de Estructuras de Datos
- **`ToUpperCaseInput`**: Una clase de registro que encapsula un único campo `input` de tipo `String`.

### Lógica de Programación
El código incluye lógica para:
1. Configuración de transporte (STDIO y SSE).
2. Registro de herramientas y recursos.
3. Implementación de un prompt personalizado.
4. Creación y configuración del servidor MCP.

## Funcionalidades Principales

### Configuración de Transporte
El servidor admite dos modos de transporte:
- **STDIO**: Configurado mediante el método `stdioServerTransport()`.
- **SSE**: Configurado mediante el método `sseServerTransport()` y un `RouterFunction` para manejar solicitudes HTTP.

### Registro de Recursos
El método `systemInfoResourceRegistration()` registra un recurso que proporciona información básica del sistema, como la versión de Java, el sistema operativo y el número de procesadores disponibles.

### Registro de Prompts
El método `greetingPromptRegistration()` define un prompt llamado `greeting` que genera un mensaje personalizado basado en un argumento llamado `name`.

### Registro de Herramientas
El método `openLibraryToolRegistrations()` registra herramientas para interactuar con una biblioteca abierta (`OpenLibrary`), incluyendo:
- Obtener una lista de libros por título.
- Obtener títulos de libros por autor.

### Configuración del Servidor MCP
El método `mcpServer()` configura el servidor MCP con las siguientes capacidades:
- Recursos.
- Herramientas.
- Prompts.
- Registro de herramientas basadas en funciones, como la conversión de texto a mayúsculas.

## Métodos y Componentes

### Métodos Principales
| Método                          | Descripción                                                                 |
|---------------------------------|-----------------------------------------------------------------------------|
| `stdioServerTransport()`        | Configura el transporte STDIO.                                              |
| `sseServerTransport()`          | Configura el transporte SSE con soporte para eventos enviados por el servidor. |
| `mcpRouterFunction()`           | Define una función de enrutamiento para el transporte SSE.                  |
| `mcpServer()`                   | Configura el servidor MCP con capacidades, herramientas y recursos.         |
| `openLibraryToolRegistrations()`| Registra herramientas para interactuar con la biblioteca abierta.           |
| `systemInfoResourceRegistration()` | Registra un recurso que proporciona información del sistema.              |
| `greetingPromptRegistration()`  | Registra un prompt personalizado para generar mensajes de saludo.           |
| `openLibrary()`                 | Crea una instancia de `OpenLibrary` para interactuar con la biblioteca.     |

### Componentes Clave
| Componente                      | Descripción                                                                 |
|---------------------------------|-----------------------------------------------------------------------------|
| `StdioServerTransport`          | Transporte basado en entrada/salida estándar.                               |
| `WebFluxSseServerTransport`     | Transporte basado en SSE para manejar eventos enviados por el servidor.     |
| `McpSyncServer`                 | Servidor MCP con capacidades de sincronización.                             |
| `McpSchema.Resource`            | Representa un recurso en el servidor MCP.                                   |
| `McpSchema.Prompt`              | Representa un prompt en el servidor MCP.                                    |
| `FunctionCallback`              | Permite registrar herramientas basadas en funciones Java.                   |

## Insights
1. **Modularidad**: El diseño del servidor MCP es altamente modular, permitiendo la configuración de transporte, herramientas, recursos y prompts de manera independiente.
2. **Uso de Spring Framework**: La integración con Spring Framework facilita la configuración condicional basada en propiedades, lo que permite flexibilidad en el despliegue.
3. **Extensibilidad**: El uso de `FunctionCallback` y `ToolHelper` permite registrar herramientas personalizadas fácilmente, lo que hace que el servidor sea extensible.
4. **Interacción Dinámica**: El prompt `greeting` demuestra cómo el servidor puede generar respuestas dinámicas basadas en entradas del usuario.
5. **Soporte para SSE**: La inclusión de transporte SSE lo hace adecuado para aplicaciones en tiempo real que requieren actualizaciones continuas desde el servidor.

## Propiedades Condicionales
El código utiliza la anotación `@ConditionalOnProperty` para habilitar configuraciones específicas basadas en las propiedades de configuración:
- `transport.mode=stdio`: Activa el transporte STDIO.
- `transport.mode=sse`: Activa el transporte SSE y el enrutamiento HTTP.

## Dependencias
El archivo utiliza las siguientes dependencias:
- **Spring Framework**: Para la configuración del servidor y transporte.
- **Jackson**: Para la serialización de objetos a JSON.
- **SLF4J**: Para el registro de logs.
- **OpenLibrary**: Para interactuar con una biblioteca abierta.
- **MCP Framework**: Para definir recursos, prompts y herramientas en el servidor MCP.

# Documentación del Código: `Application.java`

## Descripción General

Este archivo contiene una aplicación Spring Boot que utiliza un cliente de inteligencia artificial (IA) para interactuar con un modelo de procesamiento de lenguaje natural. La aplicación está diseñada para ejecutar preguntas predefinidas y obtener respuestas generadas por el modelo de IA. Además, se integra con el protocolo Model Context Protocol (MCP) para gestionar herramientas y funciones relacionadas con el procesamiento de archivos.

## Estructura del Código

El código incluye tanto la declaración de estructuras de datos como lógica de negocio. A continuación, se detalla la funcionalidad principal:

1. **Inicialización de la Aplicación**: Configuración de Spring Boot y ejecución de la aplicación.
2. **Integración con MCP**: Configuración de un cliente MCP para interactuar con herramientas externas.
3. **Ejecución de Preguntas Predefinidas**: Uso de un cliente de chat para realizar preguntas y obtener respuestas del modelo de IA.
4. **Definición de Beans**: Configuración de beans de Spring para la inyección de dependencias.

## Componentes Principales

### 1. `main` (Método Principal)
- **Función**: Punto de entrada de la aplicación Spring Boot.
- **Detalles**: Llama a `SpringApplication.run` para iniciar el contexto de la aplicación.

### 2. Bean: `CommandLineRunner`
- **Función**: Ejecuta preguntas predefinidas al iniciar la aplicación.
- **Detalles**:
  - Utiliza un cliente de chat (`ChatClient`) para interactuar con el modelo de IA.
  - Realiza dos preguntas predefinidas y muestra las respuestas en la consola.
  - Cierra el contexto de la aplicación al finalizar.

### 3. Bean: `functionCallbacks`
- **Función**: Configura una lista de callbacks para herramientas MCP.
- **Detalles**:
  - Obtiene una lista de herramientas disponibles desde el cliente MCP.
  - Crea instancias de `McpFunctionCallback` para cada herramienta.

### 4. Bean: `mcpClient`
- **Función**: Configura un cliente MCP síncrono.
- **Detalles**:
  - Utiliza transporte estándar de entrada/salida (`StdioClientTransport`) para comunicarse con el servidor MCP.
  - Inicializa el cliente y muestra un mensaje de confirmación en la consola.

### 5. Método: `getDbPath`
- **Función**: Devuelve la ruta del directorio de la base de datos.
- **Detalles**:
  - Construye la ruta utilizando el directorio de trabajo actual y un subdirectorio `target`.

## Dependencias Importantes

| Dependencia                          | Descripción                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------|
| `org.springframework.boot`          | Framework para construir aplicaciones basadas en Spring Boot.               |
| `org.springframework.ai.chat.client`| Cliente para interactuar con modelos de IA mediante prompts.               |
| `org.springframework.ai.mcp.client` | Cliente para interactuar con el protocolo MCP.                              |
| `java.nio.file.Paths`                | Utilidad para manejar rutas de archivos.                                    |
| `java.time.Duration`                 | Manejo de duraciones temporales.                                            |

## Flujo de Ejecución

1. La aplicación se inicia mediante el método `main`.
2. Se configura el cliente MCP y se inicializan las herramientas disponibles.
3. Se ejecuta el `CommandLineRunner` para realizar preguntas predefinidas al modelo de IA.
4. Las respuestas del modelo se imprimen en la consola.
5. El contexto de la aplicación se cierra al finalizar.

## Insights

- **Integración con MCP**: La aplicación utiliza el protocolo MCP para gestionar herramientas externas, lo que permite una mayor flexibilidad y modularidad.
- **Uso de Prompts**: El cliente de chat permite interactuar con el modelo de IA mediante prompts, lo que es útil para tareas de procesamiento de lenguaje natural.
- **Configuración Modular**: La aplicación utiliza beans de Spring para configurar componentes reutilizables, lo que facilita la inyección de dependencias y la separación de responsabilidades.
- **Timeout Configurable**: El cliente MCP tiene un tiempo de espera configurable, lo que permite manejar escenarios de latencia en la comunicación con el servidor.

## Ejemplo de Salida en Consola

```
Running predefined questions with AI model responses:

QUESTION: Can you explain the content of the spring-ai-mcp-overview.txt file?
ASSISTANT: [Respuesta generada por el modelo de IA]

QUESTION: Pleses summarize the content of the spring-ai-mcp-overview.txt file and store it a new summary.md as Markdown format?
ASSISTANT: [Respuesta generada por el modelo de IA]
```

## Configuración Requerida

- **Servidor MCP**: Es necesario tener un servidor MCP configurado y accesible.
- **Dependencias de Maven/Gradle**: Asegúrese de incluir las dependencias necesarias en el archivo de configuración del proyecto.
- **Ruta de la Base de Datos**: La ruta de la base de datos se genera automáticamente en el subdirectorio `target` del directorio de trabajo actual.

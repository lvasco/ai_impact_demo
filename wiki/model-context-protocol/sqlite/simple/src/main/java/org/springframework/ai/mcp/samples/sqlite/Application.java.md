# Documentación de `Application.java`

## Descripción General
El archivo `Application.java` implementa una aplicación Spring Boot que interactúa con un modelo de inteligencia artificial (IA) y una base de datos SQLite. La aplicación utiliza clientes de MCP (Multi-Client Protocol) para realizar operaciones como la inicialización de herramientas, ejecución de preguntas predefinidas y manejo de funciones específicas. Además, se configura un cliente de chat para interactuar con el modelo de IA y responder preguntas relacionadas con la base de datos.

---

## Estructura del Código

### Importaciones
El código importa varias clases y paquetes necesarios para la funcionalidad de la aplicación:
- **Spring Boot**: Para la configuración y ejecución de la aplicación.
- **MCP**: Para la interacción con el protocolo MCP y transporte de datos.
- **ChatClient**: Para la comunicación con el modelo de IA.
- **Java Utilidades**: Para manejo de listas, rutas de archivos y duración.

### Declaración de la Clase
La clase `Application` está anotada con `@SpringBootApplication`, lo que indica que es el punto de entrada de una aplicación Spring Boot.

---

## Componentes Principales

### Método `main`
```java
public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
}
```
Este método inicia la aplicación Spring Boot.

---

### Bean: `CommandLineRunner`
```java
@Bean
public CommandLineRunner predefinedQuestions(ChatClient.Builder chatClientBuilder,
        List<McpFunctionCallback> functionCallbacks, ConfigurableApplicationContext context) {
```
Este bean define un `CommandLineRunner` que ejecuta preguntas predefinidas utilizando un cliente de chat conectado a un modelo de IA. Las preguntas están relacionadas con la base de datos SQLite y abarcan temas como:
1. Consulta de productos y precios.
2. Cálculo del precio promedio.
3. Análisis de distribución de precios.
4. Diseño de una nueva tabla para órdenes de clientes.

#### Flujo de Ejecución:
1. Se construye un cliente de chat con funciones predefinidas.
2. Se ejecutan las preguntas predefinidas y se imprimen las respuestas del modelo de IA.
3. Se cierra el contexto de la aplicación al finalizar.

---

### Bean: `functionCallbacks`
```java
@Bean
public List<McpFunctionCallback> functionCallbacks(McpSyncClient mcpClient) {
```
Este bean genera una lista de `McpFunctionCallback` basada en las herramientas disponibles en el cliente MCP. Cada herramienta se convierte en un callback que puede ser utilizado por el cliente de chat.

---

### Bean: `mcpClient`
```java
@Bean(destroyMethod = "close")
public McpSyncClient mcpClient() {
```
Este bean configura un cliente MCP síncrono para interactuar con el servidor MCP. Se utiliza un transporte basado en `StdioClientTransport` y se define un tiempo de espera para las solicitudes.

#### Flujo de Inicialización:
1. Se configuran los parámetros del servidor MCP, incluyendo la ruta de la base de datos SQLite.
2. Se inicializa el cliente MCP y se imprime el estado de inicialización.

---

### Método Privado: `getDbPath`
```java
private static String getDbPath() {
    return Paths.get(System.getProperty("user.dir"), "test.db").toString();
}
```
Este método obtiene la ruta absoluta del archivo de base de datos SQLite (`test.db`) en el directorio actual del usuario.

---

## Insights

### Uso de MCP
- La aplicación utiliza MCP para interactuar con herramientas externas y realizar operaciones relacionadas con la base de datos SQLite.
- El cliente MCP se inicializa con parámetros específicos, lo que permite una integración directa con el servidor MCP.

### Interacción con IA
- El cliente de chat permite realizar preguntas predefinidas al modelo de IA, lo que facilita la automatización de tareas como análisis de datos y diseño de estructuras de base de datos.

### Modularidad
- La aplicación está diseñada de manera modular, con beans separados para la configuración del cliente MCP, los callbacks de funciones y el cliente de chat. Esto mejora la mantenibilidad y escalabilidad del código.

### Configuración de Base de Datos
- La ruta de la base de datos SQLite se configura dinámicamente utilizando el directorio actual del usuario, lo que permite flexibilidad en entornos de desarrollo y producción.

---

## Tablas

### Preguntas Predefinidas
| **Pregunta**                                                                 | **Propósito**                                                                 |
|------------------------------------------------------------------------------|-------------------------------------------------------------------------------|
| ¿Puedes conectarte a mi base de datos SQLite y decirme qué productos están disponibles y sus precios? | Consultar productos y precios en la base de datos.                           |
| ¿Cuál es el precio promedio de todos los productos en la base de datos?      | Calcular el precio promedio de los productos.                                |
| ¿Puedes analizar la distribución de precios y sugerir optimizaciones de precios? | Realizar un análisis de distribución de precios y sugerir mejoras.           |
| ¿Podrías ayudarme a diseñar y crear una nueva tabla para almacenar órdenes de clientes? | Diseñar una nueva tabla para gestionar órdenes de clientes.                  |

### Beans Configurados
| **Bean**                  | **Propósito**                                                                 |
|---------------------------|-------------------------------------------------------------------------------|
| `CommandLineRunner`       | Ejecutar preguntas predefinidas con el modelo de IA.                         |
| `functionCallbacks`       | Generar una lista de callbacks basados en herramientas MCP.                  |
| `mcpClient`               | Configurar e inicializar el cliente MCP para interactuar con el servidor.    |

---

## Dependencias Clave
| **Paquete/Clase**                  | **Uso**                                                                 |
|------------------------------------|-------------------------------------------------------------------------|
| `org.springframework.boot`         | Configuración y ejecución de la aplicación Spring Boot.                |
| `org.springframework.ai.chat.client.ChatClient` | Comunicación con el modelo de IA para responder preguntas.            |
| `org.springframework.ai.mcp.client.McpClient` | Interacción con el protocolo MCP para operaciones de base de datos.   |
| `java.nio.file.Paths`              | Manejo de rutas de archivos para la base de datos SQLite.              |

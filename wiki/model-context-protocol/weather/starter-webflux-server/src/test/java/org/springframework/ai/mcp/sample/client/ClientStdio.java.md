# Documentación del Código: `ClientStdio.java`

## Descripción General

Este archivo contiene la implementación de un cliente que utiliza transporte estándar de entrada/salida (stdio) para interactuar con un servidor MCP (Model Context Protocol). El servidor MCP se inicia automáticamente por el cliente, pero requiere que el archivo JAR del servidor sea construido previamente.

El propósito principal de este programa es configurar los parámetros del servidor, inicializar el transporte stdio y ejecutar un cliente de ejemplo (`SampleClient`).

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```
org.springframework.ai.mcp.sample.client
```

### Importaciones
El programa utiliza las siguientes clases y bibliotecas:
- `java.io.File`: Para obtener la ruta absoluta del directorio actual.
- `io.modelcontextprotocol.client.transport.ServerParameters`: Para configurar los parámetros del servidor MCP.
- `io.modelcontextprotocol.client.transport.StdioClientTransport`: Para manejar el transporte stdio.

### Clase Principal: `ClientStdio`
La clase contiene un único método `main` que ejecuta la lógica principal del cliente.

---

## Lógica del Programa

### Método `main`
El método `main` realiza las siguientes operaciones:

1. **Impresión de la Ruta Absoluta**:
   - Se imprime en la consola la ruta absoluta del directorio actual utilizando:
     ```java
     System.out.println(new File(".").getAbsolutePath());
     ```

2. **Configuración de Parámetros del Servidor**:
   - Se crea una instancia de `ServerParameters` utilizando un patrón de construcción (`builder`).
   - Los parámetros configurados incluyen:
     - `-Dspring.ai.mcp.server.stdio=true`: Habilita el transporte stdio en el servidor.
     - `-Dspring.main.web-application-type=none`: Desactiva el tipo de aplicación web.
     - `-Dlogging.pattern.console=`: Desactiva el patrón de logging en la consola.
     - `-jar`: Especifica el archivo JAR del servidor que se ejecutará.
     - Ruta del JAR: `model-context-protocol/weather/starter-webflux-server/target/mcp-weather-starter-webflux-server-0.0.1-SNAPSHOT.jar`.

3. **Inicialización del Transporte Stdio**:
   - Se crea una instancia de `StdioClientTransport` utilizando los parámetros configurados.

4. **Ejecución del Cliente de Ejemplo**:
   - Se instancia y ejecuta un cliente de ejemplo (`SampleClient`) utilizando el transporte stdio:
     ```java
     new SampleClient(transport).run();
     ```

---

## Detalles Técnicos

### Configuración del Servidor
La configuración del servidor se realiza mediante la clase `ServerParameters` y utiliza un patrón de construcción (`builder`). Los argumentos proporcionados son específicos para habilitar el transporte stdio y ejecutar un servidor basado en WebFlux.

### Transporte Stdio
El transporte stdio es manejado por la clase `StdioClientTransport`, que permite la comunicación entre el cliente y el servidor MCP utilizando flujos estándar de entrada y salida.

### Dependencias
El programa depende de las siguientes bibliotecas externas:
- `io.modelcontextprotocol.client.transport`: Para manejar la configuración y el transporte del servidor.

---

## Insights

1. **Automatización del Servidor**:
   - Este cliente tiene la capacidad de iniciar automáticamente el servidor MCP, lo que simplifica el proceso de configuración y ejecución.

2. **Requisito de Construcción del JAR**:
   - Antes de ejecutar este cliente, es necesario construir el archivo JAR del servidor utilizando Maven:
     ```bash
     ./mvnw clean install -DskipTests
     ```

3. **Flexibilidad en la Configuración**:
   - El uso de `ServerParameters.builder` permite una configuración flexible y extensible del servidor MCP.

4. **Uso de WebFlux**:
   - El servidor parece estar basado en WebFlux, lo que sugiere que es reactivo y no bloqueante.

5. **Dependencia de `SampleClient`**:
   - La clase `SampleClient` no está definida en este archivo, pero es esencial para la ejecución del cliente. Se asume que esta clase implementa la lógica específica de interacción con el servidor MCP.

---

## Tabla de Parámetros del Servidor

| Parámetro                                | Descripción                                                                 |
|------------------------------------------|-----------------------------------------------------------------------------|
| `-Dspring.ai.mcp.server.stdio=true`      | Habilita el transporte stdio en el servidor.                               |
| `-Dspring.main.web-application-type=none`| Desactiva el tipo de aplicación web.                                       |
| `-Dlogging.pattern.console=`             | Desactiva el patrón de logging en la consola.                              |
| `-jar`                                   | Especifica que se ejecutará un archivo JAR.                                |
| Ruta del JAR                             | `model-context-protocol/weather/starter-webflux-server/target/mcp-weather-starter-webflux-server-0.0.1-SNAPSHOT.jar` | Ruta del archivo JAR del servidor. |

---

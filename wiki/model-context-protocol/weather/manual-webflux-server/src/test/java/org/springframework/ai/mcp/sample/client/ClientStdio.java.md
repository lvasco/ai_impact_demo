# Documentación de `ClientStdio.java`

## Descripción General

El archivo `ClientStdio.java` implementa un cliente que utiliza transporte estándar de entrada/salida (stdio) para interactuar con un servidor MCP (Model Context Protocol). Este cliente inicia automáticamente el servidor MCP, pero requiere que el archivo JAR del servidor sea construido previamente. 

El propósito principal de este programa es demostrar cómo configurar y ejecutar un cliente MCP utilizando transporte stdio.

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```java
package org.springframework.ai.mcp.sample.client;
```

### Importaciones
El programa utiliza las siguientes clases y bibliotecas:
- **`java.io.File`**: Para obtener la ruta absoluta del directorio actual.
- **`io.modelcontextprotocol.client.transport.ServerParameters`**: Para configurar los parámetros del servidor MCP.
- **`io.modelcontextprotocol.client.transport.StdioClientTransport`**: Para manejar el transporte stdio entre el cliente y el servidor.

---

## Lógica del Programa

### Método `main`
El método principal realiza las siguientes operaciones:

1. **Impresión de la Ruta Absoluta**:
   ```java
   System.out.println(new File(".").getAbsolutePath());
   ```
   Muestra en la consola la ruta absoluta del directorio actual. Esto puede ser útil para depuración o para verificar el contexto de ejecución.

2. **Configuración de Parámetros del Servidor**:
   ```java
   var stdioParams = ServerParameters.builder("java")
       .args("-Dtransport.mode=stdio", "-Dspring.main.web-application-type=none", "-jar",
             "model-context-protocol/mcp-weather-server/target/mcp-weather-server-0.0.1-SNAPSHOT.jar")
       .build();
   ```
   - Se utiliza un constructor (`builder`) para configurar los parámetros del servidor.
   - Los argumentos incluyen:
     - `-Dtransport.mode=stdio`: Configura el modo de transporte como stdio.
     - `-Dspring.main.web-application-type=none`: Desactiva el contexto web de Spring.
     - `-jar`: Especifica el archivo JAR del servidor MCP que se ejecutará.

3. **Inicialización del Transporte**:
   ```java
   var transport = new StdioClientTransport(stdioParams);
   ```
   - Se crea una instancia de `StdioClientTransport` utilizando los parámetros configurados previamente.

4. **Ejecución del Cliente**:
   ```java
   new SampleClient(transport).run();
   ```
   - Se crea una instancia de `SampleClient` con el transporte configurado y se ejecuta su método `run()`.

---

## Comentarios Importantes

- **Requisito Previo**: Antes de ejecutar este cliente, es necesario construir el archivo JAR del servidor MCP utilizando el comando:
  ```bash
  ./mvnw clean install -DskipTests
  ```
  Esto asegura que el servidor MCP esté disponible en la ruta especificada.

- **Transporte Stdio**: Este tipo de transporte permite que el cliente inicie automáticamente el servidor MCP, eliminando la necesidad de iniciar el servidor manualmente.

---

## Insights

1. **Uso de `ServerParameters`**:
   - La clase `ServerParameters` permite una configuración flexible del servidor MCP, lo que facilita la personalización de los argumentos de ejecución.

2. **Integración con Spring**:
   - Aunque el servidor MCP utiliza Spring, el cliente desactiva explícitamente el contexto web de Spring (`-Dspring.main.web-application-type=none`), lo que sugiere que este cliente está diseñado para ejecutarse en un entorno no web.

3. **Dependencia del JAR del Servidor**:
   - La ruta del archivo JAR del servidor está codificada en el programa. Esto puede ser un punto de mejora si se desea hacer el cliente más dinámico, permitiendo que la ruta sea configurable.

4. **Ejecución Automática del Servidor**:
   - Este enfoque simplifica la interacción cliente-servidor, pero también introduce una dependencia directa entre el cliente y el servidor, lo que podría limitar la flexibilidad en ciertos escenarios.

---

## Tabla de Argumentos del Servidor

| Argumento                              | Descripción                                                                 |
|----------------------------------------|-----------------------------------------------------------------------------|
| `-Dtransport.mode=stdio`               | Configura el modo de transporte como stdio.                                |
| `-Dspring.main.web-application-type=none` | Desactiva el contexto web de Spring.                                       |
| `-jar`                                 | Especifica el archivo JAR del servidor MCP que se ejecutará.               |
| `model-context-protocol/mcp-weather-server/target/mcp-weather-server-0.0.1-SNAPSHOT.jar` | Ruta del archivo JAR del servidor MCP. |

---

## Licencia

Este archivo está licenciado bajo la **Apache License, Version 2.0**. Para más detalles, consulte el archivo de licencia en [https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0).

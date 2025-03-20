# Documentación de `ClientStdio.java`

## Descripción General

El archivo `ClientStdio.java` implementa un cliente que utiliza transporte estándar de entrada/salida (stdio) para interactuar con un servidor MCP (Model Context Protocol). Este cliente inicia automáticamente el servidor MCP, pero requiere que el archivo JAR del servidor sea construido previamente. 

El código utiliza la biblioteca `modelcontextprotocol` para configurar los parámetros del servidor y ejecutar el cliente. 

## Metadatos

| **Nombre del Archivo** | `ClientStdio.java` |
|-------------------------|--------------------|
| **Paquete**             | `org.springframework.ai.mcp.sample.client` |
| **Licencia**            | Apache License 2.0 |

## Dependencias

El programa depende de las siguientes clases y paquetes:

- **`io.modelcontextprotocol.client.transport.ServerParameters`**: Clase utilizada para configurar los parámetros del servidor MCP.
- **`io.modelcontextprotocol.client.transport.StdioClientTransport`**: Clase que implementa el transporte estándar de entrada/salida para el cliente MCP.
- **`SampleClient`**: Clase que ejecuta el cliente MCP utilizando el transporte configurado.

## Funcionalidad

### Flujo Principal

1. **Configuración de Parámetros del Servidor**:
   - Se utiliza la clase `ServerParameters` para construir los parámetros necesarios para iniciar el servidor MCP.
   - Los parámetros incluyen configuraciones específicas como habilitar el transporte stdio, deshabilitar el tipo de aplicación web, y especificar el archivo JAR del servidor.

2. **Inicialización del Cliente**:
   - Se crea una instancia de `StdioClientTransport` utilizando los parámetros configurados.
   - Se pasa esta instancia al constructor de `SampleClient`.

3. **Ejecución del Cliente**:
   - El método `run()` de `SampleClient` se invoca para iniciar la interacción con el servidor MCP.

### Configuración de Parámetros del Servidor

| **Parámetro**                          | **Descripción**                                                                 |
|----------------------------------------|---------------------------------------------------------------------------------|
| `-Dspring.ai.mcp.server.stdio=true`    | Habilita el transporte stdio para el servidor MCP.                              |
| `-Dspring.main.web-application-type=none` | Desactiva el tipo de aplicación web.                                           |
| `-Dlogging.pattern.console=`           | Desactiva el patrón de registro en la consola.                                 |
| `-jar`                                 | Indica que se ejecutará un archivo JAR.                                        |
| `model-context-protocol/mcp-webflux-server-starter/target/mcp-webflux-server-starter-0.0.1-SNAPSHOT.jar` | Ruta al archivo JAR del servidor MCP. |

## Código

El código principal se encuentra en el método `main` de la clase `ClientStdio`. Este método realiza las siguientes acciones:

```java
public static void main(String[] args) {
    var stdioParams = ServerParameters.builder("java")
        .args("-Dspring.ai.mcp.server.stdio=true", "-Dspring.main.web-application-type=none",
              "-Dlogging.pattern.console=", "-jar",
              "model-context-protocol/mcp-webflux-server-starter/target/mcp-webflux-server-starter-0.0.1-SNAPSHOT.jar")
        .build();

    new SampleClient(new StdioClientTransport(stdioParams)).run();
}
```

## Insights

- **Automatización del Servidor MCP**: Este cliente simplifica la interacción con el servidor MCP al iniciar automáticamente el servidor utilizando transporte stdio. Sin embargo, requiere que el archivo JAR del servidor esté previamente construido.
  
- **Configuración Flexible**: La clase `ServerParameters` permite configurar múltiples parámetros del servidor de manera programática, lo que facilita la personalización según las necesidades del entorno.

- **Uso de Transporte Stdio**: El transporte stdio es útil en escenarios donde se necesita una comunicación directa entre el cliente y el servidor sin depender de protocolos de red más complejos.

- **Dependencia de Maven**: La construcción del archivo JAR del servidor requiere el uso de Maven (`./mvnw clean install -DskipTests`), lo que implica que el entorno de desarrollo debe estar configurado para manejar proyectos Maven.

- **Extensibilidad**: La implementación utiliza clases genéricas como `SampleClient` y `StdioClientTransport`, lo que sugiere que el diseño puede ser extendido para soportar otros tipos de transporte o clientes.

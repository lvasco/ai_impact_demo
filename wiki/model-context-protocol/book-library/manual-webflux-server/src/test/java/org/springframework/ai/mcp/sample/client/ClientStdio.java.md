# Documentación de `ClientStdio.java`

## Descripción General

El archivo `ClientStdio.java` implementa un cliente que utiliza transporte estándar de entrada/salida (stdio) para interactuar con un servidor MCP (Model Context Protocol). Este cliente inicia automáticamente el servidor MCP, pero requiere que el archivo JAR del servidor sea construido previamente. El transporte se configura con parámetros específicos para ejecutar el servidor MCP en modo stdio.

## Metadatos

| **Nombre del Archivo** | `ClientStdio.java` |
|-------------------------|--------------------|
| **Paquete**             | `org.springframework.ai.mcp.sample.client` |
| **Licencia**            | Apache License 2.0 |

## Estructura del Código

### Importaciones

El código utiliza las siguientes clases y paquetes:

| **Clase/Paquete** | **Descripción** |
|--------------------|-----------------|
| `java.io.File` | Para obtener la ruta absoluta del directorio actual. |
| `org.springframework.ai.mcp.client.transport.ServerParameters` | Clase para construir los parámetros del servidor MCP. |
| `org.springframework.ai.mcp.client.transport.StdioClientTransport` | Clase que implementa el transporte stdio para el cliente MCP. |

### Lógica Principal

El programa contiene lógica que realiza las siguientes acciones:

1. **Impresión de la Ruta Absoluta**:
   - Utiliza `File` para imprimir la ruta absoluta del directorio actual.

2. **Configuración de Parámetros del Servidor**:
   - Utiliza `ServerParameters.builder()` para construir los parámetros necesarios para iniciar el servidor MCP. Los parámetros incluyen:
     - Modo de transporte (`stdio`).
     - Tipo de aplicación web (`none`).
     - Ruta al archivo JAR del servidor MCP.

3. **Inicialización del Transporte**:
   - Crea una instancia de `StdioClientTransport` utilizando los parámetros configurados.

4. **Ejecución del Cliente**:
   - Crea una instancia de `SampleClient` con el transporte configurado y ejecuta el cliente mediante el método `run()`.

### Código

```java
public class ClientStdio {

    public static void main(String[] args) {

        // Imprime la ruta absoluta del directorio actual
        System.out.println(new File(".").getAbsolutePath());

        // Configuración de parámetros del servidor MCP
        var stdioParams = ServerParameters.builder("java")
            .args("-Dtransport.mode=stdio", "-Dspring.main.web-application-type=none", "-jar",
                    "model-context-protocol/mcp-webflux-server/target/mcp-webflux-server-0.0.1-SNAPSHOT.jar")
            .build();

        // Inicialización del transporte stdio
        var transport = new StdioClientTransport(stdioParams);

        // Ejecución del cliente
        new SampleClient(transport).run();
    }
}
```

## Detalles Técnicos

### Parámetros del Servidor MCP

| **Parámetro** | **Descripción** |
|---------------|-----------------|
| `-Dtransport.mode=stdio` | Configura el modo de transporte como stdio. |
| `-Dspring.main.web-application-type=none` | Desactiva el tipo de aplicación web de Spring. |
| `-jar` | Indica que se ejecutará un archivo JAR. |
| `model-context-protocol/mcp-webflux-server/target/mcp-webflux-server-0.0.1-SNAPSHOT.jar` | Ruta al archivo JAR del servidor MCP. |

### Clases Utilizadas

| **Clase** | **Función** |
|-----------|-------------|
| `ServerParameters` | Permite construir los parámetros necesarios para iniciar el servidor MCP. |
| `StdioClientTransport` | Implementa el transporte stdio para la comunicación entre el cliente y el servidor MCP. |
| `SampleClient` | Clase que ejecuta la lógica del cliente utilizando el transporte configurado. |

## Insights

1. **Dependencia de Construcción del Servidor**:
   - Antes de ejecutar este cliente, es necesario construir el archivo JAR del servidor MCP utilizando Maven (`./mvnw clean install -DskipTests`).

2. **Flexibilidad en la Configuración**:
   - La clase `ServerParameters` permite configurar fácilmente los parámetros del servidor, lo que facilita la personalización del transporte y otros aspectos del servidor MCP.

3. **Uso de Transporte Stdio**:
   - El transporte stdio es útil para escenarios donde se requiere una comunicación directa entre el cliente y el servidor sin necesidad de configuraciones de red complejas.

4. **Integración con Spring**:
   - Aunque el servidor MCP utiliza Spring, el cliente desactiva el tipo de aplicación web (`none`), lo que sugiere que este cliente está diseñado para ser ligero y específico para la interacción con el servidor MCP.

5. **Ruta Absoluta**:
   - La impresión de la ruta absoluta del directorio actual puede ser útil para depuración o para verificar el entorno de ejecución.

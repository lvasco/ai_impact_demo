# Documentación de `ClientStdio.java`

## Descripción General

El archivo `ClientStdio.java` implementa un cliente que interactúa con un servidor MCP (Model Context Protocol) utilizando transporte estándar de entrada/salida (stdio). Este cliente permite inicializar la conexión con el servidor, listar herramientas disponibles y realizar llamadas a herramientas específicas para obtener datos como pronósticos del clima y alertas.

El servidor MCP se inicia automáticamente por el cliente, pero requiere que el archivo JAR del servidor sea construido previamente.

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```
org.springframework.ai.mcp.sample.client
```

### Importaciones
El programa utiliza las siguientes clases y paquetes:
- **`java.util.Map`**: Para manejar pares clave-valor en las solicitudes.
- **`io.modelcontextprotocol.client.McpClient`**: Para construir y manejar el cliente MCP.
- **`io.modelcontextprotocol.client.transport.ServerParameters`**: Para configurar los parámetros del servidor.
- **`io.modelcontextprotocol.client.transport.StdioClientTransport`**: Para manejar el transporte stdio.
- **`io.modelcontextprotocol.spec.McpSchema.CallToolRequest`**: Representa una solicitud para llamar a una herramienta.
- **`io.modelcontextprotocol.spec.McpSchema.CallToolResult`**: Representa el resultado de una llamada a una herramienta.
- **`io.modelcontextprotocol.spec.McpSchema.ListToolsResult`**: Representa el resultado de listar herramientas disponibles.

### Clase Principal
La clase principal es `ClientStdio`, que contiene el método `main` para ejecutar la lógica del cliente.

## Lógica del Programa

### Inicialización del Servidor
1. **Configuración de parámetros del servidor**:
   - Se utiliza `ServerParameters.builder` para configurar el servidor con el comando `java` y los argumentos necesarios para ejecutar el archivo JAR del servidor MCP.
   - El archivo JAR especificado es: `mcp-weather-stdio-server-0.0.1-SNAPSHOT.jar`.

2. **Creación del transporte**:
   - Se utiliza `StdioClientTransport` para manejar la comunicación con el servidor a través de stdio.

3. **Construcción del cliente**:
   - Se crea una instancia de `McpClient` en modo síncrono utilizando el transporte configurado.

4. **Inicialización del cliente**:
   - Se llama al método `initialize` para establecer la conexión con el servidor.

### Operaciones del Cliente
1. **Listar herramientas disponibles**:
   - Se llama al método `listTools` del cliente para obtener una lista de herramientas disponibles en el servidor.
   - El resultado se imprime en la consola.

2. **Llamar a herramientas específicas**:
   - **Pronóstico del clima**:
     - Se llama a la herramienta `getWeatherForecastByLocation` con parámetros de latitud y longitud (`47.6062`, `-122.3321`).
     - El resultado se imprime en la consola.
   - **Alertas**:
     - Se llama a la herramienta `getAlerts` con el parámetro de estado (`NY`).
     - El resultado se imprime en la consola.

3. **Cierre del cliente**:
   - Se llama al método `closeGracefully` para cerrar la conexión con el servidor de manera segura.

## Ejemplo de Uso

### Construcción del Servidor
Antes de ejecutar el cliente, es necesario construir el archivo JAR del servidor MCP utilizando Maven:
```bash
./mvnw clean install -DskipTests
```

### Ejecución del Cliente
El cliente se ejecuta como una aplicación Java estándar:
```bash
java -cp <ruta_al_classpath> org.springframework.ai.mcp.sample.client.ClientStdio
```

## Insights

1. **Transporte Stdio**:
   - Este cliente utiliza transporte estándar de entrada/salida (stdio), lo que simplifica la interacción con el servidor MCP al no requerir configuraciones de red adicionales.

2. **Extensibilidad**:
   - La arquitectura del cliente permite agregar nuevas herramientas al servidor MCP y llamarlas desde el cliente con facilidad.

3. **Uso de MCP**:
   - MCP (Model Context Protocol) es un protocolo que facilita la interacción entre clientes y servidores para ejecutar herramientas específicas y obtener resultados.

4. **Gestión de Recursos**:
   - El cliente asegura un cierre adecuado de la conexión con el servidor mediante el método `closeGracefully`.

5. **Dependencias**:
   - El cliente depende de bibliotecas específicas de MCP (`io.modelcontextprotocol`) para manejar la comunicación y las operaciones.

## Tablas

### Métodos Principales

| Método                  | Descripción                                                                 |
|-------------------------|-----------------------------------------------------------------------------|
| `initialize()`          | Inicializa la conexión con el servidor MCP.                                |
| `listTools()`           | Lista las herramientas disponibles en el servidor.                        |
| `callTool(CallToolRequest)` | Llama a una herramienta específica con los parámetros proporcionados.     |
| `closeGracefully()`     | Cierra la conexión con el servidor de manera segura.                       |

### Herramientas Llamadas

| Herramienta                     | Parámetros                              | Descripción                          |
|---------------------------------|-----------------------------------------|--------------------------------------|
| `getWeatherForecastByLocation` | `latitude`, `longitude`                 | Obtiene el pronóstico del clima para una ubicación específica. |
| `getAlerts`                    | `state`                                 | Obtiene alertas para un estado específico. |

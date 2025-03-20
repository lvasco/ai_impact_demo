# Documentación de `SampleClient.java`

## Descripción General

El archivo `SampleClient.java` implementa un cliente que interactúa con un sistema basado en el protocolo **Model Context Protocol (MCP)**. Este cliente utiliza un transporte proporcionado por `ClientMcpTransport` para realizar operaciones como inicialización, verificación de conectividad, listado de herramientas disponibles y ejecución de herramientas específicas. 

El propósito principal de esta clase es demostrar cómo interactuar con un cliente MCP para realizar tareas específicas, como obtener pronósticos del clima y alertas.

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```java
package org.springframework.ai.mcp.sample.client;
```

### Importaciones
El código utiliza las siguientes clases y paquetes:
- **`java.util.Map`**: Para manejar pares clave-valor en las solicitudes.
- **`io.modelcontextprotocol.client.McpClient`**: Para construir y manejar el cliente MCP.
- **`io.modelcontextprotocol.spec.ClientMcpTransport`**: Define el transporte utilizado por el cliente MCP.
- **`io.modelcontextprotocol.spec.McpSchema.CallToolRequest`**: Representa una solicitud para ejecutar una herramienta.
- **`io.modelcontextprotocol.spec.McpSchema.CallToolResult`**: Representa el resultado de la ejecución de una herramienta.
- **`io.modelcontextprotocol.spec.McpSchema.ListToolsResult`**: Representa el resultado de listar las herramientas disponibles.

---

## Detalles de la Clase

### Declaración de la Clase
```java
public class SampleClient
```
La clase `SampleClient` es una implementación de un cliente MCP que interactúa con herramientas remotas a través de un transporte definido.

### Atributos
| Atributo              | Tipo                     | Descripción                                                                 |
|-----------------------|--------------------------|-----------------------------------------------------------------------------|
| `transport`           | `ClientMcpTransport`    | Transporte utilizado para la comunicación con el cliente MCP.              |

### Constructor
```java
public SampleClient(ClientMcpTransport transport)
```
El constructor inicializa el cliente con un transporte específico.

| Parámetro   | Tipo                  | Descripción                                      |
|-------------|-----------------------|--------------------------------------------------|
| `transport` | `ClientMcpTransport` | Transporte para la comunicación con el cliente. |

---

## Métodos

### `run()`
```java
public void run()
```
Este método ejecuta la lógica principal del cliente MCP. Realiza las siguientes operaciones:

1. **Inicialización del Cliente**:
   - Construye un cliente MCP síncrono utilizando el transporte proporcionado.
   - Inicializa el cliente llamando a `client.initialize()`.

2. **Verificación de Conectividad**:
   - Realiza un "ping" al servidor para verificar la conectividad.

3. **Listado de Herramientas Disponibles**:
   - Llama al método `client.listTools()` para obtener una lista de herramientas disponibles.
   - Imprime la lista de herramientas en la consola.

4. **Ejecución de Herramientas**:
   - Ejecuta la herramienta `getWeatherForecastByLocation` con parámetros de latitud y longitud.
   - Ejecuta la herramienta `getAlerts` con el parámetro de estado.
   - Imprime los resultados de ambas herramientas en la consola.

5. **Cierre del Cliente**:
   - Cierra el cliente de manera controlada llamando a `client.closeGracefully()`.

| Operación                     | Método Utilizado                  | Descripción                                                                 |
|-------------------------------|------------------------------------|-----------------------------------------------------------------------------|
| Inicialización del cliente    | `client.initialize()`             | Prepara el cliente para su uso.                                            |
| Verificación de conectividad  | `client.ping()`                   | Comprueba la conectividad con el servidor.                                 |
| Listado de herramientas       | `client.listTools()`              | Obtiene una lista de herramientas disponibles.                             |
| Ejecución de herramientas     | `client.callTool(CallToolRequest)`| Ejecuta herramientas específicas con parámetros definidos.                 |
| Cierre del cliente            | `client.closeGracefully()`        | Libera los recursos y cierra la conexión de manera controlada.             |

---

## Ejemplo de Uso

El método `run()` demuestra cómo interactuar con el cliente MCP. A continuación, se muestra un ejemplo de las herramientas ejecutadas:

1. **Pronóstico del Clima**:
   - Herramienta: `getWeatherForecastByLocation`
   - Parámetros: 
     - `latitude`: `"47.6062"`
     - `longitude`: `"-122.3321"`

2. **Alertas**:
   - Herramienta: `getAlerts`
   - Parámetros:
     - `state`: `"NY"`

---

## Insights

1. **Modularidad**:
   - La clase está diseñada para ser reutilizable con diferentes implementaciones de transporte (`ClientMcpTransport`), lo que la hace flexible y extensible.

2. **Uso de MCP**:
   - El cliente utiliza el protocolo MCP para interactuar con herramientas remotas, lo que sugiere que este protocolo es adecuado para sistemas distribuidos.

3. **Manejo de Recursos**:
   - El método `closeGracefully()` asegura que los recursos se liberen correctamente, lo que es crucial en aplicaciones de red.

4. **Demostración de Funcionalidades**:
   - El método `run()` no solo ejecuta las operaciones, sino que también sirve como una demostración de las capacidades del cliente MCP.

5. **Interfaz Síncrona**:
   - El cliente MCP se construye como una interfaz síncrona (`McpClient.sync()`), lo que implica que las operaciones se ejecutan de manera bloqueante. Esto puede ser relevante para aplicaciones donde la sincronización es crítica.

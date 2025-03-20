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
El código utiliza las siguientes dependencias:
- **`java.util.Map`**: Para manejar pares clave-valor en las solicitudes.
- **`io.modelcontextprotocol.client.McpClient`**: Para construir y manejar el cliente MCP.
- **`io.modelcontextprotocol.spec.ClientMcpTransport`**: Define el transporte utilizado por el cliente MCP.
- **`io.modelcontextprotocol.spec.McpSchema.CallToolRequest`**: Representa una solicitud para ejecutar una herramienta.
- **`io.modelcontextprotocol.spec.McpSchema.CallToolResult`**: Representa el resultado de la ejecución de una herramienta.
- **`io.modelcontextprotocol.spec.McpSchema.ListToolsResult`**: Representa el resultado de listar las herramientas disponibles.

### Clase `SampleClient`
La clase `SampleClient` encapsula la lógica para interactuar con el cliente MCP. 

#### Atributos
| Atributo              | Tipo                     | Descripción                                                                 |
|-----------------------|--------------------------|-----------------------------------------------------------------------------|
| `transport`           | `ClientMcpTransport`    | Transporte utilizado para la comunicación con el cliente MCP.              |

#### Constructor
| Constructor                          | Descripción                                                                 |
|--------------------------------------|-----------------------------------------------------------------------------|
| `SampleClient(ClientMcpTransport transport)` | Inicializa el cliente con un transporte específico.                        |

#### Métodos
| Método                  | Retorno  | Descripción                                                                 |
|-------------------------|----------|-----------------------------------------------------------------------------|
| `run()`                 | `void`   | Ejecuta la lógica principal del cliente, incluyendo inicialización, ping, listado de herramientas y ejecución de herramientas. |

---

## Lógica del Programa

El método `run()` contiene la lógica principal del cliente. A continuación, se detalla el flujo de ejecución:

1. **Inicialización del Cliente MCP**:
   - Se crea una instancia de `McpClient` utilizando el transporte proporcionado.
   - Se inicializa el cliente llamando a `client.initialize()`.

2. **Verificación de Conectividad**:
   - Se realiza un "ping" al servidor para verificar la conectividad mediante `client.ping()`.

3. **Listado de Herramientas Disponibles**:
   - Se obtienen las herramientas disponibles llamando a `client.listTools()`.
   - El resultado (`ListToolsResult`) se imprime en la consola.

4. **Ejecución de Herramientas**:
   - **Pronóstico del Clima**:
     - Se ejecuta la herramienta `getWeatherForecastByLocation` con parámetros de latitud y longitud.
     - El resultado (`CallToolResult`) se imprime en la consola.
   - **Alertas**:
     - Se ejecuta la herramienta `getAlerts` con el parámetro de estado.
     - El resultado (`CallToolResult`) se imprime en la consola.

5. **Cierre del Cliente**:
   - Se cierra el cliente de manera controlada llamando a `client.closeGracefully()`.

---

## Ejemplo de Uso

El siguiente es un ejemplo de cómo se podría utilizar la clase `SampleClient`:

```java
ClientMcpTransport transport = new CustomMcpTransport(); // Implementación personalizada del transporte
SampleClient client = new SampleClient(transport);
client.run();
```

---

## Insights

1. **Modularidad**:
   - La clase está diseñada de manera modular, separando la lógica de transporte (`ClientMcpTransport`) de la lógica del cliente (`SampleClient`).

2. **Uso de MCP**:
   - El cliente utiliza el protocolo MCP para interactuar con herramientas remotas, lo que lo hace adecuado para sistemas distribuidos.

3. **Extensibilidad**:
   - La clase puede extenderse fácilmente para soportar nuevas herramientas o funcionalidades del cliente MCP.

4. **Gestión de Recursos**:
   - El cliente asegura un cierre controlado de los recursos mediante el método `client.closeGracefully()`.

5. **Demostración de Funcionalidades**:
   - El código no solo implementa la lógica del cliente, sino que también sirve como una demostración de cómo interactuar con el cliente MCP.

---

## Licencia

Este archivo está licenciado bajo la **Apache License, Version 2.0**. Para más detalles, consulte [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

# Documentación de `SampleClient.java`

## Descripción General

El archivo `SampleClient.java` implementa una clase que actúa como cliente para interactuar con un sistema basado en el protocolo Model Context Protocol (MCP). Este cliente utiliza un transporte específico (`ClientMcpTransport`) para comunicarse con el servidor MCP y realizar diversas operaciones, como inicialización, ping, listado de herramientas disponibles y ejecución de herramientas específicas.

## Estructura del Código

### Paquete
El código está ubicado en el paquete:
```
org.springframework.ai.mcp.sample.client
```

### Importaciones
El programa utiliza las siguientes bibliotecas y clases:
| **Biblioteca/Clase** | **Descripción** |
|-----------------------|-----------------|
| `java.util.Map` | Para manejar pares clave-valor en las solicitudes. |
| `io.modelcontextprotocol.client.McpClient` | Clase principal para interactuar con el cliente MCP. |
| `io.modelcontextprotocol.spec.ClientMcpTransport` | Define el transporte utilizado para la comunicación con el servidor MCP. |
| `io.modelcontextprotocol.spec.McpSchema.CallToolRequest` | Representa una solicitud para ejecutar una herramienta específica. |
| `io.modelcontextprotocol.spec.McpSchema.CallToolResult` | Representa el resultado de la ejecución de una herramienta. |
| `io.modelcontextprotocol.spec.McpSchema.ListToolsResult` | Representa el resultado de listar las herramientas disponibles. |

### Declaración de la Clase
La clase `SampleClient` contiene la lógica para interactuar con el cliente MCP. 

#### Atributos
| **Nombre** | **Tipo** | **Descripción** |
|------------|----------|-----------------|
| `transport` | `ClientMcpTransport` | Transporte utilizado para la comunicación con el servidor MCP. |

#### Constructor
| **Nombre** | **Parámetros** | **Descripción** |
|------------|----------------|-----------------|
| `SampleClient` | `ClientMcpTransport transport` | Inicializa el cliente con el transporte especificado. |

#### Métodos
| **Nombre** | **Descripción** |
|------------|-----------------|
| `run()` | Contiene la lógica principal para interactuar con el cliente MCP. Realiza las siguientes operaciones: inicialización, ping, listado de herramientas, ejecución de herramientas y cierre del cliente. |

## Lógica del Programa

### Flujo de Ejecución
1. **Inicialización del Cliente MCP**:
   - Se crea una instancia de `McpClient` utilizando el transporte proporcionado.
   - Se llama al método `initialize()` para inicializar el cliente.

2. **Ping al Servidor**:
   - Se ejecuta el método `ping()` para verificar la conectividad con el servidor.

3. **Listado de Herramientas Disponibles**:
   - Se llama al método `listTools()` para obtener una lista de herramientas disponibles en el servidor.
   - El resultado se imprime en la consola.

4. **Ejecución de Herramientas**:
   - Se ejecuta la herramienta `getWeatherForecastByLocation` con parámetros de ubicación (latitud y longitud). El resultado se imprime en la consola.
   - Se ejecuta la herramienta `getAlerts` con el parámetro de estado (`state`). El resultado se imprime en la consola.

5. **Cierre del Cliente**:
   - Se llama al método `closeGracefully()` para cerrar el cliente de manera segura.

### Ejemplo de Uso
El método `run()` demuestra cómo interactuar con el cliente MCP para realizar operaciones comunes. Los resultados de cada operación se imprimen en la consola.

## Insights

1. **Modularidad**:
   - La clase está diseñada de manera modular, separando el transporte (`ClientMcpTransport`) de la lógica del cliente (`McpClient`).

2. **Uso de MCP**:
   - El código utiliza el protocolo MCP para realizar operaciones como listado de herramientas y ejecución de herramientas específicas. Esto sugiere que el sistema está diseñado para ser extensible y adaptable a diferentes herramientas.

3. **Gestión de Recursos**:
   - El método `closeGracefully()` asegura que los recursos del cliente se liberen correctamente, lo que es crucial para evitar fugas de memoria o conexiones abiertas.

4. **Interacción con Herramientas**:
   - La clase demuestra cómo interactuar con herramientas específicas mediante solicitudes (`CallToolRequest`) y cómo manejar los resultados (`CallToolResult`).

5. **Flexibilidad**:
   - El uso de `Map` para pasar parámetros a las herramientas permite una gran flexibilidad en la definición de las solicitudes.

## Licencia

El código está licenciado bajo la **Apache License, Version 2.0**. Esto permite su uso, modificación y distribución bajo ciertas condiciones. Para más detalles, consulte [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0).

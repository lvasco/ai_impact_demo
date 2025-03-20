# Documentación de `McpServerConfig.java`

## Descripción General

El archivo `McpServerConfig.java` es una clase de configuración en una aplicación Spring que define y configura los componentes necesarios para un servidor MCP (Model Context Protocol). Este servidor puede operar en diferentes modos de transporte (STDIO o SSE) y está diseñado para interactuar con herramientas y recursos, como un cliente de API de clima.

## Estructura del Código

El código contiene tanto declaraciones de estructuras de datos como lógica de configuración. A continuación, se detalla cada sección:

### 1. **Transporte STDIO**
```java
@Bean
@ConditionalOnProperty(prefix = "transport", name = "mode", havingValue = "stdio")
public StdioServerTransport stdioServerTransport() {
    return new StdioServerTransport();
}
```
- **Descripción**: Configura un transporte basado en STDIO (entrada/salida estándar) para el servidor MCP.
- **Condición**: Este transporte se activa si la propiedad `transport.mode` está configurada como `stdio`.

---

### 2. **Transporte SSE**
```java
@Bean
@ConditionalOnProperty(prefix = "transport", name = "mode", havingValue = "sse")
public WebFluxSseServerTransport sseServerTransport() {
    return new WebFluxSseServerTransport(new ObjectMapper(), "/mcp/message");
}
```
- **Descripción**: Configura un transporte basado en SSE (Server-Sent Events) para el servidor MCP.
- **Condición**: Este transporte se activa si la propiedad `transport.mode` está configurada como `sse`.
- **Detalles Adicionales**: Utiliza un endpoint `/mcp/message` para la comunicación.

---

### 3. **Función de Enrutamiento para SSE**
```java
@Bean
@ConditionalOnProperty(prefix = "transport", name = "mode", havingValue = "sse")
public RouterFunction<?> mcpRouterFunction(WebFluxSseServerTransport transport) {
    return transport.getRouterFunction();
}
```
- **Descripción**: Define una función de enrutamiento para el transporte SSE, utilizada por Spring WebFlux para iniciar un servidor HTTP.
- **Condición**: Se activa si `transport.mode` está configurado como `sse`.

---

### 4. **Cliente de API de Clima**
```java
@Bean
public WeatherApiClient weatherApiClient() {
    return new WeatherApiClient();
}
```
- **Descripción**: Crea una instancia de `WeatherApiClient`, que probablemente se utiliza para interactuar con una API de clima.

---

### 5. **Servidor MCP Sincrónico**
```java
@Bean
public McpSyncServer mcpServer(ServerMcpTransport transport, WeatherApiClient weatherApiClient) {
    var capabilities = McpSchema.ServerCapabilities.builder()
        .tools(true)
        .logging()
        .build();

    McpSyncServer server = McpServer.sync(transport)
        .serverInfo("MCP Demo Weather Server", "1.0.0")
        .capabilities(capabilities)
        .tools(McpToolUtils.toSyncToolRegistration(ToolCallbacks.from(weatherApiClient)))
        .build();
    
    return server;
}
```
- **Descripción**: Configura un servidor MCP sincrónico con las siguientes características:
  - **Capacidades del Servidor**:
    - Soporte para herramientas con notificaciones de cambios en la lista.
    - Soporte para registro de logs.
  - **Información del Servidor**:
    - Nombre: `MCP Demo Weather Server`.
    - Versión: `1.0.0`.
  - **Herramientas**: Registra herramientas sincronizadas basadas en el cliente de API de clima (`WeatherApiClient`).

---

## Clases y Componentes Importados

| Clase/Componente                          | Descripción                                                                 |
|-------------------------------------------|-----------------------------------------------------------------------------|
| `StdioServerTransport`                    | Proporciona transporte basado en STDIO para el servidor MCP.               |
| `WebFluxSseServerTransport`               | Proporciona transporte basado en SSE para el servidor MCP.                 |
| `RouterFunction`                          | Define rutas HTTP para el transporte SSE.                                  |
| `WeatherApiClient`                        | Cliente para interactuar con una API de clima.                             |
| `McpSyncServer`                           | Servidor MCP sincrónico con soporte para herramientas y recursos.          |
| `McpSchema.ServerCapabilities`            | Define las capacidades del servidor MCP.                                   |
| `McpToolUtils` y `ToolCallbacks`          | Utilidades para registrar herramientas en el servidor MCP.                 |

---

## Propiedades de Configuración

| Propiedad               | Valores Posibles | Descripción                                                                 |
|-------------------------|------------------|-----------------------------------------------------------------------------|
| `transport.mode`        | `stdio`, `sse`   | Define el modo de transporte para el servidor MCP.                         |

---

## Insights

1. **Modularidad**: La configuración está diseñada de manera modular, permitiendo activar diferentes modos de transporte (`stdio` o `sse`) según las propiedades de configuración.
2. **Extensibilidad**: El uso de `McpToolUtils` y `ToolCallbacks` sugiere que el servidor puede extenderse fácilmente para soportar nuevas herramientas.
3. **Integración con Spring WebFlux**: La integración con WebFlux para el transporte SSE permite manejar solicitudes HTTP de manera reactiva.
4. **Uso de Condiciones**: El uso de `@ConditionalOnProperty` asegura que solo se carguen los componentes necesarios según la configuración, optimizando el rendimiento y reduciendo el consumo de recursos.

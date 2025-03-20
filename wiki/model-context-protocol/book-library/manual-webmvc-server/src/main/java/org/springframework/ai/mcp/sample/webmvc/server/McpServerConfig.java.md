# Documentación de `McpServerConfig.java`

## Descripción General

El archivo `McpServerConfig.java` define la configuración de un servidor MCP (Message Communication Protocol) utilizando Spring Framework. Este servidor está diseñado para manejar diferentes modos de transporte (SSE y STDIO) y proporciona capacidades como herramientas, recursos y prompts. Además, incluye funcionalidades específicas como la conversión de texto a mayúsculas y la integración con una biblioteca externa (`OpenLibrary`).

---

## Estructura del Código

### Declaraciones de Datos

El archivo contiene las siguientes estructuras de datos:

1. **`ToUpperCaseInput`**: Una clase de registro que encapsula un único campo `input` de tipo `String`. Se utiliza como entrada para una función que convierte texto a mayúsculas.

   ```java
   public static record ToUpperCaseInput(String input) {}
   ```

2. **`SyncResourceRegistration`**: Registro de un recurso que proporciona información del sistema, como la versión de Java, el sistema operativo, etc.

3. **`SyncPromptRegistration`**: Registro de un prompt que genera un mensaje de saludo personalizado basado en un argumento de entrada (`name`).

---

### Lógica del Código

El archivo contiene lógica para:

1. **Configuración de Transporte**:
   - **SSE (Server-Sent Events)**:
     - Define un transporte basado en SSE utilizando `WebMvcSseServerTransport`.
     - Expone un endpoint en `/mcp/message`.
   - **STDIO**:
     - Define un transporte basado en entrada/salida estándar utilizando `StdioServerTransport`.

2. **Configuración del Servidor MCP**:
   - Define las capacidades del servidor, como soporte para herramientas, recursos y prompts.
   - Registra herramientas, recursos y prompts personalizados.
   - Integra una biblioteca externa (`OpenLibrary`) para funcionalidades adicionales.

3. **Herramientas Personalizadas**:
   - **`toUpperCase`**: Convierte un texto a mayúsculas.
   - **`getBooks`**: Obtiene una lista de libros por título desde `OpenLibrary`.
   - **`getBookTitlesByAuthor`**: Obtiene títulos de libros por autor desde `OpenLibrary`.

4. **Recursos Personalizados**:
   - **Información del Sistema**:
     - Proporciona detalles como la versión de Java, el sistema operativo, la arquitectura, etc., en formato JSON.

5. **Prompts Personalizados**:
   - **Saludo Personalizado**:
     - Genera un mensaje de saludo basado en un argumento de entrada (`name`).

---

## Componentes Principales

### Beans Definidos

| Bean                          | Descripción                                                                                     |
|-------------------------------|-------------------------------------------------------------------------------------------------|
| `webMvcSseServerTransport`    | Configura el transporte SSE para manejar mensajes en `/mcp/message`.                           |
| `routerFunction`              | Define las rutas para el transporte SSE.                                                      |
| `stdioServerTransport`        | Configura el transporte STDIO para entrada/salida estándar.                                    |
| `mcpServer`                   | Configura el servidor MCP con capacidades, herramientas, recursos y prompts personalizados.    |
| `openLibrary`                 | Instancia de `OpenLibrary` para interactuar con una API externa de libros.                    |

---

### Capacidades del Servidor MCP

| Capacidad       | Descripción                                                                 |
|------------------|-----------------------------------------------------------------------------|
| Recursos         | Soporte para notificaciones de cambios en la lista de recursos.            |
| Herramientas     | Soporte para herramientas con notificaciones de cambios en la lista.       |
| Prompts          | Soporte para prompts con notificaciones de cambios en la lista.            |
| Logging          | Soporte para registro de eventos.                                          |

---

### Herramientas Registradas

| Herramienta               | Descripción                                | Entrada                  | Salida       |
|---------------------------|--------------------------------------------|--------------------------|--------------|
| `toUpperCase`             | Convierte un texto a mayúsculas.           | `ToUpperCaseInput`       | `String`     |
| `getBooks`                | Obtiene una lista de libros por título.    | `String` (título)        | Lista de libros |
| `getBookTitlesByAuthor`   | Obtiene títulos de libros por autor.       | `String` (autor)         | Lista de títulos |

---

### Recursos Registrados

| Recurso           | URI              | Descripción                                                                 |
|--------------------|------------------|-----------------------------------------------------------------------------|
| Información del Sistema | `system://info` | Proporciona información básica del sistema, como versión de Java y SO.      |

---

### Prompts Registrados

| Prompt    | Descripción                     | Argumentos          | Respuesta                                   |
|-----------|---------------------------------|---------------------|--------------------------------------------|
| `greeting`| Genera un saludo personalizado. | `name` (obligatorio)| Mensaje de saludo con el nombre proporcionado. |

---

## Insights

1. **Modularidad**: El diseño del servidor MCP es modular, permitiendo la adición de nuevas herramientas, recursos y prompts de manera sencilla.
2. **Flexibilidad en Transporte**: Soporta múltiples modos de transporte (SSE y STDIO), lo que lo hace adaptable a diferentes entornos.
3. **Integración Externa**: La integración con `OpenLibrary` demuestra la capacidad del servidor para interactuar con APIs externas.
4. **Uso de Java Records**: La clase `ToUpperCaseInput` utiliza `record`, una característica moderna de Java, para simplificar la definición de clases de datos inmutables.
5. **Soporte para JSON**: Utiliza `ObjectMapper` de Jackson para manejar datos en formato JSON, lo que facilita la interoperabilidad con otros sistemas.

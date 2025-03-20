# Documentación de `McpServerApplication.java`

## Descripción General

El archivo `McpServerApplication.java` implementa una aplicación Spring Boot que actúa como un servidor MCP (Model Context Protocol). Este servidor proporciona herramientas y recursos para interactuar con datos y realizar operaciones específicas, como convertir texto a mayúsculas, registrar recursos del sistema y manejar prompts personalizados.

## Estructura del Código

El código contiene tanto **declaraciones de estructuras de datos** como **lógica funcional**. A continuación, se detalla cada sección:

### Declaraciones de Estructuras de Datos

1. **`ToUpperCaseInput`**: 
   - Una clase de registro (`record`) que encapsula un único campo `input` de tipo `String`.
   - Utilizada como entrada para la herramienta de conversión de texto a mayúsculas.

2. **`McpSchema.Resource`**:
   - Representa un recurso del sistema con metadatos como URI, descripción y tipo de contenido.

3. **`McpSchema.Prompt`**:
   - Define un prompt con un identificador, descripción y argumentos necesarios.

4. **`McpSchema.PromptArgument`**:
   - Representa un argumento requerido para un prompt.

5. **`McpSchema.TextResourceContents`**:
   - Contiene los datos de un recurso en formato de texto.

6. **`McpSchema.PromptMessage`**:
   - Representa un mensaje dentro de un prompt, con un rol y contenido asociado.

### Lógica Funcional

1. **Método `main`**:
   - Punto de entrada de la aplicación Spring Boot.

2. **Definición de Beans**:
   - **`openLibraryTools`**: Proporciona herramientas basadas en métodos de la clase `OpenLibrary`.
   - **`tools`**: Define una herramienta que convierte texto a mayúsculas utilizando una función lambda.
   - **`resourceRegistrations`**: Registra un recurso del sistema que proporciona información básica del sistema operativo y la JVM.
   - **`promptRegistrations`**: Registra un prompt que genera un mensaje de saludo personalizado basado en un argumento de entrada.
   - **`rootsChangeConsumer`**: Define un consumidor que registra cambios en los recursos raíz.

## Detalles de Implementación

### Herramienta: Conversión a Mayúsculas

- **Nombre**: `toUpperCase`
- **Descripción**: Convierte una cadena de texto a mayúsculas.
- **Entrada**: Objeto de tipo `ToUpperCaseInput` con un campo `input` de tipo `String`.
- **Salida**: Cadena de texto en mayúsculas.

### Registro de Recursos del Sistema

- **Recurso**: `system://info`
- **Descripción**: Proporciona información básica del sistema, como:
  - Versión de Java
  - Nombre, versión y arquitectura del sistema operativo
  - Número de procesadores disponibles
  - Marca de tiempo actual
- **Formato de Respuesta**: JSON

### Registro de Prompts

- **Prompt**: `greeting`
- **Descripción**: Genera un mensaje de saludo personalizado.
- **Argumentos**:
  | Nombre | Descripción         | Obligatorio |
  |--------|---------------------|-------------|
  | `name` | Nombre a saludar    | Sí          |
- **Lógica**:
  - Si no se proporciona el argumento `name`, se utiliza el valor predeterminado `"friend"`.
  - Genera un mensaje de usuario con el saludo personalizado.

### Consumidor de Cambios en Recursos Raíz

- **Función**: Registra y registra en el log los cambios en los recursos raíz.
- **Log**: Utiliza SLF4J para registrar los recursos raíz registrados.

## Insights

1. **Uso de Spring Boot**:
   - La aplicación aprovecha la configuración basada en anotaciones de Spring Boot para definir beans y gestionar la inyección de dependencias.

2. **Extensibilidad**:
   - La arquitectura permite agregar fácilmente nuevas herramientas, recursos y prompts mediante la definición de nuevos beans.

3. **Manejo de Excepciones**:
   - El registro de recursos del sistema incluye manejo de excepciones para garantizar la robustez en caso de errores al generar información del sistema.

4. **Interoperabilidad**:
   - Utiliza `ObjectMapper` de Jackson para serializar datos a JSON, lo que facilita la interoperabilidad con otros sistemas.

5. **Uso de Funciones Lambda**:
   - Las herramientas y registros de prompts aprovechan funciones lambda para simplificar la lógica y mejorar la legibilidad del código.

6. **Registro de Logs**:
   - La clase utiliza SLF4J para registrar eventos importantes, como el registro de recursos raíz, lo que facilita la depuración y el monitoreo.

## Dependencias Clave

- **Spring Boot**: Para la configuración y ejecución de la aplicación.
- **Jackson**: Para la serialización de objetos a JSON.
- **SLF4J**: Para el registro de logs.
- **Model Context Protocol (MCP)**: Para la definición de recursos, prompts y herramientas.

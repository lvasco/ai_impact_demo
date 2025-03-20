# Documentación de `McpServerApplication.java`

## Descripción General
El archivo `McpServerApplication.java` define una aplicación Spring Boot que actúa como servidor. Incluye configuraciones para herramientas (`ToolCallback`) que permiten realizar operaciones específicas, como convertir texto a mayúsculas y proporcionar servicios relacionados con el clima. Este archivo contiene tanto lógica como declaraciones de estructuras de datos.

---

## Estructura del Código

### Paquete
El código está ubicado en el paquete:
```
org.springframework.ai.mcp.sample.server
```

### Importaciones
El archivo utiliza las siguientes clases y paquetes:
- **Spring Boot**:
  - `SpringApplication`
  - `SpringBootApplication`
  - `Bean`
- **Herramientas de IA**:
  - `ToolCallback`
  - `ToolCallbackProvider`
  - `FunctionToolCallback`
  - `MethodToolCallbackProvider`

---

## Componentes Principales

### Clase `McpServerApplication`
La clase principal de la aplicación, anotada con `@SpringBootApplication`, sirve como punto de entrada para la ejecución del servidor Spring Boot.

#### Método `main`
```java
public static void main(String[] args) {
    SpringApplication.run(McpServerApplication.class, args);
}
```
Este método inicia la aplicación Spring Boot.

---

### Métodos y Beans

#### `weatherTools`
```java
@Bean
public ToolCallbackProvider weatherTools(WeatherService weatherService) {
    return MethodToolCallbackProvider.builder().toolObjects(weatherService).build();
}
```
- **Propósito**: Proporciona un `ToolCallbackProvider` que utiliza un servicio de clima (`WeatherService`) para realizar operaciones relacionadas con el clima.
- **Dependencia**: Requiere una instancia de `WeatherService`.

---

#### `toUpperCase`
```java
@Bean
public ToolCallback toUpperCase() {
    return FunctionToolCallback.builder("toUpperCase", (TextInput input) -> input.input().toUpperCase())
        .inputType(TextInput.class)
        .description("Put the text to upper case")
        .build();
}
```
- **Propósito**: Define una herramienta (`ToolCallback`) que convierte texto a mayúsculas.
- **Configuración**:
  - Nombre: `toUpperCase`
  - Tipo de entrada: `TextInput`
  - Descripción: "Put the text to upper case"
- **Lógica**: Utiliza una función lambda para transformar el texto proporcionado en mayúsculas.

---

### Registro `TextInput`
```java
public record TextInput(String input) {
}
```
- **Propósito**: Representa una estructura de datos simple para encapsular texto de entrada.
- **Campo**:
  - `input`: Cadena de texto que será procesada.

---

## Insights

### Uso de Spring Boot
La clase utiliza la anotación `@SpringBootApplication` para configurar automáticamente la aplicación y simplificar la inicialización del servidor.

### Herramientas de IA
El código implementa herramientas (`ToolCallback`) que pueden ser utilizadas para realizar operaciones específicas. Esto sugiere que la aplicación está diseñada para ser extensible y modular.

### Diseño Modular
El uso de `ToolCallbackProvider` y `FunctionToolCallback` permite agregar nuevas herramientas fácilmente sin modificar la lógica principal de la aplicación.

### Registro `TextInput`
El uso de `record` en Java para definir `TextInput` es una elección moderna que simplifica la creación de clases de datos inmutables.

### Dependencias Externas
El método `weatherTools` depende de un servicio externo (`WeatherService`), lo que indica que la aplicación puede interactuar con APIs o servicios relacionados con el clima.

---

## Tabla de Configuración de Beans

| **Bean**         | **Tipo**                  | **Propósito**                          | **Dependencia**       |
|-------------------|---------------------------|----------------------------------------|-----------------------|
| `weatherTools`    | `ToolCallbackProvider`    | Proveer herramientas relacionadas con el clima | `WeatherService`      |
| `toUpperCase`     | `ToolCallback`           | Convertir texto a mayúsculas           | `TextInput`           |

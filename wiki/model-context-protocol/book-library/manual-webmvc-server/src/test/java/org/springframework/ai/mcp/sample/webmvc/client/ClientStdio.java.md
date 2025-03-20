# Documentación de `ClientStdio.java`

## Descripción General

El archivo `ClientStdio.java` implementa un cliente que utiliza un transporte basado en `stdio` para interactuar con un servidor MCP (Model Context Protocol). Este cliente inicia automáticamente el servidor MCP, pero requiere que el archivo JAR del servidor sea construido previamente. El transporte `stdio` permite la comunicación entre el cliente y el servidor a través de la entrada y salida estándar.

## Metadatos

| Atributo       | Valor                                                                 |
|----------------|-----------------------------------------------------------------------|
| **Nombre**     | `ClientStdio.java`                                                   |
| **Paquete**    | `org.springframework.ai.mcp.sample.webmvc.client`                    |
| **Licencia**   | Apache License, Version 2.0                                          |
| **Dependencias** | `ServerParameters`, `StdioClientTransport`, `SampleClient`          |

## Funcionalidad

El programa realiza las siguientes acciones principales:

1. **Configuración de Parámetros del Servidor**:  
   Se construyen los parámetros necesarios para iniciar el servidor MCP utilizando la clase `ServerParameters`. Estos parámetros incluyen:
   - El comando para ejecutar el servidor (`java`).
   - Argumentos específicos como el modo de transporte (`stdio`), el tipo de aplicación Spring (`none`), y la ruta al archivo JAR del servidor.

2. **Inicialización del Transporte**:  
   Se crea una instancia de `StdioClientTransport` utilizando los parámetros configurados previamente. Este transporte se encarga de gestionar la comunicación entre el cliente y el servidor.

3. **Ejecución del Cliente**:  
   Se instancia y ejecuta un cliente de ejemplo (`SampleClient`) que utiliza el transporte configurado para interactuar con el servidor.

## Código

### Declaración de Clases y Métodos

| Clase          | Método         | Descripción                                                                 |
|----------------|----------------|-----------------------------------------------------------------------------|
| `ClientStdio`  | `main`         | Método principal que configura el transporte, inicializa el cliente y lo ejecuta. |

### Lógica Principal

El método `main` contiene la lógica principal del programa:

```java
public static void main(String[] args) {

    var stdioParams = ServerParameters.builder("java")
        .args("-Dtransport.mode=stdio", "-Dspring.main.web-application-type=none", "-jar",
                "model-context-protocol/mcp-webmvc-server/target/mcp-webmvc-server-0.0.1-SNAPSHOT.jar")
        .build();

    var transport = new StdioClientTransport(stdioParams);

    new SampleClient(transport).run();
}
```

#### Detalles de Implementación

1. **Construcción de Parámetros del Servidor**:
   - Se utiliza el método `builder` de `ServerParameters` para configurar los parámetros necesarios.
   - Los argumentos incluyen configuraciones específicas para el transporte y la ruta al archivo JAR del servidor.

2. **Inicialización del Transporte**:
   - Se crea una instancia de `StdioClientTransport` con los parámetros configurados.

3. **Ejecución del Cliente**:
   - Se instancia un cliente de ejemplo (`SampleClient`) y se llama a su método `run` para iniciar la interacción con el servidor.

## Insights

- **Dependencia del JAR del Servidor**:  
  El cliente requiere que el archivo JAR del servidor MCP esté construido previamente. Esto se menciona explícitamente en el comentario de la clase, indicando el comando necesario para construirlo:  
  ```bash
  ./mvnw clean install -DskipTests
  ```

- **Uso de `stdio` como Transporte**:  
  El transporte basado en `stdio` es útil para escenarios donde se desea una comunicación simple y directa entre el cliente y el servidor sin necesidad de configuraciones adicionales de red.

- **Flexibilidad en la Configuración**:  
  La clase `ServerParameters` permite una configuración flexible de los parámetros del servidor, lo que facilita la personalización según las necesidades del entorno.

- **Integración con Spring**:  
  Aunque el servidor utiliza Spring, el cliente desactiva explícitamente el tipo de aplicación web de Spring (`-Dspring.main.web-application-type=none`), lo que sugiere que no se requiere un entorno web para este cliente.

## Tabla de Argumentos del Servidor

| Argumento                              | Descripción                                                                 |
|----------------------------------------|-----------------------------------------------------------------------------|
| `-Dtransport.mode=stdio`               | Configura el modo de transporte como `stdio`.                              |
| `-Dspring.main.web-application-type=none` | Desactiva el tipo de aplicación web de Spring.                             |
| `-jar`                                 | Especifica que se ejecutará un archivo JAR.                                |
| `model-context-protocol/mcp-webmvc-server/target/mcp-webmvc-server-0.0.1-SNAPSHOT.jar` | Ruta al archivo JAR del servidor MCP.                                      |

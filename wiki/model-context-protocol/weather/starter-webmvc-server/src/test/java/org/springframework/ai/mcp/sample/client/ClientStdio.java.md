# Documentación de `ClientStdio.java`

## Descripción General

El archivo `ClientStdio.java` implementa un cliente que utiliza transporte estándar de entrada/salida (stdio) para interactuar con un servidor MCP (Model Context Protocol). Este cliente tiene la capacidad de iniciar automáticamente el servidor MCP, siempre y cuando el archivo JAR del servidor haya sido construido previamente.

El propósito principal de este programa es demostrar cómo configurar y ejecutar un cliente MCP utilizando transporte stdio, con parámetros específicos para el servidor.

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```java
org.springframework.ai.mcp.sample.client
```

### Importaciones
El programa utiliza las siguientes clases y bibliotecas:
- `java.io.File`: Para obtener la ruta absoluta del directorio actual.
- `io.modelcontextprotocol.client.transport.ServerParameters`: Para configurar los parámetros del servidor MCP.
- `io.modelcontextprotocol.client.transport.StdioClientTransport`: Para manejar el transporte stdio entre el cliente y el servidor.

### Clase Principal
La clase principal es `ClientStdio`, que contiene el método `main` para ejecutar el cliente.

---

## Detalles del Código

### Método `main`
El método `main` realiza las siguientes operaciones:

1. **Impresión de la Ruta Absoluta**:
   ```java
   System.out.println(new File(".").getAbsolutePath());
   ```
   Imprime la ruta absoluta del directorio actual, lo que puede ser útil para depuración o verificación del entorno de ejecución.

2. **Configuración de Parámetros del Servidor**:
   Se crea un objeto `ServerParameters` utilizando un patrón de construcción (`builder`). Los parámetros configurados incluyen:
   - `java`: Comando para ejecutar la JVM.
   - Argumentos específicos para el servidor:
     - `-Dspring.ai.mcp.server.stdio=true`: Habilita el transporte stdio en el servidor.
     - `-Dspring.main.web-application-type=none`: Desactiva el tipo de aplicación web.
     - `-Dlogging.pattern.console=`: Desactiva el patrón de registro en consola.
     - `-jar`: Indica que se ejecutará un archivo JAR.
     - Ruta al archivo JAR del servidor: `model-context-protocol/weather/starter-webmvc-server/target/mcp-weather-starter-webmvc-server-0.0.1-SNAPSHOT.jar`.

   Ejemplo de construcción:
   ```java
   var stdioParams = ServerParameters.builder("java")
       .args("-Dspring.ai.mcp.server.stdio=true", "-Dspring.main.web-application-type=none",
             "-Dlogging.pattern.console=", "-jar",
             "model-context-protocol/weather/starter-webmvc-server/target/mcp-weather-starter-webmvc-server-0.0.1-SNAPSHOT.jar")
       .build();
   ```

3. **Inicialización del Transporte**:
   Se crea un objeto `StdioClientTransport` utilizando los parámetros configurados:
   ```java
   var transport = new StdioClientTransport(stdioParams);
   ```

4. **Ejecución del Cliente**:
   Se instancia y ejecuta un cliente de ejemplo (`SampleClient`) utilizando el transporte configurado:
   ```java
   new SampleClient(transport).run();
   ```

---

## Requisitos Previos

Antes de ejecutar este cliente, es necesario construir el archivo JAR del servidor MCP. Esto se puede lograr ejecutando el siguiente comando en el directorio del proyecto:
```bash
./mvnw clean install -DskipTests
```

---

## Insights

1. **Transporte Stdio**:
   - Este cliente utiliza transporte stdio, lo que significa que la comunicación entre el cliente y el servidor se realiza a través de los flujos estándar de entrada y salida.
   - Es una solución útil para entornos donde no se desea configurar un servidor HTTP o similar.

2. **Configuración del Servidor**:
   - Los parámetros del servidor están altamente personalizados para deshabilitar características innecesarias (como el tipo de aplicación web y el registro en consola) y enfocarse en el transporte stdio.

3. **Dependencia del Archivo JAR**:
   - El cliente depende de un archivo JAR específico del servidor (`mcp-weather-starter-webmvc-server-0.0.1-SNAPSHOT.jar`), que debe estar disponible en la ruta especificada.

4. **Extensibilidad**:
   - La clase `SampleClient` no está definida en este archivo, pero su uso sugiere que el cliente puede ser extendido o personalizado para diferentes casos de uso.

---

## Tabla de Parámetros del Servidor

| Parámetro                              | Descripción                                                                 |
|---------------------------------------|-----------------------------------------------------------------------------|
| `-Dspring.ai.mcp.server.stdio=true`   | Habilita el transporte stdio en el servidor.                               |
| `-Dspring.main.web-application-type=none` | Desactiva el tipo de aplicación web.                                       |
| `-Dlogging.pattern.console=`          | Desactiva el patrón de registro en consola.                                |
| `-jar`                                | Indica que se ejecutará un archivo JAR.                                    |
| Ruta al JAR                           | `model-context-protocol/weather/starter-webmvc-server/target/mcp-weather-starter-webmvc-server-0.0.1-SNAPSHOT.jar` |

---

## Licencia

Este archivo está licenciado bajo la **Apache License, Version 2.0**. Para más detalles, consulte el archivo de licencia en:
[https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0)

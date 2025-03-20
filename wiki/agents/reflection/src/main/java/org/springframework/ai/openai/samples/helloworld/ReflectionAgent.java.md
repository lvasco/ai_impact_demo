# Documentación de `ReflectionAgent.java`

## Metadatos

| **Nombre del Archivo** | ReflectionAgent.java |
|-------------------------|----------------------|

---

## Descripción General

El archivo `ReflectionAgent.java` define una clase que implementa un agente de reflexión utilizando clientes de chat (`ChatClient`) para generar contenido y proporcionar críticas iterativas. Este agente está diseñado para interactuar con modelos de chat y realizar tareas de generación y evaluación de contenido en un ciclo iterativo.

La clase utiliza dos instancias de `ChatClient`:
1. **generateChatClient**: Para generar contenido basado en las solicitudes del usuario.
2. **critiqueChatClient**: Para proporcionar críticas y recomendaciones sobre el contenido generado.

---

## Estructura de la Clase

### Paquete
La clase pertenece al paquete:
```java
org.springframework.ai.openai.samples.helloworld
```

### Importaciones
La clase utiliza las siguientes dependencias:
- `org.springframework.ai.chat.client.ChatClient`
- `org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor`
- `org.springframework.ai.chat.memory.InMemoryChatMemory`
- `org.springframework.ai.chat.model.ChatModel`
- `org.springframework.stereotype.Component`

### Anotación
La clase está marcada con la anotación `@Component`, lo que indica que es un componente gestionado por el contenedor de Spring.

---

## Declaración de la Clase

```java
public class ReflectionAgent {
```

### Atributos Privados
| **Nombre**              | **Tipo**         | **Descripción**                                                                 |
|-------------------------|------------------|---------------------------------------------------------------------------------|
| `generateChatClient`    | `ChatClient`     | Cliente de chat utilizado para generar contenido basado en las solicitudes del usuario. |
| `critiqueChatClient`    | `ChatClient`     | Cliente de chat utilizado para proporcionar críticas y recomendaciones sobre el contenido generado. |

---

## Constructor

```java
public ReflectionAgent(ChatModel chatModel)
```

### Parámetros
| **Nombre**   | **Tipo**     | **Descripción**                                                                 |
|--------------|--------------|---------------------------------------------------------------------------------|
| `chatModel`  | `ChatModel`  | Modelo de chat utilizado para construir las instancias de `ChatClient`.         |

### Lógica del Constructor
1. **generateChatClient**: Se configura con un sistema predeterminado que describe su tarea como generar contenido Java de alta calidad. También utiliza un asesor de memoria de chat basado en memoria en el sistema.
2. **critiqueChatClient**: Se configura con un sistema predeterminado que describe su tarea como proporcionar críticas y recomendaciones sobre el contenido generado. También utiliza un asesor de memoria de chat basado en memoria en el sistema.

---

## Métodos

### `run`

```java
public String run(String userQuestion, int maxIterations)
```

#### Descripción
Este método ejecuta un ciclo iterativo de generación y crítica de contenido basado en la entrada del usuario. El proceso se detiene cuando se encuentra la secuencia `<OK>` en la crítica o cuando se alcanza el número máximo de iteraciones.

#### Parámetros
| **Nombre**       | **Tipo**   | **Descripción**                                                                 |
|------------------|------------|---------------------------------------------------------------------------------|
| `userQuestion`   | `String`   | Pregunta o solicitud del usuario para generar contenido.                        |
| `maxIterations`  | `int`      | Número máximo de iteraciones para el ciclo de generación y crítica.             |

#### Retorno
| **Tipo**   | **Descripción**                                                                 |
|------------|---------------------------------------------------------------------------------|
| `String`   | Contenido generado final después de las iteraciones de generación y crítica.    |

#### Lógica del Método
1. **Generación inicial**: Utiliza `generateChatClient` para generar contenido basado en la pregunta del usuario.
2. **Iteraciones**:
   - Utiliza `critiqueChatClient` para criticar el contenido generado.
   - Si la crítica contiene `<OK>`, el ciclo se detiene.
   - Si no, utiliza la crítica como entrada para generar una nueva versión del contenido.
3. **Retorno**: Devuelve el contenido generado final.

---

## Insights

1. **Uso de Spring**: La clase está diseñada como un componente de Spring, lo que facilita su integración en aplicaciones basadas en este framework.
2. **Iteración Controlada**: El método `run` implementa un ciclo iterativo que permite mejorar el contenido generado hasta alcanzar un estado aceptable o hasta que se alcance el límite de iteraciones.
3. **Memoria de Chat**: La clase utiliza `InMemoryChatMemory` para mantener el contexto de las interacciones, lo que puede ser útil para mejorar la coherencia en las respuestas generadas.
4. **Separación de Responsabilidades**: La clase utiliza dos clientes de chat separados para generación y crítica, lo que permite una clara separación de responsabilidades y facilita la extensibilidad.
5. **Configuración Flexible**: Los clientes de chat se configuran con sistemas predeterminados y asesores, lo que permite personalizar su comportamiento según las necesidades del usuario.

---

## Ejemplo de Uso

```java
ChatModel chatModel = new ChatModel(); // Instancia del modelo de chat
ReflectionAgent agent = new ReflectionAgent(chatModel);

String userQuestion = "Genera un programa en Java que calcule el factorial de un número.";
int maxIterations = 5;

String result = agent.run(userQuestion, maxIterations);
System.out.println("Resultado final:\n" + result);
```

---

## Licencia

Este archivo está licenciado bajo la **Apache License, Version 2.0**. Para más detalles, consulte [la licencia](https://www.apache.org/licenses/LICENSE-2.0).

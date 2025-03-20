# Documentación de `RoutingWorkflow.java`

## Descripción General

La clase `RoutingWorkflow` implementa un patrón de flujo de trabajo de enrutamiento que clasifica entradas y las dirige a tareas especializadas de seguimiento. Este enfoque permite la separación de responsabilidades al enrutar diferentes tipos de entradas a procesos optimizados para categorías específicas.

### Características Principales

- **Clasificación de Contenido**: Utiliza un modelo de lenguaje (LLM) o un modelo de clasificación tradicional para analizar y clasificar entradas.
- **Procesamiento Especializado**: Cada ruta tiene un prompt especializado optimizado para tipos específicos de entrada.
- **Casos de Uso Comunes**:
  - Sistemas de soporte al cliente que enrutan consultas (facturación, soporte técnico, etc.).
  - Sistemas de moderación de contenido que dirigen contenido a procesos de revisión adecuados.
  - Optimización de consultas al enrutar preguntas simples/complejas a capacidades de modelos diferentes.

### Dependencias

- **Spring AI**: Utiliza el cliente de chat de Spring AI (`ChatClient`) para interactuar con modelos de lenguaje y convertir respuestas en objetos estructurados (`RoutingResponse`).
- **Java Util**: Utiliza clases como `Map` y `Assert` para manejar datos y validar entradas.

---

## Métodos Públicos

### `RoutingWorkflow(ChatClient chatClient)`

#### Descripción
Constructor que inicializa la clase con una instancia de `ChatClient`.

#### Parámetros
- `chatClient`: Instancia del cliente de chat que se utilizará para interactuar con el modelo de lenguaje.

---

### `String route(String input, Map<String, String> routes)`

#### Descripción
Enruta una entrada a un prompt especializado basado en la clasificación del contenido. Este método analiza la entrada, selecciona la ruta más adecuada y procesa la entrada utilizando el prompt especializado correspondiente.

#### Proceso
1. **Análisis de Contenido**: Determina la categoría más adecuada para la entrada.
2. **Selección de Prompt**: Escoge el prompt especializado para la categoría seleccionada.
3. **Procesamiento**: Procesa la entrada con el prompt seleccionado.

#### Parámetros
- `input`: Texto de entrada que será clasificado y procesado.
- `routes`: Mapa de nombres de rutas y sus prompts especializados.

#### Retorno
- `String`: Respuesta procesada desde la ruta especializada seleccionada.

#### Validaciones
- `input` no puede ser nulo.
- `routes` no puede ser nulo ni vacío.

#### Excepciones
- `IllegalArgumentException`: Si la ruta seleccionada no se encuentra en el mapa de rutas.

---

## Métodos Privados

### `String determineRoute(String input, Iterable<String> availableRoutes)`

#### Descripción
Analiza el contenido de la entrada y determina la ruta más adecuada basada en la clasificación del contenido. Utiliza un modelo de lenguaje para analizar términos clave, contexto y patrones en la entrada.

#### Proceso
1. **Análisis de Contenido**: Utiliza un prompt para analizar la entrada y las opciones disponibles.
2. **Selección de Ruta**: Devuelve la clave de la ruta seleccionada basada en el análisis.

#### Parámetros
- `input`: Texto de entrada que será analizado.
- `availableRoutes`: Conjunto de opciones de rutas disponibles.

#### Retorno
- `String`: Clave de la ruta seleccionada.

---

## Estructuras de Datos

### `RoutingResponse`

La clase utiliza un objeto estructurado llamado `RoutingResponse` para representar la respuesta del modelo de lenguaje. Este objeto incluye:
- `reasoning`: Explicación breve de por qué la entrada debe ser dirigida a un equipo específico.
- `selection`: Nombre del equipo seleccionado.

---

## Insights

1. **Separación de Responsabilidades**: La implementación permite manejar entradas diversas de manera eficiente al delegar cada tipo de entrada a un proceso especializado.
2. **Uso de LLM**: La clase aprovecha modelos de lenguaje para realizar análisis de contenido y clasificación, lo que mejora la precisión en la selección de rutas.
3. **Validaciones Robustas**: Se asegura que las entradas y configuraciones sean válidas antes de proceder, reduciendo errores en tiempo de ejecución.
4. **Flexibilidad**: El diseño permite agregar nuevas rutas y prompts especializados sin modificar la lógica principal.
5. **Uso de Prompts Dinámicos**: Los prompts se generan dinámicamente para adaptarse a las opciones de rutas disponibles y al contenido de entrada.

---

## Referencias

- [Spring AI ChatClient](https://docs.spring.io/spring-ai/reference/1.0/api/chatclient.html)
- [Spring AI Structure Output](https://docs.spring.io/spring-ai/reference/1.0/api/structured-output-converter.html)
- [Building Effective Agents](https://www.anthropic.com/research/building-effective-agents)

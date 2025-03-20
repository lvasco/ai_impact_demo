# Documentación de `ParallelizationlWorkflow`

## Descripción General

La clase `ParallelizationlWorkflow` implementa el patrón de flujo de trabajo de paralelización para el procesamiento concurrente eficiente de múltiples operaciones de Modelos de Lenguaje (LLM). Este patrón permite la ejecución paralela de llamadas a LLM con agregación automática de resultados, mejorando significativamente el rendimiento en escenarios de procesamiento por lotes.

### Variaciones del Patrón

1. **Sectioning**: Divide una tarea compleja en subtareas independientes que pueden procesarse de manera concurrente. Ejemplo: analizar diferentes secciones de un documento simultáneamente.
2. **Voting**: Ejecuta múltiples veces el mismo prompt en paralelo para obtener diversas perspectivas o implementar mecanismos de votación por mayoría. Útil para tareas de validación o construcción de consenso.

### Beneficios Clave

- Mayor rendimiento mediante procesamiento concurrente.
- Mejor utilización de la capacidad de la API de LLM.
- Reducción del tiempo total de procesamiento para operaciones por lotes.
- Mejora en la calidad de los resultados mediante múltiples perspectivas (en escenarios de votación).

### Casos de Uso

- Procesamiento de grandes volúmenes de elementos similares pero independientes.
- Tareas que requieren múltiples perspectivas independientes o validaciones.
- Escenarios donde el tiempo de procesamiento es crítico y las tareas son paralelizables.
- Operaciones complejas que pueden descomponerse en subtareas independientes.

### Consideraciones de Implementación

- Asegurarse de que las tareas sean verdaderamente independientes para evitar problemas de consistencia.
- Considerar los límites de tasa de la API al determinar la capacidad de ejecución paralela.
- Monitorear el uso de recursos (memoria, CPU) al escalar operaciones paralelas.
- Implementar un manejo adecuado de errores para fallos en tareas paralelas.

---

## Detalles de la Clase

### Paquete

```java
package com.example.agentic;
```

### Dependencias Importadas

| Paquete/Clase                          | Descripción                                                                 |
|----------------------------------------|-----------------------------------------------------------------------------|
| `java.util.List`                       | Para manejar listas de entradas y resultados.                              |
| `java.util.concurrent.CompletableFuture` | Para manejar tareas asíncronas y paralelas.                                |
| `java.util.concurrent.ExecutorService` | Para gestionar un grupo de hilos.                                          |
| `java.util.concurrent.Executors`       | Para crear un grupo de hilos con un número fijo de trabajadores.           |
| `java.util.stream.Collectors`          | Para recolectar resultados de flujos.                                      |
| `org.springframework.ai.chat.client.ChatClient` | Cliente para interactuar con la API de LLM.                                |
| `org.springframework.util.Assert`     | Para validar argumentos de entrada.                                        |

---

## Métodos

### Constructor

```java
public ParallelizationlWorkflow(ChatClient chatClient)
```

#### Descripción
Inicializa una instancia de `ParallelizationlWorkflow` con un cliente de chat (`ChatClient`) para interactuar con la API de LLM.

#### Parámetros
- `chatClient`: Instancia de `ChatClient` utilizada para realizar llamadas a la API de LLM.

---

### `parallel`

```java
public List<String> parallel(String prompt, List<String> inputs, int nWorkers)
```

#### Descripción
Procesa múltiples entradas de manera concurrente utilizando un grupo de hilos fijo y un mismo template de prompt. Mantiene el orden de los resultados correspondiente al orden de las entradas.

#### Parámetros
| Parámetro   | Tipo            | Descripción                                                                                     |
|-------------|-----------------|-------------------------------------------------------------------------------------------------|
| `prompt`    | `String`        | Template del prompt que se usará para cada entrada. No puede ser `null`.                        |
| `inputs`    | `List<String>`  | Lista de cadenas de entrada a procesar. Cada entrada se procesa de manera independiente.        |
| `nWorkers`  | `int`           | Número de hilos concurrentes a utilizar. Debe ser mayor a 0.                                    |

#### Retorno
- `List<String>`: Lista de resultados procesados en el mismo orden que las entradas.

#### Excepciones
| Excepción                     | Condición                                                                                       |
|-------------------------------|-------------------------------------------------------------------------------------------------|
| `IllegalArgumentException`    | Si `prompt` es `null`, `inputs` es `null` o está vacío, o si `nWorkers` es menor o igual a 0.   |
| `RuntimeException`            | Si falla el procesamiento de alguna entrada. La causa contiene detalles del error específico.   |

#### Implementación
1. Valida los parámetros de entrada.
2. Crea un grupo de hilos fijo con `nWorkers`.
3. Procesa cada entrada en paralelo utilizando `CompletableFuture`.
4. Espera a que todas las tareas se completen.
5. Devuelve los resultados en el mismo orden que las entradas.
6. Cierra el grupo de hilos al finalizar.

---

## Insights

- **Patrón de Diseño**: Este código implementa el patrón de diseño de paralelización, ideal para tareas que pueden dividirse en subtareas independientes.
- **Manejo de Errores**: Se utiliza `RuntimeException` para encapsular errores específicos durante el procesamiento de entradas.
- **Escalabilidad**: El uso de un grupo de hilos fijo permite controlar el número de tareas concurrentes, lo que es crucial para respetar los límites de tasa de la API y optimizar el uso de recursos.
- **Integración con LLM**: La clase está diseñada para interactuar con APIs de LLM a través de `ChatClient`, lo que la hace adecuada para aplicaciones que requieren procesamiento de lenguaje natural.
- **Orden de Resultados**: Garantiza que los resultados se devuelvan en el mismo orden que las entradas, lo cual es importante para mantener la correlación entre entrada y salida.

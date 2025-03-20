# Documentación de `OrchestratorWorkers.java`

## Información General

**Archivo:** `OrchestratorWorkers.java`  
**Paquete:** `com.example.agentic`  
**Licencia:** Apache License 2.0  

Este archivo implementa el patrón de diseño **Orchestrator-workers**, que utiliza un modelo de lenguaje central (LLM) como orquestador para descomponer tareas complejas en subtareas, delegarlas a modelos especializados (trabajadores) y sintetizar los resultados en una respuesta cohesiva. Este enfoque es útil para tareas adaptativas que requieren coordinación entre múltiples componentes especializados.

---

## Componentes Clave

### Clases y Registros

| **Clase/Registro**         | **Descripción**                                                                 |
|-----------------------------|---------------------------------------------------------------------------------|
| `OrchestratorWorkers`       | Clase principal que implementa el patrón Orchestrator-workers.                 |
| `Task`                      | Representa una subtarea identificada por el orquestador para ser ejecutada.    |
| `OrchestratorResponse`      | Contiene el análisis del orquestador y las subtareas identificadas.            |
| `FinalResponse`             | Contiene el análisis del orquestador y las respuestas combinadas de los trabajadores. |

### Constantes

| **Constante**                     | **Descripción**                                                                 |
|-----------------------------------|---------------------------------------------------------------------------------|
| `DEFAULT_ORCHESTRATOR_PROMPT`     | Prompt predeterminado para el orquestador.                                      |
| `DEFAULT_WORKER_PROMPT`           | Prompt predeterminado para los trabajadores.                                    |

---

## Métodos Públicos

### Constructor: `OrchestratorWorkers(ChatClient chatClient)`
Crea una instancia de `OrchestratorWorkers` utilizando los prompts predeterminados.

| **Parámetro**       | **Descripción**                                                                 |
|----------------------|---------------------------------------------------------------------------------|
| `chatClient`         | Cliente de chat utilizado para interactuar con los modelos de lenguaje.        |

---

### Constructor: `OrchestratorWorkers(ChatClient chatClient, String orchestratorPrompt, String workerPrompt)`
Crea una instancia de `OrchestratorWorkers` utilizando prompts personalizados.

| **Parámetro**         | **Descripción**                                                                 |
|------------------------|---------------------------------------------------------------------------------|
| `chatClient`           | Cliente de chat utilizado para interactuar con los modelos de lenguaje.        |
| `orchestratorPrompt`   | Prompt personalizado para el orquestador.                                      |
| `workerPrompt`         | Prompt personalizado para los trabajadores.                                    |

---

### Método: `FinalResponse process(String taskDescription)`
Procesa una tarea utilizando el patrón Orchestrator-workers. Este método sigue tres pasos principales:
1. El orquestador analiza la tarea y la descompone en subtareas.
2. Los trabajadores ejecutan cada subtarea en paralelo.
3. Los resultados se combinan en una respuesta final.

| **Parámetro**         | **Descripción**                                                                 |
|------------------------|---------------------------------------------------------------------------------|
| `taskDescription`      | Descripción de la tarea a procesar.                                            |

| **Retorno**            | **Descripción**                                                                 |
|------------------------|---------------------------------------------------------------------------------|
| `FinalResponse`        | Contiene el análisis del orquestador y las respuestas combinadas de los trabajadores. |

| **Excepción**          | **Condición**                                                                   |
|------------------------|---------------------------------------------------------------------------------|
| `IllegalArgumentException` | Se lanza si `taskDescription` es nulo o está vacío.                         |

---

## Estructuras de Datos

### Registro: `Task`
Representa una subtarea identificada por el orquestador.

| **Campo**       | **Tipo**       | **Descripción**                                                                 |
|------------------|---------------|---------------------------------------------------------------------------------|
| `type`          | `String`      | Tipo o categoría de la tarea (e.g., "formal", "conversational").                |
| `description`   | `String`      | Descripción detallada de lo que el trabajador debe realizar.                    |

---

### Registro: `OrchestratorResponse`
Contiene el análisis del orquestador y las subtareas identificadas.

| **Campo**       | **Tipo**       | **Descripción**                                                                 |
|------------------|---------------|---------------------------------------------------------------------------------|
| `analysis`      | `String`      | Explicación detallada de la tarea y cómo diferentes enfoques sirven a sus aspectos. |
| `tasks`         | `List<Task>`  | Lista de subtareas identificadas por el orquestador.                            |

---

### Registro: `FinalResponse`
Contiene el análisis del orquestador y las respuestas combinadas de los trabajadores.

| **Campo**           | **Tipo**       | **Descripción**                                                                 |
|----------------------|---------------|---------------------------------------------------------------------------------|
| `analysis`          | `String`      | Entendimiento y desglose de la tarea original por parte del orquestador.        |
| `workerResponses`   | `List<String>`| Respuestas de los trabajadores para cada subtarea.                              |

---

## Insights

1. **Patrón de Diseño:** El patrón Orchestrator-workers es ideal para tareas complejas y adaptativas donde las subtareas no pueden ser predefinidas. Esto lo hace especialmente útil en aplicaciones de inteligencia artificial y procesamiento de lenguaje natural.

2. **Flexibilidad:** La implementación permite prompts personalizados para el orquestador y los trabajadores, lo que facilita la adaptación a diferentes casos de uso.

3. **Paralelismo:** Las subtareas se procesan en paralelo, lo que mejora la eficiencia en tareas que involucran múltiples componentes.

4. **Validación:** Se utiliza la clase `Assert` de Spring para garantizar que los parámetros esenciales no sean nulos o vacíos, mejorando la robustez del código.

5. **Interacción con LLM:** La clase depende de `ChatClient` para interactuar con modelos de lenguaje, lo que sugiere que está diseñada para integrarse con sistemas de inteligencia artificial basados en LLM.

6. **Uso de Registros:** Los registros (`record`) en Java proporcionan una forma concisa y eficiente de definir estructuras de datos inmutables, lo que mejora la claridad y la seguridad del código.

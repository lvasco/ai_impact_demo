# Documentación de `EvaluatorOptimizer`

## Descripción General

`EvaluatorOptimizer` implementa el patrón de flujo de trabajo Evaluador-Optimizador para interacciones con Modelos de Lenguaje Extenso (LLM). Este flujo de trabajo orquesta un proceso iterativo de doble LLM, donde un modelo genera respuestas y otro las evalúa y proporciona retroalimentación para refinarlas. Este enfoque es similar al proceso iterativo de refinamiento de un escritor humano.

### Componentes Principales
1. **Generador LLM**: Produce respuestas iniciales y las refina basándose en la retroalimentación.
2. **Evaluador LLM**: Analiza las respuestas y proporciona retroalimentación detallada para su mejora.

### Criterios de Uso
Este flujo de trabajo es efectivo en escenarios que cumplen las siguientes condiciones:
- Existen criterios claros para evaluar la calidad de las respuestas.
- El refinamiento iterativo aporta un valor medible al resultado.
- La tarea se beneficia de múltiples rondas de crítica y mejora.

### Indicadores de Aptitud
- Las respuestas del LLM pueden mejorarse demostrablemente cuando se articula retroalimentación.
- El evaluador LLM puede proporcionar retroalimentación sustantiva y procesable.

### Ejemplos de Aplicaciones
- Traducción literaria que requiere capturar matices sutiles mediante refinamiento iterativo.
- Tareas de búsqueda complejas que necesitan múltiples rondas de análisis.
- Generación de código donde la calidad puede mejorarse mediante revisiones sistemáticas.
- Creación de contenido que requiere múltiples borradores y mejoras específicas.

---

## Estructuras de Datos

### `Generation`
Representa un paso de generación de solución. Contiene los pensamientos del modelo y la solución propuesta.

| Campo       | Tipo   | Descripción                                                                 |
|-------------|--------|-----------------------------------------------------------------------------|
| `thoughts`  | String | Comprensión del modelo sobre la tarea y la retroalimentación.              |
| `response`  | String | Solución propuesta por el modelo.                                          |

---

### `EvaluationResponse`
Representa una respuesta de evaluación. Contiene el resultado de la evaluación y retroalimentación detallada.

| Campo         | Tipo   | Descripción                                                                 |
|---------------|--------|-----------------------------------------------------------------------------|
| `evaluation`  | Enum   | Resultado de la evaluación: `PASS`, `NEEDS_IMPROVEMENT`, o `FAIL`.         |
| `feedback`    | String | Retroalimentación detallada para la mejora.                                |

#### `Evaluation` (Enum)
- `PASS`: Todos los criterios se cumplen sin necesidad de mejoras.
- `NEEDS_IMPROVEMENT`: Se requieren mejoras.
- `FAIL`: No cumple con los criterios.

---

### `RefinedResponse`
Representa la respuesta final refinada. Contiene la solución final y la cadena de pensamiento que muestra la evolución de la solución.

| Campo            | Tipo            | Descripción                                                                 |
|------------------|-----------------|-----------------------------------------------------------------------------|
| `solution`       | String          | Solución final.                                                            |
| `chainOfThought` | List<Generation>| Cadena de pensamiento que muestra la evolución de la solución.             |

---

## Atributos

| Atributo            | Tipo       | Descripción                                                                 |
|---------------------|------------|-----------------------------------------------------------------------------|
| `chatClient`        | ChatClient | Cliente de chat utilizado para interactuar con los modelos LLM.            |
| `generatorPrompt`   | String     | Prompt predeterminado para el generador LLM.                               |
| `evaluatorPrompt`   | String     | Prompt predeterminado para el evaluador LLM.                               |

---

## Métodos

### `EvaluatorOptimizer(ChatClient chatClient)`
Constructor que inicializa el flujo de trabajo con prompts predeterminados.

| Parámetro      | Tipo       | Descripción                                      |
|----------------|------------|--------------------------------------------------|
| `chatClient`   | ChatClient | Cliente de chat para interactuar con los LLM.    |

---

### `EvaluatorOptimizer(ChatClient chatClient, String generatorPrompt, String evaluatorPrompt)`
Constructor que permite personalizar los prompts del generador y evaluador.

| Parámetro          | Tipo       | Descripción                                      |
|--------------------|------------|--------------------------------------------------|
| `chatClient`       | ChatClient | Cliente de chat para interactuar con los LLM.    |
| `generatorPrompt`  | String     | Prompt personalizado para el generador.          |
| `evaluatorPrompt`  | String     | Prompt personalizado para el evaluador.          |

---

### `RefinedResponse loop(String task)`
Inicia el flujo de trabajo Evaluador-Optimizador para una tarea dada. Orquesta el proceso iterativo de generación y evaluación hasta alcanzar una solución satisfactoria.

| Parámetro | Tipo   | Descripción                                      |
|-----------|--------|--------------------------------------------------|
| `task`    | String | Tarea o problema a resolver mediante refinamiento iterativo. |

**Retorno**: `RefinedResponse`  
Contiene la solución final y la cadena de pensamiento que muestra la evolución de la solución.

---

### `RefinedResponse loop(String task, String context, List<String> memory, List<Generation> chainOfThought)`
Implementación recursiva interna del bucle Evaluador-Optimizador. Mantiene el estado de intentos previos y retroalimentación mientras refina la solución.

| Parámetro          | Tipo            | Descripción                                                                 |
|--------------------|-----------------|-----------------------------------------------------------------------------|
| `task`             | String          | Tarea original a resolver.                                                 |
| `context`          | String          | Contexto acumulado incluyendo intentos previos y retroalimentación.         |
| `memory`           | List<String>    | Lista de intentos previos de solución para referencia.                      |
| `chainOfThought`   | List<Generation>| Cadena de pensamiento que rastrea la evolución de las soluciones.           |

**Retorno**: `RefinedResponse`  
Contiene la solución final y el historial completo de la solución.

---

### `Generation generate(String task, String context)`
Genera o refina una solución basada en la tarea y el contexto de retroalimentación.

| Parámetro | Tipo   | Descripción                                      |
|-----------|--------|--------------------------------------------------|
| `task`    | String | Tarea principal o problema a resolver.           |
| `context` | String | Intentos previos y retroalimentación acumulada.  |

**Retorno**: `Generation`  
Contiene los pensamientos del modelo y la solución propuesta.

---

### `EvaluationResponse evalute(String content, String task)`
Evalúa si una solución cumple con los requisitos y criterios de calidad especificados.

| Parámetro  | Tipo   | Descripción                                      |
|------------|--------|--------------------------------------------------|
| `content`  | String | Contenido de la solución a evaluar.              |
| `task`     | String | Tarea original contra la cual evaluar la solución.|

**Retorno**: `EvaluationResponse`  
Contiene el resultado de la evaluación y retroalimentación detallada.

---

## Insights

1. **Patrón de Diseño**: Este flujo de trabajo sigue un enfoque iterativo y recursivo, lo que lo hace ideal para tareas que requieren refinamiento continuo.
2. **Flexibilidad**: Los prompts del generador y evaluador son personalizables, lo que permite adaptar el flujo de trabajo a diferentes dominios.
3. **Estructura Modular**: La separación clara entre generación, evaluación y refinamiento facilita la extensibilidad y el mantenimiento del código.
4. **Uso de `ChatClient`**: La dependencia de `ChatClient` sugiere que este flujo de trabajo está diseñado para integrarse con sistemas de IA basados en chat, como LLMs.
5. **Validación de Entradas**: El uso de `Assert` asegura que los parámetros esenciales no sean nulos o vacíos, mejorando la robustez del sistema.

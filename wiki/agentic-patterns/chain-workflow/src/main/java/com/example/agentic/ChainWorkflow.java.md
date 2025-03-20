# Documentación de `ChainWorkflow`

## Descripción General

La clase `ChainWorkflow` implementa el patrón de flujo de trabajo de encadenamiento de prompts (*Prompt Chaining*), que descompone tareas complejas en una secuencia de llamadas a modelos de lenguaje (LLM). Cada paso procesa la salida del paso anterior, lo que permite realizar transformaciones graduales y específicas sobre los datos de entrada.

Este flujo de trabajo es ideal para situaciones donde la tarea puede dividirse en subtareas fijas y bien definidas. El objetivo principal es mejorar la precisión al dividir una tarea compleja en pasos más simples, aunque esto puede aumentar la latencia.

### Flujo de Trabajo

El flujo de trabajo consta de cuatro pasos principales:

1. **Extracción de valores numéricos y métricas**: Identifica y extrae valores numéricos y sus métricas asociadas del texto.
2. **Estandarización a formato de porcentaje**: Convierte los valores numéricos a porcentajes o decimales, según corresponda.
3. **Ordenamiento en orden descendente**: Ordena los valores numéricos en orden descendente.
4. **Formateo como tabla en Markdown**: Presenta los datos ordenados en una tabla con formato Markdown.

## Detalles de Implementación

### Atributos

| Atributo              | Tipo                | Descripción                                                                 |
|-----------------------|---------------------|-----------------------------------------------------------------------------|
| `DEFAULT_SYSTEM_PROMPTS` | `String[]`         | Prompts predefinidos que definen los pasos de transformación en el flujo.   |
| `chatClient`          | `ChatClient`        | Cliente de chat de Spring AI utilizado para realizar llamadas a LLM.       |
| `systemPrompts`       | `String[]`          | Prompts personalizados que definen los pasos de transformación.            |

### Constructores

| Constructor                                                                 | Descripción                                                                 |
|-----------------------------------------------------------------------------|-----------------------------------------------------------------------------|
| `ChainWorkflow(ChatClient chatClient)`                                      | Inicializa el flujo de trabajo con el cliente de chat y los prompts por defecto. |
| `ChainWorkflow(ChatClient chatClient, String[] systemPrompts)`              | Inicializa el flujo de trabajo con el cliente de chat y prompts personalizados. |

### Métodos

#### `chain(String userInput)`

Este método ejecuta el flujo de trabajo de encadenamiento de prompts. Procesa el texto de entrada a través de una serie de pasos definidos por los prompts del sistema. Cada paso utiliza la salida del paso anterior como entrada.

| Parámetro      | Tipo       | Descripción                                                                 |
|----------------|------------|-----------------------------------------------------------------------------|
| `userInput`    | `String`   | Texto de entrada que contiene los datos numéricos a procesar.              |

**Retorno**: `String`  
Devuelve el resultado final después de ejecutar todos los pasos del flujo de trabajo.

**Proceso**:
1. Imprime el estado inicial del texto de entrada.
2. Itera sobre los prompts del sistema, aplicando cada transformación secuencialmente.
3. Imprime los resultados intermedios después de cada paso.
4. Devuelve el resultado final.

### Prompts del Sistema

| Paso | Descripción                                                                                     | Ejemplo de Formato                                                                 |
|------|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| 1    | Extrae valores numéricos y métricas asociadas del texto.                                        | `92: customer satisfaction`<br>`45%: revenue growth`                              |
| 2    | Convierte valores numéricos a porcentajes o decimales.                                          | `92%: customer satisfaction`<br>`45%: revenue growth`                             |
| 3    | Ordena los valores numéricos en orden descendente.                                              | `92%: customer satisfaction`<br>`87%: employee satisfaction`                      |
| 4    | Formatea los datos ordenados como una tabla en Markdown.                                        | `| Metric                | Value |`<br>`|:--                    |--:|`<br>`| Customer Satisfaction | 92% |` |

## Insights

- **Encadenamiento de Prompts**: Este patrón es útil para dividir tareas complejas en subtareas más manejables, mejorando la precisión de los resultados.
- **Uso de LLM**: Cada paso del flujo de trabajo utiliza un modelo de lenguaje para realizar transformaciones específicas, lo que permite manejar tareas que requieren procesamiento semántico avanzado.
- **Flexibilidad**: Los prompts del sistema pueden personalizarse para adaptarse a diferentes casos de uso.
- **Estandarización y Formateo**: El flujo de trabajo incluye pasos para estandarizar y presentar los datos en un formato legible, como tablas en Markdown.
- **Integración con Spring AI**: La clase utiliza el cliente de chat de Spring AI (`ChatClient`), lo que facilita la integración con aplicaciones basadas en esta tecnología.

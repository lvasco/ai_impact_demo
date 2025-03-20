# Documentación de `RoutingResponse`

## Descripción General

`RoutingResponse` es una **estructura de datos** implementada como un `record` en Java. Representa la respuesta generada por el proceso de clasificación de enrutamiento, utilizado para capturar y comunicar las decisiones de enrutamiento realizadas por un clasificador basado en un modelo de lenguaje (LLM). Este `record` es parte del paquete `com.example.agentic` y está diseñado para ser utilizado en conjunto con el flujo de trabajo definido en `RoutingWorkflow`.

## Propósito

El propósito principal de `RoutingResponse` es encapsular dos piezas clave de información relacionadas con el enrutamiento:

1. **Razonamiento (`reasoning`)**: Una explicación detallada de por qué se eligió una ruta específica, considerando factores como términos clave, intención del usuario y nivel de urgencia.
2. **Selección (`selection`)**: El nombre de la ruta seleccionada que manejará la entrada basada en el análisis de clasificación.

## Declaración

```java
public record RoutingResponse(
    String reasoning,
    String selection
) {
}
```

## Campos

| Campo       | Tipo   | Descripción                                                                                     |
|-------------|--------|-------------------------------------------------------------------------------------------------|
| `reasoning` | String | Explicación detallada del razonamiento detrás de la selección de la ruta.                       |
| `selection` | String | Nombre de la ruta seleccionada que manejará la entrada según el análisis de clasificación.      |

## Uso

Este `record` se utiliza principalmente en el contexto del flujo de trabajo de enrutamiento (`RoutingWorkflow`) para capturar las decisiones tomadas por el clasificador. Es una estructura inmutable que permite almacenar y transmitir información de manera eficiente.

## Relación con otras clases

- **`RoutingWorkflow`**: `RoutingResponse` es utilizado por esta clase para comunicar las decisiones de enrutamiento. 
- **Modelo de Lenguaje (LLM)**: Aunque no se detalla en este archivo, el razonamiento y la selección están basados en el análisis realizado por un clasificador LLM.

## Insights

- **Inmutabilidad**: Al ser un `record`, `RoutingResponse` es inmutable, lo que garantiza que los datos no puedan ser modificados después de su creación.
- **Simplicidad**: La estructura del `record` permite una representación concisa y clara de los datos relacionados con el enrutamiento.
- **Extensibilidad**: Aunque actualmente solo contiene dos campos, esta estructura puede ser extendida en el futuro si se requiere más información sobre el proceso de enrutamiento.
- **Licencia**: El archivo está licenciado bajo la Apache License 2.0, lo que permite su uso, modificación y distribución bajo ciertas condiciones.

## Metadatos del Archivo

- **Nombre del Archivo**: `RoutingResponse.java`
- **Autor**: Christian Tzolov
- **Licencia**: Apache License 2.0
- **Paquete**: `com.example.agentic`

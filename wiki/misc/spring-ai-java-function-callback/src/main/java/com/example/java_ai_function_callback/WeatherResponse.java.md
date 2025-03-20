# Documentación de `WeatherResponse`

## Descripción General

La clase `WeatherResponse` es una estructura de datos inmutable que encapsula información relacionada con las condiciones climáticas. Proporciona detalles como la temperatura actual, la sensación térmica, las temperaturas mínima y máxima, la presión atmosférica, la humedad y la unidad de medida utilizada. Esta clase está diseñada para ser utilizada en el contexto de la aplicación `SpringAiJavaFunctionCallbackApplication`.

---

## Estructura de la Clase

### Atributos

| Atributo       | Tipo                                                | Descripción                                                                 |
|----------------|-----------------------------------------------------|-----------------------------------------------------------------------------|
| `temp`         | `double`                                            | Temperatura actual.                                                        |
| `feels_like`   | `double`                                            | Sensación térmica.                                                         |
| `temp_min`     | `double`                                            | Temperatura mínima registrada.                                             |
| `temp_max`     | `double`                                            | Temperatura máxima registrada.                                             |
| `pressure`     | `int`                                               | Presión atmosférica medida en hPa (hectopascales).                         |
| `humidity`     | `int`                                               | Humedad relativa en porcentaje.                                            |
| `unit`         | `SpringAiJavaFunctionCallbackApplication.Unit`      | Unidad de medida utilizada (por ejemplo, Celsius o Fahrenheit).            |

---

### Constructor

#### `WeatherResponse`

```java
public WeatherResponse(double temp, double feels_like, double temp_min,
        double temp_max, int pressure, int humidity, SpringAiJavaFunctionCallbackApplication.Unit unit)
```

- **Descripción**: Constructor que inicializa todos los atributos de la clase.
- **Parámetros**:
  - `temp`: Temperatura actual.
  - `feels_like`: Sensación térmica.
  - `temp_min`: Temperatura mínima registrada.
  - `temp_max`: Temperatura máxima registrada.
  - `pressure`: Presión atmosférica en hPa.
  - `humidity`: Humedad relativa en porcentaje.
  - `unit`: Unidad de medida utilizada.

---

### Métodos

| Método                     | Retorno                                                | Descripción                                                                 |
|----------------------------|-------------------------------------------------------|-----------------------------------------------------------------------------|
| `getTemp()`                | `double`                                              | Devuelve la temperatura actual.                                            |
| `getFeels_like()`          | `double`                                              | Devuelve la sensación térmica.                                             |
| `getTemp_min()`            | `double`                                              | Devuelve la temperatura mínima registrada.                                 |
| `getTemp_max()`            | `double`                                              | Devuelve la temperatura máxima registrada.                                 |
| `getPressure()`            | `int`                                                 | Devuelve la presión atmosférica en hPa.                                    |
| `getHumidity()`            | `int`                                                 | Devuelve la humedad relativa en porcentaje.                                |
| `getUnit()`                | `SpringAiJavaFunctionCallbackApplication.Unit`        | Devuelve la unidad de medida utilizada.                                    |

---

## Insights

1. **Inmutabilidad**: La clase está diseñada como inmutable, ya que todos los atributos son `final` y no existen métodos para modificarlos después de la inicialización. Esto asegura que los datos climáticos no puedan ser alterados una vez que se crea una instancia de `WeatherResponse`.

2. **Unidad de Medida**: El atributo `unit` utiliza una enumeración (`SpringAiJavaFunctionCallbackApplication.Unit`), lo que sugiere que las unidades de medida están predefinidas y controladas dentro de la aplicación. Esto mejora la consistencia y evita errores relacionados con unidades no válidas.

3. **Encapsulamiento**: Todos los atributos son privados y solo accesibles a través de métodos `getter`, lo que refuerza el principio de encapsulación.

4. **Aplicación Contextual**: La clase está estrechamente vinculada a la aplicación `SpringAiJavaFunctionCallbackApplication`, lo que indica que forma parte de un sistema más amplio relacionado con funciones de inteligencia artificial o procesamiento de datos climáticos.

5. **Uso Potencial**: Esta clase es ideal para representar respuestas de servicios meteorológicos o APIs relacionadas con el clima, proporcionando una estructura clara y bien definida para manejar datos climáticos.

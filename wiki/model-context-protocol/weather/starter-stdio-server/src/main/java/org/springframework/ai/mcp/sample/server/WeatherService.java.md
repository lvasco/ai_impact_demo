# Documentación del Servicio `WeatherService`

## Descripción General

El archivo `WeatherService.java` implementa un servicio que interactúa con la API de `https://api.weather.gov` para proporcionar información meteorológica, incluyendo pronósticos y alertas climáticas. Este servicio utiliza la biblioteca `RestClient` para realizar solicitudes HTTP y procesar las respuestas en formato JSON. 

El servicio está diseñado para ser utilizado en aplicaciones basadas en Spring y está anotado con `@Service` para que pueda ser gestionado como un componente de Spring.

---

## Estructura del Código

### Declaraciones de Estructuras de Datos

El código incluye varias clases de datos (records) que representan las respuestas JSON de la API meteorológica:

| **Clase**         | **Descripción**                                                                                     |
|--------------------|-----------------------------------------------------------------------------------------------------|
| `Points`          | Representa la respuesta de la API para obtener la URL del pronóstico basado en latitud y longitud.  |
| `Forecast`        | Representa el pronóstico meteorológico, incluyendo períodos específicos con detalles como temperatura, viento y descripción. |
| `Alert`           | Representa las alertas meteorológicas activas para un área específica.                              |

#### Detalles de las Clases de Datos

1. **`Points`**
   - Contiene una propiedad `forecast` que es la URL para obtener el pronóstico.

2. **`Forecast`**
   - Contiene una lista de períodos (`Period`) con detalles como:
     - Temperatura
     - Velocidad y dirección del viento
     - Descripción detallada del pronóstico

3. **`Alert`**
   - Contiene una lista de características (`Feature`) que incluyen:
     - Evento (e.g., tormenta, inundación)
     - Área afectada
     - Severidad
     - Descripción e instrucciones

---

### Lógica del Código

El servicio implementa dos métodos principales para interactuar con la API meteorológica:

#### 1. `getWeatherForecastByLocation(double latitude, double longitude)`
- **Descripción**: Obtiene el pronóstico meteorológico para una ubicación específica basada en latitud y longitud.
- **Parámetros**:
  - `latitude`: Latitud de la ubicación.
  - `longitude`: Longitud de la ubicación.
- **Retorno**: Una cadena de texto con el pronóstico detallado para cada período.
- **Flujo**:
  1. Realiza una solicitud a `/points/{latitude},{longitude}` para obtener la URL del pronóstico.
  2. Realiza una solicitud a la URL del pronóstico para obtener los datos meteorológicos.
  3. Procesa los datos y genera un texto legible con detalles como temperatura, viento y descripción.

#### 2. `getAlerts(String state)`
- **Descripción**: Obtiene las alertas meteorológicas activas para un estado de EE.UU.
- **Parámetros**:
  - `state`: Código de dos letras del estado (e.g., `CA` para California, `NY` para Nueva York).
- **Retorno**: Una cadena de texto con las alertas activas, incluyendo evento, área, severidad, descripción e instrucciones.
- **Flujo**:
  1. Realiza una solicitud a `/alerts/active/area/{state}` para obtener las alertas activas.
  2. Procesa los datos y genera un texto legible con los detalles de cada alerta.

---

## Anotaciones y Configuración

- **`@Service`**: Marca la clase como un componente de Spring para que pueda ser inyectado y gestionado por el contenedor de Spring.
- **`@Tool` y `@ToolParam`**: Anotaciones personalizadas que describen los métodos y parámetros para su uso en herramientas externas.
- **`@JsonIgnoreProperties` y `@JsonProperty`**: Utilizadas para mapear las respuestas JSON de la API a las clases de datos.

---

## Dependencias

El servicio utiliza las siguientes dependencias clave:
- **`RestClient`**: Para realizar solicitudes HTTP.
- **`com.fasterxml.jackson.annotation`**: Para mapear las respuestas JSON a objetos Java.

---

## Insights

1. **Uso de `RestClient`**:
   - El cliente HTTP está configurado con encabezados predeterminados como `Accept` y `User-Agent`, lo que asegura que las solicitudes sean compatibles con la API de `weather.gov`.

2. **Diseño Modular**:
   - El uso de `record` para modelar las respuestas JSON simplifica el manejo de datos y mejora la legibilidad del código.

3. **Extensibilidad**:
   - La estructura del código permite agregar fácilmente nuevos métodos para interactuar con otros endpoints de la API meteorológica.

4. **Errores Potenciales**:
   - El servicio lanza `RestClientException` si las solicitudes fallan. Sería útil implementar un manejo de errores más robusto para mejorar la resiliencia.

5. **Formato de Salida**:
   - Los métodos devuelven cadenas de texto formateadas, lo que es útil para aplicaciones que necesitan mostrar información directamente al usuario.

---

## Ejemplo de Uso

```java
public static void main(String[] args) {
    WeatherService client = new WeatherService();
    System.out.println(client.getWeatherForecastByLocation(47.6062, -122.3321)); // Pronóstico para Seattle, WA
    System.out.println(client.getAlerts("NY")); // Alertas para Nueva York
}
```

---

## Licencia

Este archivo está licenciado bajo la Licencia Apache 2.0. Para más detalles, consulte [LICENSE](https://www.apache.org/licenses/LICENSE-2.0).

# Documentación de `WeatherApiClient`

## Descripción General

`WeatherApiClient` es una clase que actúa como cliente para interactuar con la API de `https://api.weather.gov`. Proporciona funcionalidades para obtener pronósticos meteorológicos basados en coordenadas geográficas y alertas climáticas para un estado específico de los Estados Unidos. Utiliza la biblioteca `RestClient` para realizar solicitudes HTTP y deserializa las respuestas JSON en objetos Java utilizando anotaciones de Jackson.

---

## Estructura del Código

### Declaraciones de Estructuras de Datos

El código incluye varias estructuras de datos definidas como `record` para modelar las respuestas JSON de la API:

1. **`Points`**  
   Representa la respuesta de la API para obtener la URL del pronóstico meteorológico basado en coordenadas.
   - **`Props`**: Contiene la URL del pronóstico (`forecast`).

2. **`Forecast`**  
   Representa el pronóstico meteorológico.
   - **`Props`**: Contiene una lista de períodos (`periods`).
   - **`Period`**: Representa un período de tiempo con detalles como temperatura, velocidad del viento, dirección del viento, y descripción del pronóstico.

3. **`Alert`**  
   Representa las alertas climáticas activas.
   - **`Feature`**: Contiene las propiedades de una alerta específica.
   - **`Properties`**: Incluye detalles como el evento, área afectada, severidad, descripción e instrucciones.

---

### Lógica del Código

#### Constructor
- **`WeatherApiClient()`**  
  Inicializa un cliente REST (`RestClient`) con la URL base de la API y encabezados predeterminados como `Accept` y `User-Agent`.

#### Métodos Públicos

1. **`getWeatherForecastByLocation(double latitude, double longitude)`**  
   Obtiene el pronóstico meteorológico para una ubicación específica basada en latitud y longitud.
   - Realiza dos solicitudes:
     1. Obtiene la URL del pronóstico desde `/points/{latitude},{longitude}`.
     2. Recupera el pronóstico desde la URL obtenida.
   - Procesa los datos del pronóstico y los formatea en texto legible.

2. **`getAlerts(String state)`**  
   Obtiene alertas climáticas activas para un estado específico (código de dos letras, e.g., "NY").
   - Realiza una solicitud a `/alerts/active/area/{state}`.
   - Procesa las alertas y las formatea en texto legible.

#### Método `main`
- Proporciona un ejemplo de uso del cliente:
  - Obtiene el pronóstico para las coordenadas `47.6062, -122.3321` (Seattle, WA).
  - Obtiene alertas para el estado de Nueva York (`NY`).

---

## Anotaciones y Configuración

- **Anotaciones de Jackson**  
  Utiliza `@JsonIgnoreProperties` y `@JsonProperty` para mapear las respuestas JSON de la API a objetos Java.

- **Anotaciones Personalizadas**  
  - `@Tool`: Proporciona descripciones de los métodos para facilitar su uso en herramientas externas.

---

## Ejemplo de Uso

```java
public static void main(String[] args) {
    WeatherApiClient client = new WeatherApiClient();
    System.out.println(client.getWeatherForecastByLocation(47.6062, -122.3321)); // Pronóstico para Seattle, WA
    System.out.println(client.getAlerts("NY")); // Alertas para Nueva York
}
```

---

## Insights

1. **Uso de `record`**  
   La clase utiliza `record` para definir estructuras de datos inmutables, lo que simplifica la representación de respuestas JSON.

2. **Encabezados Personalizados**  
   El cliente REST incluye encabezados como `Accept` y `User-Agent`, lo cual es importante para cumplir con los requisitos de la API de `weather.gov`.

3. **Procesamiento de Datos**  
   Los métodos procesan las respuestas de la API en texto legible, lo que facilita su uso en aplicaciones que requieren datos formateados.

4. **Gestión de Errores**  
   Aunque el código lanza `RestClientException` en caso de fallos, no incluye manejo explícito de errores, lo que podría ser mejorado para mayor robustez.

5. **Extensibilidad**  
   La estructura modular del cliente permite agregar fácilmente nuevas funcionalidades para interactuar con otros endpoints de la API.

---

## Referencias

- [Documentación de la API de Weather.gov](https://www.weather.gov/documentation/services-web-api)
- [Apache License 2.0](https://www.apache.org/licenses/LICENSE-2.0)

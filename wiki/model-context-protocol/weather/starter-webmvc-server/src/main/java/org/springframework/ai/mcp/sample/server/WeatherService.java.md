# Documentación del Servicio `WeatherService`

## Descripción General

El archivo `WeatherService.java` implementa un servicio en Java que interactúa con la API de `https://api.weather.gov` para obtener información meteorológica y alertas climáticas. Este servicio utiliza la biblioteca `RestClient` para realizar solicitudes HTTP y procesar las respuestas en formato JSON. 

El servicio incluye dos funcionalidades principales:
1. Obtener el pronóstico del tiempo para una ubicación específica (latitud y longitud).
2. Obtener alertas climáticas para un estado específico de los Estados Unidos.

## Estructura del Código

### Declaraciones de Estructuras de Datos

El código define varias estructuras de datos utilizando `record` para modelar las respuestas JSON de la API. Estas estructuras están anotadas con `@JsonIgnoreProperties` y `@JsonProperty` para mapear correctamente los datos JSON a objetos Java.

#### `Points`
- Representa la respuesta de la API para obtener la URL del pronóstico basado en latitud y longitud.
- Contiene:
  - `Props`: Incluye la propiedad `forecast` que almacena la URL del pronóstico.

#### `Forecast`
- Representa el pronóstico del tiempo.
- Contiene:
  - `Props`: Incluye una lista de períodos (`periods`).
  - `Period`: Representa un período de tiempo con detalles como temperatura, velocidad del viento, dirección del viento, y descripción del pronóstico.

#### `Alert`
- Representa las alertas climáticas activas.
- Contiene:
  - `Feature`: Incluye las propiedades de cada alerta.
  - `Properties`: Detalla información como el evento, área afectada, severidad, descripción e instrucciones.

### Lógica del Código

#### Constructor
- Configura un cliente REST (`RestClient`) con:
  - URL base: `https://api.weather.gov`.
  - Encabezados predeterminados: `Accept` y `User-Agent`.

#### Métodos Públicos

1. **`getWeatherForecastByLocation(double latitude, double longitude)`**
   - Obtiene el pronóstico del tiempo para una ubicación específica.
   - Pasos:
     1. Realiza una solicitud a `/points/{latitude},{longitude}` para obtener la URL del pronóstico.
     2. Realiza una solicitud a la URL del pronóstico para obtener los datos detallados.
     3. Procesa los datos y genera un texto legible con información como temperatura, viento y descripción detallada del pronóstico.

2. **`getAlerts(String state)`**
   - Obtiene alertas climáticas activas para un estado específico.
   - Pasos:
     1. Realiza una solicitud a `/alerts/active/area/{state}` para obtener las alertas.
     2. Procesa las alertas y genera un texto legible con información como evento, área afectada, severidad, descripción e instrucciones.

#### Método `main`
- Proporciona un ejemplo de uso del servicio:
  - Obtiene el pronóstico del tiempo para Seattle, WA (latitud: 47.6062, longitud: -122.3321).
  - Obtiene alertas climáticas para el estado de Nueva York (NY).

## Anotaciones y Dependencias

- **Anotaciones Utilizadas:**
  - `@Service`: Marca la clase como un componente de servicio en Spring.
  - `@Tool`: Proporciona descripciones para las herramientas expuestas.
  - `@JsonIgnoreProperties`: Ignora propiedades desconocidas en las respuestas JSON.
  - `@JsonProperty`: Mapea propiedades JSON a campos de las estructuras de datos.

- **Dependencias Externas:**
  - `RestClient`: Para realizar solicitudes HTTP.
  - `com.fasterxml.jackson.annotation`: Para procesar JSON.

## Insights

1. **Uso de `record`:** El uso de `record` simplifica la definición de clases inmutables para modelar datos JSON, reduciendo el código repetitivo.
2. **Encapsulación de Lógica de Red:** La clase encapsula toda la lógica de red, lo que facilita su reutilización y prueba.
3. **Formato de Salida:** Los métodos generan salidas legibles para humanos, lo que es útil para integraciones con sistemas que requieren texto plano.
4. **Extensibilidad:** La estructura modular permite agregar fácilmente nuevas funcionalidades relacionadas con la API meteorológica.
5. **Gestión de Errores:** Aunque se menciona que puede lanzar `RestClientException`, no se implementa un manejo explícito de errores en los métodos, lo que podría mejorarse para mayor robustez.

## Tablas de Métodos Públicos

| Método                                | Descripción                                                                 | Parámetros                                                                 | Retorno                          |
|---------------------------------------|-----------------------------------------------------------------------------|---------------------------------------------------------------------------|----------------------------------|
| `getWeatherForecastByLocation`        | Obtiene el pronóstico del tiempo para una ubicación específica.             | `latitude` (double), `longitude` (double)                                 | `String` (texto del pronóstico) |
| `getAlerts`                           | Obtiene alertas climáticas activas para un estado de EE.UU.                 | `state` (String, código de dos letras del estado)                         | `String` (texto de las alertas) |

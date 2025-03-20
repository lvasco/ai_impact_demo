# Documentación del Servicio `WeatherService`

## Descripción General

El archivo `WeatherService.java` implementa un servicio en Java que interactúa con la API pública de `https://api.weather.gov` para obtener información meteorológica. Este servicio permite a los usuarios consultar pronósticos del clima y alertas meteorológicas para ubicaciones específicas en los Estados Unidos. Utiliza la biblioteca `RestClient` para realizar solicitudes HTTP y deserializa las respuestas JSON en objetos Java utilizando anotaciones de Jackson.

---

## Estructura del Código

### Paquete
El código pertenece al paquete:
```
org.springframework.ai.mcp.sample.server
```

### Dependencias Importadas
El servicio utiliza las siguientes dependencias:
- **Spring Framework**: Para la anotación `@Service` y la configuración del cliente REST.
- **Jackson**: Para la deserialización de JSON en objetos Java.
- **Java Stream API**: Para procesar colecciones de datos.
- **RestClient**: Para realizar solicitudes HTTP.

---

## Declaraciones de Estructuras de Datos

El código define varias estructuras de datos para modelar las respuestas de la API meteorológica:

### `Points`
Representa la respuesta de la API para obtener la URL del pronóstico basado en coordenadas.
- **Atributos**:
  - `properties`: Contiene un objeto `Props`.
    - `forecast`: URL del pronóstico.

### `Forecast`
Representa el pronóstico meteorológico.
- **Atributos**:
  - `properties`: Contiene un objeto `Props`.
    - `periods`: Lista de objetos `Period`.

#### `Period`
Representa un período de tiempo en el pronóstico.
- **Atributos**:
  - `number`: Número del período.
  - `name`: Nombre del período (e.g., "Hoy", "Esta noche").
  - `startTime` y `endTime`: Tiempos de inicio y fin.
  - `isDaytime`: Indica si es de día.
  - `temperature`: Temperatura.
  - `temperatureUnit`: Unidad de temperatura (e.g., "F").
  - `temperatureTrend`: Tendencia de la temperatura.
  - `probabilityOfPrecipitation`: Probabilidad de precipitación.
  - `windSpeed` y `windDirection`: Velocidad y dirección del viento.
  - `icon`: URL del ícono del clima.
  - `shortForecast`: Resumen breve del pronóstico.
  - `detailedForecast`: Pronóstico detallado.

### `Alert`
Representa alertas meteorológicas activas.
- **Atributos**:
  - `features`: Lista de objetos `Feature`.

#### `Feature`
Representa una alerta específica.
- **Atributos**:
  - `properties`: Contiene un objeto `Properties`.

#### `Properties`
Contiene los detalles de una alerta.
- **Atributos**:
  - `event`: Tipo de evento (e.g., "Tormenta de nieve").
  - `areaDesc`: Descripción del área afectada.
  - `severity`: Severidad del evento.
  - `description`: Descripción del evento.
  - `instruction`: Instrucciones para el público.

---

## Lógica del Código

### Métodos Públicos

#### `getWeatherForecastByLocation(double latitude, double longitude)`
Obtiene el pronóstico del clima para una ubicación específica basada en latitud y longitud.

- **Parámetros**:
  - `latitude`: Latitud de la ubicación.
  - `longitude`: Longitud de la ubicación.
- **Retorno**:
  - Una cadena de texto con el pronóstico detallado para cada período.
- **Flujo**:
  1. Realiza una solicitud a `/points/{latitude},{longitude}` para obtener la URL del pronóstico.
  2. Realiza una solicitud a la URL del pronóstico para obtener los datos.
  3. Procesa los períodos del pronóstico y genera un texto formateado.

#### `getAlerts(String state)`
Obtiene alertas meteorológicas activas para un estado de EE.UU.

- **Parámetros**:
  - `state`: Código de dos letras del estado (e.g., "CA", "NY").
- **Retorno**:
  - Una cadena de texto con las alertas formateadas.
- **Flujo**:
  1. Realiza una solicitud a `/alerts/active/area/{state}` para obtener las alertas.
  2. Procesa las alertas y genera un texto formateado.

### Método `main`
Proporciona un ejemplo de uso del servicio:
1. Obtiene el pronóstico para Seattle, WA (latitud: 47.6062, longitud: -122.3321).
2. Obtiene alertas para el estado de Nueva York (NY).

---

## Insights

1. **Uso de `RestClient`**:
   - El cliente REST está configurado con encabezados predeterminados para aceptar respuestas en formato `application/geo+json` y un agente de usuario personalizado.

2. **Deserialización Flexible**:
   - Las anotaciones `@JsonIgnoreProperties(ignoreUnknown = true)` permiten ignorar campos desconocidos en las respuestas JSON, haciendo que el servicio sea robusto frente a cambios en la API.

3. **Formato de Salida**:
   - Los métodos generan salidas legibles para humanos, lo que facilita su uso en aplicaciones de usuario final.

4. **Extensibilidad**:
   - La estructura modular del código permite agregar fácilmente nuevas funcionalidades relacionadas con la API meteorológica.

5. **Errores Potenciales**:
   - No se maneja explícitamente el caso en que las respuestas de la API sean nulas o contengan errores. Esto podría causar excepciones en tiempo de ejecución.

6. **Anotaciones de Herramientas**:
   - Las anotaciones `@Tool` sugieren que este servicio podría integrarse con sistemas de herramientas automatizadas o interfaces de usuario.

---

## Tabla de Métodos Públicos

| Método                              | Descripción                                                                 | Parámetros                          | Retorno                     |
|-------------------------------------|-----------------------------------------------------------------------------|-------------------------------------|-----------------------------|
| `getWeatherForecastByLocation`      | Obtiene el pronóstico del clima para una ubicación específica.              | `latitude`, `longitude`            | Cadena de texto formateada. |
| `getAlerts`                         | Obtiene alertas meteorológicas activas para un estado de EE.UU.            | `state` (código de dos letras)      | Cadena de texto formateada. |

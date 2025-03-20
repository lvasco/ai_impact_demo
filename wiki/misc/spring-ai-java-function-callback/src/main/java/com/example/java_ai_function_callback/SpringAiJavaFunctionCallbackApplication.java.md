# Documentación: `SpringAiJavaFunctionCallbackApplication.java`

## Descripción General
Este archivo define una aplicación Spring Boot que utiliza un cliente de chat basado en inteligencia artificial para interactuar con una función personalizada llamada `WeatherInfo`. La aplicación permite consultar las condiciones climáticas y temperaturas en diferentes ubicaciones. Además, incluye una implementación simulada de un servicio meteorológico (`MockJavaWeatherService`) para proporcionar datos ficticios.

---

## Estructura del Código

### 1. **Punto de Entrada**
La clase principal `SpringAiJavaFunctionCallbackApplication` contiene el método `main`, que inicia la aplicación Spring Boot.

```java
public static void main(String[] args) {
    SpringApplication.run(SpringAiJavaFunctionCallbackApplication.class, args);
}
```

### 2. **Inicialización**
El método `init` se define como un `CommandLineRunner` para ejecutar lógica al inicio de la aplicación. Este método utiliza el cliente de chat (`ChatClient`) para realizar una consulta sobre las condiciones climáticas en tres ubicaciones: San Francisco, Tokio y París.

```java
@Bean
public CommandLineRunner init(ChatClient.Builder chatClientBuilder) {
    return args -> {
        try {
            ChatClient chatClient = chatClientBuilder.build();
            ChatResponse response = chatClient
                    .prompt("What are the weather conditions in San Francisco, Tokyo, and Paris? Find the temperature in Celsius for each of the three locations.")
                    .functions("WeatherInfo")
                    .call().chatResponse();

            System.out.println("Response: " + response);
        }
        catch (Exception e) {
            System.out.println("Error during weather check: " + e.getMessage());
        }
    };
}
```

### 3. **Enumeración: `Unit`**
La enumeración `Unit` define las unidades de medida para la temperatura: Celsius (`C`) y Fahrenheit (`F`). Cada unidad tiene un nombre asociado.

```java
public enum Unit {
    C("metric"),
    F("imperial");

    private final String unitName;

    Unit(String unitName) {
        this.unitName = unitName;
    }

    public String getUnitName() {
        return unitName;
    }
}
```

### 4. **Configuración**
La clase interna `Config` configura la función personalizada `WeatherInfo` y su implementación. 

#### Función `weatherFunctionInfo`
Define la función `WeatherInfo` con una descripción y el tipo de entrada esperado (`WeatherRequest`).

```java
@Bean
public FunctionCallback weatherFunctionInfo(Function<WeatherRequest, WeatherResponse> currentWeather) {
    return FunctionCallback.builder()
            .function("WeatherInfo", currentWeather)
            .description(
                    "Find the weather conditions, forecasts, and temperatures for a location, like a city or state."
            )
            .inputType(WeatherRequest.class)
            .build();
}
```

#### Implementación de la Función `currentWeather`
Proporciona una implementación de la función meteorológica utilizando un servicio simulado (`MockJavaWeatherService`).

```java
@Bean
public Function<WeatherRequest, WeatherResponse> currentWeather() {
    return request -> new MockJavaWeatherService().apply(request);
}
```

### 5. **Servicio Simulado: `MockJavaWeatherService`**
Este servicio simulado implementa la lógica para devolver datos ficticios de condiciones climáticas basados en la ubicación proporcionada.

```java
static class MockJavaWeatherService implements Function<WeatherRequest, WeatherResponse> {

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        double temperature = 10.0;
        if (weatherRequest.getLocation().contains("Paris")) {
            temperature = 15.0;
        }
        else if (weatherRequest.getLocation().contains("Tokyo")) {
            temperature = 10.0;
        }
        else if (weatherRequest.getLocation().contains("San Francisco")) {
            temperature = 30.0;
        }

        return new WeatherResponse(temperature, 15.0, 20.0, 2.0, 53, 45, Unit.C);
    }
}
```

---

## Clases y Componentes

### **Clases Principales**
| Clase                              | Descripción                                                                 |
|------------------------------------|-----------------------------------------------------------------------------|
| `SpringAiJavaFunctionCallbackApplication` | Clase principal que inicia la aplicación Spring Boot.                       |
| `MockJavaWeatherService`           | Servicio simulado que proporciona datos ficticios de condiciones climáticas. |
| `Config`                           | Clase de configuración que define la función personalizada `WeatherInfo`.    |

### **Enumeraciones**
| Enumeración | Descripción                          |
|-------------|--------------------------------------|
| `Unit`      | Define las unidades de medida para la temperatura. |

---

## Insights

1. **Uso de Spring AI**: La aplicación utiliza el cliente de chat de Spring AI (`ChatClient`) para interactuar con funciones personalizadas. Esto permite integrar capacidades de inteligencia artificial en aplicaciones Java.

2. **Función Personalizada**: La función `WeatherInfo` está configurada para recibir solicitudes de tipo `WeatherRequest` y devolver respuestas de tipo `WeatherResponse`. Esto facilita la extensión de la funcionalidad para manejar diferentes tipos de datos meteorológicos.

3. **Servicio Simulado**: El uso de `MockJavaWeatherService` permite probar la funcionalidad sin depender de servicios externos reales. Esto es útil para desarrollo y pruebas.

4. **Gestión de Errores**: La lógica de inicialización incluye manejo de excepciones para capturar errores durante la interacción con el cliente de chat.

5. **Extensibilidad**: La arquitectura basada en funciones y configuraciones permite agregar nuevas funcionalidades o modificar las existentes con facilidad.

---

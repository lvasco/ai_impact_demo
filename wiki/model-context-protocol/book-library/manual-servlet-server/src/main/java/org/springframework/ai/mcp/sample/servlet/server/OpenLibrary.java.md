# Documentación de `OpenLibrary.java`

## Descripción General

El archivo `OpenLibrary.java` implementa un servicio que interactúa con la API pública de Open Library para buscar libros por título o autor. Utiliza el cliente REST de Spring para realizar solicitudes HTTP y procesar las respuestas. Este servicio está diseñado para integrarse en aplicaciones basadas en Spring.

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```
org.springframework.ai.mcp.sample.servlet.server
```

### Dependencias Importadas
El código utiliza las siguientes clases y paquetes:
- `java.util.List`
- `java.util.Map`
- `org.springframework.ai.tool.annotation.Tool`
- `org.springframework.stereotype.Service`
- `org.springframework.web.client.RestClient`

### Anotaciones
- `@Service`: Marca la clase como un componente de servicio en el contexto de Spring.
- `@Tool`: Proporciona metadatos descriptivos para los métodos que exponen funcionalidades específicas.

## Componentes Principales

### Constructor
```java
public OpenLibrary(RestClient.Builder restClientBuilder)
```
- Inicializa un cliente REST (`RestClient`) con la URL base de la API de Open Library: `https://openlibrary.org`.

### Clases Internas
1. **`Books`**:
   - Representa la respuesta de la API al buscar libros.
   - Campos:
     - `numFound` (Integer): Número total de resultados encontrados.
     - `start` (Integer): Índice de inicio de los resultados.
     - `numFoundExact` (Boolean): Indica si el número de resultados encontrados es exacto.
     - `docs` (List<Map<String, Object>>): Lista de documentos que contienen información sobre los libros.

2. **`Book`**:
   - Representa un libro individual.
   - Campos:
     - `isbn` (List<String>): Lista de números ISBN asociados al libro.
     - `title` (String): Título del libro.
     - `authorName` (List<String>): Lista de nombres de los autores.

### Métodos Públicos

#### `getBooks(String title)`
- **Descripción**: Busca libros por título.
- **Parámetros**: 
  - `title` (String): Título del libro a buscar.
- **Retorno**: 
  - `List<Book>`: Lista de libros que coinciden con el título.
- **Lógica**:
  - Realiza una solicitud GET a `/search.json` con el parámetro de consulta `q` igual al título.
  - Mapea los resultados a objetos `Book`.

#### `getBookTitlesByAuthor(String authorName)`
- **Descripción**: Busca títulos de libros por el nombre del autor.
- **Parámetros**: 
  - `authorName` (String): Nombre del autor.
- **Retorno**: 
  - `List<String>`: Lista de títulos de libros escritos por el autor.
- **Lógica**:
  - Realiza una solicitud GET a `/search/authors.json` con el parámetro de consulta `q` igual al nombre del autor.
  - Extrae el campo `top_work` de los resultados.

### Método `main`
- **Propósito**: Proporciona un ejemplo de uso del servicio.
- **Lógica**:
  - Crea una instancia de `OpenLibrary` utilizando un `RestClient.Builder`.
  - Busca libros con el título "Spring Framework" y los imprime.
  - Busca títulos de libros del autor "Craig Walls" y los imprime.

## Insights

1. **Uso de `RestClient`**:
   - El código utiliza `RestClient` de Spring para realizar solicitudes HTTP. Esto simplifica la interacción con APIs RESTful.

2. **Estructura de Datos**:
   - Las clases internas `Books` y `Book` encapsulan los datos de la API, lo que facilita el manejo de las respuestas.

3. **Anotación `@Tool`**:
   - Los métodos `getBooks` y `getBookTitlesByAuthor` están anotados con `@Tool`, lo que sugiere que podrían ser utilizados en un contexto de herramientas o integraciones automatizadas.

4. **Manejo de Respuestas Nulas**:
   - Ambos métodos verifican si la respuesta de la API es `null` y devuelven una lista vacía en ese caso, lo que mejora la robustez del código.

5. **Extensibilidad**:
   - La clase está diseñada para ser extensible. Se podrían agregar más métodos para interactuar con otros endpoints de la API de Open Library.

6. **Dependencia de Spring**:
   - La clase depende de Spring para la inyección de dependencias y la gestión del cliente REST, lo que la hace adecuada para aplicaciones Spring pero no para entornos independientes.

## Tabla de Métodos

| Método                        | Descripción                              | Parámetros          | Retorno         |
|-------------------------------|------------------------------------------|---------------------|-----------------|
| `getBooks(String title)`      | Busca libros por título.                | `title` (String)    | `List<Book>`    |
| `getBookTitlesByAuthor(String authorName)` | Busca títulos de libros por autor. | `authorName` (String) | `List<String>` |

## Licencia
El código está licenciado bajo la **Apache License 2.0**.

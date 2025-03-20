# Documentación de `OpenLibrary.java`

## METADATA
**Nombre del archivo:** `OpenLibrary.java`

---

## Descripción General

La clase `OpenLibrary` proporciona una interfaz para interactuar con la API pública de Open Library. Permite buscar libros por título y obtener títulos de libros asociados a un autor específico. Utiliza el cliente REST de Spring (`RestClient`) para realizar solicitudes HTTP a la API de Open Library.

---

## Estructura de la Clase

### Paquete
La clase pertenece al paquete:
```java
org.springframework.ai.mcp.sample.webmvc.server
```

### Importaciones
La clase utiliza las siguientes importaciones:
- `java.util.List`
- `java.util.Map`
- `org.springframework.web.client.RestClient`

---

## Declaraciones de Datos

### `Books`
Un registro que representa la respuesta de búsqueda de libros en la API. Contiene los siguientes campos:
- **`numFound`**: Número total de resultados encontrados.
- **`start`**: Índice de inicio de los resultados.
- **`numFoundExact`**: Indica si el número de resultados encontrados es exacto.
- **`docs`**: Lista de documentos (libros) representados como mapas clave-valor.

### `Book`
Un registro que representa un libro individual. Contiene los siguientes campos:
- **`isbn`**: Lista de códigos ISBN asociados al libro.
- **`title`**: Título del libro.
- **`authorName`**: Lista de nombres de autores del libro.

---

## Métodos

### Constructor: `OpenLibrary(RestClient.Builder restClientBuilder)`
Inicializa la instancia de `OpenLibrary` configurando el cliente REST (`RestClient`) con la URL base de la API de Open Library (`https://openlibrary.org`).

---

### `List<Book> getBooks(String title)`
Busca libros en la API de Open Library utilizando un título como parámetro de consulta. 

#### Proceso:
1. Realiza una solicitud GET a la ruta `/search.json` con el parámetro de consulta `q` igual al título proporcionado.
2. Convierte la respuesta en una instancia de `Books`.
3. Si no se encuentran resultados (`books == null`), retorna una lista vacía.
4. Mapea los documentos (`docs`) de la respuesta a objetos `Book` y los retorna como una lista.

#### Parámetros:
- **`title`**: Título del libro a buscar.

#### Retorno:
- Una lista de objetos `Book` que coinciden con el título proporcionado.

---

### `List<String> getBookTitlesByAuthor(String authorName)`
Obtiene los títulos de los libros asociados a un autor específico.

#### Proceso:
1. Realiza una solicitud GET a la ruta `/search/authors.json` con el parámetro de consulta `q` igual al nombre del autor.
2. Convierte la respuesta en una instancia de `Books`.
3. Si no se encuentran resultados (`books == null`), retorna una lista vacía.
4. Mapea los documentos (`docs`) de la respuesta para extraer el campo `top_work` (título principal del trabajo) y los retorna como una lista.

#### Parámetros:
- **`authorName`**: Nombre del autor.

#### Retorno:
- Una lista de títulos de libros asociados al autor proporcionado.

---

### `static void main(String[] args)`
Método principal para probar la funcionalidad de la clase. Realiza las siguientes acciones:
1. Crea una instancia de `OpenLibrary` utilizando un `RestClient.Builder`.
2. Busca libros con el título "Spring Framework" y los imprime en la consola.
3. Busca títulos de libros asociados al autor "Craig Walls" y los imprime en la consola.

---

## Insights

1. **Uso de `RestClient`**: La clase utiliza el cliente REST de Spring (`RestClient`) para realizar solicitudes HTTP. Esto simplifica la interacción con APIs externas y permite una configuración fluida de la URL base y las rutas.
   
2. **Registros (`record`) en Java**: Los registros `Books` y `Book` aprovechan la funcionalidad de `record` en Java para definir estructuras de datos inmutables con menos código boilerplate.

3. **Modularidad**: Los métodos están diseñados para ser reutilizables y encapsulan la lógica de interacción con la API de Open Library.

4. **Gestión de respuestas nulas**: La clase maneja casos en los que la respuesta de la API es nula, retornando listas vacías en lugar de valores nulos, lo que mejora la robustez del código.

5. **Uso de Streams**: La clase utiliza `Stream API` para transformar y filtrar datos de manera eficiente, lo que es ideal para manejar colecciones grandes.

6. **Dependencia de Spring**: La clase depende de Spring Framework, lo que la hace adecuada para aplicaciones basadas en Spring pero limita su uso en entornos que no utilizan este framework.

---

## Ejemplo de Uso

### Entrada:
```java
OpenLibrary openLibrary = new OpenLibrary(RestClient.builder());
List<Book> books = openLibrary.getBooks("Spring Framework");
List<String> booksByAuthor = openLibrary.getBookTitlesByAuthor("Craig Walls");
```

### Salida:
```plaintext
[Book[isbn=[1234567890], title=Spring Framework Essentials, authorName=[Craig Walls]]]
[Spring Framework Essentials, Spring Boot in Action]
```

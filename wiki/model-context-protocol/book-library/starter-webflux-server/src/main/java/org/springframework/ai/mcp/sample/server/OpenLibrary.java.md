# Documentación de `OpenLibrary.java`

## METADATA
**Nombre del archivo:** `OpenLibrary.java`

---

## Descripción General
La clase `OpenLibrary` proporciona una interfaz para interactuar con la API pública de Open Library. Permite buscar libros por título y obtener títulos de libros asociados a un autor específico. Utiliza el cliente REST de Spring para realizar solicitudes HTTP y procesar las respuestas.

---

## Estructura de la Clase

### Paquete
La clase está ubicada en el paquete:
```java
org.springframework.ai.mcp.sample.server
```

### Importaciones
La clase utiliza las siguientes bibliotecas:
- **Java estándar**: `java.util.List`, `java.util.Map`.
- **Spring Framework**:
  - `org.springframework.ai.tool.annotation.Tool`
  - `org.springframework.stereotype.Service`
  - `org.springframework.web.client.RestClient`

---

## Declaraciones de Datos

### `Books`
Un registro que representa la estructura de respuesta de la API para la búsqueda de libros. Contiene:
- `numFound` (Integer): Número total de resultados encontrados.
- `start` (Integer): Índice inicial de los resultados.
- `numFoundExact` (Boolean): Indica si el número de resultados encontrados es exacto.
- `docs` (List<Map<String, Object>>): Lista de documentos que representan los libros.

### `Book`
Un registro que representa un libro individual. Contiene:
- `isbn` (List<String>): Lista de códigos ISBN del libro.
- `title` (String): Título del libro.
- `authorName` (List<String>): Lista de nombres de autores del libro.

---

## Métodos

### Constructor
#### `OpenLibrary()`
Constructor por defecto que inicializa el cliente REST con la URL base de Open Library (`https://openlibrary.org`).

#### `OpenLibrary(RestClient.Builder restClientBuilder)`
Constructor que permite personalizar el cliente REST utilizando un `RestClient.Builder`.

---

### Métodos Públicos

#### `getBooks(String title)`
**Descripción:** Obtiene una lista de libros que coinciden con el título proporcionado.  
**Parámetros:**
- `title` (String): Título del libro a buscar.

**Retorno:**  
Devuelve una lista de objetos `Book`. Si no se encuentran resultados, devuelve una lista vacía.

**Lógica:**
1. Realiza una solicitud GET a la ruta `/search.json` con el parámetro de consulta `q` igual al título.
2. Procesa la respuesta y mapea los documentos (`docs`) a objetos `Book`.

---

#### `getBookTitlesByAuthor(String authorName)`
**Descripción:** Obtiene una lista de títulos de libros asociados a un autor específico.  
**Parámetros:**
- `authorName` (String): Nombre del autor.

**Retorno:**  
Devuelve una lista de títulos de libros (`List<String>`). Si no se encuentran resultados, devuelve una lista vacía.

**Lógica:**
1. Realiza una solicitud GET a la ruta `/search/authors.json` con el parámetro de consulta `q` igual al nombre del autor.
2. Procesa la respuesta y extrae el campo `top_work` de cada documento.

---

### Método `main`
**Descripción:** Método principal para probar las funcionalidades de la clase.  
**Lógica:**
1. Crea una instancia de `OpenLibrary`.
2. Busca libros por el título `"Spring Framework"` y los imprime.
3. Busca títulos de libros por el autor `"Craig Walls"` y los imprime.

---

## Insights

1. **Uso de Spring Framework:** La clase utiliza el cliente REST de Spring (`RestClient`) para realizar solicitudes HTTP de manera eficiente y segura.
2. **Anotación `@Tool`:** Los métodos `getBooks` y `getBookTitlesByAuthor` están anotados con `@Tool`, lo que sugiere que podrían ser utilizados como herramientas en un contexto más amplio, posiblemente en aplicaciones de inteligencia artificial o automatización.
3. **Estructura Modular:** La clase está bien estructurada, separando claramente los datos (`Books`, `Book`) de la lógica de negocio.
4. **Extensibilidad:** El diseño permite extender fácilmente la funcionalidad para interactuar con otras partes de la API de Open Library.
5. **Gestión de Errores:** Si la respuesta de la API es `null`, los métodos devuelven listas vacías, lo que evita errores inesperados en el flujo de ejecución.

---

## Tabla de Métodos

| **Método**                  | **Descripción**                                      | **Parámetros**          | **Retorno**                |
|-----------------------------|----------------------------------------------------|-------------------------|---------------------------|
| `OpenLibrary()`             | Constructor por defecto.                           | Ninguno                 | Instancia de `OpenLibrary` |
| `OpenLibrary(RestClient.Builder)` | Constructor con cliente REST personalizado.      | `RestClient.Builder`    | Instancia de `OpenLibrary` |
| `getBooks(String title)`    | Obtiene libros por título.                         | `title` (String)        | `List<Book>`              |
| `getBookTitlesByAuthor(String authorName)` | Obtiene títulos de libros por autor.            | `authorName` (String)   | `List<String>`            |
| `main(String[] args)`       | Método principal para pruebas.                     | `args` (String[])       | Ninguno                   |

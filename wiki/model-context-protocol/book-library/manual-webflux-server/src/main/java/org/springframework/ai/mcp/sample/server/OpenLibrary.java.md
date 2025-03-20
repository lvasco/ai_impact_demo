# Documentación de `OpenLibrary.java`

## Descripción General

La clase `OpenLibrary` proporciona una interfaz para interactuar con la API pública de Open Library. Permite buscar libros por título y obtener títulos de libros asociados a un autor específico. Utiliza el cliente REST de Spring (`RestClient`) para realizar solicitudes HTTP a la API de Open Library.

---

## Metadatos

| Atributo       | Valor                                      |
|----------------|--------------------------------------------|
| **Nombre del Archivo** | `OpenLibrary.java`                       |
| **Autor**       | Christian Tzolov                          |
| **Licencia**    | Apache License, Version 2.0               |
| **Paquete**     | `org.springframework.ai.mcp.sample.server` |

---

## Estructuras de Datos

### `Books`
Un registro que representa la respuesta de la API al buscar libros.

| Campo           | Tipo                        | Descripción                                      |
|------------------|-----------------------------|--------------------------------------------------|
| `numFound`       | `Integer`                  | Número total de resultados encontrados.         |
| `start`          | `Integer`                  | Índice de inicio de los resultados.             |
| `numFoundExact`  | `Boolean`                  | Indica si el número de resultados es exacto.    |
| `docs`           | `List<Map<String, Object>>`| Lista de documentos que representan libros.     |

---

### `Book`
Un registro que encapsula información básica sobre un libro.

| Campo           | Tipo            | Descripción                                      |
|------------------|-----------------|--------------------------------------------------|
| `isbn`           | `List<String>` | Lista de números ISBN asociados al libro.       |
| `title`          | `String`       | Título del libro.                               |
| `authorName`     | `List<String>` | Lista de nombres de los autores del libro.      |

---

## Métodos

### Constructor: `OpenLibrary(RestClient.Builder restClientBuilder)`
Inicializa una instancia de `OpenLibrary` configurando el cliente REST con la URL base de la API de Open Library.

| Parámetro            | Tipo                | Descripción                                      |
|----------------------|---------------------|--------------------------------------------------|
| `restClientBuilder`  | `RestClient.Builder`| Constructor del cliente REST.                   |

---

### `List<Book> getBooks(String title)`
Busca libros en Open Library que coincidan con un título dado.

| Parámetro | Tipo     | Descripción                                      |
|-----------|----------|--------------------------------------------------|
| `title`   | `String` | Título del libro a buscar.                       |

| Retorno         | Tipo         | Descripción                                      |
|------------------|--------------|--------------------------------------------------|
| `List<Book>`     | `List<Book>` | Lista de libros que coinciden con el título.    |

**Lógica:**
1. Realiza una solicitud GET a la ruta `/search.json` con el parámetro de consulta `q` igual al título proporcionado.
2. Si no se encuentran resultados (`books == null`), retorna una lista vacía.
3. Mapea los documentos (`docs`) de la respuesta a objetos `Book`.

---

### `List<String> getBookTitlesByAuthor(String authorName)`
Obtiene los títulos principales de libros escritos por un autor específico.

| Parámetro     | Tipo     | Descripción                                      |
|---------------|----------|--------------------------------------------------|
| `authorName`  | `String` | Nombre del autor a buscar.                       |

| Retorno             | Tipo             | Descripción                                      |
|----------------------|------------------|--------------------------------------------------|
| `List<String>`       | `List<String>`  | Lista de títulos principales de libros del autor.|

**Lógica:**
1. Realiza una solicitud GET a la ruta `/search/authors.json` con el parámetro de consulta `q` igual al nombre del autor.
2. Si no se encuentran resultados (`books == null`), retorna una lista vacía.
3. Extrae el campo `top_work` de cada documento (`docs`) y lo retorna como una lista de títulos.

---

### `static void main(String[] args)`
Método principal para probar la funcionalidad de la clase.

**Lógica:**
1. Crea una instancia de `OpenLibrary` utilizando un `RestClient.Builder`.
2. Busca libros relacionados con el título `"Spring Framework"` y los imprime.
3. Busca títulos de libros escritos por `"Craig Walls"` y los imprime.

---

## Insights

1. **Uso de `RestClient` de Spring**: La clase utiliza el cliente REST de Spring para realizar solicitudes HTTP, lo que simplifica la interacción con APIs externas.
2. **Registros (`record`) en Java**: Se emplean registros para modelar datos inmutables (`Books` y `Book`), lo que mejora la legibilidad y reduce el código boilerplate.
3. **Manejo de Respuestas Nulas**: La clase maneja escenarios donde la API no retorna resultados (`books == null`), asegurando que no se produzcan errores inesperados.
4. **Modularidad**: Los métodos están bien definidos y encapsulan funcionalidades específicas, lo que facilita su reutilización y prueba.
5. **Dependencia Externa**: La clase depende de la API de Open Library y del cliente REST de Spring, lo que puede requerir configuraciones adicionales en un entorno real.

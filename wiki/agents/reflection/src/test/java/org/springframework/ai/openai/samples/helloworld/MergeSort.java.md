# Documentación de `MergeSort.java`

## Descripción General

Este archivo implementa el algoritmo de ordenamiento **Merge Sort** en Java. Merge Sort es un algoritmo de ordenamiento basado en el paradigma de **divide y vencerás**, que divide un arreglo en subarreglos más pequeños, los ordena recursivamente y luego los combina en un arreglo ordenado.

El programa incluye una función principal (`main`) que ejecuta varios casos de prueba para demostrar el funcionamiento del algoritmo.

---

## Estructura del Código

### Paquete
El archivo pertenece al paquete:
```java
package org.springframework.ai.openai.samples.helloworld;
```

### Clases y Métodos

#### Clase `MergeSort`
Contiene la implementación del algoritmo Merge Sort y métodos auxiliares para realizar el ordenamiento.

| Método                          | Descripción                                                                                     |
|---------------------------------|-------------------------------------------------------------------------------------------------|
| `public static void mergeSort(int[] array)` | Método público que inicia el proceso de ordenamiento. Valida si el arreglo es válido y lo pasa al método recursivo. |
| `private static void mergeSort(int[] array, int[] tempArray, int start, int end)` | Método recursivo que divide el arreglo en mitades y llama al método `merge` para combinarlas. |
| `private static void merge(int[] array, int[] tempArray, int start, int mid, int end)` | Combina dos subarreglos ordenados en un único arreglo ordenado. |
| `public static void main(String[] args)` | Método principal que ejecuta casos de prueba para demostrar el funcionamiento del algoritmo. |
| `private static void printArray(int[] array)` | Método auxiliar para imprimir los elementos de un arreglo en la consola. |

---

## Detalles de Implementación

### Algoritmo Merge Sort

1. **División**:
   - El arreglo se divide en dos mitades recursivamente hasta que cada subarreglo tenga un solo elemento o esté vacío.

2. **Combinación**:
   - Los subarreglos se combinan en un arreglo ordenado utilizando un arreglo temporal (`tempArray`).

3. **Optimización**:
   - Se utiliza `System.arraycopy` para copiar datos al arreglo temporal, lo que mejora la eficiencia.

### Casos de Prueba

El método `main` incluye varios casos de prueba para validar el algoritmo con diferentes tipos de arreglos:

| Caso de Prueba | Descripción                              | Ejemplo de Entrada         | Ejemplo de Salida          |
|----------------|------------------------------------------|----------------------------|----------------------------|
| 1              | Arreglo desordenado                     | `{12, 11, 13, 5, 6, 7}`    | `{5, 6, 7, 11, 12, 13}`    |
| 2              | Arreglo con elementos repetidos          | `{5, 5, 5, 5, 5, 5}`       | `{5, 5, 5, 5, 5, 5}`       |
| 3              | Arreglo vacío                           | `{}`                       | `{}`                       |
| 4              | Arreglo con números negativos           | `{-1, -3, -2, -5, -4}`     | `{-5, -4, -3, -2, -1}`     |
| 5              | Arreglo ya ordenado                     | `{1, 2, 3, 4, 5, 6}`       | `{1, 2, 3, 4, 5, 6}`       |
| 6              | Arreglo en orden inverso                | `{9, 7, 5, 3, 1, 0}`       | `{0, 1, 3, 5, 7, 9}`       |

---

## Insights

1. **Complejidad Temporal**:
   - **Promedio y peor caso**: \(O(n \log n)\), donde \(n\) es el tamaño del arreglo.
   - **Mejor caso**: También \(O(n \log n)\), ya que el algoritmo siempre divide y combina los subarreglos.

2. **Complejidad Espacial**:
   - Requiere espacio adicional proporcional al tamaño del arreglo debido al uso del arreglo temporal (`tempArray`).

3. **Estabilidad**:
   - Merge Sort es un algoritmo **estable**, lo que significa que preserva el orden relativo de los elementos iguales.

4. **Uso de Recursión**:
   - La implementación utiliza recursión para dividir el arreglo. Esto puede llevar a un consumo adicional de memoria en el stack para arreglos muy grandes.

5. **Casos Especiales**:
   - El algoritmo maneja correctamente arreglos vacíos y arreglos con elementos repetidos.

6. **Optimización**:
   - No se realiza una copia adicional de los elementos del lado derecho, ya que estos ya están en su lugar en el arreglo original.

---

## Licencia

El código está licenciado bajo la **Apache License 2.0**. Para más detalles, consulte el archivo de licencia en [https://www.apache.org/licenses/LICENSE-2.0](https://www.apache.org/licenses/LICENSE-2.0).

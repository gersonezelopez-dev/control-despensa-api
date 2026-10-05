# Control de Despensa API

## Descripción

Este proyecto consiste en el desarrollo de una API REST básica para consultar y analizar los productos 
almacenados en la despensa de un hogar.

Los productos se almacenan temporalmente en memoria mediante una colección `List<Producto>`.

La aplicación permite listar productos, realizar búsquedas por identificador y categoría, consultar 
productos con stock bajo, identificar el producto con mayor valor almacenado y obtener un resumen 
general del inventario.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Maven
- IntelliJ IDEA
- API REST
- JSON

## Requisitos

Para ejecutar el proyecto se necesita:

- Java 17 o superior.
- IntelliJ IDEA.
- Maven o Maven Wrapper.
- Puerto 8080 disponible.

## Estructura principal

La aplicación se divide principalmente en:

- `ControlDespensaApiApplication`: clase principal de Spring Boot.
- `Producto`: representa cada producto almacenado.
- `ResumenInventario`: representa el resumen general del inventario.
- `ProductoController`: contiene los endpoints REST y la lógica de consulta.

## Clase Producto

La clase `Producto` contiene los siguientes atributos:

- id
- nombre
- categoria
- cantidad
- precioUnitario

También contiene el método `calcularSubtotal()`, que multiplica la cantidad disponible por el precio unitario.

## Clase ResumenInventario

La clase `ResumenInventario` almacena:

- Cantidad de productos diferentes.
- Total de unidades.
- Valor monetario total del inventario.

## Endpoints

| Operación | Método | Ruta | Estado esperado |
|---|---|---|---|
| Listar productos | GET | /api/productos | 200 |
| Buscar por identificador | GET | /api/productos/{id} | 200 o 404 |
| Buscar por categoría | GET | /api/productos/categoria/{categoria} | 200 |
| Consultar stock bajo | GET | /api/productos/stock-bajo | 200 |
| Consultar producto de mayor valor | GET | /api/productos/mayor-valor | 200 |
| Obtener resumen | GET | /api/productos/resumen | 200 |

## Ejecución

Ejecutar la clase:

`ControlDespensaApiApplication`

La aplicación se inicia de forma predeterminada en:

`http://localhost:8080`

Para consultar todos los productos:

`GET http://localhost:8080/api/productos`

## Ejemplo de respuesta JSON

```json
{
  "id": 3,
  "nombre": "Arroz",
  "categoria": "Granos",
  "cantidad": 4,
  "precioUnitario": 8.5
}
```

## Ejemplo del resumen

```json
{
  "cantidadProductos": 6,
  "totalUnidades": 25,
  "valorTotal": 250.5
}
```

## Estudiante de Universidad Mariano Gálvez

Nombre: Gerson Ezequiel López Enriquez  
Carné: 9941-25-22144
# JavaTest for Inditex

Se resuelve el challenge pactado utilizando las tecnologías que se mencionan al final del documento.

El servicio devuelve un objeto con todos los datos de los precios, filtrando por Fecha, ProductoId y BrandId.
En caso de haber más de un precio para esos 3 parámetros, se obtiene el de mayor prioridad.
Se tiene en cuenta las validaciones de formato de los parametros, y tambien se verifica que tanto el Producto y la Brand por las que se esta intentando filtrar,
efectivamente existan.

Para que sea mas sencillo para quien revise este ejercicio, se deployó el proyecto en Heroku, el cual esta accesible desde el siguiente link:
https://inditex.herokuapp.com/swagger-ui/index.html

Allí se puede ejecutar el endpoint que devolverá los resultados correspondientes.

## :hammer: Tecnologias Utilizadas
- `Java 11` 
- `Spring Boot` 
- `H2 Database` 
- `JPA y Hibernate` 
- `Unit Test: JUnit 5, Mockito, RestAssured`

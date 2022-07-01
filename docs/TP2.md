
# Entrega 2
Además de permitir, a los usuarios, compartir sus itinerarios de viajes, queremos que puedan estimar y comparar distintas propuestas, a la hora de elegir dónde viajar.
Por eso, también queremos incorporar a un posible viaje los costos de alquiler de un vehículo.

## Vehículos

Sabemos que cuentan con:

* Marca: es el nombre de la empresa que lo fabrica.
* Modelo: es el nombre del vehículo.
* Año de Fabricación: es el año en que se fabricó esa unidad específica.
* Costo Diario: cada vehículo tiene un costo diario, a la hora de ser alquilado.
* Costo Base: corresponde al costo diario por la cantidad de días de alquiler.
* Tiene convenio: esto sucede si el vehículo es de alguna Marca con la que tengamos un convenio. A la fecha, solo tenemos convenio con la Marca “Honda”.


Antigüedad: es la cantidad de años desde su fabricación (Ej. año de fabricación 2021->antigüedad : 1 año.)
Kilometraje Libre: cada vehículo puede contar con kilometraje libre o no.

Contamos con las siguientes unidades: Motos - Autos - Camionetas.
Para determinar el costo de alquiler tenemos:
Motos: al costo base se le agrega…
Si es de una cilindrada mayor a 250cc, $500 por día.
Autos: al costo base se le agrega…
Si es hatchback (sin baúl) un 10% del costo base
Si NO es hatchback un 25% del costo base
Camionetas:  al costo base se le agrega…
Si el alquiler es menor o igual a una semana —➤ $10.000
Si el alquiler es mayor a una semana —➤ $10.000 + $1.000 más por cada día de exceso.
Si la camioneta es 4x4 se le aplica un recargo del 50% a los cálculos antes mencionados.

A los vehículos con convenio se le descuenta un 10% del costo calculado al momento.
Ej: Moto “Honda” de 500cc, con costo diario de $1.000, alquilada por 10 días.
$1.000 x 10 días + $500 x 10 días = $15.000 —➤ por tener convenio se descuenta el 10% el costo total es $13.500


## Usuarios

No a  todos los usuarios les gusta cualquier vehículo, tenemos identificado algunas formas en que determinan si les gusta.

el neófilo, le gustan los vehículos que no sean antiguos, es decir que su antigüedad sea menor a 2 años
el supersticioso, solo quiere vehículos fabricados en años pares

al caprichoso, le gustan los vehículos cuya inicial de la marca, coincida con la inicial del modelo.
el selectivo, siempre que el vehículo sea de una marca específica.
el sin límite, quiere que tenga el kilometraje libre
el combinado es la combinación de 2 ó más, de las formas de determinar mencionadas antes, en cuyo caso, para que le guste un vehículo se tienen que cumplir todas las condiciones impuestas por las distintas formas.

## Repositorios

Para cada una de las entidades Destino, Usuario,  Itinerario, Vehículo, se pide crear un objeto Repositorio que tenga una colección de objetos en memoria, y que implemente el siguiente comportamiento:

* void create(T object): Agrega un nuevo objeto a la colección, y le asigna un identificador único (id). El identificador puede ser autoincremental para evitar que se repita.

* void delete(T object): Elimina el objeto de la colección.

* void update(T object): Modifica el objeto dentro de la colección. De no existir el objeto buscado, es decir, un objeto con ese id, se debe lanzar una excepción.

* T getById(Int id): Retorna el objeto cuyo id sea el recibido como parámetro.

* List<T> search(String value): Devuelve los objetos que coincidan con la búsqueda de acuerdo a los siguientes criterios:

  1. Destino: El valor de búsqueda debe coincidir parcialmente con el nombre del país, o con el nombre de la ciudad.
  2. Usuario: El valor de búsqueda debe coincidir parcialmente con su nombre o apellido, o exáctamente con su username.
  3. Itinerario: El valor de búsqueda debe coincidir parcialmente con el país o ciudad del destino que corresponda o con alguna de las actividades.
  1. Vehículo: El valor de búsqueda debe coincidir exactamente con la marca o con el comienzo del modelo.




## Servicio de actualización
Nuestro repositorio de Destinos debe actualizarse a demanda obteniendo datos desde un servicio externo. Para ello debemos implementar

Para actualizar nuestro repositorio de Destinos utilizaremos el método getDestinos() de ServiceDestinos. El mismo se conectará con un servicio REST y retornará un JSON con el siguiente formato:
```
[
{
"id": 9,
"pais": "Argentina",
"ciudad": "Mar del Plata",
"costo": 10000
},
{
"id": 12,
"pais": "Brasil",
"ciudad": "Rio de Janeiro",
"costo": 20000
},
{
"pais": "Indonesia",
"ciudad": "Bali",
"costo": 30000
},
(...)
]
```

## Se pide:


1. Diseñar e implementar el modelo de objetos de dominio.

2. Diseñar e implementar los casos de prueba correspondientes.
Armar el juego de datos necesario para realizar las pruebas.
Codificar los tests unitarios.
Usar mocks y/o stubs para testear los casos que impliquen llamadas al Services de Destinos

3. La entrega debe desarrollarse en un branch develop, que será mergeado en master, a través de un pull request, luego de ser aprobado por el tutor del grupo.

4. Integrar el proyecto con Github Action.

5. Agregar al README los badges.

6. Generar un tag llamado “entrega-2” en el repositorio.


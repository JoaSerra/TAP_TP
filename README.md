Trabajo Practico Técnicas Avanzadas de Programación
UNIVERSIDAD DE PALERMO

Joaquin Serra
Legajo: 0133581

Primera entrega: 28/04
Segunda entrega: 09/06


--------------------- Consigna del trabajo ---------------------

Una pequeña sala de teatro independiente requiere que se gestione la venta de entradas para sus espectáculos.
Cuentan con dos espacios:
* Una sala con capacidad para 70 personas. Costo variable en las entradas, las tipo A, tienen un costo del doble de las tipo B
* En anfiteatro a cielo abierto con una capacidad para 120 personas. Precio único.

Para lo cual un usuario registrado en el sistema registra la siguiente información:

Artista
Fecha de la función
Hora de la función
Sala (sala, anfiteatro)
Precio de la entrada
Duración
Tipo de show (infantil, musical, obra de teatro)

En el proceso de carga se debe validar que no se superpongan los espectaculos en una misma fecha y hora, para permitir la carga de espectaculos un mismo día, debe haber una hora libre en la sala para su limpieza previa al inicio del proximo show

El sistema debe poder mostrar los espectaculos próximos a presentarse en la sala, así como los anteriores, en este ultimo caso por supuesto no debe poder permitir realizar la compra de entradas.

--------------------- ESPECIFICACIONES ---------------------

Lenguaje: Java

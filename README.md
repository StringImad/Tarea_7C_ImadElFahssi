# Tarea_7C_ImadElFahssi - Intercambio de datos entre sistemas
#Abre el fichero CSV adjunto y estudia su contenido. Este fichero ha sido generado con un programa específico que genera horarios del profesorado de un centro educativo. Como puedes comprobar, este archivo tiene los campos separados por punto y coma.

Las columnas son:

 - número identificativo de la fila (no es significativo, se puede ignorar),

 - curso implicado (1ESOA, ..., 1DAW, 2DAW, etc),

 - iniciales del profesor,

 - asignatura que se imparte,

 - aula, si esa hora no tiene aula queda entre comillas vacías

 - día de la semana (1 Lunes, 2 Martes, 3 Miércoles, etc.) y

 - la hora del día asignada:
        - 1 es 1ª hora, 
        - 2 es 2ª hora, 
        - 3 es 3ª hora, 
        - 5 es la 4ª hora,
        - 6 es la 5ª hora,
        - 7 es la 6ª hora,
La hora 4 no existe por ser el recreo y el resto de horas (de 8 en adelante) se pueden ignorar por ser el horario de tarde, que en nuestro caso no vamos a tener en cuenta.

Cada registro del fichero representa una hora de clase semanal en el instituto, de forma que, por ejemplo, la fila
13;"2ESOB";"IBR ";"AMBLS";"408 ";1;1 representa la primera hora (1) del lunes (1) de 2ESOB que tiene el profesor IBR en el aula 408 y la asignatura impartida es AMBLS.

Presta atención a las dobles comillas que hay en los campos de texto, que deben ser quitadas a la hora de ir guardando los distintos datos en los objetos. Si hay espacios en blanco en los campos de texto también se deben quitar. Todos los datos se pueden guardar en modo texto, excepto los números de día y hora. 

Crea un nuevo proyecto Maven, llamado tarea7c+tunombre. Implementa una aplicación que permita consultar el horario semanal de cualquier unidad (1ESOA, 1ESOB, etc) o de cualquier profesor/a. Para ello, una vez leído el fichero desde la carpeta raíz del proyecto, se procederá a almacenar la información de la siguiente forma:
1. Todos los registros se guardarán en una lista de objetos POJO, cuya clase se ha de crear para tal efecto. El nombre de la clase lo decides tú, pero debe ser significativo. Una vez guardados los objetos en la lista, ésta debe quedar ordenada primero por día y luego por hora.

2. El conjunto de grupos y de iniciales de profesores se guardarán en dos estructuras SET, ya que no debe haber duplicados. Estas estructuras deben estar ordenadas siguiendo el orden alfabético. 
3. Otras estructuras de datos auxiliares que estimes convenientes, siempre y cuando esté debidamente justificado su uso.


A PARTIR DE ESTE PUNTO, EN NINGÚN CASO SE DEBE LEER DE NUEVO EL FICHERO CSV, PUESTO QUE LA INFORMACIÓN YA ESTÁ EN LAS ESTRUCTURAS DE DATOS.
PARTE A
Cuando toda la información esté en las estructuras de datos, la aplicación proporciona dos opciones:
a) Consultar horarios por profesor/a.
b) Consultar horarios por grupo.


Si el usuario selecciona a), el programa mostrará las iniciales del profesorado, para que el usuario elija una. Una vez proporcionada la inicial por parte del usuario, el programa guardará en un fichero json, en la raíz del proyecto, el horario seleccionado, si existe, volcando el objeto POJO en el archivo, al igual que hicimos con el ejercicio de las app. El nombre del fichero estará compuesto por las iniciales del profesor en cuestión y la extensión del archivo. Por ejemplo, para el profesor JCF, el archivo se llamará JCF.json. 

Si el usuario selecciona b), el programa mostrará los grupos que hay en el instituto, para que el usuario elija uno. Una vez proporcionado el grupo, el programa guardará en un fichero csv, en la raíz del proyecto, el horario seleccionado, si existe. El nombre del fichero estará compuesto por las iniciales del grupo en cuestión y la extensión del archivo. Por ejemplo, para el grupo 1DAW, el archivo se llamará 1DAW.csv.

PARTE B

Además, en otra clase que contenga otro método main y aplicando API Stream, realiza las siguientes acciones sobre la lista de objetos leídos desde el fichero:
a) Obtener todos los registros de 1ESOA que no son de la asignatura MUS.
b) Contar las horas que se imparten de la asignatura PROGR
c) Obtener una lista con las iniciales del profesorado que imparte la asignatura REL, ordenadas en orden inverso al orden alfabético.
d) Obtener en una lista las aulas donde imparte clase el profesor "JFV"
e) Contar el número de asignaturas distintas que hay
f) Contar el total de horas que se imparten a última hora de la mañana.
g) Mostrar por consola los profesores que tienen clase a primera hora de la mañana.

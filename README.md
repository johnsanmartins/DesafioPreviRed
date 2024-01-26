**Levantar el proyecto y dirigirse a la siguiente dirección de Swagger


**USUARIOS

**Crear
http://localhost:8080/api/usuarios/crearUsuario

**Una vez ahi ingresar el siguiente JSON

{
"nombre": "Juan Rodriguez",
"email": "juan@rodriguez.org",
"password": "hunter2"
}

**Delete

http://localhost:8080/api/usuarios/eliminar/1

curl -X 'DELETE' \
  'http://localhost:8080/api/usuarios/1' \

**Actualizar

http://localhost:8080/api/usuarios/actualizar/1

**buscar

http://localhost:8080/api/usuarios/buscar/1

 

**Para ver la BBDD pueden dirigirse a la dirección 

http://localhost:8080/h2-console user:sa pass:password

**En caso de querer usar POSTMAN pueden usar el siguiente CURL

curl --location 'http://localhost:8080/api/usuarios/crearUsuario' \ --header 'Content-Type: application/json' \ --data-raw '{"nombre": "Juan Rodriguez","email": "juan@rodriguez.org","password": "hunter2"}'

**TAREAS

**Crear
http://localhost:8080/api/tareas/crearTarea

**Una vez ahi ingresar el siguiente JSON

{
"nombreTarea": "prueba",
"descripcion": "descripcion de prueba",
"estadoTarea": [
{
"estado": "creado"
}] 
}

**Delete

http://localhost:8080/api/tareas/eliminar/1

curl -X 'DELETE' \
  'http://localhost:8080/api/tareas/1' \

**Actualizar

http://localhost:8080/api/tareas/actualizar/1

**buscar

http://localhost:8080/api/tareas/buscar/1

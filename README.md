merdadolibrePrueba 

Antes de ejecutar la aplicación se debe crear el schema de la base de datos mutantesdb, si de pronto se presenta algun problema algun problema al tratar de subir la aplicación se debe ejecutar este comando en la base de datos creada **SET GLOBAL time_zone = '-3:00';** 

Para desplegar la aplicación se debe iniciar la clase AplicacionMutante está clase 
crea la tabla **registrar_adn** donde se almacena la secuencia de adn y si el usuario 
es mutante o no, una vez hecho esto los servicios ya estarán disponibles para ejecutarse

Adjuntos los postman de los servicios que se pueden consumir en estos momentos 

Servicio que valida las secuencias de adn que se han validado

**curl --location --request GET 'http://localhost:8080/api/stats' \
--header 'Content-Type: application/json'**

Servicio que valida si una cadena de adn es de un mutante o un humano

**curl --location --request POST 'http://localhost:8080/api/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
 "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}'**


**curl --location --request POST 'http://localhost:8080/api/mutant' \
--header 'Content-Type: application/json' \
--data-raw '{
 "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATTT",
        "AGACGG",
        "GCGTcA",
        "TCACTG"
    ]
}'**

si un usuario es mutante el servicio /mutant responde un 200 OK de lo contrario retorna un 403 FORBIDDEN

**NOTA:** si se desea cambiar los datos de conexión a la base de datos se puede realizar en el application yml


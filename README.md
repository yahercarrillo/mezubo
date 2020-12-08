#MEZUBO TEST

Elemento donde se alojaran las configuraciones
y procesos de automatizacion de bases de datos
bajo la herramienta DB-MIGRATE y Back End Bajo Java

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Estructura de 3 proyectos :


### Componentes 📋
* COMMONS: estructura con objetos comunes
* CONFIGURER: Configurador de propiedades y en donde ademas se aloja el POM padre
* STORAGE: Api Expuesta
* DATABASE: Estructura de base de datos, bajo DB-MIGRATE


### Pre-requisitos 📋

_Que cosas necesitas para instalar el software y como instalarlas_

JAVA 11 
POSTGRES 11
MAVEN. 3.3.6 Fue realizado

### Instalación 🔧

_Aqui tienes una serie de pasos para poder ejecutar estos proyectos en ambiente LOCAL

DB-MIGRATE
* **Instalar Dependencias** - *DevOps* - npm install
* **Instalar DB-MIgrate** - *DevOps* - sudo npm install -g db-migrate-pg
* **Instalar Soporte Postgres** - *DevOps* - sudo npm install -g db-migrate-pg
* **Desplegar Scripts** - *DevOps* - db-migrate up
* **RollBack Scripts** - *DevOps* - db-migrate down

JAVA - MAVEN
* **Instalar Dependencias** - *DevOps* - mvn install (commons)
* **Generar Artefacto Logica del negocio** - *DevOps* - mvn clean package (storage)
* **Poner en marcha el artefacto** - *DevOps* - java -jar storage.war

DOCKER
* **Instalar Dependencias** - *DevOps* - A continuacion, mirar el archivo DickerFile contenido en el proyecto.


RESPUESTAS EJERCICIO CONCEPTUAL

En el contenido del archivo "RespuestasRefactoring", pueden verificar
los criterios a los conceptos del ejercicio de refactoring




_URL: https://github.com/yahercarrillo/mezubo
## Autores ✒️

_Gestores_

* **Yaher Carrillo** - *Aqsw* - [yahercarrillo](https://github.com/yahercarrillo/mezubo)





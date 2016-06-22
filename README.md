# Nuova Aplicacion Web

Instalar

+ Apache Tomcat 7
+ Java JDK 7
+ Apache Maven
+ Git - Git Bash
+ Eclipse (Luna)
+ Mysql 5.6.X (WAMP)
+ Heidi SQL


Descargar el codigo
> git clone https://github.com/arjonamiguel/nuova_master.git

> git pull

MySQL
+ Crear Base de Datos "xxxx"

Configurar Base de Datos en Nuova:
nuova_master/src/main/webapp/WEB-INF/jdbc.properties

Compilar el proyecto
> mvn clean install

Deployar el proyecto
> mvn tomcat7:run

Levantar el proyecto en browser
http://localhost:8080/nuova
user: userName
pass: password


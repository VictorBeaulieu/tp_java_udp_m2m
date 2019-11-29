API simple de communication UDP en Java
En reception, l'api est bloquante, avec timer reglable

fichiers sources :
------------------
* src/UDPApi.java => fichier source de l'api (classe java)
* src/UDPApiException.java => exception specifique pour l'api
* src/samplexxx.c => exemples d'utilisation client et serveur

lignes de compil (sortie dans lib/):
------------------
javac -d bin src/*.java


exemple d'utilisation :
-----------------------
java -classpath lib sampleserver 8889					(local_port)
java -classpath lib sampleclient 8888 127.0.0.1 8889 coucou 2000	(local_port  dest_ip  dest_port  message  timeout)

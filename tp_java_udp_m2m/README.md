# Base pour le TP de M2M

API simple de communication UDP en Java  
En reception, l'api est bloquante, avec timer reglable

Ce Projet servira de base pour les tps de M2M.

## Comment l'utiliser ?

faire une copie de travail ce projet avec git clone:
```bash
cd /home/etudiant/workspace
git clone https://m2m.anthony-thomas.fr/m2m/base-tp-java.git tp-<nom1>-<nom2>
```

Créer un projet `tp-<nom1>-<nom2>` sur le groupe **m2m**.  
Une fois le projet créé, suivre les instructions en dessous de **"Push an existing Git repository"**

```bash
cd tp-<nom1>-<nom2>
git remote rename origin old-origin
git remote add origin https://m2m.anthony-thomas.fr/m2m/tp-<nom1>-<nom2>.git
git push -u origin --all
git push -u origin --tags
```

## Liens des cours et TP
http://cours.anthony-thomas.fr/m2m-dii5/

## Fichiers sources :
* src/UDPApi.java => fichier source de l'api (classe java)
* src/UDPApiException.java => exception specifique pour l'api
* src/samplexxx.java => exemples d'utilisation client et serveur

## Compiler (sortie dans bin/):
```bash
javac -d bin src/*.java
```

## Exemple d'utilisation :
```bash
# java -classpath bin sampleserver local_port
java -classpath bin sampleserver 8889
# java -classpath bin sampleclient local_port  dest_ip  dest_port  message timeout
java -classpath bin sampleclient 8888 127.0.0.1 8889 coucou 2000
```

**Code source à rendre à la fin du TP en faisant un git push de vos commit.**

## Commit and push
```bash
git add .
git commit -m "tp1 m2m"
git push
```


# Spécification : Calculatrice

## 1.Overview
Notre protocole permet de faire des calculs, les opérations supportées sont :
* addition [ADD]
* multiplication [MUL]
* soustraction [SUB]

La demande est envoyée sous la forme d'un String de la forme :

"Opération Opérande1 Opérande2"

Avec Opérande1 et Opérande2 comme des nombres.

Un message d'erreur sera envoyé si les conditions ne sont pas remplies

## 2.Transport layer protocol
Notre protocole utilise TCP. Le client établit la connexion. Il doit connaître l'adresse IP du serveur. Le serveur écoute sur le port TCP 1234.

Le serveur ferme la connection après que le Client envoie la String "X".
## 3.Messages
Il y a trois types de message :
* CalcDemand :
La demande de calcul faite par le client
* Result :
Le résultat calculé par le serveur
* Unkown Operation :
S'il y a un élément inconnu dans la demande du client, le serveur enverra ce message d'erreur.

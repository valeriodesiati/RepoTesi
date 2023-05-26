# Indice
 - [About the project](#About-the-project)
 - [Microservizio checkemail](#Microservizio-checkemail)
   - [Funzionamento](#Funzionamento)
 - [Infrastruttura](#Infrastruttura)
   - [App Service](#App-Service)
   - [Database](#Database)
 - [Pipelines](#Pipelines)
   - [Pipeline build-deploy.yml](#Pipeline-build-deploy.yml)
 - [Autori e Copyright](#Autori-e-Copyright)

# About the project
Il repository Spring-ID è utilizzato per contenere il microservizio in Java.


# Microservizio checkemail
Il microservizio è realizzato in Java con il framework Spring Boot e l'API Java Persistence Spring Data JPA.

##Funzionamento
Il microservizio è composto da tre Controller
 - `EmailController.java`<br />
   Consente di effetture richieste con metodi `POST`, `GET` e `DELETE`.<br />
   Con il metodo `POST` è possibile creare un utente nella tabella `email` con i campi `id` e `email`.<br />
   Con il metodo `GET`, specificando il campo `email`, si possono ottenere tutte le informazioni riguardo l'utente scelto.<br />
   Con il metodo `DELETE`, specificando il campo `email`, si può cancellare il record dell'utente dal database.

 - `UsersController.java`<br />
   Consente di effetture richieste con metodi `POST`, `GET` e `DELETE`.<br />
   Con il metodo `POST` è possibile creare un utente nella tabella `users` con i campi `id` e `email`, `name`, `surname`.<br />
   Con il metodo `GET`, specificando il campo `email`, si possono ottenere tutte le informazioni riguardo l'utente scelto.<br />
   Con il metodo `DELETE`, specificando il campo `email`, si può cancellare il record dell'utente dal database.

 - `JoinController.java`<br />
   Consente di effettuare richieste con il metodo `GET`.<br />
   La richiesta viene effettuata specificando il campo `email`. Viene poi effettuata una join query tra la tabella `email` e `users`.<br />
   Se il campo `email` fornito risulta presente in entrambe le tabelle viene dato in output un codice HTTP 200, altrimenti 402.


# Infrastruttura

### App Service
L'intero microservizio si appoggia su un__app service__ con pubblicazione di codice, accessibile a [questo indirizzo](https://restservice-springid.azurewebsites.net/join/checkemail/fiorenzo.tittaferrante@aesys.tech).
Il messaggio printato sullo schermo sarà unauthorized a causa dei plugin che richiedono nell'head della richiesta un _token_.

### Database
Il microservizio è collegato ad un __server singolo di Database di Azure per PostgreSQL__, con configurazioni di base. <br/>
Per evitare problemi di connessione da parte di JDBC, sono state aggiunte delle righe nell'[application.properties](https://dev.azure.com/id-microservice/Spring-ID/_git/Spring-ID?path=/src/main/resources/application.properties&version=GBmain)
riguardo le connessioni al db.


# Pipelines

### Pipeline build-deploy.yml
La pipeline è azionata automaticamente quando viene fatto una pull in _src/_.
Azionata la pipeline _build-deploy.yml_, questa si occupa della __build__ e del __deploy__ del microservizio in un app service di nome [restservice-springid](http://restservice-springid.azurewebsites.net/).


## Autori e Copyright
 © Fiorenzo Tittaferrante<br />
 © Valerio Desiati

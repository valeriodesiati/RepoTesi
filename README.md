# Indice
 - [About the project](#About-the-project)
 - [Infrastruttura](#Infrastruttura)
   - [Virtual Machine](#Virtual-Machine)
 - [Pipelines](#Pipelines)
   - [Pipeline update.yml](#Pipeline-update.yml)
   - [Pipeline build-deploy.yml](#Pipeline-build-deploy.yml)
 - [Microservizio checkemail](#Microservizio-checkemail)
   - [Funzionamento](#Funzionamento)
 - [Infrastruttura](#Infrastruttura)
   - [App Service](#App-Service)
   - [Database](#Database)
 - [LUA Plugin](#LUA-Plugin)
   - [File](#File)
   - [Upload](#Upload)
 - [Autori e Copyright](#Autori-e-Copyright)


# About the project
La repository Kong-ID è utilizzata per contenere i file di __configurazione__, i __plugin__ e gli script per automatizzare il settings di Kong-gateway.
Kong si occupa di ricevere richieste dal client, utilizza i plugin installati e inoltra le richieste al microservizio contenuto
nel repository Spring-ID.
La repository Spring-ID è utilizzato per contenere il microservizio in Java.


# Infrastruttura

### Virtual Machine
Creata una macchina virtuale su Azure con Debian e con 1 Gb di RAM sul quale viene creato il __container__ contenente Kong.
Sono esposte diverse porte:
 - __8000__: riceve richieste dai client
 - __8001__: porta di ascolto per l'admin, sul quale è possibile vedere i JSON di Kong
 - __8002__: kong manager, per gestire l'ambiente Kong in modalità grafica


# Pipelines

### Pipeline update.yml
La pipeline è azionata automaticamente quando c'è un cambiamento che riguarda:
 - _config/_
 - _plugin/_
 - _docker-compose.yml_

Si occupa di far eseguire, all'interno della macchina virtuale kong-gateway creata su Azure, lo script _configure_. Lo script si occuperà di creare i container caricare i plugin e i file JSON riguardanti servizi, routes e utilizzatori sul container di Kong.

### Pipeline build-deploy.yml

La pipeline è azionata automaticamente quando viene fatto una pull in _src/_.
Azionata la pipeline _build-deploy.yml_, questa si occupa della __build__ e del __deploy__ del microservizio in un app service di nome restservice-springid.

# Microservizio checkemail

Il microservizio è realizzato in Java con il framework Spring Boot e l'API Java Persistence Spring Data JPA.

## Funzionamento
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

L'intero microservizio si appoggia su un__app service__ con pubblicazione di codice.
Il messaggio printato sullo schermo sarà unauthorized a causa dei plugin che richiedono nell'head della richiesta un _token_.

### Database

Il microservizio è collegato ad un __server singolo di Database di Azure per PostgreSQL__, con configurazioni di base. <br/>
Per evitare problemi di connessione da parte di JDBC, sono state aggiunte delle righe nell'application.properties.
riguardo le connessioni al db.

# LUA Plugin
Il plugin prevede l'integrazione di Kong con un microservizio per verificare l'avvenuto acquisto di un modulo applicativo da parte di un utente, opportunamente identificato tramite token.<br />
### File
I file relativi al plugin si trovano nella directory `plugin/checkemail`.
### Upload
L'aggiunta del plugin è stata semplificata aggiungendo due script, `configure` e `script/addplugin` che consentono di effettuare lo start dei container, leggere tutti i plugin nella directory `/plugin` e di copiare tutti i file nel container, in modo che vengano aggiunti alla configurazione di Kong Gateway. <br />
Sono previsti anche gli upload dei servizi, route, consumer e plugin nativi di Kong tramite delle `curl` al link di configurazione del gateway, prendendo le configurazioni da file `.json` nella directory `config/checkemail`.

## Autori e Copyright
 © Valerio Desiati
 © Fiorenzo Tittaferrante
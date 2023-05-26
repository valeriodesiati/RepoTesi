# Indice
 - [About the project](#About-the-project)
 - [Infrastruttura](#Infrastruttura)
   - [Virtual Machine](#Virtual-Machine)
 - [Pipelines](#Pipelines)
   - [Pipeline update.yml](#Pipeline-update.yml)
 - [LUA Plugin](#LUA-Plugin)
   - [File](#File)
   - [Upload](#Upload)
 - [Autori e Copyright](#Autori-e-Copyright)


# About the project
Il repository Kong-ID è utilizzato per contenere i file di __configurazione__, i __plugin__ e gli script per automatizzare il settings di Kong-gateway.
Kong si occupa di ricevere richieste dal client, utilizza i plugin installati e inoltra le richieste al microservizio contenuto
nel repository [Spring-ID](https://dev.azure.com/id-microservice/Spring-ID/_git/Spring-ID).


# Infrastruttura

### Virtual Machine
Creata una macchina virtuale su Azure con Debian e con 1 Gb di RAM sul quale viene creato il __container__ contenente Kong.
Il container di Kong è accessibile dall'esterno a [questo link](http://checkemail.westeurope.cloudapp.azure.com:8002/overview).
Sono esposte diverse porte:
 - __8000__: riceve richieste dai client
 - __8001__: porta di ascolto per l'admin, sul quale è possibile vedere i JSON di Kong
 - __8002__: kong manager, per gestire l'ambiente Kong in modalità grafica


# Pipelines
Il file _update.yml_ è la pipeline CI/CD per automatizzare i processi di contenerizzazione e di installazione dei plugin.

### Pipeline update.yml
La pipeline è azionata automaticamente quando c'è un cambiamento che riguarda:
 - _config/_
 - _plugin/_
 - _docker-compose.yml_

Si occupa di far eseguire, all'interno della macchina virtuale [kong-gateway](https://portal.azure.com/#@aesys.tech/resource/subscriptions/af0f31ec-6bc5-4e0d-9c47-c509f316251c/resourceGroups/RG-TittaferranteFiorenzo/providers/Microsoft.Compute/virtualMachines/kong-gateway/overview) 
creata su Azure, lo script _configure_. Lo script si occuperà di creare i container caricare i plugin e i 
file JSON riguardanti servizi, routes e utilizzatori sul container di Kong.


# LUA Plugin
Il plugin prevede l'integrazione di Kong con un microservizio per verificare l'avvenuto acquisto di un modulo applicativo da parte di un utente, opportunamente identificato tramite token.<br />
### File
I file relativi al plugin si trovano nella directory `plugin/checkemail`.
### Upload
L'aggiunta del plugin è stata semplificata aggiungendo due script, `configure` e `script/addplugin` che consentono di effettuare lo start dei container, leggere tutti i plugin nella directory `/plugin` e di copiare tutti i file nel container, in modo che vengano aggiunti alla configurazione di Kong Gateway. <br />
Sono previsti anche gli upload dei servizi, route, consumer e plugin nativi di Kong tramite delle `curl` al link di configurazione del gateway, prendendo le configurazioni da file `.json` nella directory `config/checkemail`.

## Autori e Copyright
 © Fiorenzo Tittaferrante<br/>
 © Valerio Desiati
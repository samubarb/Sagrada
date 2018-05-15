# SocketDescription

####Login:
 - login login-name
 - risposta login logged-UserID

####mossa:
 - carta strumento CartaStrumento
 - posiziono dado dice-IdUtente-posizione-
 - non faccio nulla null
 
notifica player :
 - Disconnesso PlayerName-disconnected
 - Connesso PlayerName-connected

statogui

turno giocatore

login
stabilisco connessione
invio richiesta login
richiedo username 
invio username
responso registrato o meno

Client                                      Server
        connessione server ->             
        <- conferma connessione server
        invio richiesta login ->
        <- richiesta username
        invio username ->
        <- conferma login
La conferma login può essere true o false
    - True se l'username è unico e il numero di player è minore di 4
    - False se l'username è già in uso e il player non è inattivo o il numero di giocatori è già 4
    
mossa
invio al giocatore che è il suo turno
giocatore sceglie se usare carta strumento o dado
se carta strumento
    richiedo quello che serve per la carta strumento
    se richiesto dalla tool inserisco la posizione del dado finale
se dado
    richiedo dove posizione il dado

notifica player
ogni x millisecondi il server ha un thread che verifica se il client è ancora connesso
se il player si disconnette:
    setto il player come inactive
    notifico gli altri che si è disconnesso il giocatore

riconnessione player
il player per riconnettersi deve utilizzare lo stesso nome
se il nome è già presente cambio il giocatore da inactive a active
notifico gli altri player della riconnessione

avvio partita
connessione 1 giocatore: aspetto altri giocatori
connessione 2 giocatore:  faccio partire thread con counter per far partire la partita
se il timer non è ancora scaduto:
    connessione 3 giocatore: aspetto altri giocatori o timer
    connessione 4 giocatore: cancello timer e avvio la partita


Creazione setting game
il setting del game viene effettuata dopo che il timer  è scaduto o dopo che il numero di player ha raggiunto i 4 giocatori
le impostazioni di game viene effettuata in base al numero
es:
    - numero dadi estratti = (n° player)*2)+1
    - numero carte obbiettivo private estratte = n° player.
    
Turno

Client                                                  Server
        client in attesa che sia il suo turno
        <------E' il turno del player, scegli la mossa
        mossa scelta----------->
        <------ vuoi fare altro?
        ----|--> si torna a mossa scelta
            |
            |--> no concludi turno
            
Mossa
Il player può fare 3 mosse
 - usare toolcard
 - prendere e posizionare un dado
 - non fare nulla
il giocatore ha la facoltà di posizionare un solo dado per volta come mossa base o non fare nulla e può utilizzare
una toolcard per turno

    




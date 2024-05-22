### PAO-Proiect
Proiect Programare Avanasată pe Obiecte

### Gestiunea unui Aeroport
Proiectul va implementa un sistem complex de gestionare a
unui aeroport, permițând administrarea
`zborurilor, pasagerilor, angajaților, aeronavelor, terminalelor, companiilor aeriene și porților de îmbarcare.`
Acesta va include funcționalități `CRUD` și `interogări avansate` pentru a asigura o operare eficientă și organizată a activităților aeroportuare.

### Obiecte
- `Flight`
- `Passenger`
- `Baggage`
- `Airport`
- `Employee`
- `Runway`
- `Ticket`
- `Aircraft`
- `Airline`

### Acțiuni / Interogări
1. Adăugare Zbor
2. Vizualizare Detalii Zbor
3. Actualizare Zbor
4. Ștergere Zbor
5. Adăugare Pasager
6. Vizualizare Listă Pasageri pentru un Zbor
7. Modificare Detalii Pasager
8. Căutarea Zborurilor după Destinație
9. Verificarea disponibilității Zborului
10. Generare raport Zborurui
11. Adăugare Angajat
12. Vizualizare Detalii Angajat

### Cerinte
Fiecare student va lucra la un poriect individual. Proiectul este structurat în mai multe etape.
Condiția de punctare a proiectelor:

* Să nu prezinte erori de compilare
* Să se implementeze cerințele date
* Predare: Săptămâna `13`

`Comenzile se vor da prin citirea de la tastatura!` <br>
`Nu neste necesara interfata grafică!`

1. Definirea sistemului
    - [x] Să se creeze o listă pe baza temei alese cu cel puțin `7-8` acțiuni/interogări (de preferat și operații diferite de `CRUD`)
      care se pot face în cadrul sistemului și o listă cu cel puțin `5-6` tipuri de obiecte

2. Implementare <br>
   Să se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct. <br>
   Aplicația va conține:
    - [x] clase simple cu atribute private / protected si metode de acces
    - [x] clase imutabile (după caz)
    - [ ] tratare excepții
    - [ ] excepții custom
    - [ ] comparatori
    - [ ] lambda expressions
    - [ ] string builder / buffer
    - [x] cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior (eg. `List, Set, Map`, etc.)
      dintre care cel puțin una să fie sortată
    - [ ] se vor folosi array-uri uni-/bidimensionale în cazul în care nu se parcurg
      colecțiile până la data checkpoint-ului (nu este mandatory)
    - [ ] utilizare moștenire pentru crearea de clase adiționale și utilizarea lor în cadrul colecțiilor;
    - [ ] cel puțin o clasă serviciu care să expună operațiile sistemului
    - [ ] o clasă Main din care sunt făcute apeluri către servicii
    - [ ] Singelton patter
    - [ ] conceptele de bază (abstractizare, mostenire, polimorfism) -> metodele din services si repository`s
      să fie expuse de interfete(sau clase abstracte dupa caz)
    - [ ] să se foloseasca lucrul cu fisiere (ex: audit operatii executate, diferite rapoarte ale datelor din baza etc), 
   minim o actiune cu fisiere
3. Extindeți proiectul prin realizarea persistenței utilizând o bază de date relațională și JDBC
    - [ ] Să se realizeze servicii care să expună operații de tip create, read, update și delete pentru cel puțin 4
      dintre clasele definite. Se vor realiza servicii singleton generice pentru scrierea și citirea din baza de date.

4. Realizarea unui serviciu de audit
    - [ ] Se va realiza un serviciu care să scrie într-un fișier de tip CSV de fiecare dată când este
      executată una dintre acțiunile descrise. Structura fișierului: nume_acțiune, timestamp.
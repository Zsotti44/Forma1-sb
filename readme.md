# Forma 1 projekt feladat

## Megjegyzések

- Az adatbázis importálható a database mappából
- Nem módosítottam a portot, a [http://localhost:8080](http://localhost:8080) -on érhető el
- [http://localhost:8080/diag/available](http://localhost:8080/diag/available) végponton az API elérhetőségét ellenőrizhetjük
- [http://localhost:8080/diag/sqlHealth](http://localhost:8080/diag/sqlHealth) végponton bár van, de még funkció nincs különösebben mögötte
- A models mappában a UserDTO és MessageDTO-k 
- Az adatbázisban létrehoztam egy view-t, az egyik feladathoz egy táblázatot kell megjeleníteni, ami 3 táblát használ. Gondoltam egy ilyenre, hogy győztesek, de ez csak egy ötlet.

>Sorry, az adatbázis táblák, oszlopok magyarok voltak, így úgy voltam vele, hogy maradjon magyar a többi tábla is, keverni még rosszabb :D 

### Regisztráció: 

A [http://localhost:8080/auth/registration](http://localhost:8080/auth/registration) POST végponton működik.
A bodyban küldendő json: 
```
{
    "name": "adrian3",
    "password": "a"
}
```

### Bejelentkezés:

A [http://localhost:8080/auth/login](http://localhost:8080/auth/login) POST végponton működik. A Headerben basic autentikációt kell használni.
A response egyelőre így néz ki. Majd módosítjuk attól függően mi kell, vagy mi nem, meg persze ha végül bekerülne a JWT Token.
```
{
    "userName": "adrian",
    "userId": 1,
    "permission": 1
}
```


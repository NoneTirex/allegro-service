Micro-serwis do wyświetlania nazwy ostatnio modyfikowanego repozytorium na proflu "allegro" w serwisie github.

Do pobrania nazwy ostatnio modyfikowanego repozytorium zostało użyte Github Rest API v3 dostępne [tutaj](https://developer.github.com/v3/repos/#list-user-repositories "Github Rest API v3").


Konfiguracja
---------------------------
```yaml
github:
  oauth-token: OAUTH-TOKEN    # Opcjonalne, OAuth2 token: https://developer.github.com/v3/#authentication
```

Założenia projektu
---------------------------
Aplikacja uruchamiana i testowana przy użyciu Java 8.



Aplikacja została skonstruowana tak, aby była w jak największym stopniu niezawodna.
Problemem może być ograniczenie ilościowe użyć API Githuba (bez autoryzacji 60 użyć, a z autoryzacją 5000).
Użycie klucza OAuth2 jest opcjonalne dla bardziej wymagających użytkowników.


Uruchamianie testów
---------------------------
Testy można uruchomić w Intellij IDEA przy użyciu podstawowej konfiguracji JUnit (trzeba wybrać pakiet i sposób szukania testów).
Innym sposóbe na uruchomienie testów jest wywołanie polecenia `mvn test`.


Budowanie aplikacji
---------------------------
Aplikacje buduje się poprzez wywołanie polecenia `mvn package`.
Po zbudowaniu aplikacji można ją uruchomić poprzez wywołanie polecenia `java -jar target/allegro-service-1.0-SNAPSHOT.jar`.
Więcej informacji o budowaniu i uruchamianiu aplikacji spring boot znajdziesz [tutaj](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-first-application.html#getting-started-first-application-executable-jar "Creating an Executable Jar").


Uruchomienie aplikacji
---------------------------
Aplikacje można uruchomić poprzez wywołanie polecenia `mvn spring-boot:run`.
Więcej informacji o uruchamianiu aplikacji spring boot znajdziesz [tutaj](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html "Running Your Application Using Spring Boot").


Decyzje projektowe
---------------------------
1. Obsługa API Githuba - jest to pierwszy spotkany problem podczas konstruowania aplikacji.
Miałem do wyboru użyć gotowej biblioteki do API Githuba lub stworzyć własną bibliotekę, która mi to umożliwiała.
Użycie biblioteki jest jak najbardziej wygodne, ale problem pojawia sie kiedy stawia się na prostote aplikacji.
Stwierdziłem, że nie ma sensu używać rozbudowanej biblioteki, gdyż wszelkie zmiany w działaniu dużej biblioteki mogą być problematyczne.
2. Ograniczenie ilości zapytań do API Githuba - na początku nie planowałem dodawać autoryzacji za pomocą klucza OAuth2.
Zdecydowałem się to zrobić ze względu na rate limit do API Githuba. Częste wysyłanie zapytań do Githuba może zablokować całkowicie API.
Początkowo planowałem zrobic cache z nazwą repozytorium, ale finalnie zrezygnowałem z tego rozwiązania.
Przy 5000 użyć API możemy wywołać wiecej, niż 1 zapytanie na sekunde (użycia resetowane są 60 minut od pierwszego wywołania API pod resecie).
Micro-serwis do wyświetlania nazwy ostatnio modyfikowanego repozytorium na proflu "allegro" w serwisie github.

Do pobrania nazwy ostatnio modyfikowanego repozytorium zostało użyte Github Rest API v3 dostępne [tutaj](https://developer.github.com/v3/repos/#list-user-repositories "Github Rest API v3").


Konfiguracja
------------
```yaml
github:
  oauth-token: OAUTH-TOKEN    # opcjonalne
```
Więcej o kluczu OAuth2: https://developer.github.com/v3/#authentication
Podanie nieprawidłowego klucza OAuth2 uniemożliwia korzystanie z API Githuba.

Założenia projektu
------------
Aplikacja była uruchamiana i testowana przy użyciu Java 8.

Aplikacja została skonstruowana tak, aby była w jak największym stopniu niezawodna.
Problemem może być ograniczenie ilościowe użyć API Githuba (bez autoryzacji 60 użyć, a z autoryzacją 5000).
Użycie klucza OAuth2 jest opcjonalne. Zostało stworzone na potrzeby bardziej wymagających użytkowników.

Aplikacja domyślnie jest otwarta na porcie `8080`

Aplikacja umożliwia:
  + `/api/status` - Wyświetlenie statusu API.
  + `/api/last-modification` - Pobranie nazwy repozytorium, który kod był ostatnio modyfikowany

Uruchomienie testów
------------
Testy można uruchomić w Intellij IDEA przy użyciu podstawowej konfiguracji JUnit (trzeba wybrać pakiet i sposób szukania testów).
Innym sposobem na uruchomienie testów jest wywołanie polecenia `mvn test`.


Budowanie aplikacji
------------
Aplikacje buduje się poprzez wywołanie polecenia `mvn package`.
Po zbudowaniu aplikacji można ją uruchomić poprzez wywołanie polecenia `java -jar target/allegro-service-1.0-SNAPSHOT.jar`.
Więcej informacji o budowaniu i uruchamianiu aplikacji Spring Boot jest dostępnych [tutaj](https://docs.spring.io/spring-boot/docs/current/reference/html/getting-started-first-application.html#getting-started-first-application-executable-jar "Creating an Executable Jar").


Uruchomienie aplikacji
------------
Aplikacje można uruchomić poprzez wywołanie polecenia `mvn spring-boot:run`.
Więcej informacji o uruchamianiu aplikacji Spring Boot jest dostępnych [tutaj](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html "Running Your Application Using Spring Boot").


Decyzje projektowe
------------
1. Obsługa API Githuba - jest to pierwszy spotkany problem podczas konstruowania aplikacji.
Miałem do wyboru użyć gotowej biblioteki do API Githuba lub stworzyć własną bibliotekę, która mi to umożliwia.
Użycie biblioteki jest jak najbardziej wygodne, ale problem pojawia sie kiedy stawia się na prostotę aplikacji.
Stwierdziłem, że nie ma sensu używać rozbudowanej biblioteki, gdyż wszelkie zmiany w działaniu dużej biblioteki mogą być problematyczne.
2. Ograniczenie ilości zapytań do API Githuba - na początku nie planowałem dodawać autoryzacji za pomocą klucza OAuth2.
Zdecydowałem się to zrobić ze względu na rate limit API Githuba. Częste wysyłanie zapytań do Githuba może zablokować całkowicie API.
Początkowo planowałem zrobic cache z nazwą repozytorium, ale finalnie zrezygnowałem z tego rozwiązania.
Przy 5000 użyć API możemy wywołać więcej, niż 1 zapytanie na sekundę (użycia resetowane są 60 minut od pierwszego wywołania API).
3. Dzięki [API Githuba](https://developer.github.com/v3/repos/#list-user-repositories " API Githuba") można sortować repozytoria za pomocą dodania odpowiednich parametrów.
Najbardziej pasującym rozwiązaniem było użycie sortowania za pomocą `pushed`.
Zauważyłem, że nie działa to w 100% tak jakbym chciał. Parametr `pushed_at` oprócz ostatniej modyfikacji w kodzie (ostatni commit) pokazuje również dodanie nowego pliku w zakładce releases: https://github.com/react-community/native-directory/issues/20
Aby sprawdzić ostatnią modyfikacje kodu trzeba użyć API do pobierania commitów i sprawdzić, w którym repozytorium czas ostatniego commita jest najnowszy. Uznałem, że nie o to chodziło w zadaniu i jest to tylko kwestia interpretacji.
Moim zdaniem treść w zadaniu rekrutacyjnym powinna być bardziej sprecyzowana, gdyż można ją różnie interpretować.
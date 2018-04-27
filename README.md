# Allegro Service
Micro-serwis do wyświetlania nazwy ostatnio modyfikowanego repozytorium na proflu "allegro" w serwisie github.

Do pobrania nazwy ostatnio modyfikowanego repozytorium zostało użyte Github Rest API v3 dostępne [tutaj](https://developer.github.com/v3/repos/#list-user-repositories "Github Rest API v3").




### Decyzje projektowe
1. Obsługa API Githuba - jest to pierwszy spotkany problem podczas konstruowania aplikacji.
Miałem do wyboru użyć gotowej biblioteki do API Githuba lub stworzyć własną bibliotekę, która mi to umożliwiała.
Użycie biblioteki jest jak najbardziej wygodne, ale problem pojawia sie kiedy stawia się na minimalizm.
Stwierdziłem, że nie ma sensu używać rozbudowanej biblioteki, gdyż wszelkie zmiany w działaniu dużej biblioteki mogą być problematyczne.
2.
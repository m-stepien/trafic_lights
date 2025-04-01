# Symulacja inteligentnych świateł drogowych

## Opis

Symulacja skrzyżowania z czterema drogami (NORTH, SOUTH, EAST, WEST). 
System dynamicznie zarządza światłami w zależności od natężenia ruchu oraz wspiera zielone strzałki do skrętu.

## Funkcjonalności

- Cykl świateł: GREEN → YELLOW → RED (z kontrolą kierunku)
- Zielone strzałki do skrętu, aktywowane przez dodatkową logikę
- Wybór implementacji przez konfigurację (`simple` / `arrow`)
- Przetwarzanie komend JSON: dodanie pojazdu, krok symulacji
- Zwracanie wyniku JSON z informacją, które pojazdy opuściły skrzyżowanie
- Wykorzystanie abstract factory w celu łatwej dalszej rozbudowy projektu
- Testy

## Uruchamianie
```bash
mvn clean package
java -jar target/simulator.jar input.json output.json
```


## Uruchamianie testów
```bash
mvn clean test
```


## Konfiguracja
W pliku config.properties, znajdującym się w resources, możesz wybrać typ logiki działania skrzyżowania:
```properties
branch.type=simple
```
Możliwe wartości to simple i arrow. W przypadku arrow jest to skrzyżowanie ze strzałkami w prawo.

## Algorytm zmiany świateł
1. Zielone światło pozostaje aktywne przez co najmniej 3 kroki (`MIN_GREEN`) lub do momentu gdy jego droga jest pusta.
2. Po tym czasie system oblicza priorytet dla każdej drogi:
     ```
     priorytet = liczba pojazdów * 1.5 + czas oczekiwania
     ```
3. Światła zmieniają się na żółte po wykonaniu przejazdu samochodów
4. Gdy światła są żółte potrzebny jest kolejny step aby zmieniły się na odpowiadające im stany (tylko światło które było zielone i to które będzie zielone zmieniają się na żółte, pozostałe są dalej czerwone)
5. W przypadku ze strzałką tylko kolejna droga, patrząc w kolejności zgodnej z ruchem zegara, ma aktywną strzałkę. Dzięki temu nie istnieje możliwość kolizji.
     


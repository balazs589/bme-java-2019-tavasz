
### BME Üzemmérnök-informatikus szak: Objektumorientált programozás, 2019, Java

### Féléves házi feladat:

Absztrakt síkidom-osztály készítése, ennek segítségével szabályos háromszög, négyzet és kör megvalósítása. Ezen síkidomokat középpontjuk és egy csúcsuk (kör esetén a körvonal egy pontja) határozza meg, amelyek legyenek kétdimenziós (x, y) koordinátáikkal konstruálhatók. A síkidomoknak legyen olyan metódusa, amellyel eldönthető, hogy egy adott pont a síkidom területére esik-e. Legyen továbbá olyan metódusuk is, ami megadja, hogy a síkidomoknak van-e közös pontja egy adott sugarú és középpontú körrel.

Feladat egy főprogram készítése, parancssori üzemmódban a felhasználó által standard inputon beadott nevű szöveges fájlból {típus, középpont, csúcs} tartalmú sorok beolvasása (a java.io csomag megfelelő osztályainak használatával) és a beolvasott síkidomok közül azok eltárolása heterogén kollekcióban, amelyek teljes terjedelmükben az origó középpontú egységkörön kívül esnek. Ezután a szabványos bemenetről koordináták beolvasása a fájl végéig, és az egyes pontokhoz azon eltárolt síkidomok adatainak {típus, középpont, csúcs} kiírása, amelyek az adott pontot tartalmazzák.

A szöveges fájlok írása-olvasása mellett az alakzatok legyenek szerializálhatók.

Opcionálisan a program a tárolt alakzatokat képes legyen megjeleníteni JavaFX grafikus felületen.

### Dokumentáció:

Shapes/javadoc.zip


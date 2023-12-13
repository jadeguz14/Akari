# Akari
A programozás alapjai 3 – Házi feladat dokumentáció
Jankó Bendegúz – VLASX1
Akari/Light up
Felhasználói Útmutató:
1.	Kezdeti lépések:
o	Amikor elindítod a játékot, egy kezdőmenü jelenik meg.
o	Itt találsz egy "Játék indítása" gombot, egy Nehézségi szint kiválasztására szolgáló ComboBox-ot, valamint egy menüsávot, ahol a szabályok, betöltés és kilépés opciók vannak.
2.	Szabályok megtekintése:
o	Ha kíváncsi vagy a játék szabályaira, kattints a "Szabályok" opcióra a menüsávon.
o	Egy felugró ablakban megjelennek a szabályok, és az "OK" gombbal zárhatod be ezt az ablakot.
3.	Pálya Betöltése:
o	Válaszd ki a "Betöltés" opciót a menüsávról.
o	Ekkor egy fájlkezelő ablak jelenik meg, ahol kiválaszthatsz egy .xml fájlt egy játék betöltésére.
o	Ha hibás fájlt választasz, a program üres pályát jelenít meg.
4.	Játék elindítása:
o	Miután beállítottad a nehézségi szintet a "Játék indítása" gombra kattintva a menü eltűnik, és megjelenik maga a játék.
5.	Játék Irányítása:
o	A játékot a bal és jobb egér gombokkal irányíthatod.
o	A fekete cellákra való kattintások hatástalanok.
6.	Fehér cellák kezelése:
o	Bal kattintással fehér cellára lámpát helyezhetsz el, amely megvilágítja a cellához tartozó sort és oszlopot.
o	Újabb kattintás eltávolítja a lámpát.
o	Jobb kattintással jelölőt helyezhetsz el, amelyet ismételt kattintással cserélhetsz lámpára vagy eltüntethetsz.
7.	Ellenőrzés Feladás és Mentés:
o	A játék felületén két további gomb található: "Ellenőrzés", "Feladás" és "Mentés".
o	" Ellenőrzés " gombra kattintva ellenőrizheted, hogy helyesen oldottad-e meg a feladványt.
o	Sikeres megoldás esetén visszatérsz a menübe.
o	" Feladás " gombra kattintva automatikusan visszakerülsz a kezdőmenübe.
o	" Mentés" gombra kattintva elmenti az aktuális játékállást, amit később be lehet tölteni a menüből.
 
Use-case-ek:
Use Case 1: Játék indítása és Nehézségi szint kiválasztása:
1.	Cselekvés: Felhasználó elindítja a játékot.
2.	Rendszer válasza: Megjelenik a kezdőmenü.
3.	Cselekvés: A felhasználó kiválasztja a "Játék indítása" gombot.
4.	Rendszer válasza: Elindul egy könnyű nehézségi szintű játék.
________________________________________
Use Case 2: Szabályok megtekintése:
1.	Cselekvés: Felhasználó a kezdőmenüből kiválasztja a "Szabályok" opciót.
2.	Rendszer válasza: Egy felugró ablakban megjelennek a játék szabályai.
3.	Cselekvés: Felhasználó rákattint az "OK" gombra.
4.	Rendszer válasza: A szabályok ablak bezárul.
________________________________________
Use Case 3: Pálya betöltése:
1.	Cselekvés: A felhasználó a kezdőmenüből kiválasztja a "Betöltés" opciót.
2.	Rendszer válasza: Megjelenik a fájlkezelő ablak.
3.	Cselekvés: Felhasználó kiválaszt egy .xml fájlt.
4.	Rendszer válasza: A kiválasztott pálya betöltődik, vagy ha a fájl nem megfelelő, üres pálya jelenik meg.
________________________________________
Use Case 4: Játék irányítása:
1.	Cselekvés: A felhasználó elindítja a játékot.
2.	Rendszer válasza: A menü eltűnik, és megjelenik a játékfelület.
3.	Cselekvés: A felhasználó irányítja a játékot a jobb és bal egér gombokkal.
4.	Rendszer válasza: A fekete cellákra kattintások hatástalanok. Fehér cellára bal kattintással lámpát helyez el, jobb kattintással jelölőt helyez el vagy cserél.
________________________________________
Use Case 5: Játék ellenőrzése és Feladás:
1.	Cselekvés: A játék közben a felhasználó rákattint az "Ellenőrzés" gombra.
2.	Rendszer válasza: A rendszer ellenőrzi, hogy a feladvány megfelelően van-e megoldva.
3.	Rendszer válasza: Ha a megoldás helyes, a rendszer jelzi, majd visszatér a menübe.
4.	Rendszer válasza: Ha a megoldás helytelen, a játék folytatódik.
________________________________________
Use Case 6: Feladás és Visszatérés a Menübe:
1.	Cselekvés: A játék közben a felhasználó rákattint a "Feladás" gombra.
2.	Rendszer válasza: A játék befejeződik, és a felhasználó visszakerül a kezdőmenübe.
________________________________________
Use Case 7: Mentés
1.	Cselekvés: A játék közben a felhasználó rákattint a "Mentés" gombra.
2.	Rendszer válasza: A játék elmenti az aktuális állást, amit a saveFiles mappából később be lehet tölteni
 ________________________________________

Osztályok és metódusok leírása:
Cell.java:
Konstruktor:
o	Cell(State initialState): Inicializál egy cellát a megadott kezdeti állapottal, alapértelmezés szerint fényerőszint és kapcsolódó szám nélkül.
•	Elérhetőségek:
o	getState(): Lekérdezi a cella jelenlegi állapotát.
o	getLightLevel(): Lekérdezi a cella fény szintjét.
o	getNumber(): Lekérdezi a cellához kapcsolt számot.
•	Módosítók:
o	setState(State state): Beállítja a cella állapotát.
o	setNumber(int number): Beállítja a cellához kapcsolt számot.
o	setLightLevel(int lightLevel): Beállítja a cella fény szintjét.
•	Fény szint módosítása:
o	addLight(): Növeli a cella fény szintjét eggyel.
o	subLight(): Csökkenti a cella fény szintjét eggyel.
GameBoard.java:
A GameBoard osztály reprezentálja a játék grafikus felhasználói felületét. A JFrame osztályból származik, magában foglalva a játéktábla panelt, vezérlőpanelt, és metódusokat a kijelző frissítésére.
•	Konstruktor:
o	GameBoard(): Inicializál egy GameBoard objektumot, beállítva a grafikus felhasználói felületet.
•	Elérhetőségek:
o	getBoard(): Visszaadja a játéktáblát reprezentáló 2D tömböt.
o	getGameCell(int row, int col): Visszaadja a megadott sor és oszlop indexű Cell objektumot a játéktábláról.
o	getCellPanel(int row, int col): Visszaadja a megadott sor és oszlop indexű cellát reprezentáló JPanel-t.
o	getNumOfRows(): Visszaadja a játéktábla sorainak számát.
o	getNumOfColumns(): Visszaadja a játéktábla oszlopainak számát.
•	Játék ellenőrzés:
o	checkLamps(): Ellenőrzi, hogy az egyes sorokban nincsenek egymást látó lámpák. Ha igen, hamis értékkel tér vissza, jelezve a lámpák elrendezésének érvénytelenségét.
•	Cella háttérszínének beállítása:
o	setCellLit(int row, int col): Beállítja a megadott cella panel háttérszínét az állapotának és fény szintjének megfelelően.
•	Sor és oszlop kezelése:
o	setRow(int row, int lampCol): Beállítja a megadott sorban lévő cellák háttérszínét egy lámpa helyzetétől függően.
o	setCol(int col, int lampRow): Beállítja a megadott oszlopban lévő cellák háttérszínét egy lámpa helyzetétől függően.
 
•	Inicializáció és Frissítés:
o	init(): Inicializálja a grafikus felhasználói felületet, beállítva a keretet, létrehozva a játék- és vezérlőpaneleket.
o	createBoard(): Létrehozza a játéktáblát, inicializálva egy 7x7-es méretű Cell objektumokból álló 2D tömbbel.
o	createControlPanel(): Létrehozza a játék irányításához szükséges vezérlőpanelt, tartalmazva a "Ellenőrzés", "Feladás"  és "Mentés" gombokat.
o	createGamePanel(): Létrehozza a játéktáblát megjelenítő panelt, beállítva a cellákat reprezentáló JPanel-ek háttérszínét és tartalmát.
o	updateGamePanel(): Frissíti a játéktábla panelt a játéktábla változásainak megfelelően.
GameLogic.java
A GameLogic osztály kezeli a játék logikáját. Ellenőrzi a játékállapotot, kezeli a játék végét, és kezeli a játékos lépéseit.
•	Konstruktor:
o	GameLogic(GameBoard gameBoard): A GameLogic osztály konstruktora, amely inicializálja a játéklogikához kapcsolódó GameBoard példányt.
•	Játék ellenőrzése:
o	checkGame(): Ellenőrzi a játék jelenlegi állapotát. Hamisat ad vissza, ha a játékszabályokat nem sértik, különben igazat.
•	Játék vége kezelése:
o	gameEnded(): Kezeli a játék végét, megjelenítve egy üzenetablakot a játékállapot alapján.
•	Játék feladása:
o	giveUp(): Kezeli a játékos feladását, visszatérve a főmenübe.
•	Játékos lépések kezelése:
o	playerMove(): Beállítja az egérfigyelőt a játéktáblán történő játékos lépések kezeléséhez.
•	Belső egérfigyelő osztály:
o	MouseListener: Az egér eseményeket kezelő osztály, amely a játékos lépéseit kezeli. A bal és jobb egérgombok lenyomására reagál, és azoknak megfelelően kezeli a játéktáblát és a játékcellákat.
FileHandler.java
A FileHandler osztály felelős a játéktábla beolvasásáért egy XML fájlból, majd létrehoz egy ehhez megfelelő GameBoard példányt.
•	Konstruktor:
o	FileHandler(String xmlFilePath): Az osztály konstruktora, amely beolvassa az XML fájlt, majd létrehoz egy GameBoard példányt.
•	Metódusok:
o	getBoard(): Visszaadja a létrehozott GameBoard példányt.
o	createBoardFromFile(String xmlFilePath): Létrehoz egy GameBoard példányt az XML fájlból származó információk elemzésével. A DocumentBuilder és a DOM (Document Object Model) segítségével olvassa be és elemzi a fájlt, majd frissíti a játéktáblát az XML adatok alapján.
o	createFileFromBoard(String xmlFilePath): Létrehoz egy XML fájlt az aktuális játékállásból
Menu.java
A Menu osztály a játék főmenüjét reprezentálja. Lehetőséget nyújt új játék indítására, mentett játék betöltésére, játékszabályok megtekintésére és az alkalmazás kiléptetésére.
•	Konstruktor:
o	Menu(): Az osztály konstruktora, ami inicializálja a menüt, létrehozza a fő JFrame-t, hozzáad komponenseket, és beállítja az eseményfigyelőket.
•	Metódusok:
o	createFrame(String text): Létrehoz egy JFrame-t a megadott szöveggel és beállításokkal. Beállítja a JFrame méretét, kilépési operációját és helyét a képernyő közepén.
o	createPanel(): Létrehoz egy JPanel-t BoxLayout-tal és a megadott háttérszínnel.
o	createFileMenu(JFrame frame): Létrehoz egy fájlmenüt az alkalmazáshoz a betöltés, szabályok megtekintése és a játék kiléptetése lehetőségekkel.
o	createStartButton(String text, JComboBox<String> diffBox, JFrame frame): Létrehoz egy indító gombot a megadott szöveggel, JComboBox-al és Jframe-el. Beállítja a gomb tulajdonságait, és hozzáad egy eseményfigyelőt.
o	startGame(JComboBox diffBox): Új játékot indít a kiválasztott nehézségi szint alapján. A nehézségi szintnek megfelelő fájl elérési útját állítja be, majd véletlenszerűen kiválaszt egy fájlt és betölti a játéktáblát.
o	loadGame(): Betölt egy játékot a kiválasztott fájlból a fájlkiválasztó segítségével.

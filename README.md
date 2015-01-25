MyTutor Web-Applikation
=======
ENTWICKLER:         EUGEN GERING 
                    METEHAN KILIN

Project description
----------------------------------------------------------------------------------------------
Im Rahmen der Veranstaltung „Web-Technologien“ erhalten die teilnehmenden Gruppen, die aus drei 
beziehungsweise zwei Personen bestehen, die Aufgabe eine dynamische Web-Applikation aufzusetzen. 
Hierbei sollen neue Technologien im Bereich der Web-Entwicklung erlernt und im Projekt eingesetzt 
werden. Des Weiteren soll in dieser Veranstaltung das Arbeiten in Gruppen als auch das Arbeiten mit 
GitHub gefördert werden.

In unserem Projekt besteht die Aufgabe darin, eine „MyTutor“ Web-Applikation zu programmieren. 
Studenten können sich in dieser, als Tutor im Rahmen der Hochschule eintragen, beziehungsweise selber
nach Tutoren suchen. Die Anforderungen und Richtlinien der Veranstaltung „Web-Technologien“ stellten 
für uns eine große Herausforderung dar. Trotz anfänglicher Schwierigkeiten, mit der Umsetzung neuer 
Technologien, konnten wir uns schnell in unser Thema einarbeiten. In dieser Dokumentation wird unsere 
„MyTutor“ Web-Site im Detail vorgestellt und das Projektumfeld beschrieben.

##### Requirements analysis (use-cases, functional and non- functional requirements)
###### Die Funktionalität beinhaltet wie im UseCase Ersichtlich: 
-Registrieren
-Einloggen
-Profil Anzeigen
-Profil Bearbeiten 
-Tutorien anbieten 
-Tutoren suchen

###### Use-Case
![Use-Case]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/1.png)


##### First idea (mockups / wireframes)
Login:
Auf dieser Seite gibt es die Möglichkeit sich einzuloggen oder sich neu zu registrieren. Nach der 
Registrierung wird man wieder auf diese Login-Maske zurückgeleitet.
Nach dem Einloggen wird die Session gestartet, die bei dem betätigen der Logout taste beendet wird.

![Login]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/2.png)

![Realisierung]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/3.png)

Tutor werden:
Hier gibt es die Möglichkeit sich als Tutor einzuschreiben. Bei der Realisierung ist uns aufgefallen, 
dass mehrere Studiengänge identische Module anbieten. Aus diesem Grund ist, die Angabe zum Studiengang
essentiell. Die Möglichkeit seinen Studiengang einzugeben befindet sich unter dem Reiter Profil 
Bearbeiten. Den Eintrag zum Semester und den Textfeld haben wir im Nachhinein als überflüssig
angesehen.

![Tutor werden]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/4.png)

![Realisierung]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/5.png)

Tutor suchen:
Hier hat man die Möglichkeit die Suche nach einem Tutorium, entweder zu Filtern oder nach allen 
Tutorien zu suchen. Die Suchkriterien zur Filterung ist einerseits der Studiengang und andererseits
das Fach. Bei der Realisierung haben wir noch einen zusätzlichen Button, welcher zum Profil des 
Tutoren verlinkt, hinzugefügt.

![Tutor suchen]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/6.png)

![Realisierung]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/7.png)



##### Used technologies (Why? How do they work?)

Sämtliche Views haben wir in HTML5 und CSS3 entwickelt. Das Zusammenspiel von JavaScript und JQuery1in 
den einigen Views eingesetzt. Das Responsive Web Design ist mit Hilfe von Bootstrap2 implementiert. 
Die Kommunikation zwischen den Teammitgliedern hat anhand des webbasierter Hosting Dienstes GITHub 
stattgefunden. Die Programmierung erfolgte mittels Eclipse und dem integrierten Play Framework. In 
diesem Framework hatten wir die Möglichkeit uns mit der Sprache Scala zu befassen. Diese ist 
beispielweise für die Iteration, von übergebenen Werten, notwendig. Da die Webseite mehr an Dynamik 
gewinnen soll haben wir die Implementation von Ajax, JSON3 (siehe Abb.2)und Websockets als eine 
dringende Notwendigkeit empfunden. Wenn ein Student ein Tutorium anbietet, wird über die Websockets4, 
welches die Daten per JSON verarbeitet, und das implementierte Observable Pattern direkt eine 
Nachricht im „footer“ angezeigt. Die Registrierten Studenten werden in der Relationalen Datenbank 
SQLite
abgespeichert, welches keinen Redundanten Studenten beinhalten kann. Beim Logging wird der Student in 
der Datenbank durch die Einstimmung des Primary Key (email) mit seinem Passwort überprüft. 
Abschließend wird alles über Heroku Deployed.

![jQuery]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/8.png)

![Websockets]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/9.png)

![Bootstrap]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/10.png)

![Ajax & JSON]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/11.png)


##### What you have learned


Nach der erfolgreichen Beendigung dieses Projektes, fällt unser Fazit sehr positiv aus. Zwar hatten 
wir anfangs einige Schwierigkeiten mit der Umsetzung einer Webapplikation, jedoch haben wir diese 
Hürde gemeistert und dadurch zahlreiche neue Erfahrungen gesammelt. Besonders hat uns sehr gefallen, 
dass wir ein Projekt von der Entwurfsphase bis zum Deployement gestaltet haben. Auch das Lösen von 
Problemen wurde in unserem Projekt stark vereinfacht, indem wir diese zuerst schriftlich gelöst und 
anschließend Implementiert haben.



##### Problems

Ein großes Problem, auf das wir gestoßen sind, war die Realisierung in nur einem
Semester. Aus diesem Grund konnte man das Zusammenspiel jeder Technologie
nicht perfektionieren. Sich mit jeder Technologie intensiv zu beschäftigen ist sehr Zeitaufwendig.


##### Important code snippets

![Views_Main]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/12.png)

![Views_ProfilAnzeigen]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/13.png)

![Routes]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/14png)

![Application]
(https://github.com/eugering/MyTutor/blob/master/Dokumentation/15.png)

##### Zukunft

Für die Zukunft planen wir die Upload Funktion eines Profilbildes umzusetzen, sowie die Einrichtung 
einer Chat-Funktion.

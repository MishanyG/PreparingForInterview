CREATE DATABASE Cinema;
USE Cinema;
CREATE TABLE Film (
Id_Film INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Name VARCHAR(100)  NOT NULL,
Duration TIME  NOT NULL,
Price FLOAT  NOT NULL);

CREATE TABLE Tickets (
Id_Tickets INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Ticket_Number INT NOT NULL,
Id_Film INT NOT NULL,
INDEX Id_Film_fk_idx (Id_Film ASC) VISIBLE,
CONSTRAINT Id_Film_fk
FOREIGN KEY (Id_Film) REFERENCES film (Id_Film));

CREATE TABLE Schedule (
Id_Schedule INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
Id_Film INT, FOREIGN KEY (Id_Film) REFERENCES Film (Id_Film),
TIME datetime NOT NULL);

INSERT INTO Film VALUES (1,'Константин','1:00',7),(2,'Матрица','1:30',4),(3,'Я легенда','2:00',10),(4,'Суррогаты','1:00',13),(5,'Книга Илая','2:00',6);
INSERT INTO Tickets VALUES (1,'123456',1),(2,'123457',3),(3,'123458',5),(4,'123459',1),(5,'123460',3),(6,'123461',5);
INSERT INTO Schedule VALUES (1,1,'2021-01-01 0:00'),(2,2,'2021-01-01 0:30'),(3,3,'2021-01-01 1:30'),(4,4,'2021-01-01 0:30'),(5,5,'2021-01-01 0:00');

/*-------------------------------------------------------------------------------------------------------------------------------------------*
 *																																			 *
 * ошибки в расписании (фильмы накладываются друг на друга), отсортированные по возрастанию времени. Выводить надо колонки «фильм 1», «время *
 * начала», «длительность», «фильм 2», «время начала», «длительность»;																		 *
 *																																			 *
 *-------------------------------------------------------------------------------------------------------------------------------------------*/

SELECT  FilmSchedule.Name AS Film_Name_One, FilmSchedule.FilmStart AS Film_Start_One, FilmSchedule.Duration AS Film_Duration_One, FilmSchedule_.Name AS Film_Name_Two,
FilmSchedule_.FilmStart AS Film_Start_Two, FilmSchedule_.Duration AS Film_Duration_Two
FROM (SELECT F.Id_Film, F.Name, F.Duration, TIME(S.TIME) AS FilmStart, SEC_TO_TIME(TIME_TO_SEC(F.Duration) + TIME_TO_SEC(TIME(S.TIME))) AS FilmEnd
FROM Schedule AS S
JOIN Film AS F ON S.Id_Film = F.Id_Film) AS FilmSchedule
LEFT JOIN FilmSchedule AS FilmSchedule_ ON FilmSchedule_.FilmStart BETWEEN FilmSchedule.FilmStart AND FilmSchedule.FilmEnd AND FilmSchedule.Id_Film <> FilmSchedule_.Id_Film
AND FilmSchedule.FilmStart <= FilmSchedule_.FilmEnd AND FilmSchedule.FilmEnd >= FilmSchedule_.FilmStart
WHERE FilmSchedule_.Id_Film IS NOT NULL
GROUP BY Film_Start_One
ORDER BY Film_Start_One ASC;

/*-------------------------------------------------------------------------------------------------------------------------------------------*
 *																																			 *
 * перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва. Колонки «фильм 1», «время начала», 				 *
 * «длительность», «время начала второго фильма», «длительность перерыва»;																	 *
 *																																			 *
 *-------------------------------------------------------------------------------------------------------------------------------------------*/

SELECT filmN.fN AS Film_Name, SEC_TO_TIME(TIME_TO_SEC(TIME(Schedule.TIME))) AS Start_Time, filmN.fD AS Duration, Tsta.TSt AS Second_Time, break.p as Break
FROM (SELECT pause.sc as idStart, pause.sc as idNext, SEC_TO_TIME(TIME_TO_SEC(pause.time_ch) - TIME_TO_SEC(Film.Duration)) AS p, pause.sec AS secT
FROM (SELECT SEC_TO_TIME(TIME_TO_SEC(cast( ifnull(time_change,0) as TIME))) as time_ch, Id_Film as sc, q.tim AS sec
	FROM (SELECT Schedule.Id_Schedule as id, Schedule.Id_Film as Id_Film, SEC_TO_TIME(TIME_TO_SEC(TIME(Schedule.TIME)) - TIME_TO_SEC(@prev)) AS time_change,
		(@prev := SEC_TO_TIME(TIME_TO_SEC(TIME(Schedule.TIME)))) AS tim
		FROM schedule, film
		JOIN (SELECT @prev := null) as j
		WHERE Film.Id_Film = Schedule.Id_Film
		ORDER BY Schedule.Id_Film) as q
	WHERE time_change is null or time_change!=0) as
pause, Film, Schedule
WHERE Film.Id_Film = pause.sc - 1
group by p) as break, (SELECT SEC_TO_TIME(TIME_TO_SEC(cast(ifnull(TIME(Schedule.TIME),0) as TIME))) as TSt, Schedule.Id_Schedule as id
	FROM Schedule, Film WHERE Film.Id_Film = Schedule.Id_Film group by Schedule.Id_Schedule) as Tsta, Schedule, Film,
(SELECT Film.Id_Film as idF, Film.Name as fN, Film.Duration as fD FROM Film, Schedule WHERE Film.Id_Film = Schedule.Id_Film group by Schedule.Id_Schedule) as filmN
WHERE Schedule.Id_Film = Film.Id_Film - 1 AND Tsta.id = Schedule.Id_Schedule + 1 AND filmN.idF + 1 = Film.Id_Film AND break.idStart = Schedule.Id_Schedule + 1
group by Schedule.Id_Schedule
order by break desc;

/*-------------------------------------------------------------------------------------------------------------------------------------------*/

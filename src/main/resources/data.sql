delete from Genres;
insert into Genres (id, libelle) values (1, 'Animation');
insert into Genres (id, libelle) values (2, 'Science-Fiction');
insert into Genres (id, libelle) values (3, 'Documentaire');
insert into Genres (id, libelle) values (4, 'Action');
insert into Genres (id, libelle) values (5, 'Comédie');
insert into Genres (id, libelle) values (6, 'Drame');

--OK
insert into Films (titre, annee, duree, synopsis, genreId, realisateurID) values ('Jurassic Park', 1993, 128, 'Le film raconte l''histoire d''un milliardaire et son équipe de généticiens parvenant à ramener à la vie des dinosaures grâce au clonage.',2 ,1);
insert into Films (titre, annee, duree, synopsis, genreId, realisateurID) values ('The Fly', 1986, 95, 'Il s''agit de l''adaptation cinématographique de la nouvelle éponyme de l''auteur George Langelaan.',2 ,2);
insert into Films (titre, annee, duree, synopsis, genreId, realisateurID) values ('The BFG', 2016, 117, 'Le Bon Gros Géant est un géant bien différent des autres habitants du Pays des Géants.',5 ,1);
#Create Table movies
 
create table movies
(
movie_id integer, 
name varchar(1000), 
score integer
);

#Load Data
load data local infile '~/prog3/movie-name_score.txt' into table movies fields terminated by ',';

#Create Table Cast
 
create table cast
(
movie_id integer, 
cast_id integer, 
cast_name varchar(1000)
);

#Load Data
load data local infile '~/prog3/movie-cast.txt' into table cast fields terminated by ',';

select count(*) from movies;

select count(*) from cast;
select count(*) from movies;

select count(*) from cast;

insert into movies values(12345, 'strangemovie', 0);

update movies set name = 'badmovie' where movie_id = 12345;

delete from movies where movie_id = 12345;

select movie_id, name from movies where charindex('Warrior', name) > 0 group by movie_id;

select cast_id, cast_name from cast having count(movie_id) = 4 order by cast_name DESC;

select movie_id, score from movies m, cast c group by movie_id having m.score > 80 and where not exists(select cast_name, from cast, where cast_name = 'David';);

(select c.cast_id, c.cast_name, count(c.movie_id) as num_movies, avg(m.score) as average_score from movies m, cast c) except (select m.movies_id from movies m having score < 0) order by c.cast_name DESC;

select name from movies having score > (select max(score) from movies where charindex('Chronicle', name) > 0);

create view good_collaboration as ((select disinct c.cast_id) as cast_member_id1, (select distinct c.cast_id) as cast_member_id2, count(cast_member_id1.m.movie_id == cast_member_id2.m.movie_id) and m.score >= 75) as num_movies, from cast c, movies m group by cast_member_id1;

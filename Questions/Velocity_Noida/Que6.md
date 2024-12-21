**Structure of movie database are given below**
movie_detail
movie_id  Movie_name  year_made  rating

director_master
director_id  director

director_movie_mapping
director_id  Movie_id

**Write down all posible index key (primary, foreign, unique, candidate) datatype size of each column**
**movie_detail:**
movie_id: Primary key (index key), Data type: Integer, Size: Depends on the specific integer data type used (e.g., INT, BIGINT, SMALLINT)
Movie_name: No index key specified, Data type: VARCHAR or TEXT (string), Size: Varies depending on the maximum length of the movie name
year_made: No index key specified, Data type: Integer, Size: Depends on the specific integer data type used (e.g., INT, BIGINT, SMALLINT)
rating: No index key specified, Data type: Decimal or Float, Size: Depends on the specific decimal or float data type used

**director_master:**
director_id: Primary key (index key), Data type: Integer, Size: Depends on the specific integer data type used (e.g., INT, BIGINT, SMALLINT)
director: No index key specified, Data type: VARCHAR or TEXT (string), Size: Varies depending on the maximum length of the director's name

**director_movie_mapping:**
director_id: Foreign key, Data type: Integer, Size: Depends on the specific integer data type used (e.g., INT, BIGINT, SMALLINT)
Movie_id: Foreign key, Data type: Integer, Size: Depends on the specific integer data type used (e.g., INT, BIGINT, SMALLINT)

**Write a quiry to find the total number of movie in database**
SELECT COUNT(*) AS total_movies
FROM movie_detail;

**Write a query to find out name of movie made after 2000 & rating is greater than 5**
SELECT movie_detail.Movie_name
FROM movie_detail
WHERE movie_detail.year_made > 2000 AND movie_detail.rating > 5;

**Write a query to find out name of movie directed by "karan Johar"**
SELECT movie_detail.Movie_name
FROM movie_detail
JOIN director_movie_mapping ON movie_detail.movie_id = director_movie_mapping.Movie_id
JOIN director_master ON director_movie_mapping.director_id = director_master.director_id
WHERE director_master.director = 'Karan Johar';
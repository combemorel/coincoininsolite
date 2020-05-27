-- ===============================
-- DDL : Data definition language
-- ex: CREATE, ALTER, DROP, etc.
-- ===============================

-- Nettoyage en prévention d'erreurs pendant le tp

-- Sans ajout de @Table : tables au singulier
DROP TABLE IF EXISTS markers;
DROP TABLE IF EXISTS users;

-- Table users

DROP TABLE IF EXISTS users;

CREATE TABLE users(
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(40) NOT NULL,
    user_login VARCHAR(40) NOT NULL,
    user_pwd VARCHAR(255) NOT NULL,
    user_role INTEGER NOT NULL
);


-- Table markers

DROP TABLE IF EXISTS markers;

CREATE TABLE markers(
    marker_id SERIAL PRIMARY KEY,
    marker_title VARCHAR(40) NOT NULL,
    marker_description VARCHAR(255) NOT NULL,
    marker_src_image VARCHAR(255) NOT NULL,
    marker_latitude DOUBLE NOT NULL,
    marker_longitude DOUBLE NOT NULL
);

;
-- ===============================
-- DML : Data modeling language
-- ex: SELECT, INSERT, UPDATE, etc.
-- ===============================

INSERT INTO users (
    user_id,
    user_name,
    user_login,
    user_pwd,
    user_role
)
VALUES
    ( 1, 'Administrateur','Admin','P@ssw0rd',1),
    ( 2, 'Utilisateur 1','User1','password',0)
;

INSERT INTO markers (
    marker_id,
    marker_title,
    marker_description,
    marker_src_image,
    marker_latitude,
    marker_longitude
)
VALUES
    ( 1, 'Marker 1','Description marker 1','/img/test.jpg',48.048286,-1.740643),
    ( 2, 'Marker 2','Description marker 2','/img/test2.jpg',48.148286,-1.640643),
    ( 3, 'Marker 3','Description marker 3','/img/test3.jpg',48.248286,-1.540643),
    ( 4, 'Marker 4','Description marker 4','/img/test4.jpg',48.348286,-1.440643),
    ( 5, 'Marker 5','Description marker 5','/img/test5.jpg',48.448286,-1.340643)
;

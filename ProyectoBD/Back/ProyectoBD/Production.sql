CREATE OR REPLACE DIRECTORY OBJETOS_LOB AS 'C:\ProyectoBD\imagenes';
/* grants que hay que hacer en system

GRANT READ, WRITE ON DIRECTORY OBJETOS_LOB TO aa;
GRANT CREATE ANY DIRECTORY TO aa;

/*/

DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (1, 6, 'El Padrino', 175, 'La historia de la familia Corleone durante el desarrollo del crimen organizado en Nueva York.', 'https://www.youtube.com/watch?v=v72XprPxy3E', 1972, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'ElPadrino.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/


DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (2, 11, 'Our Planet', 50, 'Una serie documental que explora la diversidad de la vida en la Tierra y los impactos del cambio clim?tico.', 'https://www.youtube.com/watch?v=aETNYyrqNYE', 2019, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'OurPlanet.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/


DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (3, 10, 'Stranger Things', 55, 'Un grupo de amigos descubren fen?menos paranormales en su peque?o pueblo.', 'https://www.youtube.com/watch?v=Wre1F5YyIlA', 2016, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'StrangerThings.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/


DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (4, 12, 'Titanic', 195, 'Un joven artista y una joven de clase alta se enamoran pero el destino les tiene preparada una tragedia.', 'https://www.youtube.com/watch?v=wMZuro21wtE', 1997, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'Titanic.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/


DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (5, 9, 'Breaking Bad', 50, 'Un profesor de qu?mica con c?ncer y un exalumno se convierten en productores de metanfetaminas para asegurar el futuro econ?mico de sus familias.', 'https://www.youtube.com/watch?v=HhesaQXLuRY', 2008, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'BreakingBad.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/


DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (6, 3, 'Interestelar', 169, 'Un grupo de exploradores viaja a trav?s de un agujero de gusano en el espacio en un intento de asegurar la supervivencia de la humanidad.', 'https://www.youtube.com/watch?v=0vxOhd4qlnA', 2014, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'Interestellar.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/


DECLARE
    v_temp BLOB;
    v_bfile BFILE;
    v_nombre_foto VARCHAR(20);
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo)
    VALUES (7, 4, 'Pulp Fiction', 154, 'Varias historias interconectadas sobre cr?menes, redenci?n y violencia en Los ?ngeles.', 'https://www.youtube.com/watch?v=wZBfmBvvotE', 1994, EMPTY_BLOB())
    Returning Photo into v_temp;
    
    v_nombre_foto := 'PulpFiction.jpg';
    
    v_bfile := BFILENAME('OBJETOS_LOB', v_nombre_foto);    
    DBMS_LOB.OPEN(v_bfile, DBMS_LOB.LOB_READONLY);
    DBMS_LOB.LOADFROMFILE(v_temp, v_bfile, DBMS_LOB.getlength(v_bfile));
    DBMS_LOB.CLOSE(v_bfile);
    COMMIT;

END;
/



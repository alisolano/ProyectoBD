CREATE OR REPLACE PROCEDURE getProductionInfo (
    p_ProductionName IN VARCHAR2,
    p_Id OUT NUMBER,
    p_IdCategory OUT NUMBER,
    p_CategoryName OUT VARCHAR2, 
    p_Title OUT VARCHAR2,
    p_Duration OUT NUMBER,
    p_Synopsis OUT VARCHAR2,
    p_Trailer OUT VARCHAR2,
    p_ReleaseYear OUT NUMBER,
    p_Photo OUT BLOB
)
AS
BEGIN
    SELECT p.id, p.idCategory, c.Name, p.Title, p.Duration, p.Synopsis, p.Trailer, p.ReleaseYear, p.Photo
    INTO p_Id, p_IdCategory, p_CategoryName, p_Title, p_Duration, p_Synopsis, p_Trailer, p_ReleaseYear, p_Photo
    FROM Production p
    INNER JOIN Category c ON p.idCategory = c.id
    WHERE p.Title = p_ProductionName;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró la producción: ' || p_ProductionName);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('error.');
END getProductionInfo;

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





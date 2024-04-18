--Enlistar paises
CREATE OR REPLACE PROCEDURE getCountry (cursorCountry OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorCountry FOR
        SELECT id, Name
        FROM Country;
END getCountry;

--Enlistar nacionalidades
CREATE OR REPLACE PROCEDURE getNationality (cursorNationality OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorNationality FOR
        SELECT id, Name
        FROM Nationality;
END getNationality;

--Enlistar provincias
CREATE OR REPLACE PROCEDURE getStates (cursorStates OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorStates FOR
        SELECT id, Name
        FROM State
        WHERE idCountry = 47;
END getStates;


-- Cantones de Alajuela
CREATE OR REPLACE PROCEDURE getDistrict1 (cursorDistrict1 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict1 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 1;
END getDistrict1;

--Cantones de Heredia
CREATE OR REPLACE PROCEDURE getDistrict2 (cursorDistrict2 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict2 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 2;
END getDistrict2;

-- Cantones de Limon
CREATE OR REPLACE PROCEDURE getDistrict3 (cursorDistrict3 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict3 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 3;
END getDistrict3;

-- Cantones de Cartago
CREATE OR REPLACE PROCEDURE getDistrict4 (cursorDistrict4 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict4 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 4;
END getDistrict4;

-- Cantones de San Jose
CREATE OR REPLACE PROCEDURE getDistrict5 (cursorDistrict5 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict5 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 5;
END getDistrict5;

-- Cantones de Guanacaste
CREATE OR REPLACE PROCEDURE getDistrict6 (cursorDistrict6 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict6 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 6;
END getDistrict6;

-- Cantones de Puntarenas
CREATE OR REPLACE PROCEDURE getDistrict7 (cursorDistrict7 OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorDistrict7 FOR
        SELECT id, Name
        FROM District
        WHERE idState = 7;
END getDistrict7;

--Tipo de identificacion
CREATE OR REPLACE PROCEDURE getIdtype (cursorIdtype OUT SYS_REFCURSOR) IS
    BEGIN 
        OPEN cursorIdtype FOR
        SELECT id, Name
        FROM IdType;
END getIdtype;

/*INSERT INTO Idtype(id, Name) VALUES (1, 'Cédula');
INSERT INTO Idtype(id, Name) VALUES (2, 'Pasaporte');
COMMIT;*/

--Enlistar genero
CREATE OR REPLACE PROCEDURE getGenero (cursorGenero OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorGenero FOR
        SELECT id, Name
        FROM Gender;
END getGenero;

--Enlistar categoria
CREATE OR REPLACE PROCEDURE getCategoria (cursorCategoria OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorCategoria FOR
        SELECT id, Name
        FROM Category;
END getCategoria;

--Enlistar tipo de pago
CREATE OR REPLACE PROCEDURE getPago (cursorPago OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorPago FOR
        SELECT id, Name
        FROM Payment;
END getPago;


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

--Enlistar genero
CREATE OR REPLACE PROCEDURE getGender (cursorGenero OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorGenero FOR
        SELECT id, Name
        FROM Gender;
END getGender;

--Enlistar categoria
CREATE OR REPLACE PROCEDURE getCategory (cursorCategoria OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorCategoria FOR
        SELECT id, Name
        FROM Category;
END getCategory;

--Enlistar tipo de pago
CREATE OR REPLACE PROCEDURE getPayment(cursorPago OUT SYS_REFCURSOR) IS
    BEGIN
        OPEN cursorPago FOR
        SELECT id, Name
        FROM Payment;
END getPayment;


--verificar user
CREATE OR REPLACE PROCEDURE CheckUsernameAvailability (
    p_username IN VARCHAR2,
    p_result OUT VARCHAR2
)
IS
    v_count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_count
    FROM UserSys
    WHERE UserName = p_username;

    IF v_count > 0 THEN
        p_result := 'Username not available';
    ELSE
        p_result := 'Username available';
    END IF;
END CheckUsernameAvailability;


---verificar password
CREATE OR REPLACE PROCEDURE VerifyUserPassword (
    p_UserName IN VARCHAR2,
    p_Password IN VARCHAR2,
    p_Result OUT NUMBER
) AS
    v_Count NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_Count
    FROM UserSys
    WHERE UserName = p_UserName
    AND Password = p_Password;

    p_Result := v_Count;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END VerifyUserPassword;


CREATE OR REPLACE PROCEDURE getProductionInfo (
    p_ProductionName IN VARCHAR2,
    p_Title OUT VARCHAR2,
    p_Synopsis OUT VARCHAR2,
    p_Category OUT VARCHAR2
)
AS
BEGIN
    SELECT p.Title, p.Synopsis, c.Name
    INTO p_Title, p_Synopsis, p_Category
    FROM Production p
    INNER JOIN Category c ON p.idCategory = c.id
    WHERE p.Title = p_ProductionName;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontr  la producci n: ' || p_ProductionName);
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Ocurri  un error.');
END getProductionInfo;


SELECT * FROM Production;



--Buscar datos por nombre

CREATE OR REPLACE PROCEDURE GetDistrictIDByName(
    p_DistrictName IN VARCHAR2,
    p_DistrictID OUT NUMBER
) IS
BEGIN
    SELECT id INTO p_DistrictID
    FROM District
    WHERE name = p_DistrictName;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_DistrictID := NULL;
    WHEN OTHERS THEN
        RAISE;
END GetDistrictIDByName;


CREATE OR REPLACE PROCEDURE GetNationalityIDByName(
    p_NationalityName IN VARCHAR2,
    p_NationalityID OUT NUMBER
) IS
BEGIN
    SELECT id INTO p_NationalityID
    FROM Nationality
    WHERE name = p_NationalityName;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_NationalityID := NULL;
    WHEN OTHERS THEN
        RAISE;
END GetNationalityIDByName;



CREATE OR REPLACE PROCEDURE GetGenderIDByName(
    p_GenderName IN VARCHAR2,
    p_GenderID OUT NUMBER
) IS
BEGIN
    SELECT id INTO p_GenderID
    FROM Gender
    WHERE name = p_GenderName;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_GenderID := NULL;
    WHEN OTHERS THEN
        RAISE;
END GetGenderIDByName;


CREATE OR REPLACE PROCEDURE GetIDTypeIDByName(
    p_IDTypeName IN VARCHAR2,
    p_IDTypeID OUT NUMBER
) IS
BEGIN
    SELECT id INTO p_IDTypeID
    FROM IDType
    WHERE name = p_IDTypeName;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_IDTypeID := NULL;
    WHEN OTHERS THEN
        RAISE;
END GetIDTypeIDByName;


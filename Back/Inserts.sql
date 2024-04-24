CREATE OR REPLACE PROCEDURE InsertUserSys (
    p_FirstName IN VARCHAR2, 
    p_MiddleName IN VARCHAR2 DEFAULT NULL, 
    p_LastName IN VARCHAR2, 
    p_SecondSurname IN VARCHAR2 DEFAULT NULL, 
    p_IDNumber IN VARCHAR2, 
    p_Birthdate IN DATE DEFAULT NULL, 
    p_Photo IN BLOB DEFAULT NULL, 
    p_Email IN VARCHAR2, 
    p_PhoneNumber IN VARCHAR2, 
    p_UserName IN VARCHAR2, 
    p_Password IN VARCHAR2, 
    p_idDistrict IN NUMBER, 
    p_idNationality IN NUMBER, 
    p_idGender IN NUMBER, 
    p_idType IN NUMBER
) AS
BEGIN
    INSERT INTO UserSys (ID, FirstName, MiddleName, LastName, SecondSurname, IDNumber, Birthdate, Photo, Email, PhoneNumber, UserName, Password, idDistrict, idNationality, idGender, idType) 
    VALUES (s_user.NEXTVAL, p_FirstName, p_MiddleName, p_LastName, p_SecondSurname, p_IDNumber, p_Birthdate, p_Photo, p_Email, p_PhoneNumber, p_UserName, p_Password, p_idDistrict, p_idNationality, p_idGender, p_idType);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertUserSys;


--Insertar administrador
CREATE OR REPLACE PROCEDURE InsertAdministrator (
    p_user_id IN NUMBER
) AS
BEGIN
    INSERT INTO Administrator (id) 
    VALUES (p_user_id);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertAdministrator;

--Insertar client
CREATE OR REPLACE PROCEDURE InsertClient AS
    v_user_id NUMBER;
BEGIN
    -- Obtener el próximo valor de la secuencia s_user
    SELECT s_user.CURRVAL INTO v_user_id FROM dual;
    
    -- Insertar el nuevo cliente con el mismo ID que el usuario
    INSERT INTO Client (id) VALUES (v_user_id);
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertClient;


--Insertar persona
CREATE OR REPLACE PROCEDURE InsertPerson (
    p_FirstName IN VARCHAR2, 
    p_MiddleName IN VARCHAR2 DEFAULT NULL, 
    p_LastName IN VARCHAR2, 
    p_SecondSurname IN VARCHAR2 DEFAULT NULL, 
    p_Biography IN VARCHAR2, 
    p_Birthdate IN DATE, 
    p_Height IN NUMBER DEFAULT NULL, 
    p_Photo IN BLOB DEFAULT NULL, 
    p_Trivia IN VARCHAR2 DEFAULT NULL, 
    p_idDistrict IN NUMBER
) AS
BEGIN
    INSERT INTO Person (id, FirstName, MiddleName, LastName, SecondSurname, Biography, Birthdate, Height, Photo, Trivia, idDistrict) 
    VALUES (s_person.NEXTVAL, p_FirstName, p_MiddleName, p_LastName, p_SecondSurname, p_Biography, p_Birthdate, p_Height, p_Photo, p_Trivia, p_idDistrict);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertPerson;


--Insertar produccion 
CREATE OR REPLACE PROCEDURE InsertProduction (
    p_idCategory IN NUMBER, 
    p_Title IN VARCHAR2, 
    p_Duration IN NUMBER, 
    p_Synopsis IN VARCHAR2, 
    p_Trailer IN VARCHAR2 DEFAULT NULL, 
    p_ReleaseYear IN NUMBER, 
    p_Photo IN BLOB DEFAULT NULL
) AS
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo) 
    VALUES (s_production.NEXTVAL, p_idCategory, p_Title, p_Duration, p_Synopsis, p_Trailer, p_ReleaseYear, p_Photo);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertProduction;


CREATE OR REPLACE PROCEDURE InsertUserSys(
    p_FirstName IN VARCHAR2,
    p_MiddleName IN VARCHAR2,
    p_LastName IN VARCHAR2,
    p_SecondSurname IN VARCHAR2,
    p_IDNumber IN VARCHAR2,
    p_Birthdate IN DATE,
    p_Photo IN BLOB,
    p_Email IN VARCHAR2,
    p_PhoneNumber IN VARCHAR2,
    p_UserName IN VARCHAR2,
    p_Password IN VARCHAR2,
    p_idDistrict IN NUMBER,
    p_idNationality IN NUMBER,
    p_idGender IN NUMBER,
    p_idType IN NUMBER
) IS
    v_UserID NUMBER;
BEGIN
    INSERT INTO UserSys (id, FirstName, MiddleName, LastName, SecondSurname, IDNumber,
                         Birthdate, Photo, Email, PhoneNumber, UserName, Password,
                         idDistrict, idNationality, idGender, idType)
    VALUES (s_user.NEXTVAL, p_FirstName, p_MiddleName, p_LastName, p_SecondSurname,
            p_IDNumber, p_Birthdate, p_Photo, p_Email, p_PhoneNumber, p_UserName,
            p_Password, p_idDistrict, p_idNationality, p_idGender, p_idType)
    RETURNING id INTO v_UserID;

    INSERT INTO Client (id) VALUES (v_UserID);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertUserSys;
/


CREATE OR REPLACE PROCEDURE InsertUserSysAdministrator(
    p_FirstName IN VARCHAR2,
    p_MiddleName IN VARCHAR2,
    p_LastName IN VARCHAR2,
    p_SecondSurname IN VARCHAR2,
    p_IDNumber IN VARCHAR2,
    p_Birthdate IN DATE,
    p_Photo IN BLOB,
    p_Email IN VARCHAR2,
    p_PhoneNumber IN VARCHAR2,
    p_UserName IN VARCHAR2,
    p_Password IN VARCHAR2,
    p_idDistrict IN NUMBER,
    p_idNationality IN NUMBER,
    p_idGender IN NUMBER,
    p_idType IN NUMBER
) IS
    v_UserID NUMBER;
BEGIN
    INSERT INTO UserSys (id, FirstName, MiddleName, LastName, SecondSurname, IDNumber,
                         Birthdate, Photo, Email, PhoneNumber, UserName, Password,
                         idDistrict, idNationality, idGender, idType)
    VALUES (s_user.NEXTVAL, p_FirstName, p_MiddleName, p_LastName, p_SecondSurname,
            p_IDNumber, p_Birthdate, p_Photo, p_Email, p_PhoneNumber, p_UserName,
            p_Password, p_idDistrict, p_idNationality, p_idGender, p_idType)
    RETURNING id INTO v_UserID;

    INSERT INTO Administrator (id) VALUES (v_UserID);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertUserSysAdministrator;
/

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



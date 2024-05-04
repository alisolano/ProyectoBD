/*CREATE OR REPLACE PROCEDURE ComparePasswords(
    p_UserName IN VARCHAR2,
    p_Password IN VARCHAR2,
    p_Result OUT NUMBER
) AS
    v_StoredPassword VARCHAR2(100); 
    v_HashedPassword VARCHAR2(32); 
BEGIN
    SELECT Password INTO v_StoredPassword
    FROM UserSys
    WHERE UserName = p_UserName;

    v_HashedPassword := DBMS_OBFUSCATION_TOOLKIT.MD5(input_string => p_Password);

    IF v_StoredPassword IS NOT NULL AND v_HashedPassword = v_StoredPassword THEN
        p_Result := 1;  --si es igual
    ELSE --no es igual
        p_Result := 0; 
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        p_Result := -1; 
    WHEN OTHERS THEN
        p_Result := -2; 
END ComparePasswords;
/*/

CREATE OR REPLACE PROCEDURE ComparePasswords(
    p_UserName IN VARCHAR2,
    p_Password IN VARCHAR2,
    p_Result OUT NUMBER
) AS
    v_StoredPassword RAW(2000);
    v_EncryptedPassword RAW(2000); 
BEGIN
    SELECT Password INTO v_StoredPassword
    FROM UserSys
    WHERE UserName = p_UserName;
    v_EncryptedPassword := DBMS_CRYPTO.HASH(
        src => UTL_I18N.STRING_TO_RAW(p_Password || 'salt', 'AL32UTF8'), 
        typ => DBMS_CRYPTO.HASH_SH1
    );

    IF v_StoredPassword IS NOT NULL AND v_EncryptedPassword = v_StoredPassword THEN
        -- Las contraseņas coinciden
        p_Result := 1; 
    ELSE
        -- Las contraseņas no coinciden
        p_Result := 0; 
    END IF;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- No se encontrķ el usuario
        p_Result := -1; 
    WHEN OTHERS THEN
        -- Error
        p_Result := -2; 
END ComparePasswords;
/

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
    v_EncryptedPassword RAW(2000); 
BEGIN
    v_EncryptedPassword := DBMS_CRYPTO.HASH(
        src => UTL_I18N.STRING_TO_RAW(p_Password || 'salt', 'AL32UTF8'), 
        typ => DBMS_CRYPTO.HASH_SH1
    );

    
    INSERT INTO UserSys (id, FirstName, MiddleName, LastName, SecondSurname, IDNumber,
                         Birthdate, Photo, Email, PhoneNumber, UserName, Password,
                         idDistrict, idNationality, idGender, idType)
    VALUES (s_user.NEXTVAL, p_FirstName, p_MiddleName, p_LastName, p_SecondSurname,
            p_IDNumber, p_Birthdate, p_Photo, p_Email, p_PhoneNumber, p_UserName,
            v_EncryptedPassword, p_idDistrict, p_idNationality, p_idGender, p_idType)
    RETURNING id INTO v_UserID;

    INSERT INTO Client (id) VALUES (v_UserID);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertUserSys;
/


--Insertar administrador
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
    v_EncryptedPassword RAW(2000); 
BEGIN
    v_EncryptedPassword := DBMS_CRYPTO.HASH(
        src => UTL_I18N.STRING_TO_RAW(p_Password || 'salt', 'AL32UTF8'),
        typ => DBMS_CRYPTO.HASH_SH1
    );

    
    INSERT INTO UserSys (id, FirstName, MiddleName, LastName, SecondSurname, IDNumber,
                         Birthdate, Photo, Email, PhoneNumber, UserName, Password,
                         idDistrict, idNationality, idGender, idType)
    VALUES (s_user.NEXTVAL, p_FirstName, p_MiddleName, p_LastName, p_SecondSurname,
            p_IDNumber, p_Birthdate, p_Photo, p_Email, p_PhoneNumber, p_UserName,
            v_EncryptedPassword, p_idDistrict, p_idNationality, p_idGender, p_idType)
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
    p_ProductionType IN VARCHAR2,
    p_CategoryID IN NUMBER,
    p_Title IN VARCHAR2, 
    p_Duration IN NUMBER, 
    p_Synopsis IN VARCHAR2, 
    p_Trailer IN VARCHAR2 DEFAULT NULL, 
    p_ReleaseYear IN NUMBER, 
    p_Photo IN BLOB DEFAULT NULL
) AS
    v_ProductionID NUMBER;
BEGIN
    INSERT INTO Production (id, idCategory, Title, Duration, Synopsis, Trailer, ReleaseYear, Photo) 
    VALUES (s_production.NEXTVAL, p_CategoryID, p_Title, p_Duration, p_Synopsis, p_Trailer, p_ReleaseYear, p_Photo)
    RETURNING id INTO v_ProductionID;

    CASE p_ProductionType
        WHEN 'Movie' THEN
            INSERT INTO Movie (id, idProduction) VALUES (s_movie.NEXTVAL, v_ProductionID);
        WHEN 'Series' THEN
            INSERT INTO Series (id, idProduction) VALUES (s_series.NEXTVAL, v_ProductionID);
        WHEN 'Documentary' THEN
            INSERT INTO Documentary (id, idProduction) VALUES (s_documentary.NEXTVAL, v_ProductionID);
        WHEN 'Other' THEN
            INSERT INTO Other (id, idProduction) VALUES (s_other.NEXTVAL, v_ProductionID);
        ELSE
            NULL;
    END CASE;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertProduction;


SELECT * FROM Administrator;
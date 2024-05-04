-- correr esto desde sys:  GRANT EXECUTE ON SYS.DBMS_CRYPTO TO proyectoDBA;
--codigo para encriptar contraseñas
--confirmar contraseña
CREATE OR REPLACE PROCEDURE ComparePasswords(
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




CREATE OR REPLACE PROCEDURE UpdatePerson (
    p_id IN NUMBER,
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
    UPDATE Person 
    SET 
        FirstName = p_FirstName,
        MiddleName = p_MiddleName,
        LastName = p_LastName,
        SecondSurname = p_SecondSurname,
        Biography = p_Biography,
        Birthdate = p_Birthdate,
        Height = p_Height,
        Photo = p_Photo,
        Trivia = p_Trivia,
        idDistrict = p_idDistrict
    WHERE id = p_id;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END UpdatePerson;


CREATE OR REPLACE PROCEDURE UpdateProduction (
    p_id IN NUMBER,
    p_idCategory IN NUMBER,
    p_Title IN VARCHAR2,
    p_Duration IN NUMBER,
    p_Synopsis IN VARCHAR2,
    p_Trailer IN VARCHAR2,
    p_ReleaseYear IN NUMBER,
    p_Photo IN BLOB
) AS
BEGIN
    UPDATE Production 
    SET 
        idCategory = p_idCategory,
        Title = p_Title,
        Duration = p_Duration,
        Synopsis = p_Synopsis,
        Trailer = p_Trailer,
        ReleaseYear = p_ReleaseYear,
        Photo = p_Photo
    WHERE id = p_id;
    
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END UpdateProduction;



CREATE OR REPLACE PROCEDURE InsertWishlist (
    p_idClient IN NUMBER
) AS
BEGIN
    INSERT INTO Wishlist (id, idClient)
    VALUES (s_wishlist.NEXTVAL, p_idClient);
    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        RAISE;
END InsertWishlist;



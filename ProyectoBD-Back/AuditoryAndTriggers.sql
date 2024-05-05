GRANT CREATE TRIGGER TO proyectoDBA;


ALTER TABLE Country
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(10),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(10)
);

-- State
ALTER TABLE State
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- District
ALTER TABLE District
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Person
ALTER TABLE Person
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Nationality
ALTER TABLE Nationality
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- JobTitle
ALTER TABLE JobTitle
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Bond
ALTER TABLE Bond
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- NationalityPerPerson
ALTER TABLE NationalityPerPerson
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- JobPerPersonPerProduction
ALTER TABLE JobPerPersonPerProduction
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- UserSys
ALTER TABLE UserSys
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Client
ALTER TABLE Client
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Administrator
ALTER TABLE Administrator
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Payment
ALTER TABLE Payment
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- PaymentPerClient
ALTER TABLE PaymentPerClient
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Wishlist
ALTER TABLE Wishlist
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- AdministratorPerPerson
ALTER TABLE AdministratorPerPerson
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Cart
ALTER TABLE Cart
ADD (
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- CartPerClient
ALTER TABLE CartPerClient
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Production
ALTER TABLE Production
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Gender
ALTER TABLE Gender
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);



CREATE OR REPLACE TRIGGER beforeInsertGender
BEFORE INSERT ON Gender
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertGender;

CREATE OR REPLACE TRIGGER beforeUpdateGender
BEFORE UPDATE ON Gender
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateGender;


-- IdType
ALTER TABLE IdType
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Category
ALTER TABLE Category
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Catalogue
ALTER TABLE Catalogue
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- ProductionPerCatalogue
ALTER TABLE ProductionPerCatalogue
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- CartPerProduction
ALTER TABLE CartPerProduction
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- CataloguePerAdministrator
ALTER TABLE CataloguePerAdministrator
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- WishlistPerProduction
ALTER TABLE WishlistPerProduction
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Review
ALTER TABLE Review
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- PriceRecord
ALTER TABLE PriceRecord
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- PersonPerProduction
ALTER TABLE PersonPerProduction
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- ProductionPerPlatform
ALTER TABLE ProductionPerPlatform
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Documentary
ALTER TABLE Documentary
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Other
ALTER TABLE Other
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Movie
ALTER TABLE Movie
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Platform
ALTER TABLE Platform
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Season
ALTER TABLE Season
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Series
ALTER TABLE Series
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Episode
ALTER TABLE Episode
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);

-- Picture
ALTER TABLE Picture
ADD (
    CreationDate DATE,
    CreatedBy VARCHAR2(15),
    LastModificationDate DATE,
    LastModifiedBy VARCHAR2(15)
);




-- Triggers para la tabla Country
CREATE OR REPLACE TRIGGER beforeInsertCountry
BEFORE INSERT ON Country
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertCountry;

CREATE OR REPLACE TRIGGER beforeUpdateCountry
BEFORE UPDATE ON Country
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCountry;

-- Triggers para la tabla State
CREATE OR REPLACE TRIGGER beforeInsertState
BEFORE INSERT ON State
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertState;

CREATE OR REPLACE TRIGGER beforeUpdateState
BEFORE UPDATE ON State
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateState;

-- Triggers para la tabla District
CREATE OR REPLACE TRIGGER beforeInsertDistrict
BEFORE INSERT ON District
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertDistrict;

CREATE OR REPLACE TRIGGER beforeUpdateDistrict
BEFORE UPDATE ON District
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateDistrict;

-- Triggers para la tabla Person
CREATE OR REPLACE TRIGGER beforeInsertPerson
BEFORE INSERT ON Person
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPerson;

CREATE OR REPLACE TRIGGER beforeUpdatePerson
BEFORE UPDATE ON Person
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePerson;

-- Triggers para la tabla Nationality
CREATE OR REPLACE TRIGGER beforeInsertNationality
BEFORE INSERT ON Nationality
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertNationality;

CREATE OR REPLACE TRIGGER beforeUpdateNationality
BEFORE UPDATE ON Nationality
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateNationality;

-- Triggers para la tabla JobTitle
CREATE OR REPLACE TRIGGER beforeInsertJobTitle
BEFORE INSERT ON JobTitle
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertJobTitle;

CREATE OR REPLACE TRIGGER beforeUpdateJobTitle
BEFORE UPDATE ON JobTitle
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateJobTitle;

-- Triggers para la tabla Bond
CREATE OR REPLACE TRIGGER beforeInsertBond
BEFORE INSERT ON Bond
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertBond;

CREATE OR REPLACE TRIGGER beforeUpdateBond
BEFORE UPDATE ON Bond
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateBond;

-- Triggers para la tabla NationalityPerPerson
CREATE OR REPLACE TRIGGER beforeInsertNatPerPerson
BEFORE INSERT ON NationalityPerPerson
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertNatPerPerson;

CREATE OR REPLACE TRIGGER beforeUpdateNatPerPerson
BEFORE UPDATE ON NationalityPerPerson
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateNatPerPerson;

-- Triggers para la tabla JobPerPersonPerProduction
CREATE OR REPLACE TRIGGER beforeInsertJobPersonProd
BEFORE INSERT ON JobPerPersonPerProduction
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertJobPersonProd;

CREATE OR REPLACE TRIGGER beforeUpdateJobPersonProd
BEFORE UPDATE ON JobPerPersonPerProduction
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateJobPersonProd;

-- Triggers para la tabla UserSys
CREATE OR REPLACE TRIGGER beforeInsertUserSys
BEFORE INSERT ON UserSys
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertUserSys;

CREATE OR REPLACE TRIGGER beforeUpdateUserSys
BEFORE UPDATE ON UserSys
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateUserSys;



-- Triggers para la tabla Client
CREATE OR REPLACE TRIGGER beforeInsertClient
BEFORE INSERT ON Client
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertClient;

CREATE OR REPLACE TRIGGER beforeUpdateClient
BEFORE UPDATE ON Client
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateClient;

-- Triggers para la tabla Administrator
CREATE OR REPLACE TRIGGER beforeInsertAdministrator
BEFORE INSERT ON Administrator
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertAdministrator;

CREATE OR REPLACE TRIGGER beforeUpdateAdministrator
BEFORE UPDATE ON Administrator
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateAdministrator;

-- Triggers para la tabla Payment
CREATE OR REPLACE TRIGGER beforeInsertPayment
BEFORE INSERT ON Payment
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPayment;

CREATE OR REPLACE TRIGGER beforeUpdatePayment
BEFORE UPDATE ON Payment
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePayment;

-- Triggers para la tabla Wishlist
CREATE OR REPLACE TRIGGER beforeInsertWishlist
BEFORE INSERT ON Wishlist
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertWishlist;

CREATE OR REPLACE TRIGGER beforeUpdateWishlist
BEFORE UPDATE ON Wishlist
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateWishlist;

-- Triggers para la tabla AdministratorPerPerson
CREATE OR REPLACE TRIGGER beforeInsertAdmPerPerson
BEFORE INSERT ON AdministratorPerPerson
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertAdmPerPerson;

CREATE OR REPLACE TRIGGER beforeUpdateAdmPerPerson
BEFORE UPDATE ON AdministratorPerPerson
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateAdmPerPerson;



-- Triggers para la tabla Cart
CREATE OR REPLACE TRIGGER beforeInsertCart
BEFORE INSERT ON Cart
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertCart;

CREATE OR REPLACE TRIGGER beforeUpdateCart
BEFORE UPDATE ON Cart
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCart;

-- Triggers para la tabla CartPerClient
CREATE OR REPLACE TRIGGER beforeInsertCartPerClient
BEFORE INSERT ON CartPerClient
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertCartPerClient;

CREATE OR REPLACE TRIGGER beforeUpdateCartPerClient
BEFORE UPDATE ON CartPerClient
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCartPerClient;

-- Triggers para la tabla Production
CREATE OR REPLACE TRIGGER beforeInsertProduction
BEFORE INSERT ON Production
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertProduction;

CREATE OR REPLACE TRIGGER beforeUpdateProduction
BEFORE UPDATE ON Production
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateProduction;









CREATE OR REPLACE TRIGGER beforeInsertGender
BEFORE INSERT ON Gender
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertGender;

CREATE OR REPLACE TRIGGER beforeUpdateGender
BEFORE UPDATE ON Gender
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateGender;



CREATE OR REPLACE TRIGGER beforeInsertIdType
BEFORE INSERT ON IdType
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertIdType;

CREATE OR REPLACE TRIGGER beforeUpdateIdType
BEFORE UPDATE ON IdType
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateIdType;


CREATE OR REPLACE TRIGGER beforeInsertCategory
BEFORE INSERT ON Category
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertCategory;

CREATE OR REPLACE TRIGGER beforeUpdateCategory
BEFORE UPDATE ON Category
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCategory;


CREATE OR REPLACE TRIGGER beforeInsertCatalogue
BEFORE INSERT ON Catalogue
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertCatalogue;

CREATE OR REPLACE TRIGGER beforeUpdateCatalogue
BEFORE UPDATE ON Catalogue
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCatalogue;



CREATE OR REPLACE TRIGGER beforeInsertProdPerCata
BEFORE INSERT ON ProductionPerCatalogue
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertProdPerCata;

CREATE OR REPLACE TRIGGER beforeUpdateProdPerCata
BEFORE UPDATE ON ProductionPerCatalogue
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateProdPerCata;



CREATE OR REPLACE TRIGGER beforeInsertCartProd
BEFORE INSERT ON CartPerProduction
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END CartProd;

CREATE OR REPLACE TRIGGER beforeUpdateCartProd
BEFORE UPDATE ON CartPerProduction
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCartProd;



CREATE OR REPLACE TRIGGER beforeInsertCataAdmin
BEFORE INSERT ON CataloguePerAdministrator
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertCataAdmin;

CREATE OR REPLACE TRIGGER beforeUpdateCataAdmin
BEFORE UPDATE ON CataloguePerAdministrator
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateCataAdmin;



CREATE OR REPLACE TRIGGER beforeInsertWishPerPro
BEFORE INSERT ON WishlistPerProduction
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertWishPerPro;

CREATE OR REPLACE TRIGGER beforeUpdateWishPerPro
BEFORE UPDATE ON WishlistPerProduction
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateWishPerPro;


CREATE OR REPLACE TRIGGER beforeInsertReview
BEFORE INSERT ON Review
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertReview;

CREATE OR REPLACE TRIGGER beforeUpdateReview
BEFORE UPDATE ON Review
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateReview;



CREATE OR REPLACE TRIGGER beforeInsertPriceRecord
BEFORE INSERT ON PriceRecord
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPriceRecord;

CREATE OR REPLACE TRIGGER beforeUpdatePriceRecord
BEFORE UPDATE ON PriceRecord
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePriceRecord;


CREATE OR REPLACE TRIGGER beforeInsertPersonProd
BEFORE INSERT ON PersonPerProduction
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPersonProd;

CREATE OR REPLACE TRIGGER beforeUpdatePersonProd
BEFORE UPDATE ON PersonPerProduction
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePersonProd;



CREATE OR REPLACE TRIGGER beforeInsertProduPlat
BEFORE INSERT ON ProductionPerPlatform
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertProduPlat;

CREATE OR REPLACE TRIGGER beforeUpdateProduPlat
BEFORE UPDATE ON ProductionPerPlatform
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateProduPlat;


CREATE OR REPLACE TRIGGER beforeInsertDocumentary
BEFORE INSERT ON Documentary
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertDocumentary;

CREATE OR REPLACE TRIGGER beforeUpdateDocumentary
BEFORE UPDATE ON Documentary
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateDocumentary;


CREATE OR REPLACE TRIGGER beforeInsertOther
BEFORE INSERT ON Other
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertOther;

CREATE OR REPLACE TRIGGER beforeUpdateOther
BEFORE UPDATE ON Other
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateOther;


CREATE OR REPLACE TRIGGER beforeInsertMovie
BEFORE INSERT ON Movie
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertMovie;

CREATE OR REPLACE TRIGGER beforeUpdateMovie
BEFORE UPDATE ON Movie
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateMovie;


CREATE OR REPLACE TRIGGER beforeInsertPlatform
BEFORE INSERT ON Platform
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPlatform;

CREATE OR REPLACE TRIGGER beforeUpdatePlatform
BEFORE UPDATE ON Platform
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePlatform;


-- Triggers para la tabla Season
CREATE OR REPLACE TRIGGER beforeInsertSeason
BEFORE INSERT ON Season
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertSeason;

CREATE OR REPLACE TRIGGER beforeUpdateSeason
BEFORE UPDATE ON Season
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateSeason;

-- Triggers para la tabla Series
CREATE OR REPLACE TRIGGER beforeInsertSeries
BEFORE INSERT ON Series
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertSeries;

CREATE OR REPLACE TRIGGER beforeUpdateSeries
BEFORE UPDATE ON Series
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateSeries;

-- Triggers para la tabla Episode
CREATE OR REPLACE TRIGGER beforeInsertEpisode
BEFORE INSERT ON Episode
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertEpisode;

CREATE OR REPLACE TRIGGER beforeUpdateEpisode
BEFORE UPDATE ON Episode
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdateEpisode;

-- Triggers para la tabla Picture
CREATE OR REPLACE TRIGGER beforeInsertPicture
BEFORE INSERT ON Picture
FOR EACH ROW
BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPicture;

CREATE OR REPLACE TRIGGER beforeUpdatePicture
BEFORE UPDATE ON Picture
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePicture;


CREATE OR REPLACE TRIGGER beforeInsertPaymentPerClient
BEFORE INSERT ON PaymentPerClient
FOR EACH ROW

BEGIN 
    :new.CreatedBy := USER;
    :new.CreationDate := SYSDATE;
END beforeInsertPaymentPerClient;

CREATE OR REPLACE TRIGGER beforeUpdatePaymentPerClient
BEFORE UPDATE ON PaymentPerClient
FOR EACH ROW
BEGIN 
    :new.LastModifiedBy := USER;
    :new.LastModificationDate := SYSDATE;
END beforeUpdatePaymentPerClient;

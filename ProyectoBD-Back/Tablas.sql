CREATE TABLE Country
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_nn NOT NULL
);

ALTER TABLE Country
MODIFY (Name VARCHAR2(50));


CREATE TABLE State
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name NOT NULL,
      idCountry NUMBER(6)
);
ALTER TABLE State
MODIFY (Name VARCHAR2(50));

CREATE TABLE District
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT nameD NOT NULL,
      idState NUMBER(6)
);
ALTER TABLE District
MODIFY (Name VARCHAR2(100));


CREATE TABLE Person (
    id NUMBER(6),
    FirstName VARCHAR2(50) CONSTRAINT fnPerson NOT NULL,
    MiddleName VARCHAR2(50),
    LastName VARCHAR2(50) CONSTRAINT lmPerson NOT NULL,
    SecondSurname VARCHAR2(50),
    Biography VARCHAR2(4000) CONSTRAINT bioPerson NOT NULL,
    Birthdate DATE CONSTRAINT birhtPerson NOT NULL,
    Height NUMBER(5, 2),
    Photo BLOB,
    Trivia VARCHAR2(4000),
    idDistrict NUMBER(6)
);

CREATE TABLE Nationality
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_nt NOT NULL
);

ALTER TABLE Nationality
MODIFY (Name VARCHAR2(50));

CREATE TABLE JobTitle
(
      id   NUMBER(6),
      title VARCHAR2(15) CONSTRAINT name_jt NOT NULL
);


CREATE TABLE Bond (
    id NUMBER(6),
    relationship VARCHAR2(50),
    idFirstPerson NUMBER(6),
    idSecondPerson NUMBER(6)
);


CREATE TABLE NationalityPerPerson (
    id NUMBER(6),
    idPerson NUMBER(6),
    idNationality NUMBER(6)
);

CREATE TABLE JobPerPersonPerProduction (
    id NUMBER(6),
    idPerson NUMBER(6),
    idJobTitle NUMBER(6),
    idProduction NUMBER(6)
);

CREATE TABLE UserSys (
    id NUMBER(6),
    FirstName VARCHAR2(50) CONSTRAINT fnUser NOT NULL,
    MiddleName VARCHAR2(50),
    LastName VARCHAR2(50) CONSTRAINT lnUser NOT NULL,
    SecondSurname VARCHAR2(50),
    IDNumber VARCHAR2(20) CONSTRAINT idNumberUser NOT NULL,
    Birthdate DATE,
    Photo BLOB,
    Email VARCHAR2(100) CONSTRAINT emailUser NOT NULL,
    PhoneNumber VARCHAR2(20),
    UserName VARCHAR2(50) CONSTRAINT usernameUser NOT NULL,
    Password VARCHAR2(50) CONSTRAINT pswUser NOT NULL,
    idDistrict NUMBER(6),
    idNationality NUMBER(6) CONSTRAINT ntUser NOT NULL,
    idGender NUMBER(6),
    idType NUMBER(6)
);

CREATE TABLE Client (
    id NUMBER(6)
);


CREATE TABLE Administrator (
    id NUMBER(6)
);


CREATE TABLE Payment (
    id NUMBER(6),
    Name VARCHAR2(15) CONSTRAINT name_pm NOT NULL
);

ALTER TABLE Payment
MODIFY (Name VARCHAR2(40));

CREATE TABLE PaymentPerClient (
    id NUMBER (6),
    idClient NUMBER (6),
    idPayment NUMBER (6)
);

CREATE TABLE Wishlist (
    id NUMBER (6),
    idClient NUMBER(6)
);


CREATE TABLE AdministratorPerPerson (
    id NUMBER (6),
    idAdministrator NUMBER (6),
    idPerson NUMBER (6)
);


CREATE TABLE Cart (
    id NUMBER(6),
    idPayment NUMBER(6),
    creationDate DATE,
    closureDate DATE
);

CREATE TABLE CartPerClient (
    id NUMBER (6),
    idCart NUMBER (6),
    idClient NUMBER (6)
);

CREATE TABLE Production (
    id NUMBER(6),
    idCategory NUMBER(6),
    Title VARCHAR2(100) CONSTRAINT titleProdu NOT NULL,
    Duration NUMBER(4) CONSTRAINT durProdu NOT NULL,
    Synopsis VARCHAR2(1000) CONSTRAINT synoProdu NOT NULL,
    Trailer VARCHAR2(200),
    ReleaseYear NUMBER(4) CONSTRAINT relaseProdu NOT NULL,
    Photo BLOB
);


CREATE TABLE Gender
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_gnd NOT NULL
);

CREATE TABLE IdType
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_idType NOT NULL
);


CREATE TABLE Category
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_category NOT NULL
);


CREATE TABLE Catalogue
(
      id   NUMBER(6)
);

CREATE TABLE ProductionPerCatalogue
(
      id   NUMBER(6),
      idProduction NUMBER(6),
      idCatalogue NUMBER(6)
);

CREATE TABLE CartPerProduction
(
      id   NUMBER(6),
      idCart NUMBER(6),
      idProduction NUMBER(6)
);

CREATE TABLE CataloguePerAdministrator
(
      id   NUMBER(6),
      idAdministrator NUMBER(6),
      idCatalogue NUMBER(6)
);

CREATE TABLE WishlistPerProduction
(
      id   NUMBER(6),
      idWishlist NUMBER(6),
      idProduction NUMBER(6)
);

CREATE TABLE Review
(
      id   NUMBER(6),
      idProduction NUMBER(6),
      idClient NUMBER(6),
      Stars NUMBER(1),
      Commentary VARCHAR2(15) CONSTRAINT Commentary_review NOT NULL
);

CREATE TABLE PriceRecord
(
      id   NUMBER(6),
      idProduction NUMBER(6),
      price NUMBER(6),
      priceDate DATE
);

CREATE TABLE PersonPerProduction
(
      id   NUMBER(6),
      idPerson NUMBER(6),
      idProduction NUMBER(6)
);

CREATE TABLE ProductionPerPlatform
(
      id   NUMBER(6),
      idPlatform NUMBER(6),
      idProduction NUMBER(6),
      Price NUMBER(6)
);

CREATE TABLE Documentary
(
      id   NUMBER(6),
      idProduction NUMBER(6)
);

CREATE TABLE Other
(
      id   NUMBER(6),
      idProduction NUMBER(6)
);

CREATE TABLE Movie
(
      id   NUMBER(6),
      idProduction NUMBER(6)
);

CREATE TABLE Platform
(
      id   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_Platform NOT NULL
);

CREATE TABLE Season
(
      id   NUMBER(6),
      idSeries   NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_Season NOT NULL,
      seasonNumber NUMBER(2)
);

CREATE TABLE Series
(
      id   NUMBER(6),
      idProduction  NUMBER(6)
);

CREATE TABLE Episode
(
      id   NUMBER(6),
      idSeason  NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_Episode NOT NULL,
      episodeNumber NUMBER(2)
);

CREATE TABLE Picture
(
      id   NUMBER(6),
      idProduction  NUMBER(6),
      Name VARCHAR2(15) CONSTRAINT name_Picture NOT NULL,
      Picture BLOB
);


---
--- Primary keys
---

alter table Country
      add constraint pk_Country primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table State
      add constraint pk_State primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table District
      add constraint pk_District primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
   
alter table Person
      add constraint pk_Person primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);   
    
    
alter table Nationality
      add constraint pk_Nationality primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table NationalityPerPerson
      add constraint pk_NationalityPerPerson primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table Bond
      add constraint pk_bond primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      

alter table JobPerPersonPerProduction
      add constraint pk_JPPP primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      

alter table UserSys
      add constraint pk_user primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);

alter table JobTitle
      add constraint pk_job primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table Client
      add constraint pk_client primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table Payment
      add constraint pk_payment primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table PaymentPerClient
      add constraint pk_ppc primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table Wishlist
      add constraint pk_wish primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table Administrator
      add constraint pk_ad primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table AdministratorPerPerson
      add constraint pk_app primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table Cart
      add constraint pk_cart primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table CartPerClient
      add constraint pk_cpc primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table Production
      add constraint pk_production primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);     
      
alter table Gender
      add constraint pk_Gender primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table IdType
      add constraint pk_IdType primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);   
      
alter table Category
      add constraint pk_Category primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);  

alter table Catalogue
      add constraint pk_Catalogue primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);

alter table ProductionPerCatalogue
      add constraint pk_ProductionPerCatalogue primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table CartPerProduction
      add constraint pk_CartPerProduction primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);
      
alter table CataloguePerAdministrator
      add constraint pk_CataloguePerAdministrator primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);      
      
alter table WishlistPerProduction
      add constraint pk_WishlistPerProduction primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);         
      
alter table Review
      add constraint pk_Review primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);  
      
alter table PriceRecord
      add constraint pk_PriceRecord primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);       
      
alter table PersonPerProduction
      add constraint pk_PersonPerProduction primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);             
      
alter table ProductionPerPlatform
      add constraint pk_ProductionPerPlatform primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table Documentary
      add constraint pk_Documentary primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table Other
      add constraint pk_Other primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table Movie
      add constraint pk_Movie primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table Platform
      add constraint pk_Platform primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K); 
      
alter table Season
      add constraint pk_Season primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);      
      
alter table Series
      add constraint pk_Series primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);    
      
alter table Episode
      add constraint pk_Episode primary key (id)
      using index 
      tablespace AA_Index
      pctfree 20
      storage
      (initial 16K next 16K);          
---
--- Foreign keys
---

ALTER TABLE State
ADD
CONSTRAINT FK_State_Country FOREIGN KEY (idCountry) REFERENCES Country(id);

ALTER TABLE District
ADD
CONSTRAINT FK_District_State FOREIGN KEY (idState) REFERENCES State(id);

ALTER TABLE Person
ADD
CONSTRAINT FK_Person_District FOREIGN KEY (idDistrict) REFERENCES District(id);

ALTER TABLE Bond
ADD
CONSTRAINT FK_Bond_Person FOREIGN KEY (idFirstPerson) REFERENCES Person(id);

ALTER TABLE Bond
ADD
CONSTRAINT FK_Bond_Person2 FOREIGN KEY (idSecondPerson) REFERENCES Person(id);

ALTER TABLE NationalityPerPerson
ADD
CONSTRAINT FK_NPPersonID_Person FOREIGN KEY (idPerson) REFERENCES Person(id);

ALTER TABLE NationalityPerPerson
ADD
CONSTRAINT FK_NationalityIDPP_Nationality FOREIGN KEY (idNationality) REFERENCES Nationality(id);

ALTER TABLE JobPerPersonPerProduction
ADD
CONSTRAINT FK_JPPersonP_Person FOREIGN KEY (idPerson) REFERENCES Person(id);

ALTER TABLE JobPerPersonPerProduction
ADD
CONSTRAINT FK_JobPPP_jobTitle FOREIGN KEY (idJobTitle) REFERENCES JobTitle(id);

ALTER TABLE JobPerPersonPerProduction
ADD
CONSTRAINT FK_JPPProduction_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE UserSys
ADD
CONSTRAINT FK_UserSys_District FOREIGN KEY (idDistrict) REFERENCES District(id);

ALTER TABLE UserSys
ADD
CONSTRAINT FK_UserSys_Nationality FOREIGN KEY (idNationality) REFERENCES Nationality(id);

ALTER TABLE UserSys
ADD
CONSTRAINT FK_UserSys_Gender FOREIGN KEY (idGender) REFERENCES Gender(id);

ALTER TABLE UserSys
ADD
CONSTRAINT FK_UserSys_IdType FOREIGN KEY (idType) REFERENCES IdType(id);

ALTER TABLE Administrator
ADD
CONSTRAINT FK_Administrator_UserSys FOREIGN KEY (id) REFERENCES UserSys(id);

ALTER TABLE Client
ADD
CONSTRAINT FK_Client_UserSys FOREIGN KEY (id) REFERENCES UserSys(id);

ALTER TABLE PaymentPerClient
ADD
CONSTRAINT FK_PaymentPerClient_Client FOREIGN KEY (idClient) REFERENCES Client(id);

ALTER TABLE PaymentPerClient
ADD
CONSTRAINT FK_PaymentPerClient_Payment FOREIGN KEY (idPayment) REFERENCES Payment(id);

ALTER TABLE Wishlist
ADD
CONSTRAINT FK_Wishlist_Client FOREIGN KEY (idClient) REFERENCES Client(id);

ALTER TABLE AdministratorPerPerson
ADD
CONSTRAINT FK_AdPerPerson_Administrator FOREIGN KEY (idAdministrator) REFERENCES Administrator(id);

ALTER TABLE AdministratorPerPerson
ADD
CONSTRAINT FK_AdPerPerson_Person FOREIGN KEY (idPerson) REFERENCES Person(id);

ALTER TABLE Cart
ADD
CONSTRAINT FK_Cart_Payment FOREIGN KEY (idPayment) REFERENCES Payment(id);

ALTER TABLE CartPerClient
ADD
CONSTRAINT FK_CartPerClient_Cart FOREIGN KEY (idCart) REFERENCES Cart(id);

ALTER TABLE CartPerClient
ADD
CONSTRAINT FK_CartPerClient_Client FOREIGN KEY (idClient) REFERENCES Client(id);

ALTER TABLE Production
ADD
CONSTRAINT FK_Production_Category FOREIGN KEY (idCategory) REFERENCES Category(id);

ALTER TABLE ProductionPerCatalogue
ADD
CONSTRAINT FK_ProPerCat_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE ProductionPerCatalogue
ADD
CONSTRAINT FK_ProPerCat_Catalogue FOREIGN KEY (idCatalogue) REFERENCES Catalogue(id);

ALTER TABLE CartPerProduction
ADD
CONSTRAINT FK_CartPerPro_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE CartPerProduction
ADD
CONSTRAINT FK_CartPerProduction_Cart FOREIGN KEY (idCart) REFERENCES Cart(id);

ALTER TABLE CataloguePerAdministrator
ADD
CONSTRAINT FK_CatPerAdm_Administrator FOREIGN KEY (idAdministrator) REFERENCES Administrator(id);

ALTER TABLE CataloguePerAdministrator
ADD
CONSTRAINT FK_CatPerAdm_Catalogue FOREIGN KEY (idCatalogue) REFERENCES Catalogue(id);

ALTER TABLE WishlistPerProduction
ADD
CONSTRAINT FK_WishPerPro_Wishlist FOREIGN KEY (idWishlist) REFERENCES Wishlist(id); 

ALTER TABLE WishlistPerProduction
ADD
CONSTRAINT FK_WishPerPro_Production FOREIGN KEY (idProduction) REFERENCES Production(id); 

ALTER TABLE Review
ADD
CONSTRAINT FK_Review_Production FOREIGN KEY (idProduction) REFERENCES Production(id); 

ALTER TABLE Review
ADD
CONSTRAINT FK_Review_Client FOREIGN KEY (idClient) REFERENCES Client(id);

ALTER TABLE PriceRecord
ADD
CONSTRAINT FK_PriceRecord_Production FOREIGN KEY (idProduction) REFERENCES Production(id); 

ALTER TABLE PersonPerProduction
ADD
CONSTRAINT FK_PersonPerPro_Production FOREIGN KEY (idProduction) REFERENCES Production(id); 

ALTER TABLE PersonPerProduction
ADD
CONSTRAINT FK_PersonPerProduction_Person FOREIGN KEY (idPerson) REFERENCES Person(id); 

ALTER TABLE ProductionPerPlatform
ADD
CONSTRAINT FK_ProPerPlat_Platform FOREIGN KEY (idPlatform) REFERENCES Platform(id); 

ALTER TABLE ProductionPerPlatform
ADD
CONSTRAINT FK_ProdPerPlat_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE Documentary
ADD
CONSTRAINT FK_Documentary_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE Other
ADD
CONSTRAINT FK_Other_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE Movie
ADD
CONSTRAINT FK_Movie_Production FOREIGN KEY (idProduction) REFERENCES Production(id);


ALTER TABLE Season
ADD
CONSTRAINT FK_Season_Series FOREIGN KEY (idSeries) REFERENCES Series(id);

ALTER TABLE Series
ADD
CONSTRAINT FK_Series_Production FOREIGN KEY (idProduction) REFERENCES Production(id);

ALTER TABLE Episode
ADD
CONSTRAINT FK_Episode_Season FOREIGN KEY (idSeason) REFERENCES Production(id);
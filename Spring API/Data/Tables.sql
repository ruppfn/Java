CREATE TABLE PERSONAS(
	ID INT IDENTITY(1,1),
	FIRST_NAME VARCHAR(30) NOT NULL,
	LAST_NAME VARCHAR(30) NOT NULL,
	EMAIL VARCHAR(100) NULL,
	[ADDRESS] VARCHAR(150) NULL
)

CREATE TABLE ROLES(
	ID INT IDENTITY(1,1),
	[ROLE] VARCHAR(50)
)

CREATE TABLE USUARIOS(
	ID INT IDENTITY(1,1),
	USERNAME VARCHAR(100) NOT NULL,
	[PASSWORD] VARCHAR(100) NOT NULL
)

CREATE TABLE USER_ROLE(
	[USER_ID] INT NOT NULL,
	ROLE_ID INT NOT NULL
)

GO

ALTER TABLE PERSONAS ADD CONSTRAINT PK_PERSONAS_ID PRIMARY KEY(ID)

ALTER TABLE ROLES ADD CONSTRAINT PK_ROLES_ID PRIMARY KEY (ID)

ALTER TABLE USUARIOS ADD CONSTRAINT PK_USUARIOS_ID PRIMARY KEY (ID)

ALTER TABLE USER_ROLE ADD CONSTRAINT FK_USER_ROLE_USER_ID FOREIGN KEY ([USER_ID])
	REFERENCES USUARIOS(ID)
	
ALTER TABLE USER_ROLE ADD CONSTRAINT FK_USER_ROLE_ROLE_ID FOREIGN KEY (ROLE_ID)
	REFERENCES ROLES(ID)

ALTER TABLE USER_ROLE ADD CONSTRAINT PK_USER_ROLE_ROLE_ID_USER_ID PRIMARY KEY (ROLE_ID, [USER_ID])
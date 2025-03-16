# Fintech Project

## 📊 About the Project

   This is a personal finance management system developed in Java using Servlets and JDBC for interaction with an Oracle database. The goal is to allow users to efficiently manage their expenses, income, and investments by providing functionalities for registration, editing, deletion, and viewing.

## 🚀 Features

   - User registration and secure login

   - Investment management (full CRUD)

   - Session control with authentication filters

   - User validation with database integration

## 🛠️ Technologies Used

 - **Java** as the main programming language for application development.

- **Servlets** for business logic control and HTTP request management.

- **JDBC** for connection and data manipulation in an Oracle database.

- **Oracle Database** for data persistence.

- **Bootstrap** for responsive web page design.

- **HTML/CSS/JSP** for the user interface.

## 🎯 Project Structure

- **bean**: Model classes representing system entities (e.g., `Usuario`, `Investimento`).

- **dao**: Implementation of the DAO pattern for database interaction.

- **controller**: Servlets responsible for request and response handling.

- **filter**: Security filters for access control.

- **singleton**: Database connection management.

## ⚡ How to Run the Project

1. Clone the repository:```git clone https://github.com/JonasHCzaja/fintech-jdbc.git```
2. Import the project into a Java-compatible IDE (IntelliJ, Eclipse, etc.).
3. Configure the Oracle database and update the credentials in ConnectionManager.
4. Run the server (Apache Tomcat recommended).

## 📂 Database Scripts

Create the Users and Investments tables in Oracle:

```sql

CREATE TABLE T_FNT_USUARIO (
        DS_EMAIL     VARCHAR2(60 CHAR) NOT NULL, 
	NM_NOME      VARCHAR2(60 CHAR) NOT NULL, 
	DS_SENHA     VARCHAR2(50 BYTE) NOT NULL,
        CONSTRAINT T_FNT_USUARIO_PK PRIMARY KEY (DS_EMAIL)
   );


CREATE TABLE T_FNT_DESPESA  (
        CD_DESPESA             NUMBER(4,0) NOT NULL, 
	DS_TIPO_DESPESA        VARCHAR2(20 BYTE) NOT NULL, 
	DS_DESPESA             VARCHAR2(60 CHAR) NOT NULL, 
	DS_CATEGORIA           VARCHAR2(20 CHAR) NOT NULL, 
	VL_DESPESA             NUMBER NOT NULL, 
	DT_DESPESA             DATE NOT NULL, 
	DS_CARTAO_CREDITO      VARCHAR2(20 CHAR), 
	DS_EMAIL               VARCHAR2(60 CHAR) NOT NULL,
        CONSTRAINT T_FNT_DESPESA_PK PRIMARY KEY (CD_DESPESA, DS_EMAIL),
        CONSTRAINT T_FNT_DESPESA_USUARIO_FK FOREIGN KEY (DS_EMAIL) REFERENCES T_FNT_USUARIO (DS_EMAIL)
   );
   
CREATE TABLE T_FNT_DIVIDA ( 
   	CD_DIVIDA      NUMBER(4,0) NOT NULL, 
	VL_VALOR       NUMBER NOT NULL, 
	DS_DIVIDA      VARCHAR2(60 CHAR), 
	DT_PRAZO       DATE NOT NULL, 
	DS_EMAIL       VARCHAR2(60 CHAR) NOT NULL,
        CONSTRAINT T_FNT_DIVIDA_PK PRIMARY KEY (CD_DIVIDA, DS_EMAIL),
        CONSTRAINT T_FNT_DIVIDA_USUARIO_FK FOREIGN KEY (DS_EMAIL) REFERENCES T_FNT_USUARIO (DS_EMAIL)
   );

CREATE TABLE T_FNT_GANHO ( 
   	CD_GANHO            NUMBER(4,0) NOT NULL, 
	DS_GANHO            VARCHAR2(60 CHAR) NOT NULL, 
	DS_CATEGORIA        VARCHAR2(20 CHAR) NOT NULL, 
	VL_GANHO            NUMBER NOT NULL, 
	DT_RECEBIMENTO      DATE NOT NULL, 
	DS_EMAIL            VARCHAR2(60 CHAR) NOT NULL,
        CONSTRAINT T_FNT_GANHO_PK PRIMARY KEY (CD_GANHO, DS_EMAIL),
        CONSTRAINT T_FNT_GANHO_USUARIO_FK FOREIGN KEY (DS_EMAIL) REFERENCES T_FNT_USUARIO (DS_EMAIL)
   );
   
CREATE TABLE T_FNT_INVESTIMENTO (
   	CD_ATIVO            NUMBER(4,0) NOT NULL, 
	NM_ATIVO            VARCHAR2(30 CHAR) NOT NULL, 
	DS_CATEGORIA        VARCHAR2(15 CHAR) NOT NULL, 
	VL_PRECO_MEDIO      NUMBER NOT NULL, 
	NR_QUANTIDADE       NUMBER(5,0) NOT NULL, 
	DS_EMAIL            VARCHAR2(60 CHAR) NOT NULL,
        CONSTRAINT T_FNT_INVESTIMENTO_PK PRIMARY KEY (DS_EMAIL, CD_ATIVO),
        CONSTRAINT T_FNT_INVESTIMENTO_USUARIO_FK FOREIGN KEY (DS_EMAIL) REFERENCES T_FNT_USUARIO (DS_EMAIL)
   );

```

## 🎓 Academic Context

   This project was developed as part of the Analysis and Systems Development course at FIAP. The objective is to apply concepts of object-oriented programming, web development with Java, and database integration studied during the first year of the course.

#

Developed by **JonasHCzaja**.


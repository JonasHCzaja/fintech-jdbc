# Fintech Project

## 📊 Sobre o Projeto

   Este é um sistema de controle de finanças pessoais desenvolvido em Java utilizando Servlets e JDBC para interação com um banco de dados Oracle. O objetivo é permitir que usuários gerenciem suas despesas, ganhos e investimentos de forma eficiente, oferecendo funcionalidades de cadastro, edição, exclusão e visualização.

## 🚀 Funcionalidades

   - Cadastro de usuários e login seguro

   - Gerenciamento de investimentos (CRUD completo)

   - Controle de sessões com filtros de autenticação

   - Validação de usuários com integração ao banco de dados

## 🛠️ Tecnologias Utilizadas

   - Java como linguagem principal para o desenvolvimento da aplicação.

   - Servlets para controle da lógica de negócios e gerenciamento de requisições HTTP.

   - JDBC para conexão e manipulação de dados em um banco Oracle.

   - Oracle Database para persistência de dados

   - Bootstrap para o design responsivo das páginas web

   - HTML/CSS/JSP para a interface do usuário

## 🎯 Estrutura do Projeto

   - bean: Classes de modelo representando entidades do sistema (ex: Usuario, Investimento).

   - dao: Implementação do padrão DAO para interação com o banco de dados.

   - controller: Servlets responsáveis pelo controle de requisições e respostas.

   - filter: Filtros de segurança para controle de acesso.

   - singleton: Gerenciamento da conexão com o banco de dados.

## ⚡ Como Executar o Projeto

   1. Clone o repositório:

   2. git clone https://github.com/JonasHCzaja/fintech-jdbc.git

   3. Importe o projeto em uma IDE compatível com Java (IntelliJ, Eclipse, etc).

   4. Configure o banco de dados Oracle e atualize as credenciais no ConnectionManager.

   5. Execute o servidor (Apache Tomcat recomendado).

## 📂 Scripts do Banco de Dados

Crie a tabela de usuários e investimentos no Oracle:

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

## 🎓 Contexto Acadêmico

   Este projeto foi desenvolvido como trabalho do curso de Análise e Desenvolvimento de Sistemas da FIAP. O objetivo é aplicar conceitos de programação orientada a objetos, desenvolvimento web com Java e integração com banco de dados estudados durante o primeiro ano do curso.

#

Desenvolvido por JonasHCzaja.


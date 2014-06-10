-- DROPA TODOS OS ELEMENTOS DE BANCO DE DADOS --
DROP TABLE carrinho;
DROP TABLE cliente;
DROP TABLE produto;
DROP SEQUENCE seq_cliente;
DROP SEQUENCE seq_produto;
DROP SEQUENCE seq_carrinho;

-- CRIACAO DAS SEQUENCES --
CREATE SEQUENCE seq_cliente
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE seq_produto
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE SEQUENCE seq_carrinho
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

-- CRIACAO DAS TABELAS --
CREATE TABLE cliente
(
  id_cliente integer DEFAULT nextval('seq_cliente') PRIMARY KEY,
  nome character varying(200),
  telefone character varying(20),
  cpf character varying(15),
  cep character varying(20),
  dt_nascimento character varying(10),
  endereco character varying(200),
  email character varying(50),
  login character varying(100),
  senha character varying(100),
  administrador boolean
);

CREATE TABLE produto
(
  id_produto integer DEFAULT nextval('seq_produto') PRIMARY KEY,
  codigo_barra integer,
  descricao character varying(200),
  dt_fabricacao character varying(10),
  marca character varying(20),
  observacao character varying(200),
  qtd_estoque integer,
  valor numeric(30,2)
);

CREATE TABLE carrinho
(
  id_carrinho integer DEFAULT nextval('seq_carrinho') PRIMARY KEY,
  id_cliente integer REFERENCES cliente(id_cliente),
  id_produto integer REFERENCES produto(id_produto),
  dt_compra varchar(20),
  qtd_produto integer,
  pago boolean DEFAULT false,
  num_pedido integer
);

-- POPULANDO AS TABELAS --
INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha, administrador)
VALUES ('ALAN SAULO','(61) 3043-1111','111.111.111-11','70.000-111','01/01/2001','Rua 1 Lote 1 Casa1','alan@gmail.com','alan.saulo','senha111',true);

INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha, administrador)
VALUES ('BELTRANO DA SILVA','(61) 3043-2222','222.222.222-22','70.000-222','02/02/2002','Rua 2 Lote 2 Casa2','beltrano@gmail.com','beltrano.silva','senha222',false);

INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha, administrador)
VALUES ('CICLANO COSTA','(61) 3043-3333','333.333.333-33','70.000-333','03/03/2003','Rua 3 Lote 3 Casa3','ciclano@gmail.com','ciclano.costa','senha333',false);

INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor)
VALUES (1111111,'Monitor LCD','05/05/2014','LG','Produto Novo.',100,1111.11);

INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor)
VALUES (2222222,'Monitor LCD','05/05/2014','Samsumg','Produto Novo.',200,1222.22);

INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor)
VALUES (3333333,'Teclado sem fio','05/05/2014','DELL','Produto Novo.',300,333.33);

-- DROPA TODOS OS ELEMENTOS DE BANCO DE DADOS --
DROP TABLE carrinho;
DROP TABLE cliente;
DROP TABLE produto;

-- CRIACAO DAS TABELAS --
CREATE TABLE cliente
(
  id_cliente integer  AUTO_INCREMENT,
  nome character varying(200),
  telefone character varying(20),
  cpf character varying(15),
  cep character varying(20),
  dt_nascimento character varying(10),
  endereco character varying(200),
  email character varying(50),
  login character varying(100),
  senha character varying(100),
  administrador boolean,
PRIMARY KEY (id_cliente)
);

CREATE TABLE produto
(
  id_produto integer AUTO_INCREMENT,
  codigo_barra integer,
  descricao character varying(200),
  dt_fabricacao character varying(10),
  marca character varying(20),
  observacao character varying(200),
  qtd_estoque integer,
  valor numeric(30,2),
PRIMARY KEY (id_produto)
);

CREATE TABLE carrinho
(
  id_carrinho integer AUTO_INCREMENT,
  id_cliente integer REFERENCES cliente(id_cliente),
  id_produto integer REFERENCES produto(id_produto),
  dt_compra varchar(20),
  qtd_produto integer,
  pago boolean DEFAULT false,
  num_pedido integer,
PRIMARY KEY (id_carrinho)
);

-- POPULA AS TABELAS --
INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha, administrador)
VALUES ('Administrador','(61) 3043-1111','111.111.111-11','70.000-111','01/01/2001','Rua 1 Lote 1 Casa1','admin@gmail.com','admin','admin',true);

INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha, administrador)
VALUES ('Jo‹o Silva','(61) 3043-2222','222.222.222-22','70.000-222','02/02/2002','Rua 2 Lote 2 Casa2','joao@gmail.com','joao.silva','123',false);

INSERT INTO cliente (nome, telefone, cpf, cep, dt_nascimento, endereco, email, login, senha, administrador)
VALUES ('Ana Silva','(61) 3043-3333','333.333.333-33','70.000-333','03/03/2003','Rua 3 Lote 3 Casa3','ana@gmail.com','ana.silva','456',false);

INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor)
VALUES (1111111,'Monitor LCD','05/05/2014','LG','Tela 17 polegadas - branco.',10,350.00);

INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor)
VALUES (2222222,'Monitor LED','05/05/2014','Samsumg','Tela 15 polega.',15,420.50);

INSERT INTO produto (codigo_barra, descricao, dt_fabricacao, marca, observacao, qtd_estoque, valor)
VALUES (3333333,'Teclado sem fio','05/05/2014','DELL','Modelo ver‹o 2014.',20,45.90);

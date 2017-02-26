DROP ALL OBJECTS;

CREATE TABLE TB_CATEGORIA (
  ID       INT(9)      NOT NULL AUTO_INCREMENT,
  NOME     VARCHAR(50) NOT NULL,
  REVISADO VARCHAR(1)  NOT NULL,
  CONSTRAINT PK_CATEGORIA PRIMARY KEY (ID)
);
ALTER TABLE TB_CATEGORIA ADD CONSTRAINT UQ_CATEGORIA_NOME UNIQUE (NOME);


CREATE TABLE TB_ORIGEM (
  ID    INT(9)       NOT NULL AUTO_INCREMENT,
  ALIAS VARCHAR(50)  NOT NULL,
  NOME  VARCHAR(100) NOT NULL,
  CONSTRAINT PK_ORIGEM PRIMARY KEY (ID)
);
ALTER TABLE TB_ORIGEM ADD CONSTRAINT UQ_ORIGEM_ALIAS UNIQUE (ALIAS);
ALTER TABLE TB_ORIGEM ADD CONSTRAINT UQ_ORIGEM_NOME UNIQUE (NOME);


CREATE TABLE TB_PREVISAO (
  ID        INT(9)        NOT NULL AUTO_INCREMENT,
  DATA      DATE          NULL,
  DESCRICAO VARCHAR(255)  NULL,
  VALOR     DECIMAL(9, 2) NULL,
  CATEGORIA INT(9)        NULL,
  REALIZADA BOOLEAN       NOT NULL DEFAULT FALSE,
  BILL      BOOLEAN       NOT NULL DEFAULT FALSE,
  CONSTRAINT PK_PREVISAO PRIMARY KEY (ID)
);
ALTER TABLE TB_PREVISAO ADD CONSTRAINT FK_PREVISAO_CATEGORIA FOREIGN KEY (CATEGORIA) REFERENCES TB_CATEGORIA (ID);


CREATE TABLE TB_MOVIMENTACAO (
  ID              INT(9)        NOT NULL AUTO_INCREMENT,
  DATA            DATE          NULL,
  NUMERODOCUMENTO VARCHAR(50)   NULL,
  DESCRICAO1      VARCHAR(255)  NULL,
  DESCRICAO2      VARCHAR(255)  NULL,
  DESCRICAO3      VARCHAR(255)  NULL,
  VALOR           DECIMAL(9, 2) NULL,
  ORIGEM          INT(9)        NULL,
  SALDO           DECIMAL(9, 2) NULL,
  REALIZA         INT(9)        NULL,
  ANTERIOR        INT(9)        NULL,
  CONSTRAINT PK_MOVIMENTACAO PRIMARY KEY (ID)
);
ALTER TABLE TB_MOVIMENTACAO ADD CONSTRAINT FK_MOVIMENTACAO_ORIGEM FOREIGN KEY (ORIGEM) REFERENCES TB_ORIGEM (ID);
ALTER TABLE TB_MOVIMENTACAO ADD CONSTRAINT FK_MOVIMENTACAO_PREVISAO FOREIGN KEY (REALIZA) REFERENCES TB_PREVISAO (ID);
ALTER TABLE TB_MOVIMENTACAO ADD CONSTRAINT FK_MOVIMENTACAO_MOVIMENTACAO_ANTERIOR FOREIGN KEY (ANTERIOR) REFERENCES TB_MOVIMENTACAO (ID);

-- adicao de duas colunas na movimentacao

CREATE TABLE TB_ARQUIVO (
  ID              INT(9)      NOT NULL AUTO_INCREMENT,
  NOME            VARCHAR(50) NOT NULL,
  DESCRICAO       VARCHAR(255) NOT NULL,
  DATA_REFERENCIA DATE        NULL,
  NOME_ARQUIVO    VARCHAR(100) NOT NULL,
  CONTEUDO        MEDIUMBLOB  NOT NULL,
  TIPO_CONTEUDO   VARCHAR(255) NOT NULL,
  CONSTRAINT PK_ARQUIVO PRIMARY KEY (ID)
);

CREATE TABLE TB_MESCONTABIL (
  ANO             INT(4)        NOT NULL,
  MES             INT(4)        NOT NULL,
  CONTACARREGADA  BOOLEAN       NOT NULL DEFAULT FALSE,
  FATURACARREGADA BOOLEAN       NOT NULL DEFAULT FALSE,
  MESCONCLUIDO    BOOLEAN       NOT NULL DEFAULT FALSE,
  COMENTARIO      VARCHAR(1000) NULL,
  CONSTRAINT PK_MESCONTABIL PRIMARY KEY (ANO, MES)
);
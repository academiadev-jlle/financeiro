CREATE SEQUENCE usuario_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE usuario (
  id         bigint                 NOT NULL DEFAULT nextval('usuario_id_seq' :: regclass),
  created_at timestamp without time zone,
  updated_at timestamp without time zone,
  email      character varying(60)  NOT NULL,
  nome       character varying(120) NOT NULL,
  senha      character varying(16)  NOT NULL,
  CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE lancamento_financeiro_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;

CREATE TABLE lancamento_financeiro (
  id                bigint                 NOT NULL DEFAULT nextval('lancamento_financeiro_id_seq' :: regclass),
  created_at        timestamp without time zone,
  updated_at        timestamp without time zone,
  data_emissao      date                   NOT NULL,
  data_vencimento   date                   NOT NULL,
  recebedor_pagador character varying(120) NOT NULL,
  status            character varying(255) NOT NULL,
  tipolancamento    character varying(255) NOT NULL,
  valor             numeric(19, 2),
  usuario_id        bigint                 NOT NULL,
  CONSTRAINT lancamento_financeiro_pkey PRIMARY KEY (id),
  CONSTRAINT fks7k0n1893hhfig24qdnn1xbcg FOREIGN KEY (usuario_id)
  REFERENCES usuario (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
)
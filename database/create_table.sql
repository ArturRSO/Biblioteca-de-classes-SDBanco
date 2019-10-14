-- Table: public.conta

-- DROP TABLE public.conta;

CREATE TABLE public.conta
(
    idconta integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    saldo double precision,
    CONSTRAINT conta_pkey PRIMARY KEY (idconta)
)

TABLESPACE pg_default;

ALTER TABLE public.conta
    OWNER to postgres;

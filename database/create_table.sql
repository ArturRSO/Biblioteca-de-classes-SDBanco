-- Table: public.conta

-- DROP TABLE public.conta;

CREATE TABLE public.conta
(
    senha integer NOT NULL,
    nome character varying(50) COLLATE pg_catalog."default" NOT NULL,
    id integer NOT NULL,
    saldo double precision,
    CONSTRAINT conta_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.conta
    OWNER to postgres;

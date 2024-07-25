--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3
-- Dumped by pg_dump version 16.3

-- Started on 2024-07-21 22:58:50

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 5055 (class 1262 OID 16397)
-- Name: loja_virtual_mentoria_teste; Type: DATABASE; Schema: -; Owner: postgres
--

--CREATE DATABASE "loja_virtual_mentoria_teste" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';


ALTER DATABASE "loja_virtual_mentoria_teste" OWNER TO postgres;

--\connect "loja_virtual_mentoria_teste"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 255 (class 1255 OID 24977)
-- Name: validachavepessoa(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

declare existe integer;

begin
    existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_id );
if(existe <=0) then
	existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_id );
if(existe <=0) then
		raise exception 'Nao foi encontrado o ID ou PK da pessoa para realizar a associacao';
end if;
end if;
return new;
end;
$$;


ALTER FUNCTION public.validachavepessoa() OWNER TO postgres;

--
-- TOC entry 256 (class 1255 OID 24985)
-- Name: validachavepessoa2(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.validachavepessoa2() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

declare existe integer;

begin
    existe = (select count(1) from pessoa_fisica where id = NEW.pessoa_forn_id );
if(existe <=0) then
	existe = (select count(1) from pessoa_juridica where id = NEW.pessoa_forn_id );
if(existe <=0) then
		raise exception 'Nao foi encontrado o ID ou PK da pessoa para realizar a associacao';
end if;
end if;
return new;
end;
$$;


ALTER FUNCTION public.validachavepessoa2() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 224 (class 1259 OID 24630)
-- Name: acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.acesso (
                               id bigint NOT NULL,
                               descricao character varying(255) NOT NULL
);


ALTER TABLE public.acesso OWNER TO postgres;

--
-- TOC entry 247 (class 1259 OID 24816)
-- Name: avaliacao_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.avaliacao_produto (
                                          id bigint NOT NULL,
                                          pessoa_id bigint NOT NULL,
                                          produto_id bigint NOT NULL,
                                          nota integer NOT NULL,
                                          descricao character varying(255) NOT NULL
);


ALTER TABLE public.avaliacao_produto OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24589)
-- Name: categoria_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_produto (
                                          id bigint NOT NULL,
                                          nome_desc character varying(255) NOT NULL
);


ALTER TABLE public.categoria_produto OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24673)
-- Name: conta_pagar; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta_pagar (
                                    id bigint NOT NULL,
                                    dt_pagamento date,
                                    valor_desconto numeric(38,2),
                                    pessoa_id bigint NOT NULL,
                                    pessoa_forn_id bigint NOT NULL,
                                    descricao character varying(255) NOT NULL,
                                    dt_vencimento date NOT NULL,
                                    status character varying(255) NOT NULL,
                                    valor_total numeric(38,2) NOT NULL,
                                    CONSTRAINT conta_pagar_status_check CHECK (((status)::text = ANY ((ARRAY['COBRANCA'::character varying, 'VENCIDA'::character varying, 'ABERTA'::character varying, 'QUITADA'::character varying, 'NEGOCIADA'::character varying, 'FUNCIONARIO'::character varying, 'ALUGUEL'::character varying])::text[])))
);


ALTER TABLE public.conta_pagar OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 24658)
-- Name: conta_receber; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.conta_receber (
                                      id bigint NOT NULL,
                                      dt_pagamento date,
                                      dt_vencimento date,
                                      pessoa_id bigint NOT NULL,
                                      descricao character varying(255) NOT NULL,
                                      status character varying(255) NOT NULL,
                                      valor_desconto numeric(38,2),
                                      valor_total numeric(38,2) NOT NULL,
                                      CONSTRAINT conta_receber_status_check CHECK (((status)::text = ANY ((ARRAY['COBRANCA'::character varying, 'VENCIDA'::character varying, 'ABERTA'::character varying, 'QUITADA'::character varying])::text[])))
);


ALTER TABLE public.conta_receber OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 24682)
-- Name: cup_desc; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cup_desc (
                                 id bigint NOT NULL,
                                 valor_porcent_desc numeric(38,2),
                                 valor_real_desc numeric(38,2),
                                 cod_desc character varying(255) NOT NULL,
                                 data_validade_cupom date NOT NULL
);


ALTER TABLE public.cup_desc OWNER TO postgres;

--
-- TOC entry 249 (class 1259 OID 24829)
-- Name: endereco; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.endereco (
                                 id bigint NOT NULL,
                                 bairro character varying(255) NOT NULL,
                                 cep character varying(255) NOT NULL,
                                 cidade character varying(255) NOT NULL,
                                 complemento character varying(255),
                                 numero character varying(255) NOT NULL,
                                 rua_logradouro character varying(255) NOT NULL,
                                 tipo_endereco character varying(255) NOT NULL,
                                 uf character varying(255) NOT NULL,
                                 pessoa_id bigint NOT NULL,
                                 CONSTRAINT endereco_tipo_endereco_check CHECK (((tipo_endereco)::text = ANY ((ARRAY['COBRANCA'::character varying, 'ENTREGA'::character varying])::text[])))
);


ALTER TABLE public.endereco OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 24667)
-- Name: forma_pagamento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.forma_pagamento (
                                        id bigint NOT NULL,
                                        descricao character varying(255) NOT NULL
);


ALTER TABLE public.forma_pagamento OWNER TO postgres;

--
-- TOC entry 250 (class 1259 OID 24848)
-- Name: imagem_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.imagem_produto (
                                       id bigint NOT NULL,
                                       imagem_miniatura text NOT NULL,
                                       imagem_original text NOT NULL,
                                       produto_id bigint NOT NULL
);


ALTER TABLE public.imagem_produto OWNER TO postgres;

--
-- TOC entry 245 (class 1259 OID 24800)
-- Name: item_venda_loja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.item_venda_loja (
                                        id bigint NOT NULL,
                                        produto_id bigint NOT NULL,
                                        venda_compra_loja_virt_id bigint NOT NULL,
                                        quantidade double precision NOT NULL
);


ALTER TABLE public.item_venda_loja OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16398)
-- Name: marca_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.marca_produto (
                                      id bigint NOT NULL,
                                      nome_desc character varying(255) NOT NULL
);


ALTER TABLE public.marca_produto OWNER TO postgres;

--
-- TOC entry 251 (class 1259 OID 24860)
-- Name: nota_fiscal_compra; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_fiscal_compra (
                                           id bigint NOT NULL,
                                           data_compra date NOT NULL,
                                           descricao_obs character varying(255),
                                           numero_nota character varying(255) NOT NULL,
                                           serie_nota character varying(255) NOT NULL,
                                           valor_desconto numeric(38,2),
                                           valor_icms numeric(38,2) NOT NULL,
                                           valor_total numeric(38,2) NOT NULL,
                                           conta_pagar_id bigint NOT NULL,
                                           pessoa_id bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 252 (class 1259 OID 24877)
-- Name: nota_fiscal_venda; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_fiscal_venda (
                                          id bigint NOT NULL,
                                          numero character varying(255) NOT NULL,
                                          pdf text NOT NULL,
                                          serie character varying(255) NOT NULL,
                                          tipo character varying(255) NOT NULL,
                                          xml text NOT NULL,
                                          venda_compra_loja_virt_id bigint NOT NULL
);


ALTER TABLE public.nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 239 (class 1259 OID 24723)
-- Name: nota_item_produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.nota_item_produto (
                                          id bigint NOT NULL,
                                          quantidade double precision NOT NULL,
                                          nota_fiscal_compra_id bigint NOT NULL,
                                          produto_id bigint NOT NULL
);


ALTER TABLE public.nota_item_produto OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 24606)
-- Name: pessoa_fisica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_fisica (
                                      id bigint NOT NULL,
                                      cpf character varying(255) NOT NULL,
                                      data_nascimento date,
                                      email character varying(255) NOT NULL,
                                      nome character varying(255) NOT NULL,
                                      telefone character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_fisica OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 24613)
-- Name: pessoa_juridica; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pessoa_juridica (
                                        id bigint NOT NULL,
                                        email character varying(255) NOT NULL,
                                        nome character varying(255) NOT NULL,
                                        telefone character varying(255) NOT NULL,
                                        categoria character varying(255),
                                        cnpj character varying(255) NOT NULL,
                                        insc_estadual character varying(255) NOT NULL,
                                        insc_municipal character varying(255),
                                        nome_fantasia character varying(255) NOT NULL,
                                        razao_social character varying(255) NOT NULL
);


ALTER TABLE public.pessoa_juridica OWNER TO postgres;

--
-- TOC entry 253 (class 1259 OID 24896)
-- Name: produto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produto (
                                id bigint NOT NULL,
                                qtda_alerta_estoque integer,
                                qtda_estoque integer NOT NULL,
                                alerta_qtda_estoque boolean,
                                altura double precision NOT NULL,
                                ativo boolean NOT NULL,
                                descricao text NOT NULL,
                                largura double precision NOT NULL,
                                link_you_tube character varying(255),
                                nome character varying(255) NOT NULL,
                                peso double precision NOT NULL,
                                profundidade double precision NOT NULL,
                                qtde_clique integer,
                                tipo_unidade character varying(255) NOT NULL,
                                valor_venda numeric(38,2) NOT NULL
);


ALTER TABLE public.produto OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24600)
-- Name: seq_acesso; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_acesso
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_acesso OWNER TO postgres;

--
-- TOC entry 248 (class 1259 OID 24821)
-- Name: seq_avaliacao_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_avaliacao_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_avaliacao_produto OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24594)
-- Name: seq_categoria_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_categoria_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_categoria_produto OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 24681)
-- Name: seq_conta_pagar; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_pagar
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_conta_pagar OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 24666)
-- Name: seq_conta_receber; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_conta_receber
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_conta_receber OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 24687)
-- Name: seq_cup_desc; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_cup_desc
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_cup_desc OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 24626)
-- Name: seq_endereco; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_endereco
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_endereco OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 24672)
-- Name: seq_forma_pagamento; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_forma_pagamento
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_forma_pagamento OWNER TO postgres;

--
-- TOC entry 237 (class 1259 OID 24704)
-- Name: seq_imagem_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_imagem_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_imagem_produto OWNER TO postgres;

--
-- TOC entry 246 (class 1259 OID 24805)
-- Name: seq_item_venda_loja; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_item_venda_loja
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_item_venda_loja OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16403)
-- Name: seq_marca_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_marca_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_marca_produto OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 24717)
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_compra
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_nota_fiscal_compra OWNER TO postgres;

--
-- TOC entry 243 (class 1259 OID 24754)
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_fiscal_venda
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_nota_fiscal_venda OWNER TO postgres;

--
-- TOC entry 240 (class 1259 OID 24728)
-- Name: seq_nota_item_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_nota_item_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_nota_item_produto OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 24620)
-- Name: seq_pessoa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_pessoa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_pessoa OWNER TO postgres;

--
-- TOC entry 236 (class 1259 OID 24695)
-- Name: seq_produto; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_produto
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_produto OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 24746)
-- Name: seq_status_rastreio; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_status_rastreio
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_status_rastreio OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 24647)
-- Name: seq_usuario; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_usuario
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_usuario OWNER TO postgres;

--
-- TOC entry 244 (class 1259 OID 24760)
-- Name: seq_vd_cp_loja_virt; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.seq_vd_cp_loja_virt
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.seq_vd_cp_loja_virt OWNER TO postgres;

--
-- TOC entry 241 (class 1259 OID 24739)
-- Name: status_rastreio; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status_rastreio (
                                        id bigint NOT NULL,
                                        centro_distribuicao character varying(255),
                                        cidade character varying(255),
                                        estado character varying(255),
                                        status character varying(255),
                                        venda_compra_loja_virt_id bigint NOT NULL
);


ALTER TABLE public.status_rastreio OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 24635)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
                                id bigint NOT NULL,
                                pessoa_id bigint NOT NULL,
                                data_atual_senha date NOT NULL,
                                login character varying(255) NOT NULL,
                                senha character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 24642)
-- Name: usuarios_acesso; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuarios_acesso (
                                        usuario_id bigint NOT NULL,
                                        acesso_id bigint NOT NULL
);


ALTER TABLE public.usuarios_acesso OWNER TO postgres;

--
-- TOC entry 254 (class 1259 OID 24923)
-- Name: vd_cp_loja_virt; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vd_cp_loja_virt (
                                        id bigint NOT NULL,
                                        data_entrega date NOT NULL,
                                        data_venda date NOT NULL,
                                        dias_entrega integer NOT NULL,
                                        valor_desconto numeric(38,2),
                                        valor_frete numeric(38,2) NOT NULL,
                                        valor_total numeric(38,2) NOT NULL,
                                        cupom_desc_id bigint,
                                        endereco_cobranca_id bigint NOT NULL,
                                        endereco_entrega_id bigint NOT NULL,
                                        forma_pagamento_id bigint NOT NULL,
                                        nota_fiscal_venda_id bigint NOT NULL,
                                        pessoa_id bigint NOT NULL
);


ALTER TABLE public.vd_cp_loja_virt OWNER TO postgres;

--
-- TOC entry 5019 (class 0 OID 24630)
-- Dependencies: 224
-- Data for Name: acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5042 (class 0 OID 24816)
-- Dependencies: 247
-- Data for Name: avaliacao_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.avaliacao_produto (id, pessoa_id, produto_id, nota, descricao) VALUES (4, 1, 1, 10, 'teste avaliacao produto trigger');


--
-- TOC entry 5012 (class 0 OID 24589)
-- Dependencies: 217
-- Data for Name: categoria_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5027 (class 0 OID 24673)
-- Dependencies: 232
-- Data for Name: conta_pagar; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5023 (class 0 OID 24658)
-- Dependencies: 228
-- Data for Name: conta_receber; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5029 (class 0 OID 24682)
-- Dependencies: 234
-- Data for Name: cup_desc; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5044 (class 0 OID 24829)
-- Dependencies: 249
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5025 (class 0 OID 24667)
-- Dependencies: 230
-- Data for Name: forma_pagamento; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5045 (class 0 OID 24848)
-- Dependencies: 250
-- Data for Name: imagem_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5040 (class 0 OID 24800)
-- Dependencies: 245
-- Data for Name: item_venda_loja; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5010 (class 0 OID 16398)
-- Dependencies: 215
-- Data for Name: marca_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5046 (class 0 OID 24860)
-- Dependencies: 251
-- Data for Name: nota_fiscal_compra; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5047 (class 0 OID 24877)
-- Dependencies: 252
-- Data for Name: nota_fiscal_venda; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5034 (class 0 OID 24723)
-- Dependencies: 239
-- Data for Name: nota_item_produto; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5015 (class 0 OID 24606)
-- Dependencies: 220
-- Data for Name: pessoa_fisica; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.pessoa_fisica (id, cpf, data_nascimento, email, nome, telefone) VALUES (1, '10010010012', '1999-10-10', 'fjfj@gmail.com', 'rddd', '43999999999');


--
-- TOC entry 5016 (class 0 OID 24613)
-- Dependencies: 221
-- Data for Name: pessoa_juridica; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5048 (class 0 OID 24896)
-- Dependencies: 253
-- Data for Name: produto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.produto (id, qtda_alerta_estoque, qtda_estoque, alerta_qtda_estoque, altura, ativo, descricao, largura, link_you_tube, nome, peso, profundidade, qtde_clique, tipo_unidade, valor_venda) VALUES (1, 1, 1, true, 10, true, 'produto teste', 50.2, 'ddd', 'nome produto teste', 50.1, 80.2, 50, 'UN', 50.00);
INSERT INTO public.produto (id, qtda_alerta_estoque, qtda_estoque, alerta_qtda_estoque, altura, ativo, descricao, largura, link_you_tube, nome, peso, profundidade, qtde_clique, tipo_unidade, valor_venda) VALUES (2, 1, 1, true, 10, true, 'produto teste', 50.2, 'ddd', 'nome produto teste', 50.1, 80.2, 50, 'UN', 50.00);


--
-- TOC entry 5036 (class 0 OID 24739)
-- Dependencies: 241
-- Data for Name: status_rastreio; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5020 (class 0 OID 24635)
-- Dependencies: 225
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5021 (class 0 OID 24642)
-- Dependencies: 226
-- Data for Name: usuarios_acesso; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5049 (class 0 OID 24923)
-- Dependencies: 254
-- Data for Name: vd_cp_loja_virt; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 5056 (class 0 OID 0)
-- Dependencies: 219
-- Name: seq_acesso; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_acesso', 1, false);


--
-- TOC entry 5057 (class 0 OID 0)
-- Dependencies: 248
-- Name: seq_avaliacao_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_avaliacao_produto', 1, false);


--
-- TOC entry 5058 (class 0 OID 0)
-- Dependencies: 218
-- Name: seq_categoria_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_categoria_produto', 1, false);


--
-- TOC entry 5059 (class 0 OID 0)
-- Dependencies: 233
-- Name: seq_conta_pagar; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_pagar', 1, false);


--
-- TOC entry 5060 (class 0 OID 0)
-- Dependencies: 229
-- Name: seq_conta_receber; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_conta_receber', 1, false);


--
-- TOC entry 5061 (class 0 OID 0)
-- Dependencies: 235
-- Name: seq_cup_desc; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_cup_desc', 1, false);


--
-- TOC entry 5062 (class 0 OID 0)
-- Dependencies: 223
-- Name: seq_endereco; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_endereco', 1, false);


--
-- TOC entry 5063 (class 0 OID 0)
-- Dependencies: 231
-- Name: seq_forma_pagamento; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_forma_pagamento', 1, false);


--
-- TOC entry 5064 (class 0 OID 0)
-- Dependencies: 237
-- Name: seq_imagem_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_imagem_produto', 1, false);


--
-- TOC entry 5065 (class 0 OID 0)
-- Dependencies: 246
-- Name: seq_item_venda_loja; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_item_venda_loja', 1, false);


--
-- TOC entry 5066 (class 0 OID 0)
-- Dependencies: 216
-- Name: seq_marca_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_marca_produto', 1, false);


--
-- TOC entry 5067 (class 0 OID 0)
-- Dependencies: 238
-- Name: seq_nota_fiscal_compra; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_compra', 1, false);


--
-- TOC entry 5068 (class 0 OID 0)
-- Dependencies: 243
-- Name: seq_nota_fiscal_venda; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_fiscal_venda', 1, false);


--
-- TOC entry 5069 (class 0 OID 0)
-- Dependencies: 240
-- Name: seq_nota_item_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_nota_item_produto', 1, false);


--
-- TOC entry 5070 (class 0 OID 0)
-- Dependencies: 222
-- Name: seq_pessoa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_pessoa', 1, false);


--
-- TOC entry 5071 (class 0 OID 0)
-- Dependencies: 236
-- Name: seq_produto; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_produto', 1, false);


--
-- TOC entry 5072 (class 0 OID 0)
-- Dependencies: 242
-- Name: seq_status_rastreio; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_status_rastreio', 1, false);


--
-- TOC entry 5073 (class 0 OID 0)
-- Dependencies: 227
-- Name: seq_usuario; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_usuario', 1, false);


--
-- TOC entry 5074 (class 0 OID 0)
-- Dependencies: 244
-- Name: seq_vd_cp_loja_virt; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_vd_cp_loja_virt', 1, false);


--
-- TOC entry 4800 (class 2606 OID 24634)
-- Name: acesso acesso_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.acesso
    ADD CONSTRAINT acesso_pkey PRIMARY KEY (id);


--
-- TOC entry 4818 (class 2606 OID 24820)
-- Name: avaliacao_produto avaliacao_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT avaliacao_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4794 (class 2606 OID 24593)
-- Name: categoria_produto categoria_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_produto
    ADD CONSTRAINT categoria_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4808 (class 2606 OID 24680)
-- Name: conta_pagar conta_pagar_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_pagar
    ADD CONSTRAINT conta_pagar_pkey PRIMARY KEY (id);


--
-- TOC entry 4804 (class 2606 OID 24665)
-- Name: conta_receber conta_receber_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.conta_receber
    ADD CONSTRAINT conta_receber_pkey PRIMARY KEY (id);


--
-- TOC entry 4810 (class 2606 OID 24686)
-- Name: cup_desc cup_desc_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cup_desc
    ADD CONSTRAINT cup_desc_pkey PRIMARY KEY (id);


--
-- TOC entry 4820 (class 2606 OID 24836)
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- TOC entry 4806 (class 2606 OID 24671)
-- Name: forma_pagamento forma_pagamento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.forma_pagamento
    ADD CONSTRAINT forma_pagamento_pkey PRIMARY KEY (id);


--
-- TOC entry 4822 (class 2606 OID 24854)
-- Name: imagem_produto imagem_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT imagem_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4816 (class 2606 OID 24804)
-- Name: item_venda_loja item_venda_loja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT item_venda_loja_pkey PRIMARY KEY (id);


--
-- TOC entry 4792 (class 2606 OID 16402)
-- Name: marca_produto marca_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.marca_produto
    ADD CONSTRAINT marca_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4824 (class 2606 OID 24866)
-- Name: nota_fiscal_compra nota_fiscal_compra_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT nota_fiscal_compra_pkey PRIMARY KEY (id);


--
-- TOC entry 4826 (class 2606 OID 24883)
-- Name: nota_fiscal_venda nota_fiscal_venda_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT nota_fiscal_venda_pkey PRIMARY KEY (id);


--
-- TOC entry 4812 (class 2606 OID 24727)
-- Name: nota_item_produto nota_item_produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_item_produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4796 (class 2606 OID 24612)
-- Name: pessoa_fisica pessoa_fisica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_fisica
    ADD CONSTRAINT pessoa_fisica_pkey PRIMARY KEY (id);


--
-- TOC entry 4798 (class 2606 OID 24619)
-- Name: pessoa_juridica pessoa_juridica_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pessoa_juridica
    ADD CONSTRAINT pessoa_juridica_pkey PRIMARY KEY (id);


--
-- TOC entry 4830 (class 2606 OID 24902)
-- Name: produto produto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produto
    ADD CONSTRAINT produto_pkey PRIMARY KEY (id);


--
-- TOC entry 4814 (class 2606 OID 24745)
-- Name: status_rastreio status_rastreio_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT status_rastreio_pkey PRIMARY KEY (id);


--
-- TOC entry 4828 (class 2606 OID 24885)
-- Name: nota_fiscal_venda uk3sg7y5xs15vowbpi2mcql08kg; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT uk3sg7y5xs15vowbpi2mcql08kg UNIQUE (venda_compra_loja_virt_id);


--
-- TOC entry 4832 (class 2606 OID 24929)
-- Name: vd_cp_loja_virt ukhkxjejv08kldx994j4serhrbu; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT ukhkxjejv08kldx994j4serhrbu UNIQUE (nota_fiscal_venda_id);


--
-- TOC entry 4802 (class 2606 OID 24641)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- TOC entry 4834 (class 2606 OID 24927)
-- Name: vd_cp_loja_virt vd_cp_loja_virt_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT vd_cp_loja_virt_pkey PRIMARY KEY (id);


--
-- TOC entry 4853 (class 2620 OID 24988)
-- Name: conta_receber validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4861 (class 2620 OID 24990)
-- Name: endereco validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4863 (class 2620 OID 24992)
-- Name: nota_fiscal_compra validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.nota_fiscal_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4851 (class 2620 OID 24994)
-- Name: usuario validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4865 (class 2620 OID 24996)
-- Name: vd_cp_loja_virt validachavepessoa; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa BEFORE UPDATE ON public.vd_cp_loja_virt FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4854 (class 2620 OID 24989)
-- Name: conta_receber validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.conta_receber FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4862 (class 2620 OID 24991)
-- Name: endereco validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.endereco FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4864 (class 2620 OID 24993)
-- Name: nota_fiscal_compra validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.nota_fiscal_compra FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4852 (class 2620 OID 24995)
-- Name: usuario validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.usuario FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4866 (class 2620 OID 24997)
-- Name: vd_cp_loja_virt validachavepessoa2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoa2 BEFORE INSERT ON public.vd_cp_loja_virt FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4859 (class 2620 OID 24978)
-- Name: avaliacao_produto validachavepessoaavaliacaoproduto; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoaavaliacaoproduto BEFORE UPDATE ON public.avaliacao_produto FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4855 (class 2620 OID 24982)
-- Name: conta_pagar validachavepessoacontapagar; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar BEFORE UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4860 (class 2620 OID 24984)
-- Name: avaliacao_produto validachavepessoacontapagar2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar2 BEFORE INSERT ON public.avaliacao_produto FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4856 (class 2620 OID 24983)
-- Name: conta_pagar validachavepessoacontapagar2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagar2 BEFORE INSERT ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa();


--
-- TOC entry 4857 (class 2620 OID 24986)
-- Name: conta_pagar validachavepessoacontapagarpessoa_forn_id; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagarpessoa_forn_id BEFORE UPDATE ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa2();


--
-- TOC entry 4858 (class 2620 OID 24987)
-- Name: conta_pagar validachavepessoacontapagarpessoa_forn_id2; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER validachavepessoacontapagarpessoa_forn_id2 BEFORE INSERT ON public.conta_pagar FOR EACH ROW EXECUTE FUNCTION public.validachavepessoa2();


--
-- TOC entry 4835 (class 2606 OID 24648)
-- Name: usuarios_acesso acesso_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT acesso_fk FOREIGN KEY (acesso_id) REFERENCES public.acesso(id);


--
-- TOC entry 4844 (class 2606 OID 24867)
-- Name: nota_fiscal_compra conta_pagar_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_compra
    ADD CONSTRAINT conta_pagar_fk FOREIGN KEY (conta_pagar_id) REFERENCES public.conta_pagar(id);


--
-- TOC entry 4846 (class 2606 OID 24945)
-- Name: vd_cp_loja_virt cupom_desc_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT cupom_desc_fk FOREIGN KEY (cupom_desc_id) REFERENCES public.cup_desc(id);


--
-- TOC entry 4847 (class 2606 OID 24950)
-- Name: vd_cp_loja_virt endereco_cobranca_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT endereco_cobranca_fk FOREIGN KEY (endereco_cobranca_id) REFERENCES public.endereco(id);


--
-- TOC entry 4848 (class 2606 OID 24955)
-- Name: vd_cp_loja_virt endereco_entrega_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT endereco_entrega_fk FOREIGN KEY (endereco_entrega_id) REFERENCES public.endereco(id);


--
-- TOC entry 4849 (class 2606 OID 24960)
-- Name: vd_cp_loja_virt forma_pagamento_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT forma_pagamento_fk FOREIGN KEY (forma_pagamento_id) REFERENCES public.forma_pagamento(id);


--
-- TOC entry 4837 (class 2606 OID 24872)
-- Name: nota_item_produto nota_fiscal_compra_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT nota_fiscal_compra_fk FOREIGN KEY (nota_fiscal_compra_id) REFERENCES public.nota_fiscal_compra(id);


--
-- TOC entry 4850 (class 2606 OID 24965)
-- Name: vd_cp_loja_virt nota_fiscal_venda_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.vd_cp_loja_virt
    ADD CONSTRAINT nota_fiscal_venda_fk FOREIGN KEY (nota_fiscal_venda_id) REFERENCES public.nota_fiscal_venda(id);


--
-- TOC entry 4842 (class 2606 OID 24903)
-- Name: avaliacao_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.avaliacao_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 4843 (class 2606 OID 24908)
-- Name: imagem_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.imagem_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 4840 (class 2606 OID 24913)
-- Name: item_venda_loja produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 4838 (class 2606 OID 24918)
-- Name: nota_item_produto produto_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_item_produto
    ADD CONSTRAINT produto_fk FOREIGN KEY (produto_id) REFERENCES public.produto(id);


--
-- TOC entry 4836 (class 2606 OID 24653)
-- Name: usuarios_acesso usuario_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuarios_acesso
    ADD CONSTRAINT usuario_fk FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- TOC entry 4841 (class 2606 OID 24930)
-- Name: item_venda_loja venda_compra_loja_virt_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.item_venda_loja
    ADD CONSTRAINT venda_compra_loja_virt_fk FOREIGN KEY (venda_compra_loja_virt_id) REFERENCES public.vd_cp_loja_virt(id);


--
-- TOC entry 4845 (class 2606 OID 24935)
-- Name: nota_fiscal_venda venda_compra_loja_virt_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.nota_fiscal_venda
    ADD CONSTRAINT venda_compra_loja_virt_fk FOREIGN KEY (venda_compra_loja_virt_id) REFERENCES public.vd_cp_loja_virt(id);


--
-- TOC entry 4839 (class 2606 OID 24940)
-- Name: status_rastreio venda_compra_loja_virt_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status_rastreio
    ADD CONSTRAINT venda_compra_loja_virt_fk FOREIGN KEY (venda_compra_loja_virt_id) REFERENCES public.vd_cp_loja_virt(id);


-- Completed on 2024-07-21 22:58:51

--
-- PostgreSQL database dump complete
--


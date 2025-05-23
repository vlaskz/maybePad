--
-- PostgreSQL database dump
--

-- Dumped from database version 15.10 (Debian 15.10-1.pgdg120+1)
-- Dumped by pg_dump version 15.8

-- Started on 2025-05-23 02:00:07 UTC

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16564)
-- Name: note; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.note (
    id bigint NOT NULL,
    content character varying(255),
    title character varying(255),
    parent_id bigint
);


ALTER TABLE public.note OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16563)
-- Name: note_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.note_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.note_id_seq OWNER TO postgres;

--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 215
-- Name: note_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.note_id_seq OWNED BY public.note.id;


--
-- TOC entry 218 (class 1259 OID 16645)
-- Name: owner; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.owner (
    id bigint NOT NULL,
    creation_date_time timestamp(6) without time zone,
    email character varying(255) NOT NULL,
    last_update timestamp(6) without time zone,
    password character varying(255) NOT NULL
);


ALTER TABLE public.owner OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16644)
-- Name: owner_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.owner_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.owner_id_seq OWNER TO postgres;

--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 217
-- Name: owner_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.owner_id_seq OWNED BY public.owner.id;


--
-- TOC entry 214 (class 1259 OID 16514)
-- Name: page; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.page (
    path character varying(255) NOT NULL,
    content text,
    parent_id character varying(255),
    creation_date_time timestamp(6) without time zone,
    last_update timestamp(6) without time zone,
    is_locked boolean,
    owner_id bigint,
    password character varying(255)
);


ALTER TABLE public.page OWNER TO postgres;

--
-- TOC entry 3208 (class 2604 OID 16567)
-- Name: note id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.note ALTER COLUMN id SET DEFAULT nextval('public.note_id_seq'::regclass);


--
-- TOC entry 3209 (class 2604 OID 16648)
-- Name: owner id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.owner ALTER COLUMN id SET DEFAULT nextval('public.owner_id_seq'::regclass);


--
-- TOC entry 3213 (class 2606 OID 16571)
-- Name: note note_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT note_pkey PRIMARY KEY (id);


--
-- TOC entry 3215 (class 2606 OID 16652)
-- Name: owner owner_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.owner
    ADD CONSTRAINT owner_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 16520)
-- Name: page page_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.page
    ADD CONSTRAINT page_pkey PRIMARY KEY (path);


--
-- TOC entry 3217 (class 2606 OID 16654)
-- Name: owner uk_kcaoebbgb82ro5cw9nqhw19qb; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.owner
    ADD CONSTRAINT uk_kcaoebbgb82ro5cw9nqhw19qb UNIQUE (email);


--
-- TOC entry 3219 (class 2606 OID 16572)
-- Name: note fkdenwvx1lpx4tcd91ip240bgmx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT fkdenwvx1lpx4tcd91ip240bgmx FOREIGN KEY (parent_id) REFERENCES public.note(id);


--
-- TOC entry 3218 (class 2606 OID 16655)
-- Name: page fkro8pog40rmkw6axfjg7aiqx50; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.page
    ADD CONSTRAINT fkro8pog40rmkw6axfjg7aiqx50 FOREIGN KEY (owner_id) REFERENCES public.owner(id);


-- Completed on 2025-05-23 02:00:10 UTC

--
-- PostgreSQL database dump complete
--


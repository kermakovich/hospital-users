--liquibase formatted sql
--changeset ermakovich:1

create schema if not exists users;
set schema 'users';


CREATE TABLE IF NOT EXISTS user_info (
    id bigserial PRIMARY KEY,
    name varchar(35) NOT NULL,
    surname varchar(35) NOT NULL,
    fatherhood varchar(35),
    birthday date NOT NULL,
    email varchar(320) UNIQUE NOT NULL,
    role character varying(20) NOT NULL,
    password varchar(300) NOT NULL,
    external_id uuid
);


CREATE TABLE IF NOT EXISTS doctors (
    user_id bigint PRIMARY KEY,
    department varchar(50) NOT NULL,
    specialization varchar(50) NOT NULL,
    cabinet smallint,
    constraint fk_user_info foreign key(user_id) references user_info(id)
);
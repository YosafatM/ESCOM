drop database if exists OrbisESCOM;
create database OrbisESCOM;
use OrbisESCOM;

/*Experiencia*/
create table tipo_experiencia(
	idTipoExperiencia int(2) primary key,
    tipoExperiencia varchar(30)
);

create table estado(
	idEstado int(2) primary key,
    estado varchar(30)
);

create table experiencia(
	idExperiencia int(2) primary key,
    contenido varchar(300),
    nombreAlumno varchar(50),
    fechaEnvio timestamp default current_timestamp on update current_timestamp,
    tipoExperiencia int(2),
    estado int(2),
    foreign key (tipoExperiencia) references tipo_experiencia(idTipoExperiencia),
    foreign key (estado) references estado(idEstado)
);

/*Usuario*/
create table tipo_usuario(
	idTipoUsuario int(2) primary key,
    tipoUsuario varchar(20)
);

create table usuario(
	idUsuario int (2) primary key,
    nombreUsuario varchar(30),
    clave varchar(20),
    tipoUsuario int(2),
    foreign key (tipoUsuario) references tipo_usuario(idTipoUsuario)
);

/*materia*/
create table tipo_materia(
	idTipoMateria int(2) primary key,
    tipoMateria varchar(40)
);

create table materia(
	idMateria int(2) primary key,
    titulo varchar(30),
    contenido varchar(1000),
    tipoMateria int(2),
    foreign key (tipoMateria) references tipo_materia(idTipoMateria)
);

/*Reporte*/
create table reporte(
	idReporte int(2) primary key,
    submodulo varchar(30),
    explicacion varchar(300),
    metodo varchar(150),
    fechaEnvio timestamp default current_timestamp on update current_timestamp
);

/*Estadística*/
create table estadistica(
	idEstadistica int(2) primary key,
    pregunta1 varchar(5),
    pregunta2 varchar(5),
    pregunta3 varchar(5),
    pregunta4 varchar(5),
    pregunta5 varchar(5)
);

/*Registros*/
/*Catálogos*/
insert into tipo_usuario values(1, "Moderador");

insert into tipo_materia values(1, "Formación Institucional");
insert into tipo_materia values(2, "Formación Científica - Básica");
insert into tipo_materia values(3, "Formación Profesional");
insert into tipo_materia values(4, "Formación Terminal e Integración");

insert into estado values(1, "Aceptado");
insert into estado values(2, "Revisión");

insert into tipo_experiencia values(1, "Cafetería");
insert into tipo_experiencia values(2, "Clubes");
insert into tipo_experiencia values(3, "Cafetería");
insert into tipo_experiencia values(4, "Escolar");
insert into tipo_experiencia values(5, "Trámites");

/*Moderadores*/
insert into usuario values(1, "MedinaVilla", "MedinaVilla", 1);
insert into usuario values(2, "HernadezR", "HernadezR", 1);
insert into usuario values(3, "Sergio", "Sergio", 1);
insert into usuario values(4, "Yosafat", "Yosafat", 1);

/*Registros finales*/






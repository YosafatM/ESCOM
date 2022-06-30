-- Database: empleados

-- DROP DATABASE empleados;

/*CREATE DATABASE empleados
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Spain.1252'
    LC_CTYPE = 'Spanish_Spain.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;*/
	
create table Departamento(
	númDepto int not null,
	nombre char(20) not null,
	nssGerente int,
	comienzo date not null default current_date,
	constraint cveDepto
		primary key (númDepto)
);

insert into Departamento values (1, 'Recursos Humanos', null, default);
insert into Departamento values (2, 'Inovación', null, default);
insert into Departamento values (3, 'Atención a Clientes', null, default);
insert into Departamento values (4, 'Estadística', null, default);
insert into Departamento values (5, 'Tecnología', null, default);

create table Empleado(
	nss int not null,
	nombre char(20) not null,
	apPaterno char(12) not null,
	apMaterno char(12) not null,
	dirección char(30) not null,
	salario float,
	sexo char(1) not null,
	nssJefe int,
	nd int not null,
	constraint cveEmpl
		primary key (nss),
	constraint cveJefe
		foreign key (nss) references Empleado (nss)
		on delete set null,
	constraint cveEmplDepto
		foreign key (nd) references Departamento (númDepto)
		on update cascade
		on delete restrict
);

insert into Empleado values (123456, 'José', 'Martínez', 'Serrano', 'Calle 2, CDMX', 2250.50, 'H', null, 1);
insert into Empleado values (212345, 'María', 'Hernández', 'Hernández', 'Calle 5, CDMX', 2500.99, 'M', null, 2);
insert into Empleado values (312345, 'Ernesto', 'López', 'Osorio', 'Calle 10, CDMX', 2700.10, 'H', null, 3);
insert into Empleado values (412345, 'Salma', 'Plácido', 'Domínguez', 'Calle 20, CDMX', 2000.00, 'M', null, 4);
insert into Empleado values (512345, 'Paris', 'De la Cruz', 'Salgado', 'Calle 11, CDMX', 2560.50, 'H', null, 5);
insert into Empleado values (623456, 'Ignacio', 'Ramírez', 'Wah', 'Calle Faro, CDMX', 2250.50, 'H', 123456, 1);
insert into Empleado values (712345, 'Luis', 'Plata', 'Ortíz', 'Calle Río Azul, CDMX', 2300.99, 'H', 212345, 2);
insert into Empleado values (812345, 'Alexis', 'Corona', 'Serrano', 'Calle Lindavista, CDMX', 1900.10, 'H', 312345, 3);
insert into Empleado values (912345, 'Marco', 'Sánchez', 'Osorio', 'Calle Perejil, CDMX', 2300.00, 'H', 412345, 4);
insert into Empleado values (012345, 'Antonio', 'Morgado', 'López', 'Calle Bolivar, CDMX', 2160.50, 'H', 512345, 5);

select * from Departamento;



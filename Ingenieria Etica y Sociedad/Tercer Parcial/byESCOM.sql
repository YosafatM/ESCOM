drop database if exists byESCOM;
create database byESCOM;
use byESCOM;

create table tipo_experiencia(
	idTipoExperiencia int(2) primary key,
    tipoExperiencia varchar(30)
);

create table estado(
	idEstado int(2) primary key,
    estado varchar(30)
);

create table experiencia(
	idExperiencia int(2) primary key auto_increment,
    contenido varchar(1000),
    nombreAlumno varchar(50),
    fechaEnvio timestamp,
    tipoExperiencia int(2),
    estado int(2),
    foreign key (tipoExperiencia) references tipo_experiencia(idTipoExperiencia),
    foreign key (estado) references estado(idEstado)
);
create table tipoUsuario(
	idTipoUsuario int(1) primary key,
	tipoUsuario varchar(10)
);

create table usuario(
idUsuario int(100) primary key auto_increment,
nombreUsuario varchar(30),
contrasenia varchar(20),
idTipoUsuario int, 
foreign key (idTipoUsuario) 
REFERENCES tipoUsuario(idTipoUsuario));

create table tipoMateria(
	idTipoMateria int primary key, 
    tipoMateria varchar(40)
);

create table materia(idMateria int primary key auto_increment, 
	titulo varchar(50), 
	descripcion varchar(1300), 
	idTipoMateria int(3),
	foreign key (idTipoMateria) REFERENCES tipoMateria(idTipoMateria));

create table reporte(
	idReporte int(2) primary key,
    submodulo varchar(30),
    explicacion varchar(100),
    metodo varchar(50),
    fechaEnvio timestamp default current_timestamp on update current_timestamp
);

create table estadistica(
	idEstadistica int(2) primary key,
    pregunta1 varchar(5),
    pregunta2 varchar(5),
    pregunta3 varchar(5),
    pregunta4 varchar(5),
    pregunta5 varchar(5)
);

insert into tipoUsuario values(1, "Moderador");

insert into tipoMateria values(1, "Formación Institucional");
insert into tipoMateria values(2, "Formación Científica - Básica");
insert into tipoMateria values(3, "Formación Profesional");
insert into tipoMateria values(4, "Formación Terminal e Integración");

insert into estado values(1, "Aceptado");
insert into estado values(2, "Revisión");

insert into tipo_experiencia values(1, "Cafetería");
insert into tipo_experiencia values(2, "Clubes");
insert into tipo_experiencia values(3, "Cafetería");
insert into tipo_experiencia values(4, "Escolar");
insert into tipo_experiencia values(5, "Trámites");

insert into materia 
(titulo,descripcion,idTipoMateria)values('Algoritmia y Programación Estructurada',' Al cursar esta unidad de aprendizaje se apoya el dominio de las competencias tales como; resolución de problemas computacionales a través de algoritmos, interpretación de pseudo-código y habilidad para transcribir algoritmos a un lenguaje de programación,identificación los tipos de datos necesarios para representar información de un programa,resolver problemas mediante la estrategia "Divide y vencerás" y la utilización de las buenas practicas de programación en la implementación de algoritmos.
Durante esta unidad el alumno aprenderá a utilizar GitHub, la cual es una plataforma de desarrollo colaborativo de software para alojar proyectos utilizando el sistema de control de versiones, esta plataforma es muy utilizada en el mundo laboral en empresas de alto prestigio.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Matemáticas Discretas ','Esta unidad de aprendizaje es una base para las habilidades de abstracción que permitirán construir significativamente los conocimientos empleando la competencia de comunicación y de auto aprendizaje en forma responsable y colaborativa utilizando estrategias para la solución de problemas, investigación, elaboración de proyectos, entre otros.
Es importante que el egresado mejore sus estrategias de resolución de problemas en equipos de trabajo bajo presión. ', 2);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Ingeniería, Ética y Sociedad','Durante esta unidad se desarrollaran las capacidades para analizar e interpretar el papel de la ciencia y la tecnología, así como la función de la ingeniería en la sociedad, fomentando el crecimiento profesional y personal con un enfoque responsable para su entorno, a través del desarrollo de valores éticos y una actitud de respeto para el ejercicio de la profesión.
El alumno podrá mejorar las habilidades sociales, ademas de  la elaboración y presentación de un proyecto al publico.', 1);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Comunicación Oral y Escrita','La asignatura contribuye al perfil de egreso y a las unidades académicas de las áreas de formación, desarrollando habilidades de la comunicación oral y escrita, para su formación integral, que reafirma la noción del aprendizaje continuado y la necesidad de aprender a aprender, para proponer, analizar, gestionar y administrar equipos de trabajo inter y multidisciplinario en su trayectoria académica, personal y laboral.
El saber expresarse con la gente (clientes, reclutadores, accionistas, etc) es una habilidad que habré muchas puertas.', 1);
insert into materia 
(titulo,descripcion,idTipoMateria)values('Estructuras de Datos','Las estructuras de datos es una rama de las ciencias de la computación que estudia y aplica diferentes formas de organizar información dentro de una aplicación, para manipular, buscar e insertar estos datos de manera eficiente. 
Las competencias a desarrollar por medio de esta unidad de aprendizaje es el poder describir cosas tales como; aplicaciones comunes de las estructuras de datos lineal, el uso de la memoria dinámica y el concepto de recursión y ejemplificar su uso, también identificar el caso base y el caso general de un problema definido recursivamente, comparar soluciones recursivas para problemas elementales tales como factorial, programar funciones recursivas simples, determinar cuándo una solución recursiva es apropiada para un problema, describir estrategias de implementación para pilas, colas, tablas hash, las aplicaciones comunes de las estructuras de datos no lineales,la implementación para árboles, grafos y las estrategias para elegir la correcta estructura de datos.', 3);




insert into materia 
(titulo,descripcion,idTipoMateria)values('Sistemas Operativos','Esta unidad de aprendizaje proporciona los conocimientos sobre sistemas operativos y desarrolla habilidades para utilizar diferentes metodologías en el análisis, diseño, desarrollo e implementación de sistemas computacionales diseñando algoritmos eficientes para la solución de problemas computacionales a si como para plantear, negociar, analizar, diseñar y coordinar estratégicamente proyectos en el ámbito de los sistemas comunicacionales.',3);




insert into materia 
(titulo,descripcion,idTipoMateria)values('Bases de Datos','La base de datos es un sistema formado por un conjunto de datos almacenados en discos que permiten el acceso directo a ellos y un conjunto de programas que manipulen ese conjunto de datos. 
Tienen una gran relevancia a nivel empresarial, y se consideran una de las mayores aportaciones que ha dado la informática a las empresas. En la actualidad, cualquier organización, por pequeña que sea, debe contar con una base de datos, pero para que sea todo lo efectiva que debe, no basta con tenerla: hay que saber cómo gestionarlas. ', 3);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Análisis y Diseño Orientado a Objetos','El propósito de esta unidad es el de desarrollar la capacidad para analizar problemas y describir sistemas de información que resuelvan dichos problemas aplicando las técnicas, herramientas y procedimientos principales del paradigma Orientado a Objetos.
El estudiante enfrentara los principales problemas y retos que involucra el desarrollo de sistemas de información a fin de fomentar una actitud profesional y responsable en la participación de un proyecto e identificara los riesgos más comunes durante la elaboración de un proyecto grande de software.', 3);

insert into materia 
(titulo,descripcion,idTipoMateria)values('Programación Orientada a Objetos','La POO es un paradigma de programación que usa objetos y sus interacciones, para diseñar aplicaciones y programas informáticos. Está basado en varias técnicas, incluyendo herencia, abstracción, polimorfismo y encapsulamiento. 
Las competencias para esta unidad de aprendizaje son; el conocer la filosofía del paradigma orientado a objetos, para comprender su importancia en el desarrollo de sistemas computacionales, dominar los conceptos de abstracción, encapsulamiento, herencia y polimorfismo, para proponer soluciones a problemas computacionales, a través de la creación de programas simples en un lenguaje de programación, saber diferenciar entre los conceptos de sobrecarga y sobreescritura, para utilizarlos adecuadamente, en el diseño de clases, también propiciar el aprendizaje autónomo, para adquirir nuevos conocimientos, a través de la indagación de conceptos del paradigma orientado a objetos y desarrollar la habilidad para trabajar en equipo, al construir aplicaciones de software.', 3);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Tecnologías para la Web','El propósito general de esta unidad de aprendizaje es conocer y aplicar las tecnologías de vanguardia para la Web, así como diferenciar los tipos de documentos y la manera de integrar dichas tecnologías en una pagina o en una aplicación Web.
Sus competencias especificas son; el conocer y aplicar las nuevas tecnologías, paradigmas y estándares para el desarrollo de un sistema Web, identificar los diferentes tipos de documentos Web y los elementos de contenido y de presentación que lo conforman, además de conocer el modelo de objetos de los documentos XML así como las nuevas bibliotecas de funciones que permiten diseñar la presentación de una pagina Web, también está el desarrollar guiones que añadan interactividad en las paginas Web, crear páginas Web usables, accesibles y adaptables y el saber aplicar las bibliotecas de Javascript para agregar interactividad a las páginas.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Ingeniería en Software','Esta unidad de aprendizaje contribuye al perfil del egresado de Ingeniería en Sistemas Computacionales a desarrollar las habilidades de análisis y diseño de proyectos, haciendo uso de Software de gestión de proyectos así como herramientas CASE, además de integrar los principios de gestión de la calidad, regidos por los estándares establecidos para asegurar, gestionar auditar y certificar la calidad de procesos y productos informáticos así como también planificar y proyectar estratégicamente (Recursos de Hardware y Software, Recursos Humanos, componentes reutilizables) el desarrollo de proyectos de software. Así mismo, se dinamizan las competencias de pensamiento creativo, comunicación asertiva, trabajo colaborativo y participativo.', 3);

insert into materia 
(titulo,descripcion,idTipoMateria)values('Fundamentos Económicos','El alumno en esta unidad de aprendizaje lograra desarrollar capacidades para evaluar, apreciar y proyectar el comportamiento del entorno económico para la toma de decisiones, además fomentara la iniciativa y el respeto en las opiniones de las personas para promover el trabajo colaborativo.', 3);

insert into materia 
(titulo,descripcion,idTipoMateria)values('Redes de Computadoras','Esta unidad de aprendizaje proporciona los conocimientos necesarios sobre el funcionamiento y diseño de redes LAN que brinden interconectividad en redes TCP/IP.
Las competencias especificas que desarrolla son; el diseñar redes de computadora que cubran las necesidades del cliente y se ajusten a las restricciones físicas del lugar, integrar y participar en forma efectivas con grupos inter y multidisciplinarios en el proceso de diseño de una red LAN además de conocer las organizaciones y estándares responsables del diseño de estas redes, también conocer los diferentes protocolos involucrados en la transmisión de datos en red y la capa en la que éstos operan, diferenciar entre los distintos medios de transmisión de datos, saber conocer los modelos de referencia para interconectar sistemas abiertos y reconocer las funciones que deben implementar todas las redes de computadoras.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Análisis Fundamental de Circuitos','El propósito general es brindar conceptos de resolución de circuitos eléctricos para el desarrollo de sistemas computacionales. Los conocimientos adquiridos son necesarios para el análisis, y diseño de sistemas computacionales usando tecnologías de vanguardia y aplicando metodologías, normas y estándares nacionales e internacionales de calidad para crear circuitos eléctricos. Por lo que serán capaces de desempeñarse en el sector privado, público y de investigación.
En esta unidad de aprendizaje se realizan prácticas en equipo, en el laboratorio, también se realizan exposiciones y trabajos de investigación dirigidos a el área de formación profesional.', 3);

insert into materia 
(titulo,descripcion,idTipoMateria)values('Electrónica Analógica','El propósito general de esta materia es brindar conceptos de electrónica básicos para el desarrollo de sistemas computacionales. Los conocimientos adquiridos son necesarios para el análisis, y diseño de sistemas computacionales usando tecnologías de vanguardia y aplicando metodologías, normas y estándares nacionales e internacionales de calidad para crear circuitos eléctricos. Por lo que serán capaces de desempeñarse en el sector privado, público y de investigación.
En esta unidad de aprendizaje se realizan prácticas en equipo, en el laboratorio, también se realizan exposiciones y trabajos de investigación dirigidos a el área de formación profesional.', 3);




insert into materia 
(titulo,descripcion,idTipoMateria)values('Administración Financiera','La Administración Financiera es una recopilación de actividades orientadas al desarrollo de los objetivos propios de una empresa basada en la minimización de los beneficios y la minimización del riesgo, aprovechando los recursos que posee la entidad organizándolos y creando planes de trabajo específicos que permitan de forma eficiente llegar al éxito . 
Las competencias especificas que desarrolla son; el desarrollar capacidades para obtener, analizar y plantear información financiera, hacer la evaluación de proyectos de inversión para la toma de decisiones, comprender y aplicar la normatividad para la integración de información financiera, promover el orden, la limpieza y la responsabilidad para la generación de información y fomentar la iniciativa y el respeto en las opiniones de las personas para promover el trabajo colaborativo.', 3);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Teoría Computacional ','El propósito general es proporcionar la teoría computacional fundamental incluyendo los lenguajes formales y las maquinas que los reconocen (autómatas). Estos tópicos conforman en gran parte el área conocida como Teoría de la Computación.
El estudio de la Teoría Computacional tiene varios propósitos: Familiarizar a los estudiantes con los fundamentos y principios de la computación. Utilizar técnicas que serán útiles en el reconocimiento de diferentes tipos de lenguajes que se presentan en diversas situaciones en los sistemas computacionales.', 2);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Fundamentos de Diseño Digital','El propósito general de esta unidad de aprendizaje es el brindar los parámetros necesarios para el análisis de sistemas computacionales. Las funciones requeridas se refieren a una amplia gama de actividades variadas, las cuales son complejas y no rutinarias. Por otro lado, dichas actividades requieren de un alto grado de responsabilidad y autonomía.
Las competencias que la conforman son; el conocer los elementos básicos de los sistemas digitales, diseño de sistemas lógicos combinacionales, la implementación de sistemas digitales en dispositivos lógicos programables, ademas de desarrollar la capacidad de trabajo en equipo, la síntesis y resolución de problemas.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Diseño de Sistemas Digitales','Esta unidad de aprendizaje introduce al alumno al conocimiento sobre los sistemas digitales secuenciales los cuales son empleados en las distintas arquitecturas de computadoras mismo que es parte integral de la formación del Ingeniero en Sistemas Computacionales adquiriendo las siguientes competencias; como, el conocer los elementos de los sistemas digitales secuenciales, el desarrollo de la habilidad de diseñar sistemas lógicos secuenciales, ademas de desarrollar la capacidad de trabajo en equipo, la síntesis y resolución de problemas.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Análisis de Algoritmos','Esta unidad de aprendizaje contribuye al perfil del egresado de Ingeniería en Sistemas Computacionales, al desarrollar las habilidades de diseño de algoritmos eficiente para la solución de problemas computacionales, así como su evaluación. Así mismo, se desarrolla el pensamiento estratégico, el pensamiento creativo, la comunicación asertiva, el trabajo colaborativo y participativo.', 2);




insert into materia 
(titulo,descripcion,idTipoMateria)values('Instrumentación','La unidad de aprendizaje desarrolla en el egresado de Ingeniería en Sistemas Computacionales habilidades de caracterización de los distintos tipos de sensores y transductores, circuitos acondicionadores de señal, digitalización de señales analógicas y protocolos de comunicación, aplicación de metodologías, normas y estándares nacionales e internacionales de calidad para diseñar sistemas de medición digital.
Esta unidad fomenta las habilidades de integración y colaboración en equipos de trabajo, con actitud de liderazgo para la gestión de proyectos computacionales, el pensamiento estratégico y creativo, ademas del aprendizaje autónomo.', 3);
insert into materia 
(titulo,descripcion,idTipoMateria)values('Administración de Proyectos','Esta unidad de aprendizaje contribuye al perfil del egresado en Ingeniería en Sistemas Computacionales, al desarrollar las capacidades para analizar planes de negocios, generar proyectos de inversión para la toma de decisiones, comprender y aplicar la normatividad para la integración de los mismos; promover el orden, la limpieza y responsabilidad para la generación de información aplicada a los sistemas computacionales, fomentar la iniciativa y el respeto en las opiniones de las personas para promover el trabajo colaborativo.', 3);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Arquitectura de Computadoras','Esta unidad de aprendizaje contribuye al perfil del egresado de Ingeniería en Sistemas Computacionales, al desarrollar las habilidades de diseño de sistemas digitales, empleando un procesador tipo soft-core, para la solución de problemas computacionales [28]. Así mismo, se desarrolla el pensamiento estratégico, el pensamiento creativo, la comunicación asertiva, el trabajo colaborativo y participativo.', 3);




insert into materia 
(titulo,descripcion,idTipoMateria)values('Introducción a los microcontroladores','Esta unidad de aprendizaje contribuye al perfil del egresado de Ingeniería en Sistemas Computacionales, al desarrollar las habilidades de abstracción, análisis, diseño e implementación de algoritmos eficientes usando un microocontrolador para el desarrollo de algoritmos de procesamiento digital de señales y de sistemas embebidos los cuales se encuentran disponibles en cualquier aspecto de nuestra vida y están diseñados para realizar pocas funciones en tiempo real, según sea el caso. Ademas el alumno aprenderá a programar en el lenguaje ensamblador.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Aplicaciones para Comunicaciones en Red',' Esta unidad de aprendizaje contribuye a desarrollar las habilidades de diseño e implementación de aplicaciones utilizando las arquitecturas de comunicaciones Cliente/Servidor y P2P . Así mismo, se desarrolla el pensamiento estratégico, el pensamiento creativo, la comunicación asertiva, el trabajo colaborativo y participativo.', 3);


insert into materia 
(titulo,descripcion,idTipoMateria)values('Compiladores','La intención educativa de esta unidad de aprendizaje es el aplicar los conocimientos de algoritmia, programación y teoría computacional en el diseño y desarrollo de algoritmos de análisis léxico y sintáctico; así como integrar estos conocimientos en la construcción de un interprete para un lenguaje de programación especifico.', 3);



insert into materia 
(titulo,descripcion,idTipoMateria)values('Desarrollo de Sistemas Distribuidos ',' Esta unidad de aprendizaje contribuye al perfil del egresado de Ingeniería en Sistemas Computacionales, al desarrollar las habilidades de pensamiento creativo y lógico para el diseño de sistemas computacionales distribuidos que hagan uso óptimo de los recursos. Así mismo, habilita a los alumnos para el desarrollo de aplicaciones distribuidas tolerantes a fallos, que requieran el uso de protocolos para comunicaciones seguras.
Un sistema distribuido es un sistema de software cuyos componentes están separados físicamente y conectados entre sí por una red de computadoras, se comunican y coordinan entre ellos pasando mensajes. Dichos componentes interactúan entre ellos para lograr una meta común.
Las tres características principales de un sistema distribuido son; la concurrencia de componentes: los componentes pueden ejecutar sus acciones de manera concurrente e independiente. No hay un reloj global: Los componentes (nodos) de un sistema distribuido no dependen de un reloj que sincronice o indique las acciones de los distintos nodos. Falla independiente de componentes: La falla de un componente no afecta al resto de los componentes.', 4);


insert into materia (titulo,descripcion,idTipoMateria)values('Administración de Servicios de Red ','El propósito de esta unidad de aprendizaje es desarrollar sistemas de administración y monitorización de servicios de red, con base en metodologías, estándares y protocolos aplicables.
Los servicios administrados de red son aplicaciones de redes, funciones y servicios que las empresas subcontratan para que un proveedor de servicios gestionados pueda operar, supervisar y mantener de forma remota. Los servicios administrados de red abarcan desde el acceso básico a la red y los servicios de transporte, como las líneas WAN y LAN arrendadas tradicionales, hasta las más recientes conexiones WAN (SD-WAN) definidas por software y los servicios de red virtual.', 4);
insert into materia (titulo,descripcion,idTipoMateria)values('Gestión Empresarial ','Esta unidad de aprendizaje contribuye al perfil del egresado de Ingeniería en Sistemas Computacionales mediante el desarrollo de las competencias directivas de liderazgo, toma de decisiones bajo condiciones de riesgo e incertidumbre, manejo de grupos de trabajo, capacidad de negociación, manejo de conflictos, orientación al cambio y a la innovación permanente, para gestionar organizaciones con una actitud emprendedora, ética y de responsabilidad social.', 4);
insert into materia (titulo,descripcion,idTipoMateria)values('Liderazgo y Desarrollo Profesional ','Esta unidad de aprendizaje contribuye al perfil del egresado en Ingeniería en Sistemas Computacionales, al desarrollar las habilidades como la toma de decisiones para la solución de problemas, la conducción de grupos efectivos inter y multidisciplinarios, de manera colaborativa, mediante la comunicación asertiva y la evaluación de riesgos, que lo lleven al cumplimiento de metas establecidas personales y profesionales con ética, responsabilidad social y cuidado del medio ambiente .',1);

insert into experiencia(contenido,nombreAlumno,fechaEnvio,tipoExperiencia,estado) 
values('En la cafeteria, no me gusta que la gente se forme de manera fila india ya que cuando esta lloviendo todo se jode',
'PacoVilla','2008-05-31 20:00:00', 1,2) ; 
insert into experiencia(contenido,nombreAlumno,fechaEnvio,tipoExperiencia,estado) 
values('En el club de Ajderez, no me gusta que la gente se forme de manera fila india ya que cuando esta lloviendo todo se jode',
'MariaPineda','2009-05-31 20:00:00', 2,2) ; 

insert into usuario values(1, "MedinaVilla", "MedinaVilla", 1);
insert into usuario values(2, "HernadezR", "HernadezR", 1);
insert into usuario values(3, "Sergio", "Sergio", 1);
insert into usuario values(4, "Yosafat", "Yosafat", 1);

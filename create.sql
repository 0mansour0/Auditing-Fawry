Create database auditing;
use auditing;

create table USERS(
ID SERIAL ,
NAME varchar(255) ,
EMAIL varchar(255) ,
TITLE varchar(255) ,
PHOTO varchar(255) ,
constraint USER_ID_PK primary key (ID),
constraint USER_EMAIL_UQ UNIQUE (EMAIL)
);

create table BUSINESS_ENTITY(
ID SERIAL,
NAME varchar(255),
constraint BUSINESS_ENTITY_ID_PK primary key (ID),
constraint BUSINESS_ENTITY_NAME_UQ UNIQUE (NAME)
);

create table APPLICATION(
ID SERIAL,
NAME varchar(255),
constraint APPLICATION_ID_PK primary key (ID),
constraint APPLICATION_NAME_UQ UNIQUE (NAME)
);

create table ACTION_TYPE(
ID SERIAL ,
CODE varchar(255) ,
NAME_AR varchar(255),
NAME_EN varchar(255),
MESSAGE_TEMPLATE_AR varchar(255),
MESSAGE_TEMPLATE_EN varchar(255),
constraint ACTION_TYPE_ID_PK primary key (ID),
constraint ACTION_TYPE_CODE_UQ UNIQUE (CODE)
);

create table PARAM_TYPE(
ID SERIAL ,
CODE varchar(255) ,
NAME_AR varchar(255),
NAME_EN varchar(255),
constraint PARAM_TYPE_ID_PK primary key (ID),
constraint PARAM_TYPE_CODE_UQ UNIQUE (CODE)
);

create table ACTION(
ID SERIAL,
DESCRIPTION_AR varchar(255),
DESCRIPTION_EN varchar(255),
TIME datetime,
ACTION_TYPE varchar(255),
USER_EMAIL varchar(255),
BE_NAME varchar(255),
APPLICATION_NAME varchar(255),
constraint ACTION_ID_PK primary key (ID),
constraint ACTION_ACTION_TYPE_FK foreign key (ACTION_TYPE) REFERENCES ACTION_TYPE (CODE),
constraint ACTION_USER_EMAIL_FK foreign key (USER_EMAIL) REFERENCES USERS (EMAIL),
constraint ACTION_BE_NAME_FK foreign key (BE_NAME) REFERENCES BUSINESS_ENTITY (NAME),
constraint ACTION_APPLICATION_NAME_FK foreign key (APPLICATION_NAME) REFERENCES APPLICATION (NAME)
);


create table PARAM(
ID SERIAL,
PARAM_VALUE varchar(255),
ACTION_ID bigint(20) unsigned,
PARAM_TYPE varchar(255),
constraint PARAM_ID_PK primary key (ID),
constraint PARAM_ACTION_ID_FK foreign key (ACTION_ID) REFERENCES ACTION (ID) ON DELETE CASCADE ON UPDATE CASCADE,
constraint PARAM_PARAM_TYPE_FK foreign key (PARAM_TYPE) REFERENCES PARAM_TYPE (CODE)
);
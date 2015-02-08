drop table if exists user;

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment,
   username             varchar(32),
   password             varchar(32),
   honey_name           varchar(32),
   status               int default 1,
   is_delete            int default 0,
   create_time          datetime,
   latest_update_time   datetime,
   primary key (id)
);


drop table if exists role;

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null auto_increment,
   name                 varchar(32),
   description          varchar(128),
   is_delete            int default 0,
   create_time          datetime,
   latest_update_time   datetime,
   primary key (id)
);

drop table if exists user_role;

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id                   int not null auto_increment,
   user_id              int,
   role_id              int,
   primary key (id)
);

drop table if exists module;

/*==============================================================*/
/* Table: module                                                */
/*==============================================================*/
create table module
(
   id                   int not null auto_increment,
   name                 varchar(64),
   description          varchar(256),
   pid                  int,
   is_leaf              int,
   is_delete            int default 0,
   create_time          datetime,
   latest_update_time   datetime,
   url_path             varchar(256),
   primary key (id)
);

drop table if exists ACL;

/*==============================================================*/
/* Table: ACL                                                   */
/*==============================================================*/
create table ACL
(
   id                   int not null auto_increment,
   module_id            int,
   role_id              int,
   permission           int,
   primary key (id)
);

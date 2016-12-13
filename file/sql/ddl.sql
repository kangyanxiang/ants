drop table if exists persistent_logins;

drop table if exists toe_blackwhitemac;

drop table if exists toe_blackwhiteuser;

drop table if exists toe_c3p0_test_table;

drop table if exists toe_exception_log;

drop table if exists toe_permission;

drop table if exists toe_platform;

drop table if exists toe_project;

drop table if exists toe_role;

drop table if exists toe_skin;

drop table if exists toe_static_user;

drop table if exists toe_strategy;

drop table if exists toe_strategy_item;

drop table if exists toe_system_config;

drop table if exists toe_user;

drop table if exists toe_user_customer;

drop table if exists toe_user_permission;

drop table if exists toe_user_role;

/*==============================================================*/
/* Table: persistent_logins                                     */
/*==============================================================*/
create table persistent_logins
(
   username             varchar(64) not null,
   series               varchar(64) not null,
   token                varchar(64) not null,
   last_used            timestamp not null,
   primary key (series)
)
ENGINE = InnoDB
charset = UTF8;

alter table persistent_logins comment 'spring security  Remember-Me';

/*==============================================================*/
/* Table: toe_c3p0_test_table                                   */
/*==============================================================*/
create table toe_c3p0_test_table
(
   a                    char(1)
)
ENGINE = InnoDB
charset = UTF8;

/*==============================================================*/
/* Table: toe_exception_log                                     */
/*==============================================================*/
create table toe_exception_log
(
   pk_id                bigint(20) not null auto_increment,
   error_code           VARCHAR(20) not null,
   module_name          VARCHAR(100) not null,
   service_name         VARCHAR(100),
   parameter            TEXT,
   error_message        TEXT,
   create_date          int not null,
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

/*==============================================================*/
/* Table: toe_permission                                        */
/*==============================================================*/
create table toe_permission
(
   pk_id                bigint(20) not null auto_increment,
   code                 varchar(50) not null,
   alias_name           varchar(50) not null,
   parent_id            bigint(20) not null default -1,
   order_no             int not null default 0,
   create_date          int not null,
   update_date          int not null,
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_permission comment '权限表';

/*==============================================================*/
/* Table: toe_project                                           */
/*==============================================================*/
create table toe_project
(
   pk_id                bigint(20) not null,
   project_name         varchar(20) not null,
   province_id          bigint(20),
   city_id              bigint(20),
   area_id              bigint(20),
   contact_person       varchar(50),
   contact_way          varchar(11),
   remark               varchar(100),
   delete_flag          int(1) not null default 1 comment '1 正常、-1 已删除',
   create_date          int not null,
   update_date          int not null,
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_project comment '项目表';

/*==============================================================*/
/* Table: toe_role                                              */
/*==============================================================*/
create table toe_role
(
   pk_id                bigint(20) not null auto_increment,
   alias_name           varchar(50) not null,
   role_name            varchar(50) not null comment 'ROLE_SUPER_ADMIN  代表 超级管理员、
            
            ROLE_D_PROVINCE 代表 省级管理员(电信侧)、
            ROLE_D_CITY 代表 市级管理员(电信侧)、
            ROLE_D_AREA 代表 区县管理员(电信侧)、
            
            ROLE_CUSTOMER_ADMIN 代表 客户管理员(客户侧)、',
   create_date          int not null,
   update_date          int not null,
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_role comment '角色表';

/*==============================================================*/
/* Table: toe_static_user                                       */
/*==============================================================*/
create table toe_static_user
(
   pk_id                bigint(20) not null auto_increment,
   user_name            varchar(20) not null,
   password             char(32) not null,
   user_type            tinyint(2) not null comment '1 代表 普通员工、2 代表 VIP客户、3 代表 终端体验区',
   real_name            varchar(50),
   cellphone            varchar(11),
   dept_name            varchar(100),
   remark               varchar(100),
   delete_flag          int(1) not null default 1 comment '1 正常、-1 已删除',
   create_date          int not null,
   update_date          int not null,
   fk_customer_id       bigint(20) not null,
   cascade_label        varchar(200) not null,
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_static_user comment '静态用户表';

/*==============================================================*/
/* Table: toe_system_config                                     */
/*==============================================================*/
create table toe_system_config
(
   pk_id                bigint(20) not null auto_increment,
   alias_name           varchar(50) not null,
   param_key            varchar(30) not null,
   param_value          varchar(150) not null,
   remark               varchar(100),
   order_no             int not null default 0,
   create_date          int not null,
   update_date          int not null,
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_system_config comment '系统配置表';

/*==============================================================*/
/* Table: toe_user                                              */
/*==============================================================*/
create table toe_user
(
   pk_id                bigint(20) not null,
   user_name            varchar(20) not null,
   password             char(32) not null,
   user_type            tinyint(2) not null comment '1  代表 超级管理员
            
            2 代表 省级管理员(电信侧)
            3 代表 市级管理员(电信侧)
            4 代表 区县管理员(电信侧)
            
            5 代表 客户管理员(客户型)',
   parent_id            bigint(20) not null,
   province_id          bigint(20),
   city_id              bigint(20),
   area_id              bigint(20),
   contact_person       varchar(50),
   contact_way          varchar(11),
   remark               varchar(100),
   delete_flag          int(1) not null default 1 comment '1 正常、-1 已删除',
   create_date          int not null,
   update_date          int not null,
   fk_project_id        bigint(20),
   primary key (pk_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_user comment '用户表';

/*==============================================================*/
/* Table: toe_user_permission                                   */
/*==============================================================*/
create table toe_user_permission
(
   permission_id        bigint(20) not null,
   user_id              bigint(20) not null,
   primary key (user_id, permission_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_user_permission comment '用户-权限表';

/*==============================================================*/
/* Table: toe_user_role                                         */
/*==============================================================*/
create table toe_user_role
(
   user_id              bigint(20) not null,
   role_id              bigint(20) not null,
   primary key (user_id, role_id)
)
ENGINE = InnoDB
charset = UTF8;

alter table toe_user_role comment '用户-角色表';

alter table toe_user add constraint FK_u_ref_project foreign key (fk_project_id)
      references toe_project (pk_id) on delete restrict on update restrict;

alter table toe_user_permission add constraint FK_up_ref_p foreign key (permission_id)
      references toe_permission (pk_id) on delete restrict on update restrict;

alter table toe_user_permission add constraint FK_up_ref_u foreign key (user_id)
      references toe_user (pk_id) on delete restrict on update restrict;

alter table toe_user_role add constraint FK_ur_ref_role foreign key (role_id)
      references toe_role (pk_id) on delete restrict on update restrict;

alter table toe_user_role add constraint FK_ur_ref_user foreign key (user_id)
      references toe_user (pk_id) on delete restrict on update restrict;
